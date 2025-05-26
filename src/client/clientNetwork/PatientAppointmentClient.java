package client.clientNetwork;

import client.model.clientBookAppointment.*;
import com.google.gson.Gson;
import shared.AppointmentDTO;
import shared.DoctorDTO;
import shared.RequestObject;
import shared.ResponseObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class PatientAppointmentClient<Create>
{
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
          clientDoctor.getName(), clientDoctor.getName(),
          clientDoctor.getUsername(), clientDoctor.getPassword(),
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
}

