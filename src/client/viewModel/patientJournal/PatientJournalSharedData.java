package client.viewModel.patientJournal;

import client.viewModel.loginSystem.LoginSharedData;
import server.model.bookAppointment.NewDateTime;

/**
 * PatientJournalSharedData is a singleton class that holds shared data for the patient journal.
 * It provides methods to set and get patient information such as name, diagnosis, and ID.
 */
public class PatientJournalSharedData
{
  private static PatientJournalSharedData instance;
  private String patientName;
  private String diagnosis;
  private int patientId;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * This ensures that only one instance of PatientJournalSharedData exists (singleton pattern).
   */
  private PatientJournalSharedData()
  {
  }

  /**
   * Returns the singleton instance of PatientJournalSharedData.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of PatientJournalSharedData
   */
  public static synchronized PatientJournalSharedData getInstance()
  {
    if (instance == null)
    {
      instance = new PatientJournalSharedData();
    }
    return instance;
  }

  /**
   * Sets the patient name
   *
   * @param patientName the name of the patient
   */
  public void setPatientName(String patientName)
  {
    this.patientName = patientName;
  }

  /**
   * Gets the patient name.
   * @return the name of the patient
   */
  public String getPatientName()
  {
    return patientName;
  }

  /**
   * Gets the patient ID.
    * @return the ID of the patient
   */
  public int getPatientId()
  {
    return patientId;
  }

  /**
   * Sets the patient ID.
   *
   * @param patientId the ID of the patient
   */
  public void setPatientId(int patientId)
  {
    this.patientId = patientId;
  }

  /**
   * Sets the diagnosis for the patient.
   *
   * @param diagnosis the diagnosis of the patient
   * @param status    the status of the diagnosis
   * @param date      the date of the diagnosis
   * @param prescription the prescription for the diagnosis
   */
  public void setDiagnosis(String diagnosis, String status, NewDateTime date,
      String prescription)
  {
    this.diagnosis = diagnosis;
  }

  /**
   * Gets the diagnosis of the patient.
   *
   * @return the diagnosis of the patient
   */
  public String getDiagnosis()
  {
    return diagnosis;
  }

  /**
   * Gets the doctor ID from the login shared data.
   *
   * @return the ID of the doctor
   */
  public int getDoctorId()
  {
    return LoginSharedData.getInstance().getId();
  }
}
