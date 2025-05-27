package server.model.loginSystem.entities;

/**
 * User class represents a user in the login system.
 * It contains basic user information such as username and password.
 */
public abstract class User
{
  private String username;
  private String password;

  /**
   * Default constructor for User.
   * Initializes the username and password to empty strings.
   */
  public User(String username, String password)
  {
    this.username = username;
    this.password = password;
  }

  /**
   * Gets the username of the user.
   *
   * @return the username as a String
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * Gets the password of the user.
   *
   * @return the password as a String
   */
  public String getPassword()
  {
    return password;
  }
}
