package client.viewModel.loginSystem;

import client.loginNetwork.LoginClient;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginViewModel
{
  private final StringProperty emailProp = new SimpleStringProperty("");
  private final StringProperty pwProp = new SimpleStringProperty("");
  private final BooleanProperty loginBtnDisabledProp = new SimpleBooleanProperty(
      true);
  private final BooleanProperty loginSuccessProp = new SimpleBooleanProperty(
      false);

  public LoginViewModel()
  {
    this.emailProp.addListener(this::updateLoginButtonState);
    this.pwProp.addListener(this::updateLoginButtonState);
  }

  public void loginUser()
  {
    String email = emailProp.get();
    String password = pwProp.get();

    if (email != null && password != null && !email.isEmpty()
        && !password.isEmpty())

    {
      LoginClient client = new LoginClient();
      String response = client.login(emailProp.get(), pwProp.get());
      switch (response)
      {
        case "Ok":
          LoginDataStore.getInstance()
              .setDoctorData(emailProp.get(), pwProp.get());
          loginSuccessProp.set(true);
          break;
        case "Incorrect Password":
          showAlert("Incorrect password. Please try again.");
          break;
        case "Email not found":
          showAlert("Email not found. Please check your credentials.");
          break;
        default:
          showAlert("An unexpected error occurred.");
          break;
      }

      // Clear input fields regardless of outcome except for success
      if (!"Ok".equals(response))
      {
        emailProp.set("");
        pwProp.set("");
        loginSuccessProp.set(false);
      }
    }
    else
    {
      System.out.println("Fields should not be empty");
    }
  }

  private void showAlert(String message)
  {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Login Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  private void updateLoginButtonState(Observable observable)
  {
    boolean shouldDisabled = ((String) this.emailProp.get()).isEmpty()
        || ((String) this.emailProp.get()).isEmpty()
        || ((String) this.pwProp.get()).isEmpty() || this.pwProp.get() == null;
    this.loginBtnDisabledProp.set(shouldDisabled);
  }

  public StringProperty emailPropProperty()
  {
    return this.emailProp;
  }

  public StringProperty pwPropProperty()
  {
    return this.pwProp;
  }

  public BooleanProperty loginBtnEnabledProp()
  {
    return this.loginBtnDisabledProp;
  }

  public BooleanProperty loginSuccessProp()
  {
    return this.loginSuccessProp;
  }
}
