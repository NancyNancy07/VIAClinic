package client.view.managePatient;

import client.view.managePatient.addDiagnosis.AddDiagnosisController;
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

public class ManagePatientViewHandler
{
  public static void start(Stage s, ManagePatientViewModelFactory factory)
  {
    stage = s;
    ManagePatientViewHandler.factory = factory;
    showView(ViewType.FRONT);
    stage.show();
  }

  public enum ViewType
  {
    FRONT, DIAGNOSIS, PRESCRIPTION, REFERRAL, VACCINATION, TIME, CONFIRMATION
  }

  private static Stage stage;
  private static PatientsViewModel viewModel;
  private static ManagePatientViewModelFactory factory;


  public ManagePatientViewHandler(Stage stage,
      ManagePatientViewModelFactory factory)
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
        case REFERRAL -> showReferralView();
        case VACCINATION -> showVaccinationView();

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
    stage.setTitle("View Prescription");
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

