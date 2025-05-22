package client.view.managePatient;

import client.view.managePatient.addDiagnosis.AddDiagnosisController;
import client.view.managePatient.addPrescription.AddPrescriptionController;
import client.view.managePatient.addVaccination.AddVaccinationController;
import client.view.managePatient.viewPatients.ViewPatientsController;
import client.view.patientJournal.vaccination.VaccinationController;
import client.viewModel.patients.AddVaccinationViewModel;
import client.viewModel.patients.PatientJournalViewModelFactory;
import client.viewModel.patients.PatientsViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static client.view.patientJournal.PatientJournalViewHandler.showVaccinationView;

public class ManagePatientViewHandler
{
  public static void start(Stage s, PatientJournalViewModelFactory factory)
  {
    stage = s;
    ManagePatientViewHandler.factory = factory;
    showView(ViewType.FRONT);
    stage.show();
  }

  public enum ViewType
  {
    FRONT, DIAGNOSIS, PRESCRIPTION, VACCINATION, MODE, TIME, CONFIRMATION
  }

  private static Stage stage;
  private static PatientsViewModel viewModel;
  private static PatientJournalViewModelFactory factory;


  public ManagePatientViewHandler(Stage stage,
      PatientJournalViewModelFactory factory)
  {
    ManagePatientViewHandler.stage = stage;
    ManagePatientViewHandler.viewModel = viewModel;
    ManagePatientViewHandler.factory = factory;
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
        case VACCINATION -> showVaccinationView();
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

  private static void showPrescriptionView() throws IOException
  {
    AddPrescriptionController controller = new AddPrescriptionController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        ManagePatientViewHandler.class.getResource(
            "./addPrescription/addPrescription.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getAddPrescriptionViewModel());
    stage.setTitle("Patients Data");
    stage.setScene(scene);
  }

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




  //  private static void showModeView() throws IOException
  //  {
  //    ModeViewController controller = new ModeViewController();
  //
  //    FXMLLoader fxmlLoader = new FXMLLoader(
  //        ManagePatientViewHandler.class.getResource(
  //            "./modeofconsultation/mode.fxml"));
  //
  //    fxmlLoader.setControllerFactory(ignore -> controller);
  //
  //    Scene scene = new Scene(fxmlLoader.load());
  //    controller.init(viewModel, sharedData);
  //
  //    stage.setTitle("Select Mode of Consultation");
  //    stage.setScene(scene);
  //  }
  //
  //  private static void showTimeView() throws IOException
  //  {
  //    TimeViewController controller = new TimeViewController();
  //
  //    FXMLLoader fxmlLoader = new FXMLLoader(
  //        ManagePatientViewHandler.class.getResource(
  //            "./dateandtime/time.fxml"));
  //
  //    fxmlLoader.setControllerFactory(ignore -> controller);
  //
  //    Scene scene = new Scene(fxmlLoader.load());
  //    controller.init(viewModel, sharedData);
  //
  //    stage.setTitle("Select Date and Time");
  //    stage.setScene(scene);
  //  }
  //
  //  private static void showConfirmationView() throws IOException
  //  {
  //    ConfirmationViewController controller = new ConfirmationViewController();
  //
  //    FXMLLoader fxmlLoader = new FXMLLoader(
  //        ManagePatientViewHandler.class.getResource(
  //            "./confirmation/confirmation.fxml"));
  //
  //    fxmlLoader.setControllerFactory(ignore -> controller);
  //
  //    Scene scene = new Scene(fxmlLoader.load());
  //    controller.init(viewModel, sharedData);
  //
  //    stage.setTitle("Confirmation");
  //    stage.setScene(scene);
  //  }
}

