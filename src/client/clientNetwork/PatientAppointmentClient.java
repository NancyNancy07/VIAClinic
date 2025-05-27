package client.clientNetwork;

import client.model.clientBookAppointment.*;
import com.google.gson.Gson;
import shared.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;


/**
 * The PatientAppointmentClient class provides methods to interact with the server
 * for managing patient appointments, including retrieving appointments by patient or doctor ID,
 * booking new appointments, modifying existing appointments, and retrieving a list of doctors.
 */
public class PatientAppointmentClient<Create>
{
  /**
   * Retrieves a list of appointments for a specific patient by their ID.
   *
   * @param patientId The ID of the patient whose appointments are to be retrieved.
   * @return A ClientAppointmentList containing the patient's appointments, or null if an error occurs.
   */
  public ClientAppointmentList getAppointmentByPatientId(int patientId)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {
      Gson gson = new Gson();
      RequestObject request = new RequestObject();
      request.setType("patientAppointments");
      request.setId(patientId);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);

      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      List<AppointmentDTO> dtoList = response.getAppointments();

      ClientAppointmentList clientAppointments = new ClientAppointmentList();
      for (AppointmentDTO dto : dtoList)
      {
        String[] dateParts = dto.getDate().split("/");
        String[] timeParts = dto.getTime().split(":");

        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        ClientNewDateTime dateTime = new ClientNewDateTime(day, month, year,
            hour, minute);

        DoctorDTO doctorDto = dto.getDoctor();

        System.out.println(doctorDto.getFirstName());
        ClientDoctor doctor = new ClientDoctor(doctorDto.getDoctorID(),
            doctorDto.getFirstName(), doctorDto.getLastName(),
            doctorDto.getEmail(), doctorDto.getPhoneNumber(),
            doctorDto.getUserName(), doctorDto.getPassword());

        ClientAppointment app = new ClientAppointment(dto.getId(), dateTime,
            dto.getPatientId(), doctor, dto.getMode());
        clientAppointments.addAppointment(app);
      }

      return clientAppointments;
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Retrieves a list of appointments for a specific doctor by their ID.
   *
   * @param doctorId The ID of the doctor whose appointments are to be retrieved.
   * @return A ClientAppointmentList containing the doctor's appointments, or null if an error occurs.
   */
  public ClientAppointmentList getAppointmentByDoctorId(int doctorId)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {
      Gson gson = new Gson();
      RequestObject request = new RequestObject();
      request.setType("doctorAppointments");
      request.setId(doctorId);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);

      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      List<AppointmentDTO> dtoList = response.getAppointments();

      ClientAppointmentList clientAppointments = new ClientAppointmentList();
      for (AppointmentDTO dto : dtoList)
      {
        String[] dateParts = dto.getDate().split("/");
        String[] timeParts = dto.getTime().split(":");

        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        ClientNewDateTime dateTime = new ClientNewDateTime(day, month, year,
            hour, minute);

        DoctorDTO doctorDto = dto.getDoctor();

        ClientDoctor doctor = new ClientDoctor(doctorDto.getDoctorID(),
            doctorDto.getFirstName(), doctorDto.getLastName(),
            doctorDto.getEmail(), doctorDto.getPhoneNumber(),
            doctorDto.getUserName(), doctorDto.getPassword());

        PatientDTO patientDto = dto.getPatientDTO();

        ClientPatient patient = new ClientPatient(patientDto.getPatientID(),
            patientDto.getFirstName(), patientDto.getLastName(),
            patientDto.getEmail(), patientDto.getPhoneNumber(),
            patientDto.getUserName(), patientDto.getPassword(),
            patientDto.getCPR(), patientDto.getAddress());

        ClientAppointment app = new ClientAppointment(dto.getId(), dateTime,
            patient, doctor, dto.getMode());
        clientAppointments.addAppointment(app);
      }

      return clientAppointments;
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Books an appointment for a patient with a specific doctor at a specified date and time.
   *
   * @param appointment The ClientAppointment object containing the appointment details.
   * @return The booked ClientAppointment object, or null if the booking fails.
   */
  public ClientAppointment bookAppointment(ClientAppointment appointment)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {
      Gson gson = new Gson();

      // Create a request object to send;
      ClientDoctor clientDoctor = appointment.getDoctor();
      DoctorDTO doctorDTO = new DoctorDTO(clientDoctor.getDoctorID(),
          clientDoctor.getFirstName(), clientDoctor.getLastName(),
          clientDoctor.getEmail(), clientDoctor.getPhoneNumber(),
          clientDoctor.getUsername(), clientDoctor.getPassword());

      AppointmentDTO dto = new AppointmentDTO(appointment.getAppointmentID(),
          appointment.getDate(), appointment.getTime(), doctorDTO,
          appointment.getPatientID(), appointment.getMode());
      RequestObject request = new RequestObject();
      request.setType("bookAppointment");
      request.setAppointment(dto);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);

      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      AppointmentDTO responseDto = response.getAppointment();

