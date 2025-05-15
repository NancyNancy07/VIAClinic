package client.view.managePatient;

import client.viewModel.patientsJournal.PatientJournalViewModelFactory;
import client.viewModel.patientsJournal.PatientsViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class PatientGUI extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    PatientsViewModel viewModel = new PatientsViewModel();
    PatientJournalViewModelFactory factory = new PatientJournalViewModelFactory();
    ManagePatientViewHandler viewHandler = new ManagePatientViewHandler(
        primaryStage, factory);
    viewHandler.start(primaryStage, factory);
  }

  public static void main(String[] args)
  {
    launch();
  }
}
