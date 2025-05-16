package client.viewModel.patients;

import client.clientNetwork.DiagnosisListener;
import client.clientNetwork.PatientClient;
import client.viewModel.patientJournal.PatientJournalSharedData;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Diagnosis;

public class AddDiagnosisViewModel implements DiagnosisListener
{
  private ObservableList<Diagnosis> diagnoses = FXCollections.observableArrayList();

  private final PatientsSharedData patientsSharedData = PatientsSharedData.getInstance();
  private final PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  private final PatientClient patientClient;

  private final StringProperty patientName = new SimpleStringProperty();

  public AddDiagnosisViewModel()
  {
    patientClient = new PatientClient();
    patientName.set(patientsSharedData.getPatientName());
  }

  public void addDiagnosis(String diagnosisName, String status,
      NewDateTime date, String prescription)
  {
    Diagnosis diagnosis = new Diagnosis(diagnosisName, status, date,
        patientsSharedData.getDoctorId(), patientsSharedData.getPatientId(),
        prescription);
    diagnoses.add(diagnosis);
    patientClient.sendAddDiagnosis(diagnosis);
    patientsSharedData.setDiagnosis(diagnosisName, status, date, prescription);
    patientJournalSharedData.setPatientId(patientsSharedData.getPatientId());
  }

  public ObservableList<Diagnosis> getDiagnoses()
  {
    return diagnoses;
  }

  public String getDiagnosis()
  {
    return patientsSharedData.getDiagnosis();
  }

  public void setPatientName(String name)
  {
    patientsSharedData.setPatientName(name);
  }

  public String getPatientName()
  {
    return patientsSharedData.getPatientName();
  }

  @Override public void onDiagnosisAdded(boolean success, String message)
  {
    System.out.println("Diagnosis result: " + message);
  }
}
