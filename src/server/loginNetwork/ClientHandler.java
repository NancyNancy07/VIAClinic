package server.loginNetwork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.Patient;
import server.model.loginSystem.authentication.AuthenticationService;
import server.model.loginSystem.authentication.AuthenticationServiceImp;
import server.model.loginSystem.authentication.LoginRequest;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.LabResult;
import server.model.patientJournal.Prescription;
import server.util.LocalDateAdapter;
import server.util.LocalTimeAdapter;
import shared.RequestObject;
import shared.ResponseObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ClientHandler implements Runnable
{
  private Socket socket;
  private BufferedReader input;
  private PrintWriter output;
  private Gson gson;
  private AuthenticationService authService;

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

              patientResponse.setAppointments(patientAppointments);
            }
            else
            {
              patientResponse = new ResponseObject(false,
                  "No appointments found", patientId);
            }

            output.println(gson.toJson(patientResponse));
          }

          case "doctorList" ->
          {
            List<Doctor> doctorList = authService.getAllDoctors();

            ResponseObject doctorResponse;
            if (doctorList != null && !doctorList.isEmpty())
            {
              doctorResponse = new ResponseObject(true, "Doctors found", -1);
              doctorResponse.setDoctors(doctorList);
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

            if (diagnosis != null) {
              AuthenticationServiceImp.getInstance().addDiagnosis(diagnosis);
            }
            System.out.println("Received diagnosis:");
            System.out.println("  Name: " + diagnosis.getDiagnosisName());
            System.out.println("  Status: " + diagnosis.getStatus());
            System.out.println(
                "  Date Diagnosed: " + diagnosis.getDateDiagnosed());
            System.out.println("  Comment: " + diagnosis.getComment());
            System.out.println("  Doctor ID: " + diagnosis.getDoctorId());
            System.out.println("  Patient ID: " + diagnosis.getPatientId());
            System.out.println(
                "  Prescription: " + diagnosis.getPrescription());

            ResponseObject diagnosisResponse = new ResponseObject();
            diagnosisResponse.setSuccess(true);
            diagnosisResponse.setMessage("Diagnosis received by server");
            diagnosisResponse.setDiagnosis(diagnosis);

            output.println(gson.toJson(diagnosisResponse));
          }

          case "getDiagnosisList" ->
          {
            int patientId = req.getId();

            List<Diagnosis> diagnosisList = authService.getDiagnosesForPatient(patientId);

            ResponseObject diagnosisResponse;
            if (diagnosisList != null && !diagnosisList.isEmpty()) {
              diagnosisResponse = new ResponseObject(true, "Diagnoses found", patientId);
              diagnosisResponse.setDiagnoses(diagnosisList);
            } else {
              diagnosisResponse = new ResponseObject(false, "No diagnoses found", patientId);
            }

            output.println(gson.toJson(diagnosisResponse));
          }

          case "getPrescriptionList" ->
          {
            int patientId = req.getId();

            List<Prescription> prescriptionList = authService.getPrescriptionsForPatient(patientId);

            ResponseObject prescriptionResponse;

            if (prescriptionList != null && !prescriptionList.isEmpty()) {
              prescriptionResponse = new ResponseObject(true, "Prescription found", patientId);
              prescriptionResponse.setPrescriptions(prescriptionList);
            } else {
              prescriptionResponse = new ResponseObject(false, "No prescriptions found", patientId);
            }

            output.println(gson.toJson(prescriptionResponse));
          }

          case "addPrescription" ->
          {
            Prescription prescription = req.getPrescription();

            if (prescription != null) {
              AuthenticationServiceImp.getInstance().addPrescription(prescription.getMedicineName(),
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

            List<LabResult> labResultList = authService.getLabResultsForPatient(patientId);

            ResponseObject labResultResponse;

            if (labResultList != null && !labResultList.isEmpty()) {
              labResultResponse = new ResponseObject(true, "LabResult found", patientId);
              labResultResponse.setLabResults(labResultList);
            } else {
              labResultResponse = new ResponseObject(false, "No labResults found", patientId);
            }

            output.println(gson.toJson(labResultResponse));
          }
          case "addLabResult" ->
          {
            LabResult labResult = req.getLabResult();

            System.out.println("ClientHandler LabResult: " + labResult.getTestName()+ " " +labResult.getPatientId());

            if (labResult != null) {
             authService.addLabResult(labResult.getTestName(),
                 labResult.getSampleType(),
                  labResult.getDateCollected(),

                  labResult.getComment(), labResult.getDoctorId(),
                  labResult.getPatientId());
              System.out.println("LabResult was Null");
            }
            System.out.println("Received labResult");


            ResponseObject labResultResponse = new ResponseObject();
            labResultResponse.setSuccess(true);
            labResultResponse.setMessage("LabResult received by server");
            labResultResponse.setLabResult(labResult);
            System.out.println("LabResultResponse: " + labResultResponse.getLabResult().getTestName());

            output.println(gson.toJson(labResultResponse));
          }


          default ->
          {
            output.println(gson.toJson(
                new ResponseObject(false, "Unknown request type", -1)));
          }
          case "bookAppointment" ->
          {
            Appointment appointment = req.getAppointment();
            System.out.println("Received appointment:");
            System.out.println("  date: " + appointment.getDate());
            System.out.println("  time: " + appointment.getTime());
            System.out.println("  mode: " + appointment.getMode());
            System.out.println(
                "  Appointment ID: " + appointment.getAppointmentID());
            System.out.println("  Doctor ID: " + appointment.getDoctorID());
            System.out.println("  Patient ID: " + appointment.getPatientID());
            


            ResponseObject appointmentResponse = new ResponseObject();
            appointmentResponse.setSuccess(true);
            appointmentResponse.setMessage("appointment received by server");
            appointmentResponse.setAppointment(appointment);

            output.println(gson.toJson(appointmentResponse));
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

