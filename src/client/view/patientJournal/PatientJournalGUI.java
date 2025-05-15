package client.view.patientJournal;

import client.viewModel.patientsJournal.PatientsViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class PatientJournalGUI extends Application
{
  private PatientsViewModel viewModel;

  @Override public void start(Stage primaryStage) throws Exception
  {
    viewModel = new PatientsViewModel();

    PatientJournalViewHandler viewHandler = new PatientJournalViewHandler(
        primaryStage, viewModel);
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args)
  {
    launch();
  }
}
