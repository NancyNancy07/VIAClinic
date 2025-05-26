package server.loginNetwork;

import com.google.gson.Gson;
import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;
import server.model.loginSystem.authentication.AuthenticationService;
import server.model.loginSystem.authentication.AuthenticationServiceImp;
import server.model.loginSystem.authentication.LoginRequest;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.LabResult;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.Referral;
import shared.*;
import server.model.patientJournal.Vaccination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable
{
  private final Socket socket;
  private BufferedReader input;
  private PrintWriter output;
  private final Gson gson;
  private final AuthenticationService authService;

  public ClientHandler(Socket socket)
  {
    this.socket = socket;

    this.gson = new Gson();
    authService = AuthenticationServiceImp.getInstance();
  }

  @Override public void run()
  {
    try
    {
      input = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
      output = new PrintWriter(socket.getOutputStream(), true);

      String request;
      while ((request = input.readLine()) != null)
      {
        System.out.println("Received: " + request);

        // Parse the request JSON
        RequestObject req = gson.fromJson(request, RequestObject.class);
        switch (req.getType())
        {
          case "login" ->
          {
            LoginRequest loginRequest = new LoginRequest(req.getUsername(),
                req.getPassword(), req.getUserType());

            ResponseObject loginResponse = authService.login(loginRequest);
            output.println(gson.toJson(loginResponse));
          }

          case "patientAppointments" ->
          {
            int patientId = req.getId();

            List<Appointment> patientAppointments = authService.getAppointmentsForPatient(
                patientId);

            ResponseObject patientResponse;
            if (patientAppointments != null && !patientAppointments.isEmpty())
            {
              patientResponse = new ResponseObject(true, "Appointments found",
                  patientId);

              List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
              for (Appointment app : patientAppointments)
              {
                Doctor doctor = app.getDoctor();
                DoctorDTO doctorDTO = new DoctorDTO(doctor.getDoctorID(),
                    doctor.getFirstName(), doctor.getLastName(),
                    doctor.getEmail(), doctor.getPhoneNumber(),
                    doctor.getUsername(), doctor.getPassword());

                AppointmentDTO dto = new AppointmentDTO(app.getAppointmentID(),
                    app.getDate(), app.getTime(), doctorDTO, app.getPatientID(),
                    app.getMode());
                appointmentDTOs.add(dto);
              }

              patientResponse.setAppointments(appointmentDTOs);
            }
            else
            {
              patientResponse = new ResponseObject(false,
                  "No appointments found", patientId);
            }

            output.println(gson.toJson(patientResponse));
          }

          case "doctorAppointments" ->
          {
            int doctorId = req.getId();

            List<Appointment> doctorAppointments = authService.getAppointmentsForDoctor(
                doctorId);

            ResponseObject doctorResponse;
            if (doctorAppointments != null && !doctorAppointments.isEmpty())
            {
              doctorResponse = new ResponseObject(true, "Appointments found",
                  doctorId);

              List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
              for (Appointment app : doctorAppointments)
              {
                Doctor doctor = app.getDoctor();
                Patient patient = app.getPatient();
                PatientDTO patientDTO = new PatientDTO(patient.getPatientID(),
                    patient.getFirstName(), patient.getLastName(),
                    patient.getEmail(), patient.getPhoneNumber(),
                    patient.getUsername(), patient.getPassword(),
                    patient.getCPR(), patient.getAddress());

                DoctorDTO doctorDTO = new DoctorDTO(doctor.getDoctorID(),
                    doctor.getFirstName(), doctor.getLastName(),
                    doctor.getEmail(), doctor.getPhoneNumber(),
                    doctor.getUsername(), doctor.getPassword());

                AppointmentDTO dto = new AppointmentDTO(app.getAppointmentID(),
                    app.getDate(), app.getTime(), doctorDTO, patientDTO,
                    app.getMode());

                appointmentDTOs.add(dto);
              }

              doctorResponse.setAppointments(appointmentDTOs);
            }
            else
            {
              doctorResponse = new ResponseObject(false,
                  "No appointments found", doctorId);
            }

            output.println(gson.toJson(doctorResponse));
          }

          case "doctorList" ->
          {
            List<Doctor> doctorList = authService.getAllDoctors();

            ResponseObject doctorResponse;
            if (doctorList != null && !doctorList.isEmpty())
            {
              doctorResponse = new ResponseObject(true, "Doctors found", -1);

              List<DoctorDTO> doctorDTOs = new ArrayList<>();
              for (Doctor doc : doctorList)
              {
                DoctorDTO dto = new DoctorDTO(doc.getDoctorID(),
                    doc.getFirstName(), doc.getLastName(), doc.getEmail(),
                    doc.getPhoneNumber(), doc.getUsername(), doc.getPassword());
                doctorDTOs.add(dto);
              }

              doctorResponse.setDoctors(doctorDTOs);
            }
            else
            {
              doctorResponse = new ResponseObject(false, "No doctors found",
                  -1);
            }

            output.println(gson.toJson(doctorResponse));
          }

          case "patientList" ->
          {
            List<Patient> patientList = authService.getAllPatients();

            ResponseObject patientResponse;
            if (patientList != null && !patientList.isEmpty())
            {
              patientResponse = new ResponseObject(true, "Patient found", -1);
              patientResponse.setPatients(patientList);
            }
            else
            {
              patientResponse = new ResponseObject(false, "No patient found",
                  -1);
            }

            output.println(gson.toJson(patientResponse));
          }

          case "addDiagnosis" ->
          {
            Diagnosis diagnosis = req.getDiagnosis();

            if (diagnosis != null)
            {
              authService.addDiagnosis(diagnosis);
            }
            System.out.println("Received diagnosis:");

            ResponseObject diagnosisResponse = new ResponseObject();
            diagnosisResponse.setSuccess(true);
            diagnosisResponse.setMessage("Diagnosis received by server");
            diagnosisResponse.setDiagnosis(diagnosis);

            output.println(gson.toJson(diagnosisResponse));
          }

          case "getDiagnosisList" ->
          {
            int patientId = req.getId();
            List<Diagnosis> diagnosisList = authService.getDiagnosesForPatient(
                patientId);

            ResponseObject diagnosisResponse;

            if (diagnosisList != null && !diagnosisList.isEmpty())
            {
              diagnosisResponse = new ResponseObject(true, "Diagnoses found",
                  patientId);
              diagnosisResponse.setDiagnoses(diagnosisList);
            }
            else
            {
              diagnosisResponse = new ResponseObject(false,
                  "No diagnoses found", patientId);
            }

            output.println(gson.toJson(diagnosisResponse));
          }

          case "addVaccination" ->
          {
            Vaccination vaccination = req.getVaccination();

            if (vaccination != null)
            {
              Vaccination created = AuthenticationServiceImp.getInstance()
                  .addVaccination(vaccination.getVaccinationName(),
                      vaccination.getDateTaken(), vaccination.isRecommended(),
                      vaccination.getComment(), vaccination.getNextDoseDate(),
                      vaccination.getDoctorId(), vaccination.getPatientId());
              System.out.println("Received vaccination");
            }

            ResponseObject vaccinationResponse = new ResponseObject();
            vaccinationResponse.setSuccess(true);
            vaccinationResponse.setMessage("Vaccination received by server");
            vaccinationResponse.setVaccination(vaccination);

            output.println(gson.toJson(vaccinationResponse));
          }

          case "getVaccinationList" ->
          {
            int patientId = req.getId();

            List<Vaccination> vaccinationList = authService.getVaccinationsForPatient(
                patientId);

            ResponseObject vaccinationResponse;

            if (vaccinationList != null && !vaccinationList.isEmpty())
            {
              vaccinationResponse = new ResponseObject(true,
                  "Vaccinations found", patientId);
              vaccinationResponse.setVaccinations(vaccinationList);
            }
            else
            {
              vaccinationResponse = new ResponseObject(false,
                  "No vaccinations found", patientId);
            }

            output.println(gson.toJson(vaccinationResponse));
          }

          case "getPrescriptionList" ->
          {
            int patientId = req.getId();

            List<Prescription> prescriptionList = authService.getPrescriptionsForPatient(
                patientId);

            ResponseObject prescriptionResponse;

            if (prescriptionList != null && !prescriptionList.isEmpty())
            {
              prescriptionResponse = new ResponseObject(true,
                  "Prescription found", patientId);
              prescriptionResponse.setPrescriptions(prescriptionList);
            }
            else
            {
              prescriptionResponse = new ResponseObject(false,
                  "No prescriptions found", patientId);
            }

            output.println(gson.toJson(prescriptionResponse));
          }

          case "addPrescription" ->
          {
            Prescription prescription = req.getPrescription();

            if (prescription != null)
            {
              authService.addPrescription(prescription.getMedicineName(),
                  prescription.getDoseAmount(), prescription.getDoseUnit(),
                  prescription.getStartDate(), prescription.getEndDate(),
                  prescription.getFrequency(), prescription.getStatus(),
                  prescription.getComment(), prescription.getDoctorId(),
                  prescription.getPatientId());
            }
            System.out.println("Received prescription");

            ResponseObject prescriptionResponse = new ResponseObject();
            prescriptionResponse.setSuccess(true);
            prescriptionResponse.setMessage("Prescription received by server");
            prescriptionResponse.setPrescription(prescription);

            output.println(gson.toJson(prescriptionResponse));
          }

          case "getLabResultList" ->
          {
            int patientId = req.getId();

            List<LabResult> labResultList = authService.getLabResultsForPatient(
                patientId);

            ResponseObject labResultResponse;

            if (labResultList != null && !labResultList.isEmpty())
            {
              labResultResponse = new ResponseObject(true, "LabResult found",
                  patientId);
              labResultResponse.setLabResults(labResultList);
            }
            else
            {
              labResultResponse = new ResponseObject(false,
                  "No labResults found", patientId);
            }

            output.println(gson.toJson(labResultResponse));
          }

          case "addLabResult" ->
          {
            LabResult labResult = req.getLabResult();

            System.out.println(
                "ClientHandler LabResult: " + labResult.getTestName() + " "
                    + labResult.getPatientId());

            if (labResult != null)
            {
              authService.addLabResult(labResult.getTestName(),
                  labResult.getSampleType(), labResult.getDateCollected(),

                  labResult.getComment(), labResult.getDoctorId(),
                  labResult.getPatientId());
              System.out.println("LabResult was Null");
            }
            System.out.println("Received labResult");

            ResponseObject labResultResponse = new ResponseObject();
            labResultResponse.setSuccess(true);
            labResultResponse.setMessage("LabResult received by server");
            labResultResponse.setLabResult(labResult);
            System.out.println(
                "LabResultResponse: " + labResultResponse.getLabResult()
                    .getTestName());

            output.println(gson.toJson(labResultResponse));
          }

          case "bookAppointment" ->
          {
            AppointmentDTO dto = req.getAppointment();

            String[] dateParts = dto.getDate().split("/");
            String[] timeParts = dto.getTime().split(":");

            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);

            NewDateTime dateTime = new NewDateTime(day, month, year, hour,
                minute);

            // Convert DTO to server model
            DoctorDTO doctorDTO = dto.getDoctor();

            Doctor doctor = new Doctor(doctorDTO.getDoctorID(),
                doctorDTO.getFirstName(), doctorDTO.getLastName(),
                doctorDTO.getEmail(), doctorDTO.getPhoneNumber(),
                doctorDTO.getUserName(), doctorDTO.getPassword());

            Appointment appointment = new Appointment(dateTime,
                dto.getPatientId(), doctor, dto.getMode());

            ResponseObject appointmentResponse = new ResponseObject();

            Appointment createdAppointment = authService.bookAppointment(
                appointment);

            appointmentResponse.setSuccess(true);
            appointmentResponse.setMessage("Appointment received by server");

            Doctor doctor1 = createdAppointment.getDoctor();

            DoctorDTO doctorDTO1 = new DoctorDTO(doctor1.getDoctorID(),
                doctor1.getFirstName(), doctor1.getLastName(),
                doctor1.getEmail(), doctor1.getPhoneNumber(),
                doctor1.getUsername(), doctor1.getPassword());
            AppointmentDTO responseDto = new AppointmentDTO(

                createdAppointment.getAppointmentID(),
                createdAppointment.getDate(), createdAppointment.getTime(),
                doctorDTO1, createdAppointment.getPatientID(),
                createdAppointment.getMode());

            appointmentResponse.setAppointment(responseDto);
            output.println(gson.toJson(appointmentResponse));
            //            if (appointment != null)
            //            {
            //              authService.bookAppointment(appointment);
            //
            //              System.out.println("Received prescription");
            //
            //              appointmentResponse.setSuccess(true);
            //              appointmentResponse.setMessage("Appointment received by server");
            //              AppointmentDTO responseDto = new AppointmentDTO(
            //                  appointment.getAppointmentID(), appointment.getDate(),
            //                  appointment.getTime(), appointment.getDoctorID(),
            //                  appointment.getPatientID(), appointment.getMode());
            //
            //              appointmentResponse.setAppointment(responseDto);
            //              output.println(gson.toJson(appointmentResponse));
            //            }
            //            else
            //            {
            //              appointmentResponse.setSuccess(false);
            //              appointmentResponse.setMessage(
            //                  "Failed to save appointment to database");
            //            }
            //
            //            output.println(gson.toJson(appointmentResponse));
          }

          case "addReferral" ->
          {
            Referral referral = req.getReferral();

            if (referral != null)
            {
              authService.addReferral(referral);
            }
            System.out.println("Received referral:" + referral);

            ResponseObject referralResponse = new ResponseObject();
            referralResponse.setSuccess(true);
            referralResponse.setMessage("Referral received by server");
            referralResponse.setReferral(referral);

            output.println(gson.toJson(referralResponse));
          }

          case "getReferralList" ->
          {
            int patientId = req.getId();
            List<Referral> referralList = authService.getReferralsForPatient(
                patientId);

            ResponseObject referralResponse;

            if (referralList != null && !referralList.isEmpty())
            {
              referralResponse = new ResponseObject(true, "Referrals found",
                  patientId);
              referralResponse.setReferrals(referralList);
            }
            else
            {
              referralResponse = new ResponseObject(false, "No referral found",
                  patientId);
            }

            output.println(gson.toJson(referralResponse));
          }

          case "modifyAppointment" ->
          {
            AppointmentDTO dto = req.getAppointment();

            String[] dateParts = dto.getDate().split("/");
            String[] timeParts = dto.getTime().split(":");

            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);

            NewDateTime dateTime = new NewDateTime(day, month, year, hour,
                minute);

            DoctorDTO doctorDTO = dto.getDoctor();
            Doctor doctor = new Doctor(doctorDTO.getDoctorID(),
                doctorDTO.getFirstName(), doctorDTO.getLastName(),
                doctorDTO.getEmail(), doctorDTO.getPhoneNumber(),
                doctorDTO.getUserName(), doctorDTO.getPassword());

            Appointment appointmentToUpdate = new Appointment(dateTime,
                dto.getPatientId(), doctor, dto.getMode());

            appointmentToUpdate.setAppointmentID(dto.getId());

            Appointment updatedAppointment = authService.modifyAppointment(
                appointmentToUpdate);

            ResponseObject response = new ResponseObject();
            if (updatedAppointment != null)
            {
              response.setSuccess(true);
              response.setMessage("Appointment modified successfully");

              Doctor doctor1 = updatedAppointment.getDoctor();
              DoctorDTO doctorDTO1 = new DoctorDTO(doctor1.getDoctorID(),
                  doctor1.getFirstName(), doctor1.getLastName(),
                  doctor1.getEmail(), doctor1.getPhoneNumber(),
                  doctor1.getUsername(), doctor1.getPassword());

              response.setAppointment(
                  new AppointmentDTO(updatedAppointment.getAppointmentID(),
                      updatedAppointment.getDate(),
                      updatedAppointment.getTime(), doctorDTO1,
                      updatedAppointment.getPatientID(),
                      updatedAppointment.getMode()));
            }
            else
            {
              response.setSuccess(false);
              response.setMessage("Failed to modify appointment");
            }

            output.println(gson.toJson(response));

          }

          default ->
          {
            output.println(gson.toJson(
                new ResponseObject(false, "Unknown request type", -1)));
          }
        }

      }

    }

    catch (IOException e)

    {
      e.printStackTrace();
    }
    finally

    {
      try
      {
        if (input != null)
          input.close();
        if (output != null)
          output.close();
        if (socket != null && !socket.isClosed())
          socket.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}

