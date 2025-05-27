package client.viewModel.managePatients;

import client.clientNetwork.LabResultListener;

import client.clientNetwork.PatientClient;
import client.viewModel.patientJournal.PatientJournalSharedData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.LabResult;

/**
 * AddLabResultViewModel is responsible for managing the addition of lab results for a patient.
 * It interacts with the PatientClient to send lab result data and maintains a list of lab results.
 */
public class AddLabResultViewModel implements LabResultListener
{
  private ObservableList<LabResult> labResults = FXCollections.observableArrayList();

  private final PatientsSharedData patientsSharedData = PatientsSharedData.getInstance();
  private final PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  private final PatientClient patientClient;

  private final StringProperty patientName = new SimpleStringProperty();

  /**
   * Constructor initializes the PatientClient and sets the patient name from shared data.
   */
  public AddLabResultViewModel()
  {
    patientClient = new PatientClient();
    patientName.set(patientsSharedData.getPatientName());
  }

  /**
   * Adds a lab result to the list and sends it to the server.
   *
   * @param testName       the name of the lab test
   * @param sampleType     the type of sample collected
   * @param dateCollected  the date and time when the sample was collected
   * @param comment        additional comments about the lab result
   * @param doctorId       the ID of the doctor who ordered the lab test
   * @param patientId      the ID of the patient for whom the lab result is being added
   */
  public void addLabResult(String testName, String sampleType,
      NewDateTime dateCollected, String comment, int doctorId, int patientId)
  {
       LabResult labResult = new LabResult(testName, sampleType, dateCollected,comment,
           doctorId, patientId);
           labResults.add(labResult);
        System.out.println("Lab result added: " + testName);
    System.out.println("Labresult class: "+labResult.getClass());
    System.out.println("Patient Id for lab result: " + patientId);
    System.out.println("lab result: " + labResult.getTestName() + " " +labResult.getPatientId());
       patientClient.sendAddLabResult(labResult);
      patientsSharedData.setLabResult(testName, sampleType,dateCollected,comment,doctorId,patientId);
       patientJournalSharedData.setPatientId(patientsSharedData.getPatientId());
  }

  /**
   * Retrieves the list of lab results for the patient.
   *
   * @return an observable list of LabResult objects
   */
  public ObservableList<LabResult> getLabResults()
  {
    return labResults;
  }

  /**
   * Retrieves the lab result for the patient from shared data.
   *
   * @return the lab result as a String
   */
  public String getLabResult()
  {
    return patientsSharedData.getLabResult();
  }

  /**
   * Sets the name of the patient in shared data.
   *
   * @param name the name of the patient
   */
  public void setPatientName(String name)
  {
    patientsSharedData.setPatientName(name);
  }

  /**
   * Gets the name of the patient from shared data.
   *
   * @return the name of the patient
   */
  public String getPatientName()
  {
    return patientsSharedData.getPatientName();
  }

  /**
   * Gets the patient ID from shared data.
   *
   * @return the ID of the patient
   */
  public int getPatientId()
  {
    return patientsSharedData.getPatientId();
  }

  /**
   * Gets the doctor ID from shared data.
   *
   * @return the ID of the doctor
   */
  public int getDoctorId()
  {
    return patientsSharedData.getDoctorId();
  }

  /**
   * Prints a message indicating the result of adding a lab result.
   * @param success Indicates whether the operation was successful.
   * @param message A message providing additional details about the operation.
   */
  @Override public void onLabResultAdded(boolean success, String message)
  {System.out.println("lab Result: " + message);
  }
}


