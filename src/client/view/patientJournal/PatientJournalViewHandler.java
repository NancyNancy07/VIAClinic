package client.view.patientJournal;

import client.view.patientJournal.diagnosis.DiagnosisController;
import client.view.patientJournal.front.PatientJournalController;
import client.view.patientJournal.prescription.PrescriptionController;
import client.view.patientJournal.referral.ReferralController;
import client.viewModel.patientJournal.PatientDiagnosisViewModel;
import client.viewModel.managePatients.PatientsViewModel;
import client.viewModel.patientJournal.PatientJournalViewModelFactory;
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
    FRONT, DIAGNOSIS, REFERRAL, TIME, PRESCRIPTION, CONFIRMATION
  }

  private static Stage stage;
  private static PatientJournalViewModelFactory factory;
  private static PatientDiagnosisViewModel viewModel;
  private static PatientsViewModel patientsViewModel;

  public PatientJournalViewHandler(Stage stage,
      PatientJournalViewModelFactory factory)
  {
    PatientJournalViewHandler.stage = stage;
    this.factory = factory;
    PatientJournalViewHandler.viewModel = viewModel;
    PatientJournalViewHandler.patientsViewModel = patientsViewModel;
  }

  public static void showView(ViewType view)
  {
    try
    {
      switch (view)
      {
        case FRONT -> showFrontView();
        case DIAGNOSIS -> showDiagnosisView();
        case PRESCRIPTION -> showPrescriptionView();
        case REFERRAL -> showReferralView();
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
    controller.init(patientsViewModel);
    stage.setTitle("My Journal");
    stage.setScene(scene);
  }

  private static void showDiagnosisView() throws IOException
  {
    DiagnosisController controller = new DiagnosisController();
    FXMLLoader fxmlLoader = new FXMLLoader(
        PatientJournalViewHandler.class.getResource(
            "./diagnosis/diagnosis.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getPatientDiagnosisViewModel());

    stage.setTitle("View Diagnosis");
    stage.setScene(scene);
  }

  private static void showPrescriptionView() throws IOException
  {
    PrescriptionController controller = new PrescriptionController();
    FXMLLoader fxmlLoader = new FXMLLoader(
        PatientJournalViewHandler.class.getResource(
            "./prescription/prescription.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(viewModel);

    stage.setTitle("View Prescription");
    stage.setScene(scene);
  }

  private static void showReferralView() throws IOException
  {
    ReferralController controller = new ReferralController();
    FXMLLoader fxmlLoader = new FXMLLoader(
        PatientJournalViewHandler.class.getResource(
            "./referral/referral.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getPatientReferralViewModel());

    stage.setTitle("View Referral");
    stage.setScene(scene);
  }

}
