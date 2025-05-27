package client.model.clientLoginSystem.clientEntities;

/**
 * Abstract class representing a user in the client login system.
 */
public abstract class ClientUser
{
  private String username;
  private String password;

  /**
   * Constructs a ClientUser with the specified username and password.
   *
   * @param username The username of the user.
   * @param password The password of the user.
   */
  public ClientUser(String username, String password)
  {
    this.username = username;
    this.password = password;
  }

  /**
   * Gets the username of the user.
   * @return The username of the user.
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * Gets the password of the user.
   * @return The password of the user.
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Sets the password for the user.
   * @param password The new password for the user.
   */
  public void setPassword(String password)
  {
    this.password = password;
  }

  /**
   * Sets the username for the user.
   * @param username The new username for the user.
   */
  public void setUsername(String username)
  {
    this.username = username;
  }
}
