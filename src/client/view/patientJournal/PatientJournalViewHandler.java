package client.view.patientJournal;

import client.view.patientJournal.diagnosis.DiagnosisController;
import client.view.patientJournal.front.PatientJournalController;
import client.view.patientJournal.labResult.LabResultController;
import client.view.patientJournal.prescription.PrescriptionController;
import client.view.patientJournal.referral.ReferralController;
import client.view.patientJournal.vaccination.VaccinationController;
import client.viewModel.managePatients.AddVaccinationViewModel;
import client.viewModel.managePatients.PatientsViewModel;
import client.viewModel.patientJournal.PatientJournalViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * PatientJournalViewHandler is responsible for managing the different views in the
 * patient journal system. It uses a factory to get the appropriate ViewModel for
 * each view and handles the transitions between them.
 */
public class PatientJournalViewHandler
{
  /**
   * Starts the application by showing the front view.
   *
   * @param s The primary stage for the application.
   */
  public static void start(Stage s)
  {
    stage = s;
    showView(ViewType.FRONT);
    stage.show();
  }

  /**
   * Enum representing the different types of views in the patient journal system.
   */
  public enum ViewType
  {
    FRONT, DIAGNOSIS, REFERRAL, PRESCRIPTION, VACCINATION,LABRESULT
  }

  private static Stage stage;
  private static PatientJournalViewModelFactory factory;

  /**
   * Constructor for PatientJournalViewHandler.
   *
   * @param stage   The primary stage for the application.
   * @param factory The factory to create ViewModels for the different views.
   */
  public PatientJournalViewHandler(Stage stage,
      PatientJournalViewModelFactory factory)

  {
    PatientJournalViewHandler.stage = stage;
    PatientJournalViewHandler.factory = factory;
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
        case DIAGNOSIS -> showDiagnosisView();
        case PRESCRIPTION -> showPrescriptionView();
        case LABRESULT -> showLabResultView();
        //        case MODE -> showModeView();
        //        case TIME -> showTimeView();
        //        case CONFIRMATION -> showConfirmationView();

        case REFERRAL -> showReferralView();
        case VACCINATION -> showVaccinationView();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Displays the front view of the patient journal.
   *
   * @throws IOException If there is an error loading the FXML file.
   */
  private static void showFrontView() throws IOException
  {
    PatientJournalController controller = new PatientJournalController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        PatientJournalViewHandler.class.getResource(
            "./front/patientJournal.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getPatientsViewModel());
    stage.setTitle("My Journal");
    stage.setScene(scene);
  }

  /**
   * Displays the diagnosis view.
   *
   * @throws IOException If there is an error loading the FXML file.
   */
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

  /**
   * Displays the prescription view.
   *
   * @throws IOException If there is an error loading the FXML file.
   */
  private static void showPrescriptionView() throws IOException
  {
    PrescriptionController controller = new PrescriptionController();
    FXMLLoader fxmlLoader = new FXMLLoader(
        PatientJournalViewHandler.class.getResource(
            "./prescription/prescription.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getPatientDiagnosisViewModel());

    stage.setTitle("View Prescription");
    stage.setScene(scene);
  }

  /**
   * Displays the lab result view.
   *
   * @throws IOException If there is an error loading the FXML file.
   */
  private static void showLabResultView() throws IOException
  {
    LabResultController controller = new LabResultController();
    FXMLLoader fxmlLoader = new FXMLLoader(
        PatientJournalViewHandler.class.getResource(
            "./labResult/labResult.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getPatientLabResultsViewModel());

    stage.setTitle("View LabResult");
    stage.setScene(scene);
  }


  /**
   * Displays the referral view.
   *
   * @throws IOException If there is an error loading the FXML file.
   */
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

  /**
   * Displays the vaccination view.
   * @throws IOException If there is an error loading the FXML file.
   */
  public static void showVaccinationView() throws IOException
  {
    VaccinationController controller = new VaccinationController();
    FXMLLoader fxmlLoader = new FXMLLoader(
        PatientJournalViewHandler.class.getResource(
            "./vaccination/vaccination.fxml"));
    fxmlLoader.setControllerFactory(ignore -> controller);

    try
    {
      Scene scene = new Scene(fxmlLoader.load());
      controller.init(factory.getPatientVaccinationViewModel());
      stage.setTitle("View Vaccination");
      stage.setScene(scene);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Returns the AddVaccinationViewModel for managing patients.
   *
   * @return AddVaccinationViewModel instance.
   */
  public AddVaccinationViewModel getAddVaccinationViewModel()
  {
    return new AddVaccinationViewModel();
  }

}
