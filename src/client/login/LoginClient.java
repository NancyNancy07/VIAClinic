package client.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class LoginClient
{
  public static void main(String[] args)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in))
    {
      System.out.print("Enter username: ");
      String username = scanner.nextLine();
      System.out.print("Enter password: ");
      String password = scanner.nextLine();

      output.println(username);
      output.println(password);

      String response = input.readLine();
      if ("LOGIN_SUCCESS".equals(response))
      {
        System.out.println("✅ Login successful.");
      }
      else
      {
        System.out.println("❌ Login failed.");
      }

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
