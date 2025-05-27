package client.viewModel.managePatients;

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

/**
 * AddDiagnosisViewModel is responsible for managing the addition of diagnoses
 * to a patient's record. It interacts with the PatientClient to send and retrieve
 * diagnosis data, and it provides methods to manipulate the diagnosis list.
 */
public class AddDiagnosisViewModel implements DiagnosisListener
{
  private ObservableList<Diagnosis> diagnosisList = FXCollections.observableArrayList();

  private final PatientsSharedData patientsSharedData = PatientsSharedData.getInstance();
  private final PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  private final PatientClient patientClient;

  private final StringProperty patientName = new SimpleStringProperty();

  /**
   * Constructor for AddDiagnosisViewModel.
   * Initializes the PatientClient and sets the patient's name from shared data.
   */
  public AddDiagnosisViewModel()
  {
    patientClient = new PatientClient();
    patientName.set(patientsSharedData.getPatientName());
  }

  /**
   * Adds a new diagnosis to the patient's record.
   *
   * @param diagnosisName the name of the diagnosis
   * @param status the status of the diagnosis
   * @param date the date of the diagnosis
   * @param prescription the prescription associated with the diagnosis
   */
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

  /**
   * Retrieves the list of diagnoses for a specific patient.
   *
   * @param patientId the ID of the patient
   * @return an ObservableList of Diagnosis objects
   */
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

  /**
   * Gets the diagnosis name from the shared data.
   * @return  the diagnosis name as a String
   */
  public String getDiagnosis()
  {
    return patientsSharedData.getDiagnosis();
  }

  /**
   * Sets the patient's name in the shared data.
   * @param name the name of the patient
   */
  public void setPatientName(String name)
  {
    patientsSharedData.setPatientName(name);
  }

  /**
   * Gets the patient's name from the shared data.
   * @return the patient's name as a String
   */
  public String getPatientName()
  {
    return patientsSharedData.getPatientName();
  }

  /**
   * Gets the patient ID from the shared data.
   * @return the patient ID as an int
   */
  public int getPatientId()
  {
    return patientsSharedData.getPatientId();
  }

  /**
   * Gets all prescriptions for a specific patient.
   * @param id the ID of the patient
   * @return an ObservableList of Prescription objects
   */
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

  /**
   * Prints the result of adding a diagnosis.
   * @param success Indicates whether the operation was successful.
   * @param message A message providing additional details about the operation.
   */
  @Override public void onDiagnosisAdded(boolean success, String message)
  {
    System.out.println("Diagnosis result: " + message);
  }
}
