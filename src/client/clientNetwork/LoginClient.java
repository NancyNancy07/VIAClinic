package client.clientNetwork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

/**
 * The LoginClient class is responsible for handling the login process of a user.
 * It connects to the server, sends the login request, and processes the response.
 * It uses Gson for JSON serialization and deserialization.
 */
public class LoginClient
{
  /**
   * Attempts to log in a user by sending their credentials and user type
   * to the server. A socket connection is established to transmit the login
   * request and receive the server's response.
   *
   * @param username the username entered by the user
   * @param password the corresponding password
   * @param type the type of user (e.g., "patient", "doctor", etc.)
   * @return a ResponseObject containing the server's reply, or null if an error occurs
   */
  public ResponseObject login(String username, String password, String type)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {
      Gson gson = new Gson();
      RequestObject request = new RequestObject();
      request.setType("login");
      request.setUsername(username);
      request.setPassword(password);
      request.setUserType(type);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server PatientLogin: " + jsonRequest);
      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server PatientLogin: " + jsonResponse);

      if (jsonResponse == null || jsonResponse.trim().isEmpty())
      {
        return null;
      }

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);
      System.out.println(response.isSuccess());
      return response;
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }
}
