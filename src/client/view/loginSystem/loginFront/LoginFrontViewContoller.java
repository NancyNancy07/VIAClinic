package client.view.loginSystem.loginFront;

import client.view.loginSystem.LoginSystemViewHandler;

/**
 * Controller for the login front view, allowing users to choose between
 * logging in as a doctor or a patient.
 */
public class LoginFrontViewContoller
{
  /**
   * Default constructor for LoginFrontViewContoller.
   * Initializes the controller without any parameters.
   */
  public void loginAsDoctor()
  {
    LoginSystemViewHandler.showView(
        LoginSystemViewHandler.ViewType.DOCTORLOGIN);
  }

  /**
   * Navigates to the patient login view.
   * This method is called when the user chooses to log in as a patient.
   */
  public void loginAsPatient()
  {
    LoginSystemViewHandler.showView(
        LoginSystemViewHandler.ViewType.PATIENTLOGIN);
  }
}
