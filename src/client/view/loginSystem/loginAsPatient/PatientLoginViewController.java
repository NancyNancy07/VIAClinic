package client.view.loginSystem.loginAsPatient;

import client.view.loginSystem.LoginSystemViewHandler;
import client.viewModel.loginSystem.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller for the patient login view.
 */
public class PatientLoginViewController
{
  @FXML private TextField username;
  @FXML private PasswordField password;
  //  @FXML private TextField password;

  @FXML private Button loginBtn;
  private LoginViewModel loginViewModel;

  /**
   * Default constructor for PatientLoginViewController.
   * Initializes the controller without any parameters.
   * @param loginViewModel The view model containing login user information.
   */
  public void init(LoginViewModel loginViewModel)
  {
    this.loginViewModel = loginViewModel;
    username.textProperty()
        .bindBidirectional(loginViewModel.emailPropProperty());
    password.textProperty().bindBidirectional(loginViewModel.pwPropProperty());

    loginBtn.disableProperty().bind(loginViewModel.loginBtnEnabledProp());

    loginViewModel.loginSuccessProp().addListener((obs, oldVal, newVal) -> {
      if (newVal)
      {
        LoginSystemViewHandler.showView(
            LoginSystemViewHandler.ViewType.PATIENTVIEW);
      }
    });
  }

  /**
   * Handles the login action when the login button is clicked.
   * It calls the login method from the LoginViewModel to perform the login operation.
   */
  public void login()
  {
    loginViewModel.loginPatient();
  }

  /**
   * Handles the action when the back button is clicked.
   * It navigates back to the front view of the login system.
   */
  public void back()
  {
    LoginSystemViewHandler.showView(LoginSystemViewHandler.ViewType.FRONT);
  }
}
