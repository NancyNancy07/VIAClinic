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
        clientAppointments.addAppointment(
            new ClientAppointment(dateTime, dto.getPatientId(),
                dto.getDoctorId(), dto.getMode()));
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
        clientAppointments.addAppointment(
            new ClientAppointment(dateTime, dto.getPatientId(),
                dto.getDoctorId(), dto.getMode()));
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

      AppointmentDTO dto = new AppointmentDTO(appointment.getDate(),
          appointment.getTime(), appointment.getDoctorID(),
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
        return new ClientAppointment(dateTime, responseDto.getPatientId(),
            responseDto.getDoctorId(), responseDto.getMode());
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
}

