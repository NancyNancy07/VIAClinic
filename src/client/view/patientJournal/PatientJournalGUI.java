package client.view.patientJournal;

import client.viewModel.patientJournal.PatientDiagnosisViewModel;
import client.viewModel.managePatients.PatientsViewModel;
import client.viewModel.patientJournal.PatientJournalViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class PatientJournalGUI extends Application
{
  private PatientJournalViewModelFactory factory;

  @Override public void start(Stage primaryStage) throws Exception
  {
    factory = new PatientJournalViewModelFactory();
    PatientJournalViewHandler viewHandler = new PatientJournalViewHandler(
        primaryStage, factory);
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args)
  {
    launch();
  }
}
