package client.view.patientJournal;

import client.viewModel.patientJournal.PatientJournalViewModelFactory;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * PatientJournalGUI is the main class for the Patient Journal GUI application.
 * It initializes the view model and starts the application.
 */
public class PatientJournalGUI extends Application
{
  private PatientJournalViewModelFactory factory;

  /**
   * Initializes the PatientJournalGUI application.
   *
   * @param primaryStage the primary stage for this application
   * @throws Exception if an error occurs during initialization
   */
  @Override public void start(Stage primaryStage) throws Exception
  {
    factory = new PatientJournalViewModelFactory();
    PatientJournalViewHandler viewHandler = new PatientJournalViewHandler(
        primaryStage, factory);
    viewHandler.start(primaryStage);
  }

  /**
   * The main method to launch the Patient Journal GUI application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args)
  {
    launch();
  }
}
