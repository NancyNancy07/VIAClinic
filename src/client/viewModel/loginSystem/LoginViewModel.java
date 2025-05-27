package client.viewModel.loginSystem;

import client.clientNetwork.LoginClient;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import shared.ResponseObject;

/**
 * LoginViewModel is responsible for handling the login logic for both doctors and patients.
 * It manages the email and password properties, validates input, and communicates with the server.
 */
public class LoginViewModel
{
  private final StringProperty emailProp = new SimpleStringProperty("");
  private final StringProperty pwProp = new SimpleStringProperty("");
  private final BooleanProperty loginBtnDisabledProp = new SimpleBooleanProperty(
      true);
  private final BooleanProperty loginSuccessProp = new SimpleBooleanProperty(
      false);
  private String username;
  private String password;

  /**
   * Constructor for LoginViewModel.
   */
  public LoginViewModel()
  {
    this.emailProp.addListener(this::updateLoginButtonState);
    this.pwProp.addListener(this::updateLoginButtonState);
  }

  /**
   * Logs in a user as a doctor.
   * Calls the loginUserWithType method with "doctor" as the user type.
   */
  public void loginDoctor()
  {
    loginUserWithType("doctor");
  }

  /**
   * Logs in a user as a patient.
   * Calls the loginUserWithType method with "patient" as the user type.
   */
  public void loginPatient()
  {
    loginUserWithType("patient");
  }

  /**
   * Logs in a user with the specified user type.
   * Validates the email and password, communicates with the server, and updates the login state.
   *
   * @param userType the type of user to log in (e.g., "doctor" or "patient")
   */
  private void loginUserWithType(String userType)
  {
    String username = emailProp.get();
    String password = pwProp.get();

    if (username != null && password != null && !username.isEmpty()
        && !password.isEmpty())
    {
      LoginClient client = new LoginClient();
      ResponseObject response = client.login(username, password, userType);

      if (response != null && response.isSuccess())
      {
        LoginSharedData.getInstance()
            .setData(username, password, userType, response.getPatientId());
        loginSuccessProp.set(true);
      }
      else
      {
        String message = response.getMessage();
        if (message.contains("Incorrect password"))
        {
          showAlert("Incorrect password. Please try again.");
        }
        else if (message.contains("not found"))
        {
          showAlert("Email not found. Please check your credentials.");
        }
        else
        {
          showAlert("An unexpected error occurred: " + message);
        }

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

  /**
   * Returns the username of the currently logged-in user.
   * This method retrieves the username from the shared data instance.
   *
   * @return the username of the logged-in user
   */
  public String getLoginUser()
  {
    return LoginSharedData.getInstance().getUsername();
  }

  /**
   * Shows an alert dialog with the specified message.
   *
   * @param message the message to display in the alert dialog
   */
  private void showAlert(String message)
  {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Login Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * Updates the state of the login button based on the email and password properties.
   * The button is disabled if either field is empty.
   *
   * @param observable the observable object that triggered the update
   */
  private void updateLoginButtonState(Observable observable)
  {
    boolean shouldDisabled = ((String) this.emailProp.get()).isEmpty()
        || ((String) this.emailProp.get()).isEmpty()
        || ((String) this.pwProp.get()).isEmpty() || this.pwProp.get() == null;
    this.loginBtnDisabledProp.set(shouldDisabled);
  }

  /**
   * Gets the email property.
   *
   * @return the email property
   */
  public StringProperty emailPropProperty()
  {
    return this.emailProp;
  }

  /**
   * Gets the password property.
   *
   * @return the password property
   */
  public StringProperty pwPropProperty()
  {
    return this.pwProp;
  }

  /**
   * Gets the login button enabled property.
   * This property indicates whether the login button should be enabled or disabled.
   *
   * @return the login button enabled property
   */
  public BooleanProperty loginBtnEnabledProp()
  {
    return this.loginBtnDisabledProp;
  }

  /**
   * Gets the login success property.
   * This property indicates whether the login was successful.
   *
   * @return the login success property
   */
  public BooleanProperty loginSuccessProp()
  {
    return this.loginSuccessProp;
  }

  public void clearCredentials()
  {
    this.emailProp.set("");
    this.pwProp.set("");
    this.loginSuccessProp.set(false);
  }
}
