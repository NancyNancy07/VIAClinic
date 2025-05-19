package server.loginNetwork;

import com.google.gson.Gson;
import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.Patient;
import server.model.loginSystem.authentication.AuthenticationService;
import server.model.loginSystem.authentication.AuthenticationServiceImp;
import server.model.loginSystem.authentication.LoginRequest;
import server.model.patientJournal.Diagnosis;
import shared.RequestObject;
import shared.ResponseObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
        System.out.println(req.getType());
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

            if (diagnosis != null)
            {
              AuthenticationServiceImp.getInstance().addDiagnosis(diagnosis);
            }
            System.out.println("Received diagnosis:");
            System.out.println("  Name: " + (diagnosis != null ?
                diagnosis.getDiagnosisName() :
                null));
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

          case "bookAppointment" ->
          {
            Appointment appointment = req.getAppointment();
            Appointment savedAppointment = AuthenticationServiceImp.getInstance()
                .bookAppointment(appointment);

            ResponseObject appointmentResponse = new ResponseObject();
            if (savedAppointment != null)
            {
              appointmentResponse.setSuccess(true);
              appointmentResponse.setMessage(
                  "Appointment saved to database successfully");
              appointmentResponse.setAppointment(savedAppointment);
            }
            else
            {
              appointmentResponse.setSuccess(false);
              appointmentResponse.setMessage(
                  "Failed to save appointment to database");
            }

            output.println(gson.toJson(appointmentResponse));
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

