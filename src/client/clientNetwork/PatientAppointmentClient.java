package client.clientNetwork;

import com.google.gson.Gson;
import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.Doctor;
import shared.RequestObject;
import shared.ResponseObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class PatientAppointmentClient
{
  public List<Appointment> getAppointmentByPatientId(int patientId)
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

      return response.getAppointments();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

//  public Appointment bookAppointment(Appointment appointment)
//  {
//    try (Socket socket = new Socket("localhost", 1234);
//        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
//        BufferedReader input = new BufferedReader(
//            new InputStreamReader(socket.getInputStream())))
//    {
//      Gson gson = new Gson();
//
//      // Create a request object to send
//      RequestObject request = new RequestObject();
//      request.setType("bookAppointment");
//      request.setAppointment(appointment);
//
//
//      String jsonRequest = gson.toJson(request);
//      System.out.println("Sending to server: " + jsonRequest);
//
//
//      output.println(jsonRequest);
//
//      String jsonResponse = input.readLine();
//      System.out.println("Received from server: " + jsonResponse);
//
//      ResponseObject response = gson.fromJson(jsonResponse, ResponseObject.class);
//
//      return response.getAppointment();
//    }
//    catch (IOException e)
//    {
//      e.printStackTrace();
//      return null;
//    }
//  }

  public List<Doctor> getDoctorList()
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

      return response.getDoctors();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }
}
