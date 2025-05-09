package client.view.loginSystem.loginAsDoctor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import client.view.loginSystem.LoginSystemViewHandler;
import client.viewModel.loginSystem.LoginViewModel;

public class DoctorLoginViewController
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
            LoginSystemViewHandler.ViewType.DOCTORVIEW);
      }
    });
  }

  public void login()
  {
    loginViewModel.loginDoctor();
  }

  public void back()
  {
    LoginSystemViewHandler.showView(LoginSystemViewHandler.ViewType.FRONT);
  }
}
