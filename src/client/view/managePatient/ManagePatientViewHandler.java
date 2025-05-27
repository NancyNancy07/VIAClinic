package client.view.managePatient;

import client.view.managePatient.addDiagnosis.AddDiagnosisController;
import client.view.managePatient.addLabResult.AddLabResultController;
import client.view.managePatient.addPrescription.AddPrescriptionController;
import client.view.managePatient.addReferral.AddReferralController;
import client.view.managePatient.addVaccination.AddVaccinationController;
import client.view.managePatient.viewPatients.ViewPatientsController;
import client.viewModel.managePatients.ManagePatientViewModelFactory;
import client.viewModel.managePatients.PatientsViewModel;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * ManagePatientViewHandler is responsible for managing the different views related
 * to patient management. It uses a factory to get the appropriate ViewModel for
 * each view and handles the transitions between them.
 */
public class ManagePatientViewHandler
{
  /**
   * Starts the application by showing the front view.
   *
   * @param s       The primary stage for the application.
   * @param factory The factory to create ViewModels for the different views.
   */
  public static void start(Stage s, ManagePatientViewModelFactory factory)
  {
    stage = s;
    ManagePatientViewHandler.factory = factory;
    showView(ViewType.FRONT);
    stage.show();
  }

  /**
   * Enum representing the different types of views in the patient management system.
   */
  public enum ViewType
  {

    FRONT, DIAGNOSIS, PRESCRIPTION, REFERRAL, VACCINATION, LABRESULT, CONFIRMATION
  }

  private static Stage stage;
  private static PatientsViewModel viewModel;
  private static ManagePatientViewModelFactory factory;


  /**
   * Constructor for ManagePatientViewHandler.
   *
   * @param stage   The primary stage for the application.
   * @param factory The factory to create ViewModels for the different views.
   */
  public ManagePatientViewHandler(Stage stage,
      ManagePatientViewModelFactory factory)
  {
    ManagePatientViewHandler.stage = stage;
    ManagePatientViewHandler.viewModel = viewModel;
    ManagePatientViewHandler.factory = factory;
  }

  /**
   * Displays the specified view based on the ViewType.
   *
   * @param view The type of view to display.
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
   * Displays the front view of the patient management system.
   *
   * @throws IOException If an error occurs while loading the FXML file.
   */
  private static void showFrontView() throws IOException
  {
    ViewPatientsController controller = new ViewPatientsController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        ManagePatientViewHandler.class.getResource(
            "./viewPatients/ViewPatients.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getPatientsViewModel());
    stage.setTitle("Patients Data");
    stage.setScene(scene);
  }

  /**
   * Displays the prescription view.
   *
   * @throws IOException If an error occurs while loading the FXML file.
   */
  private static void showPrescriptionView() throws IOException
  {
    AddPrescriptionController controller = new AddPrescriptionController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        ManagePatientViewHandler.class.getResource(
            "./addPrescription/addPrescription.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getAddPrescriptionViewModel());
    stage.setTitle("View Prescription");
    stage.setScene(scene);
  }

  /**
   * Displays the diagnosis view.
   *
   * @throws IOException If an error occurs while loading the FXML file.
   */
  private static void showDiagnosisView() throws IOException
  {
    AddDiagnosisController controller = new AddDiagnosisController();
    FXMLLoader fxmlLoader = new FXMLLoader(
        ManagePatientViewHandler.class.getResource(
            "./addDiagnosis/addDiagnosis.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getAddDiagnosisViewModel());

    stage.setTitle("View Diagnosis");
    stage.setScene(scene);
  }

  /**
   * Displays the lab result view.
   *
   * @throws IOException If an error occurs while loading the FXML file.
   */
  private static void showLabResultView() throws IOException
  {
    AddLabResultController controller = new AddLabResultController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        ManagePatientViewHandler.class.getResource(
            "./addLabResult/addLabResult.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getAddLabResultViewModel());
    stage.setTitle(" View labResult");
    stage.setScene(scene);
  }


  /**
   * Displays the vaccination view.
   *
   * @throws IOException If an error occurs while loading the FXML file.
   */
  private static void showVaccinationView() throws IOException
  {
    AddVaccinationController controller = new AddVaccinationController();
    FXMLLoader fxmlLoader = new FXMLLoader(
        ManagePatientViewHandler.class.getResource("./addVaccination/addVaccination.fxml")
    );
    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getAddVaccinationViewModel());
    stage.setTitle("Patient Vaccination");
    stage.setScene(scene);
  }


  /**
   * Displays the referral view.
   *
   * @throws IOException If an error occurs while loading the FXML file.
   */
  private static void showReferralView() throws IOException
  {
    AddReferralController controller = new AddReferralController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        ManagePatientViewHandler.class.getResource(
            "./addReferral/addReferral.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getAddReferralViewModel());
    stage.setTitle("View Referral");
    stage.setScene(scene);
  }

}

