package client.clientNetwork;

import com.google.gson.Gson;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.Patient;
import shared.RequestObject;
import shared.ResponseObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class PatientClient
{
  public List<Patient> getPatientList()
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {

      Gson gson = new Gson();
      RequestObject request = new RequestObject();
      request.setType("patientList");
      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);

      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      return response.getPatients();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }
}
