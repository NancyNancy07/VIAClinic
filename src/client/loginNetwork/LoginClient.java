//package client.login;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class LoginClient
//{
//  public static void main(String[] args)
//  {
//    try (Socket socket = new Socket("localhost", 1234);
//        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
//        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        Scanner scanner = new Scanner(System.in))
//    {
//      System.out.print("Enter username: ");
//      String username = scanner.nextLine();
//      System.out.print("Enter password: ");
//      String password = scanner.nextLine();
//
//      output.println(username);
//      output.println(password);
//
//      String response = input.readLine();
//      System.out.println(response);
//      if ("LOGIN_SUCCESS".equals(response))
//      {
//        System.out.println("✅ Login successful.");
//      }
//      else
//      {
//        System.out.println("❌ Login failed.");
//      }
//
//    }
//    catch (IOException e)
//    {
//      e.printStackTrace();
//    }
//  }
//}

package client.loginNetwork;

import com.google.gson.Gson;
import shared.RequestObject;
import shared.ResponseObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginClient
{

  public String login(String username, String password)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {
      Gson gson = new Gson();
      RequestObject request = new RequestObject();
      request.setType("login");
      request.setEmail(username);
      request.setPassword(password);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);

      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println(
          "Received from server: " + jsonResponse); // Debug print

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);
      return response.getMessage();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return "ERROR";
    }
  }
}
