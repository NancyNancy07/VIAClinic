package client.view.loginSystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.view.loginSystem.doctorView.DoctorViewController;
import client.view.loginSystem.loginAsDoctor.DoctorLoginViewController;
import client.view.loginSystem.loginAsPatient.PatientLoginViewController;
import client.view.loginSystem.loginFront.LoginFrontViewContoller;
import client.view.loginSystem.patientView.PatientViewController;
import client.viewModel.loginSystem.ViewModelFactory;

import java.io.IOException;

/**
 * LoginSystemViewHandler is responsible for managing the different views in the
 * login system. It uses a factory to get the appropriate ViewModel for each view
 * and handles the transitions between them.
 */
public class LoginSystemViewHandler
{

  /**
   * Starts the application by showing the front view.
   * @param s The primary stage for the application.
   */
  public static void start(Stage s)
  {
    stage = s;
    showView(ViewType.FRONT);
    stage.show();
  }

  /**
   * Enum representing the different types of views in the login system.
   */
  public enum ViewType
  {
    FRONT, DOCTORLOGIN, PATIENTLOGIN, DOCTORVIEW, PATIENTVIEW
  }

  private static Stage stage;
  private static ViewModelFactory viewModelFactory;

  /**
   * Constructor for LoginSystemViewHandler.
   *
   * @param stage   The primary stage for the application.
   * @param factory The factory to create ViewModels for the different views.
   */
  public LoginSystemViewHandler(Stage stage, ViewModelFactory factory)
  {
    LoginSystemViewHandler.stage = stage;
    this.viewModelFactory = factory;
  }

  /**
   * Shows the specified view based on the ViewType.
   *
   * @param view The type of view to show.
   */
  public static void showView(ViewType view)
  {
    try
    {
      switch (view)
      {
        case FRONT -> showFrontView();
        case DOCTORLOGIN -> showDoctorLoginView();
        case PATIENTLOGIN -> showPatientLoginView();
        case DOCTORVIEW -> showDoctorView();
        case PATIENTVIEW -> showPatientView();

      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Displays the front view of the login system.
   * This method initializes the LoginFrontViewController and sets the scene.
   *
   * @throws IOException If an error occurs while loading the FXML file.
   */
  private static void showFrontView() throws IOException
  {
    LoginFrontViewContoller controller = new LoginFrontViewContoller();

    FXMLLoader fxmlLoader = new FXMLLoader(
        LoginSystemViewHandler.class.getResource(
            "./loginFront/loginFront.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    //    controller.init(client.viewModel, sharedData);
    stage.setTitle("Login for VIAClinic");
    stage.setScene(scene);
  }

  /**
   * Displays the doctor login view.
   * This method initializes the DoctorLoginViewController and sets the scene.
   *
   * @throws IOException If an error occurs while loading the FXML file.
   */
  private static void showDoctorLoginView() throws IOException
  {
    DoctorLoginViewController controller = new DoctorLoginViewController();
    FXMLLoader fxmlLoader = new FXMLLoader(
        LoginSystemViewHandler.class.getResource(
            "./loginAsDoctor/doctorLogin.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(viewModelFactory.getLoginViewModel());

    stage.setTitle("Doctor Login");
    stage.setScene(scene);
  }

  /**
   * Displays the patient login view.
   * This method initializes the PatientLoginViewController and sets the scene.
   *
   * @throws IOException If an error occurs while loading the FXML file.
   */
  private static void showPatientLoginView() throws IOException
  {
    PatientLoginViewController controller = new PatientLoginViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        LoginSystemViewHandler.class.getResource(
            "./loginAsPatient/patientLogin.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(viewModelFactory.getLoginViewModel());

    stage.setTitle("Patient Login");
    stage.setScene(scene);
  }

  /**
   * Displays the doctor's view.
   * This method initializes the DoctorViewController and sets the scene.
   *
   * @throws IOException If an error occurs while loading the FXML file.
   */
  private static void showDoctorView() throws IOException
  {
    DoctorViewController controller = new DoctorViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        LoginSystemViewHandler.class.getResource(
            "./doctorView/doctorView.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
   controller.init(viewModelFactory.getLoginViewModel());

    stage.setTitle("Doctor View");
    stage.setScene(scene);
  }

  /**
   * Displays the patient's view.
   * This method initializes the PatientViewController and sets the scene.
   *
   * @throws IOException If an error occurs while loading the FXML file.
   */
  private static void showPatientView() throws IOException
  {
    PatientViewController controller = new PatientViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        LoginSystemViewHandler.class.getResource(
            "./patientView/patientView.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(viewModelFactory.getLoginViewModel());

    stage.setTitle("Patient View");
    stage.setScene(scene);
  }
}
