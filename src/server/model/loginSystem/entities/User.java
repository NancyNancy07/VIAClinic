package server.model.loginSystem.entities;

public abstract class User
{
  private String username;
  private String password;

  public User(String username, String password)
  {
    this.username = username;
    this.password = password;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }
}

//public class User
//{
//  private String email;
//  private String password;
//
//  public User(String email, String password)
//  {
//    this.email = email;
//    this.password = password;
//  }
//
//  public String getEmail()
//  {
//    return email;
//  }
//
//  public String getPassword()
//  {
//    return password;
//  }
//}
