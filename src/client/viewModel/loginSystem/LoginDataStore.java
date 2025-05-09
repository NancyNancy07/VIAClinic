package client.viewModel.loginSystem;

public class LoginDataStore
{
  private static LoginDataStore instance;

  private String username;
  private String pwd;
  private String userType;
  private int patientId;

  private LoginDataStore()
  {
  }

  public static LoginDataStore getInstance()
  {
    if (instance == null)
    {
      instance = new LoginDataStore();
    }
    return instance;
  }

  public void setData(String email, String pwd, String userType, int patientId)
  {
    this.username = email;
    this.pwd = pwd;
    this.userType = userType;
    this.patientId = patientId;
  }

  public String getUsername()
  {
    return username;
  }

  public int getPatientId()
  {
    return patientId;
  }
}
