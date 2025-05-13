package client.viewModel.loginSystem;

public class LoginSharedData
{
  private static LoginSharedData instance;

  private String username;
  private String pwd;
  private String userType;
  private int patientId;

  private LoginSharedData()
  {
  }

  public static LoginSharedData getInstance()
  {
    if (instance == null)
    {
      instance = new LoginSharedData();
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
