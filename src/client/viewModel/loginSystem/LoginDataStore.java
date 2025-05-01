package client.viewModel.loginSystem;

public class LoginDataStore
{
  private static LoginDataStore instance;

  private String doctorEmail;
  private String pwd;

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

  public void setDoctorData(String email, String pwd)
  {
    this.doctorEmail = email;
    this.pwd = pwd;
  }

  public String getDoctorEmail()
  {
    return doctorEmail;
  }
}
