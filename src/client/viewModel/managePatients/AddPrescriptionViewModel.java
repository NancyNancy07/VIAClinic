package client.viewModel.managePatients;

import client.clientNetwork.PatientClient;
import client.clientNetwork.PrescriptionListener;
import client.viewModel.patientJournal.PatientJournalSharedData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Prescription;

/**
 * AddPrescriptionViewModel is responsible for managing the prescription data and logic
 * for adding prescriptions to a patient's record. It interacts with the PatientClient
 * to send prescription data to the server and updates the shared data instances.
 */
public class AddPrescriptionViewModel implements PrescriptionListener
{
  private ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();

  private final PatientsSharedData patientsSharedData = PatientsSharedData.getInstance();
  private final PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  private final PatientClient patientClient;

  private final StringProperty patientName = new SimpleStringProperty();

  /**
   * Constructor for AddPrescriptionViewModel.
   * Initializes the PatientClient and sets the patient name from shared data.
   */
  public AddPrescriptionViewModel()
  {
    patientClient = new PatientClient();
    patientName.set(patientsSharedData.getPatientName());
  }

  /**
   * Adds a prescription to the list and sends it to the server.
   *
   * @param medicineName the name of the medicine
   * @param doseAmount the amount of the dose
   * @param doseUnit the unit of the dose
   * @param startDate the start date of the prescription
   * @param endDate the end date of the prescription
   * @param frequency how often the medicine should be taken
   * @param status the status of the prescription
   * @param comment any additional comments about the prescription
   * @param doctorId the ID of the doctor prescribing the medicine
   * @param patientId the ID of the patient receiving the prescription
   */
  public void addPrescription(String medicineName, double doseAmount,
      String doseUnit, NewDateTime startDate, NewDateTime endDate, String frequency,
      String status, String comment, int doctorId, int patientId)
  {
    Prescription prescription = new Prescription(medicineName, doseAmount, doseUnit,
        startDate, endDate, comment, frequency, status, doctorId, patientId);
    prescriptions.add(prescription);
    patientClient.sendAddPrescription(prescription);
    patientsSharedData.setPrescription(medicineName, doseAmount, doseUnit,
        startDate, endDate, comment, frequency, status, doctorId, patientId);
    patientJournalSharedData.setPatientId(patientsSharedData.getPatientId());
  }

  /**
   * Gets the list of prescriptions.
   * @return an ObservableList of Prescription objects
   */
  public ObservableList<Prescription> getPrescriptions()
  {
    return prescriptions;
  }

  /**
   * Gets the patient name.
   * @return the name of the patient
   */
  public String getPrescription()
  {
    return patientsSharedData.getPrescription();
  }

  /**
   * Sets the patient name.
   * @param name the name of the patient
   */
  public void setPatientName(String name)
  {
    patientsSharedData.setPatientName(name);
  }

  /**
   * Gets the patient name from shared data.
   * @return the name of the patient
   */
  public String getPatientName()
  {
    return patientsSharedData.getPatientName();
  }

  /**
   * Gets the patient ID from shared data.
   * @return the ID of the patient
   */
  public int getPatientId()
  {
    return patientsSharedData.getPatientId();
  }

  /**
   * Gets the doctor ID from shared data.
   * @return the ID of the doctor
   */
  public int getDoctorId()
  {
    return patientsSharedData.getDoctorId();
  }

  /**
   * Prints the result of adding a prescription.
   * @param success Indicates whether the prescription was added successfully.
   * @param message A message providing additional information about the operation.
   */
  @Override public void onPrescriptionAdded(boolean success, String message)
  {
    System.out.println("Prescription result: " + message);
  }

  @Override public void addedPrescription(Prescription prescription)
  {
  }
}
