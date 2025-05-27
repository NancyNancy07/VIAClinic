package client.viewModel.managePatients;

import client.clientNetwork.PatientClient;
import client.viewModel.patientJournal.PatientJournalSharedData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Referral;

/**
 * AddReferralViewModel is responsible for managing the referral data and interactions
 * related to adding referrals for a patient.
 * It communicates with the PatientClient to send referral data to the server.
 */
public class AddReferralViewModel
{
  private ObservableList<Referral> referrals = FXCollections.observableArrayList();
  private final PatientsSharedData patientsSharedData = PatientsSharedData.getInstance();
  private final PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  private final PatientClient patientClient;

  private final StringProperty patientName = new SimpleStringProperty();

  /**
   * Constructor initializes the PatientClient and sets the patient name from shared data.
   */
  public AddReferralViewModel()
  {
    patientClient = new PatientClient();
    patientName.set(patientsSharedData.getPatientName());
  }

  /**
   * Adds a new referral to the list and sends it to the server.
   * @param dateCreated the date the referral was created
   * @param reason the reason for the referral
   * @param comment additional comments regarding the referral
   * @param doctorId the ID of the doctor making the referral
   * @param patientId the ID of the patient being referred
   */
  public void addReferral(NewDateTime dateCreated, String reason,
      String comment, int doctorId, int patientId)
  {
    Referral referral = new Referral(dateCreated, reason, comment, doctorId,
        patientId);
    referrals.add(referral);
    patientClient.sendAddReferral(referral);
    patientsSharedData.setReferral(dateCreated, reason, comment, doctorId,
        patientId);
    patientJournalSharedData.setPatientId(patientsSharedData.getPatientId());
  }

  /**
   * Gets the list of referrals for the patient.
   * @return an ObservableList of Referral objects
   */
  public ObservableList<Referral> getReferrals()
  {
    return referrals;
  }

  /**
   * Gets the referral details from the shared data.
   * @return the referral details as a String
   */
  public String getReferral()
  {
    return patientsSharedData.getReferral();
  }

  /**
   * Sets the patient name in the shared data.
   * @param name the name of the patient
   */
  public void setPatientName(String name)
  {
    patientsSharedData.setPatientName(name);
  }

  /**
   * Gets the patient name from the shared data.
   * @return the name of the patient
   */
  public String getPatientName()
  {
    return patientsSharedData.getPatientName();
  }

  /**
   * Gets the patient ID from the shared data.
   * @return the ID of the patient
   */
  public int getPatientId()
  {
    return patientsSharedData.getPatientId();
  }

  /**
   * Gets the doctor ID from the shared data.
   * @return the ID of the doctor
   */
  public int getDoctorId()
  {
    return patientsSharedData.getDoctorId();
  }

}
