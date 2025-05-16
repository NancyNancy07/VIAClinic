package client.viewModel.patientJournal;

import client.clientNetwork.PatientClient;
import client.viewModel.loginSystem.LoginSharedData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.patientJournal.Diagnosis;

import java.util.List;

public class PatientDiagnosisViewModel
{
  private final PatientClient patientClient;
  private final ObservableList<Diagnosis> diagnosisList;
  private PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  public PatientDiagnosisViewModel()
  {
    this.patientClient = new PatientClient();
    this.diagnosisList = FXCollections.observableArrayList();
  }

  public ObservableList<Diagnosis> getDiagnosisList(int patientId)
  {
    List<Diagnosis> diagnoses = patientClient.getPatientDiagnosis(patientId);
    diagnosisList.setAll(diagnoses);
    return diagnosisList;
  }

  public void loadDiagnosesForPatient(int patientId)
  {

  }

  public int getPatientId()
  {
    return LoginSharedData.getInstance().getId();

  }
}
