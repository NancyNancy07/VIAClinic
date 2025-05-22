package client.view.patientJournal;

import client.viewModel.patientJournal.PatientDiagnosisViewModel;
import client.viewModel.managePatients.PatientsViewModel;
import client.viewModel.patientJournal.PatientJournalViewModelFactory;
import client.viewModel.patientJournal.PatientVaccinationViewModel;
import client.viewModel.patients.AddVaccinationViewModel;
import client.viewModel.patients.PatientsViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class PatientJournalGUI extends Application
{
  private PatientJournalViewModelFactory factory;
  private PatientDiagnosisViewModel viewModel;
  private PatientsViewModel patientsViewModel;
  AddVaccinationViewModel addVaccinationViewModel = new AddVaccinationViewModel();
  PatientVaccinationViewModel patientVaccinationViewModel = new PatientVaccinationViewModel();

  @Override public void start(Stage primaryStage) throws Exception
  {
    factory = new PatientJournalViewModelFactory();
    PatientJournalViewHandler viewHandler = new PatientJournalViewHandler(
        primaryStage, factory);
        primaryStage, viewModel, patientsViewModel, addVaccinationViewModel,patientVaccinationViewModel);
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args)
  {
    launch();
  }
}
