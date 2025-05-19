package client.viewModel.patients;

import client.clientNetwork.DiagnosisListener;
import client.clientNetwork.PatientClient;
import client.viewModel.patientJournal.PatientJournalSharedData;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.PrescriptionDAO;

import java.sql.SQLException;
import java.util.List;

public class AddDiagnosisViewModel implements DiagnosisListener
{
  private ObservableList<Diagnosis> diagnosisList = FXCollections.observableArrayList();

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
      NewDateTime date, Prescription prescription)
  {
    Diagnosis diagnosis = new Diagnosis(diagnosisName, status, date,
        patientsSharedData.getDoctorId(), patientsSharedData.getPatientId(),
        prescription);
    diagnosisList.add(diagnosis);
    patientClient.sendAddDiagnosis(diagnosis);
    patientsSharedData.setDiagnosis(diagnosisName, status, date, prescription);
    patientJournalSharedData.setPatientId(patientsSharedData.getPatientId());
  }

  public ObservableList<Diagnosis> getDiagnoses(int patientId)
  {
    List<Diagnosis> diagnoses = patientClient.getPatientDiagnosis(patientId);
    if (diagnoses == null || diagnoses.isEmpty())
    {
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Diagnoses");
        alert.setHeaderText(null);
        alert.setContentText("No diagnoses found for this patient.");
        alert.showAndWait();
      });
    }
    else
    {
      diagnosisList.setAll(diagnoses);
    }

    return diagnosisList;
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

  public int getPatientId()
  {
    return patientsSharedData.getPatientId();
  }

  public ObservableList<Prescription> getAllPrescriptions(int id)
  {
    try
    {
      List<Prescription> prescriptions = PrescriptionDAO.getInstance()
          .getPrescriptionsByPatientId(id);
      return FXCollections.observableArrayList(prescriptions);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return FXCollections.observableArrayList();
    }
  }

  @Override public void onDiagnosisAdded(boolean success, String message)
  {
    System.out.println("Diagnosis result: " + message);
  }
}