      if (responseDto != null)
      {
        String[] dateParts = responseDto.getDate().split("/");
        String[] timeParts = responseDto.getTime().split(":");

        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        ClientNewDateTime dateTime = new ClientNewDateTime(day, month, year,
            hour, minute);

        DoctorDTO doctorDTO1 = responseDto.getDoctor();
        ClientDoctor doctor = new ClientDoctor(doctorDTO1.getDoctorID(),
            doctorDTO1.getFirstName(), doctorDTO1.getLastName(),
            doctorDTO1.getEmail(), doctorDTO1.getPhoneNumber(),
            doctorDTO1.getUserName(), doctorDTO1.getPassword());

        return new ClientAppointment(responseDto.getId(), dateTime,
            responseDto.getPatientId(), doctor, responseDto.getMode());
      }
      else
      {
        return null;
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Retrieves a list of doctors from the server.
   *
   * @return A ClientDoctorList containing the list of doctors, or null if an error occurs.
   */
  public ClientDoctorList getDoctorList()
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {

      Gson gson = new Gson();
      RequestObject request = new RequestObject();
      request.setType("doctorList");
      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);

      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      List<DoctorDTO> doctorDTO = response.getDoctors();
      ClientDoctorList clientDoctors = new ClientDoctorList();
      for (DoctorDTO dto : doctorDTO)
      {
        ClientDoctor clientDoctor = new ClientDoctor(dto.getDoctorID(),
            dto.getFirstName(), dto.getLastName(), dto.getEmail(),
            dto.getPhoneNumber(), dto.getUserName(), dto.getPassword());
        clientDoctors.addDoctor(clientDoctor);
      }

      return clientDoctors;
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Modifies an existing appointment for a patient.
   *
   * @param appointmentId The ID of the appointment to be modified.
   * @param patientId The ID of the patient associated with the appointment.
   * @param clientDoctor The ClientDoctor object representing the doctor for the appointment.
   * @param newDateTime The new date and time for the appointment.
   * @param newMode The new mode of the appointment (e.g., in-person, telehealth).
   * @return The modified ClientAppointment object, or null if the modification fails.
   */
  public ClientAppointment modifyAppointment(int appointmentId, int patientId,
      ClientDoctor clientDoctor, ClientNewDateTime newDateTime, String newMode)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {
      Gson gson = new Gson();

      DoctorDTO doctorDTO = new DoctorDTO(clientDoctor.getDoctorID(),
          clientDoctor.getFirstName(), clientDoctor.getLastName(),
          clientDoctor.getEmail(), clientDoctor.getPhoneNumber(),
          clientDoctor.getUsername(), clientDoctor.getPassword());

      AppointmentDTO dto = new AppointmentDTO(appointmentId,
          newDateTime.getDate(), newDateTime.getTime(), doctorDTO, patientId,
          newMode);

      RequestObject request = new RequestObject();
      request.setType("modifyAppointment");
      request.setId(appointmentId);
      request.setAppointment(dto);
      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);
      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);
      AppointmentDTO responseDto = response.getAppointment();
      if (responseDto != null)
      {
        String[] dateParts = responseDto.getDate().split("/");
        String[] timeParts = responseDto.getTime().split(":");

        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        ClientNewDateTime dateTime = new ClientNewDateTime(day, month, year,
            hour, minute);

        DoctorDTO doctorDTO1 = responseDto.getDoctor();
        ClientDoctor doctor = new ClientDoctor(doctorDTO1.getDoctorID(),
            doctorDTO1.getFirstName(), doctorDTO1.getLastName(),
            doctorDTO1.getEmail(), doctorDTO1.getPhoneNumber(),
            doctorDTO1.getUserName(), doctorDTO1.getPassword());

        return new ClientAppointment(responseDto.getId(), dateTime,
            responseDto.getPatientId(), doctor, responseDto.getMode());
      }
      else
      {
        return null;
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public boolean cancelAppointment(int appointmentId)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())))
    {
      Gson gson = new Gson();

      // Create and configure request
      RequestObject request = new RequestObject();
      request.setType("cancelAppointment");
      request.setId(appointmentId);

      // Send JSON to server
      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);
      output.println(jsonRequest);

      // Read JSON from server
      String jsonResponse = input.readLine();
      System.out.println("Received from server: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse, ResponseObject.class);
      return response.isSuccess();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return false;
    }
  }
}

