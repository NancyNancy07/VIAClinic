package server.loginNetwork;

import com.google.gson.Gson;
import server.model.loginSystem.authentication.AuthenticationService;
import server.model.loginSystem.authentication.AuthenticationServiceImp;
import server.model.loginSystem.authentication.LoginRequest;
import shared.RequestObject;
import shared.ResponseObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable
{
  private Socket socket;
  private BufferedReader input;
  private PrintWriter output;
  private Gson gson;
  //  private UserManager userManager;
  private AuthenticationService authService;

  public ClientHandler(Socket socket)
  {
    this.socket = socket;
    gson = new Gson();
    //    this.userManager = userManager;
    authService = AuthenticationServiceImp.getInstance(); // Singleton pattern recommended

    //    try
    //    {
    //      input = new BufferedReader(
    //          new InputStreamReader(socket.getInputStream()));
    //      output = new PrintWriter(socket.getOutputStream(), true);
    //    }
    //    catch (IOException e)
    //    {
    //      e.printStackTrace();
    //    }
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

        String responseMessage = switch (req.getType())
        {
          case "login" -> authService.login(
              new LoginRequest(req.getUsername(), req.getPassword()));
          default -> "Unknown request type";
        };

        // Send response
        ResponseObject response = new ResponseObject("ok", responseMessage);
        output.println(gson.toJson(response));
      }

//      String username = input.readLine();
//      String password = input.readLine();

      //      if (userManager.isValidLogin(username, password))
      //      {
      //        output.println("LOGIN_SUCCESS");
      //      }
      //      else
      //      {
      //        output.println("LOGIN_FAILED");
      //      }
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
