package client.view.loginSystem.loginAsPatient;

import client.view.loginSystem.LoginSystemViewHandler;
import client.viewModel.loginSystem.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PatientLoginViewController
{
  @FXML private TextField username;
  @FXML private TextField password;
  @FXML private Button loginBtn;
  private LoginViewModel loginViewModel;

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

  public void login()
  {
    loginViewModel.loginPatient();
  }

  public void back()
  {
    LoginSystemViewHandler.showView(LoginSystemViewHandler.ViewType.FRONT);
  }
}
