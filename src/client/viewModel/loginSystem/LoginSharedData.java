package client.viewModel.loginSystem;

/**
 * LoginSharedData is a singleton class that holds shared data for the login system.
 * It stores the username, password, user type, and patient ID.
 */
public class LoginSharedData
{
  private static LoginSharedData instance;

  private String username;
  private String pwd;
  private String userType;
  private int id;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * This ensures that only one instance of LoginSharedData exists (singleton pattern).
   */
  private LoginSharedData()
  {
  }

  /**
   * Returns the singleton instance of LoginSharedData.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of LoginSharedData
   */
  public static LoginSharedData getInstance()
  {
    if (instance == null)
    {
      instance = new LoginSharedData();
    }
    return instance;
  }

  /**
   * Sets the data for the login shared data instance.
   * This method is used to set the username, password, user type, and patient ID.
   *
   * @param email      the email of the user
   * @param pwd        the password of the user
   * @param userType   the type of user (e.g., doctor or patient)
   * @param patientId  the ID of the patient
   */
  public void setData(String email, String pwd, String userType, int patientId)
  {
    this.username = email;
    this.pwd = pwd;
    this.userType = userType;
    this.id = patientId;
  }

  /**
   * Returns the username of the logged-in user.
   *
   * @return the username
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * Gets the ID of the logged-in user.
   * @return the ID of the user
   */
  public int getId()
  {
    return id;
  }
}
