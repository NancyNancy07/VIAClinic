package client.view.loginSystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.view.loginSystem.doctorView.DoctorViewController;
import client.view.loginSystem.loginAsDoctor.DoctorLoginViewController;
import client.view.loginSystem.loginAsPatient.PatientLoginViewController;
import client.view.loginSystem.loginFront.LoginFrontViewContoller;
import client.view.loginSystem.patientView.PatientViewController;
import client.viewModel.loginSystem.AppointmentViewModel;
import client.viewModel.loginSystem.LoginViewModel;
import client.viewModel.loginSystem.ViewModelFactory;

import java.io.IOException;

public class LoginSystemViewHandler
{

  public static void start(Stage s)
  {
    stage = s;
    showView(ViewType.FRONT);
    stage.show();
  }

  public enum ViewType
  {
    FRONT, DOCTORLOGIN, PATIENTLOGIN, DOCTORVIEW, PATIENTVIEW
  }

  private static Stage stage;
  private static ViewModelFactory viewModelFactory;

  public LoginSystemViewHandler(Stage stage, ViewModelFactory factory)
  {
    LoginSystemViewHandler.stage = stage;
    this.viewModelFactory = factory;
  }

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

  private static void showDoctorView() throws IOException
  {
    DoctorViewController controller = new DoctorViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        LoginSystemViewHandler.class.getResource(
            "./doctorView/doctorView.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
//    controller.init(viewModelFactory.getAppointmentViewModel());

    stage.setTitle("Doctor View");
    stage.setScene(scene);
  }

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
