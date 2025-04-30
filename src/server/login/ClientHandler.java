package server.login;

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
  private UserManager userManager;

  public ClientHandler(Socket socket, UserManager userManager)
  {
    this.socket = socket;
    this.userManager = userManager;

    try
    {
      input = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
      output = new PrintWriter(socket.getOutputStream(), true);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    try
    {
      String username = input.readLine();
      String password = input.readLine();

      if (userManager.isValidLogin(username, password))
      {
        output.println("LOGIN_SUCCESS");
      }
      else
      {
        output.println("LOGIN_FAILED");
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
        if (input != null) input.close();
        if (output != null) output.close();
        if (socket != null && !socket.isClosed()) socket.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
