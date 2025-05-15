package client.view.patientJournal;

import client.view.patientJournal.front.PatientJournalController;
import client.viewModel.patientsJournal.PatientsViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientJournalViewHandler
{
  public static void start(Stage s)
  {
    stage = s;
    showView(ViewType.FRONT);
    stage.show();
  }

  public enum ViewType
  {
    FRONT, DIAGNOSIS, MODE, TIME, CONFIRMATION
  }

  private static Stage stage;
  private static PatientsViewModel viewModel;

  public PatientJournalViewHandler(Stage stage, PatientsViewModel viewModel)
  {
    PatientJournalViewHandler.stage = stage;
    PatientJournalViewHandler.viewModel = viewModel;
  }

  public static void showView(ViewType view)
  {
    try
    {
      switch (view)
      {
        case FRONT -> showFrontView();
        case DIAGNOSIS -> showDiagnosisView();
        //        case MODE -> showModeView();
        //        case TIME -> showTimeView();
        //        case CONFIRMATION -> showConfirmationView();

      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private static void showFrontView() throws IOException
  {
    PatientJournalController controller = new PatientJournalController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        PatientJournalViewHandler.class.getResource(
            "./front/patientJournal.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(viewModel);
    stage.setTitle("My Journal");
    stage.setScene(scene);
  }

  private static void showDiagnosisView() throws IOException
  {
//    AddDiagnosisController controller = new AddDiagnosisController();
//    FXMLLoader fxmlLoader = new FXMLLoader(
//        ManagePatientViewHandler.class.getResource(
//            "./addDiagnosis/addDiagnosis.fxml"));
//
//    fxmlLoader.setControllerFactory(ignore -> controller);
//
//    Scene scene = new Scene(fxmlLoader.load());
//    controller.init(viewModel);
//
//    stage.setTitle("View Diagnosis");
//    stage.setScene(scene);
  }

}
