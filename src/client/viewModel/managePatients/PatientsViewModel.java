package client.viewModel.managePatients;

import client.clientNetwork.DiagnosisListener;
import client.clientNetwork.PatientClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;

import java.util.List;

/**
 * PatientsViewModel is responsible for managing patient data and interactions
 * with the PatientClient. It provides methods to retrieve patient lists,
 * set patient IDs, add diagnoses, and manage shared patient data.
 */
public class PatientsViewModel implements DiagnosisListener
{
  private PatientsSharedData patientsSharedData = PatientsSharedData.getInstance();
  private List<Patient> patients;
  private int patientId;
  private PatientClient patientClient;
  private ObservableList<Diagnosis> diagnoses = FXCollections.observableArrayList();

  /**
   * Constructor for PatientsViewModel.
   * Initializes the PatientClient and registers this view model as a listener
   * for diagnosis events.
   */
  public PatientsViewModel()
  {
    patientClient = new PatientClient();
    patientClient.setDiagnosisListener(this); // Register as listener

  }

  /**
   * Retrieves the list of patients from the PatientClient.
   * If the list is not null, it returns the list of patients.
   *
   * @return List of patients or null if no patients are found.
   */
  public List<Patient> getPatientList()
  {
    PatientClient patientClient = new PatientClient();
    this.patients = patientClient.getPatientList();
    if (patients != null)
    {
      return patients;
    }

    return null;
  }

  /**
   * Sets the patient ID based on the selected patient.
   * This method updates the shared data with the patient's ID.
   *
   * @param patient the selected patient whose ID is to be set
   */
  public void setPatientId(Patient patient)
  {
    for (int i = 0; i < patients.size(); i++)
    {
      if (patients.get(i).equals(patient))
      {
        patientId = patients.get(i).getPatientID();
        patientsSharedData.setPatientId(patientId);
      }
    }
  }

  /**
   * Retrieves the patient ID from the shared data.
   *
   * @return the ID of the selected patient
   */
  public int getPatientId()
  {
    return patientId;
  }

  public void addDiagnosis(String diagnosisName, String status,
      NewDateTime date, Prescription prescription)
  {
    Diagnosis diagnosis = new Diagnosis(diagnosisName, status, date,
        patientsSharedData.getDoctorId(), getPatientId(), prescription);
    diagnoses.add(diagnosis);
    patientClient.sendAddDiagnosis(diagnosis);
    patientsSharedData.setDiagnosis(diagnosisName, status, date, prescription);
  }

  /**
   * Retrieves the list of diagnoses for the selected patient.
   * This method returns an observable list of diagnoses.
   *
   * @return ObservableList of Diagnosis objects
   */
  public ObservableList<Diagnosis> getDiagnoses()
  {
    return diagnoses;
  }

  /**
   * Retrieves the diagnosis from the shared data.
   * @return the diagnosis string from the shared data
   */
  public String getDiagnosis()
  {
    return patientsSharedData.getDiagnosis();
  }

  /**
   * Sets the patient's name in the shared data.
   * This method updates the shared data with the patient's name.
   *
   * @param name the name of the patient to be set
   */
  public void setPatientName(String name)
  {
    patientsSharedData.setPatientName(name);
  }

  /**
   * Retrieves the patient's name from the shared data.
   * @return the name of the patient
   */
  public String getPatientName()
  {
    return patientsSharedData.getPatientName();
  }

  /**
   * Prints the details of the diagnosis added.
   * @param success indicates whether the diagnosis was added successfully
   * @param message a message containing details about the diagnosis
   */
  @Override public void onDiagnosisAdded(boolean success, String message)
  {
    System.out.println("Diagnosis result: " + message);
  }

}
