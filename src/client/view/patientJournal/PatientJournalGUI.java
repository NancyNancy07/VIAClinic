package client.view.patientJournal;

import client.viewModel.patientJournal.PatientDiagnosisViewModel;
import client.viewModel.managePatients.PatientsViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class PatientJournalGUI extends Application
{
  private PatientDiagnosisViewModel viewModel;
  private PatientsViewModel patientsViewModel;

  @Override public void start(Stage primaryStage) throws Exception
  {
    viewModel = new PatientDiagnosisViewModel();
    patientsViewModel = new PatientsViewModel();
    PatientJournalViewHandler viewHandler = new PatientJournalViewHandler(
        primaryStage, viewModel, patientsViewModel);
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args)
  {
    launch();
  }
}
