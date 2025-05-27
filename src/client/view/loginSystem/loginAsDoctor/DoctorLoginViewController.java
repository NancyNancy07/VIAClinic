package client.view.loginSystem.loginAsDoctor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import client.view.loginSystem.LoginSystemViewHandler;
import client.viewModel.loginSystem.LoginViewModel;

/**
 * Controller for the doctor login view in the login system.
 * This controller handles user interactions for logging in as a doctor.
 */
public class DoctorLoginViewController
{
  @FXML private TextField username;
  @FXML private PasswordField password;
  @FXML private Button loginBtn;

  private LoginViewModel loginViewModel;

  /**
   * Initializes the DoctorLoginViewController with the provided LoginViewModel.
   * Binds the username and password fields to the view model properties.
   *
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
            LoginSystemViewHandler.ViewType.DOCTORVIEW);
      }
    });
  }

  /**
   * Handles the login action for the doctor.
   * Calls the login method in the view model to perform the login operation.
   */
  public void login()
  {
    loginViewModel.loginDoctor();
  }

  /**
   * Handles the back action to return to the front view of the login system.
   * Navigates back to the front view using the LoginSystemViewHandler.
   */
  public void back()
  {
    LoginSystemViewHandler.showView(LoginSystemViewHandler.ViewType.FRONT);
  }
}
