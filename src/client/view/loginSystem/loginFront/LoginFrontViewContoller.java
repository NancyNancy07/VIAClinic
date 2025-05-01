package client.view.loginSystem.loginFront;

import client.view.loginSystem.LoginSystemViewHandler;

public class LoginFrontViewContoller
{
  public void loginAsDoctor()
  {
    LoginSystemViewHandler.showView(
        LoginSystemViewHandler.ViewType.DOCTORLOGIN);
  }

  public void loginAsPatient()
  {
    LoginSystemViewHandler.showView(
        LoginSystemViewHandler.ViewType.PATIENTLOGIN);
  }
}
