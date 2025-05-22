package client.view.patientJournal;

import client.view.patientJournal.diagnosis.DiagnosisController;
import client.view.patientJournal.front.PatientJournalController;
import client.view.patientJournal.prescription.PrescriptionController;
import client.view.patientJournal.vaccination.VaccinationController;
import client.viewModel.patientJournal.PatientDiagnosisViewModel;
import client.viewModel.patientJournal.PatientVaccinationViewModel;
import client.viewModel.patients.AddVaccinationViewModel;
import client.viewModel.patients.PatientsViewModel;
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
    FRONT, DIAGNOSIS, MODE, TIME, PRESCRIPTION, CONFIRMATION, VACCINATION
  }

  private static Stage stage;
  private static PatientDiagnosisViewModel viewModel;
  private static PatientsViewModel patientsViewModel;
  private static AddVaccinationViewModel addVaccinationViewModel;
  private static PatientVaccinationViewModel patientVaccinationViewModel;

  public PatientJournalViewHandler(Stage stage,
      PatientDiagnosisViewModel viewModel, PatientsViewModel patientsViewModel,
      AddVaccinationViewModel vaccinationViewModel, PatientVaccinationViewModel patientVaccinationViewModel)
  {
    PatientJournalViewHandler.stage = stage;
    PatientJournalViewHandler.viewModel = viewModel;
    PatientJournalViewHandler.patientsViewModel = patientsViewModel;
    PatientJournalViewHandler.addVaccinationViewModel = vaccinationViewModel;
    PatientJournalViewHandler.patientVaccinationViewModel = patientVaccinationViewModel;
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
    controller.init(viewModel);

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

  public static void showVaccinationView() throws IOException
  {
    VaccinationController controller = new VaccinationController();
    FXMLLoader fxmlLoader = new FXMLLoader(
        PatientJournalViewHandler.class.getResource("./vaccination/vaccination.fxml"));
    fxmlLoader.setControllerFactory(ignore -> controller);

    try
    {
      Scene scene = new Scene(fxmlLoader.load());
      controller.init(patientVaccinationViewModel);
      stage.setTitle("View Vaccination");
      stage.setScene(scene);
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public AddVaccinationViewModel getAddVaccinationViewModel()
  {
    return new AddVaccinationViewModel();
  }

}
