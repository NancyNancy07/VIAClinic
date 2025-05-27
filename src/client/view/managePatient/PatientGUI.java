package client.view.managePatient;

import client.viewModel.managePatients.ManagePatientViewModelFactory;
import client.viewModel.managePatients.PatientsViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * PatientGUI is the main class for the Manage Patient GUI application.
 * It initializes the view model and starts the application.
 */
public class PatientGUI extends Application
{
  /**
   * Initializes the PatientGUI application.
   *
   * @param primaryStage the primary stage for this application
   * @throws Exception if an error occurs during initialization
 */
  @Override public void start(Stage primaryStage) throws Exception
  {
    PatientsViewModel viewModel = new PatientsViewModel();
    ManagePatientViewModelFactory factory = new ManagePatientViewModelFactory();
    ManagePatientViewHandler viewHandler = new ManagePatientViewHandler(
        primaryStage, factory);
    viewHandler.start(primaryStage, factory);
  }

  /**
   * The main method to launch the Manage Patient GUI application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args)
  {
    launch();
  }
}
