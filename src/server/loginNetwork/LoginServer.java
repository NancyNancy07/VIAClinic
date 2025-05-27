package server.loginNetwork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * LoginServer is a simple server application that listens for incoming client connections
 * on a specified port. It creates a new thread for each client connection to handle requests.
 * The server runs indefinitely until an error occurs or it is manually stopped.
 */
public class LoginServer
{
  private static final int PORT = 1234;

  public static void main(String[] args)
  {

    try (ServerSocket serverSocket = new ServerSocket(PORT))
    {
      System.out.println("✅ Login Server started on port " + PORT);
      while (true)
      {
        Socket clientSocket = serverSocket.accept();
        ClientHandler handler = new ClientHandler(clientSocket);
        Thread thread = new Thread(handler);
        thread.start();
      }
    }
    catch (IOException e)
    {
      System.err.println("❌ Server error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}


