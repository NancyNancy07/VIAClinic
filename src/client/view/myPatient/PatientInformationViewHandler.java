package client.view.myPatient;

import client.view.myPatient.myInformation.MyInformationController;
import client.viewModel.loginSystem.ViewModelFactory;
import client.viewModel.myPatient.PatientInformationViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientInformationViewHandler
{
  private static Stage stage;
  private static PatientInformationViewModelFactory viewModelFactory;

  public PatientInformationViewHandler(Stage stage, PatientInformationViewModelFactory factory)
  {
    PatientInformationViewHandler.stage = stage;
    PatientInformationViewHandler.viewModelFactory = factory;
  }

  public static void start(Stage s)
  {
    stage = s;
    showView(ViewType.PATIENT_INFORMATION);
    stage.show();
  }

  public enum ViewType
  {
    PATIENT_INFORMATION
  }

  public static void showView(ViewType view)
  {
    try
    {
      switch (view)
      {
        case PATIENT_INFORMATION -> showPatientInformationView();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  private static void showPatientInformationView() throws IOException
  {
    MyInformationController controller = new MyInformationController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        PatientInformationViewHandler.class.getResource(
            "./myInformation/myInformation.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(viewModelFactory.getPatientInformationViewModel());

    stage.setTitle("Patient Information");
    stage.setScene(scene);
  }
}
