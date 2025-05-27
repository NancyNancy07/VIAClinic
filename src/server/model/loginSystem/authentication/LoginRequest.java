package server.model.loginSystem.authentication;

/**
 * LoginRequest class represents a request for user login.
 * It contains the username, password, and user type.
 */
public class LoginRequest
{
  private String username;
  private String password;
  private String userType;

  /**
   * Default constructor for LoginRequest.
   * Initializes the username, password, and userType
   * @param username the username of the user
   * @param password the password of the user
   * @param userType the type of user (e.g., "doctor" or "patient")
   */
  public LoginRequest(String username, String password, String userType)
  {
    this.username = username;
    this.password = password;
    this.userType = userType;
  }

  /**
   * Gets the username of the login request.
   *
   * @return the username
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * Gets the password of the login request.
   *
   * @return the password
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Gets the user type of the login request.
   *
   * @return the user type (e.g., "doctor" or "patient")
   */
  public String getUserType()
  {
    return userType;
  }
}
