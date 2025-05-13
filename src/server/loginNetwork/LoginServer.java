package server.loginNetwork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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




//    try
//
//  {
//    ServerSocket serverSocket = new ServerSocket(PORT);
//    System.out.println("Server started...");
//    while (true)
//    {
//      Socket clientSocket = serverSocket.accept();
//      new Thread(new ClientHandler(clientSocket)).start();
//    }
//  }
//    catch(
//  Exception e)
//
//  {
//    e.printStackTrace();
//  }
//
//}
}
//    try (ServerSocket serverSocket = new ServerSocket(PORT))
//    {
//      System.out.println("✅ Login Server started on port " + PORT);
//      //      UserManager userManager = new UserManager();
//
//      while (true)
//      {
//        Socket clientSocket = serverSocket.accept();
//        ClientHandler handler = new ClientHandler(clientSocket);
//        Thread thread = new Thread(handler);
//        thread.start();
//      }
//    }
//    catch (IOException e)
//    {
//      System.err.println("❌ Server error: " + e.getMessage());
//      e.printStackTrace();
//    }
//  }

