package client.view.patientJournal;

import client.viewModel.patientJournal.PatientDiagnosisViewModel;
import client.viewModel.patientJournal.PatientVaccinationViewModel;
import client.viewModel.patients.AddVaccinationViewModel;
import client.viewModel.patients.PatientsViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class PatientJournalGUI extends Application
{
  private PatientDiagnosisViewModel viewModel;
  private PatientsViewModel patientsViewModel;
  AddVaccinationViewModel addVaccinationViewModel = new AddVaccinationViewModel();
  PatientVaccinationViewModel patientVaccinationViewModel = new PatientVaccinationViewModel();

  @Override public void start(Stage primaryStage) throws Exception
  {
    viewModel = new PatientDiagnosisViewModel();
    patientsViewModel = new PatientsViewModel();
    PatientJournalViewHandler viewHandler = new PatientJournalViewHandler(
        primaryStage, viewModel, patientsViewModel, addVaccinationViewModel,patientVaccinationViewModel);
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args)
  {
    launch();
  }
}
