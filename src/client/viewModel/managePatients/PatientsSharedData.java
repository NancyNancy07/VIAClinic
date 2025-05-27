package client.viewModel.managePatients;

import client.viewModel.loginSystem.LoginSharedData;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Prescription;

/**
 * PatientsSharedData is a singleton class that holds shared data related to patients.
 * It provides methods to set and get patient information such as name, diagnosis,
 * referral, prescription, lab results, and vaccination details.
 */
public class PatientsSharedData
{
  private static PatientsSharedData instance;

  private String patientName;
  private String diagnosis;
  private String referral;
  private String prescription;
  private String labResult;
  private int patientId;
  private String vaccination;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * This ensures that only one instance of PatientsSharedData exists.
   */
  private PatientsSharedData()
  {
  }

  /**
   * Returns the singleton instance of PatientsSharedData.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of PatientsSharedData
   */
  public static synchronized PatientsSharedData getInstance()
  {
    if (instance == null)
    {
      instance = new PatientsSharedData();
    }
    return instance;
  }

  /**
   * Sets the patient's name.
   *
   * @param patientName the name of the patient
   */
  public void setPatientName(String patientName)
  {
    this.patientName = patientName;
  }

  /**
   * Gets the patient's name.
   *
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
   * @param status the status of the diagnosis
   * @param date the date of the diagnosis
   * @param prescription the prescription associated with the diagnosis
   */
  public void setDiagnosis(String diagnosis, String status, NewDateTime date,
      Prescription prescription)
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
   * @return the ID of the doctor
   */
  public int getDoctorId()
  {
    return LoginSharedData.getInstance().getId();
  }

  /**
   * Sets prescription details for the patient.
   * @param medicineName the name of the medicine prescribed
   * @param doseAmount the amount of the dose prescribed
   * @param doseUnit the unit of the dose prescribed
   * @param startDate the start date of the prescription
   * @param endDate the end date of the prescription
   * @param frequency the frequency of the dose
   * @param status the status of the prescription
   * @param comment any additional comments regarding the prescription
   * @param doctorId the ID of the doctor prescribing the medication
   * @param patientId the ID of the patient receiving the prescription
   */
  public void setPrescription(String medicineName, double doseAmount,
      String doseUnit, NewDateTime startDate, NewDateTime endDate,
      String frequency, String status, String comment, int doctorId,
      int patientId)
  {
    this.prescription = medicineName;
  }

  /**
   * Gets the prescription details for the patient.
   *
   * @return the prescription details
   */
  public String getPrescription()
  {
    return prescription;
  }
  /**
   * Sets lab result details for the patient.
   *
   * @param testName the name of the lab test
   * @param dateCollected the date when the sample was collected
   * @param sampleType the type of sample collected
   * @param comment any additional comments regarding the lab result
   * @param doctorId the ID of the doctor who ordered the test
   * @param patientId the ID of the patient for whom the test was conducted
   */
  public void setLabResult(String testName,
      String dateCollected, NewDateTime sampleType,
       String comment, int doctorId, int patientId)
  {
    this.labResult = testName;
  }

  /**
   * Gets the lab result details for the patient.
   *
   * @return the lab result details
   */
  public String getLabResult()
  {
    return labResult;
  }


  /**
   * Sets vaccination details for the patient.
   *
   * @param vaccinationName the name of the vaccination
   * @param dateTaken the date when the vaccination was administered
   * @param isRecommended whether the vaccination is recommended
   * @param comment any additional comments regarding the vaccination
   * @param nextDoseDate the date for the next dose, if applicable
   */
  public void setVaccination(String vaccinationName, NewDateTime dateTaken,
      boolean isRecommended, String comment, NewDateTime nextDoseDate)
  {
    this.vaccination = vaccinationName;
  }

  /**
   * Gets the vaccination details for the patient.
   *
   * @return the vaccination details
   */
  public String getVaccination()
  {
    return vaccination;
  }

  /**
   * Sets referral details for the patient.
   *
   * @param date the date of the referral
   * @param reason the reason for the referral
   * @param comment any additional comments regarding the referral
   * @param doctorId the ID of the doctor making the referral
   * @param patientId the ID of the patient being referred
   */
  public void setReferral(NewDateTime date, String reason, String comment,
      int doctorId, int patientId)
  {
    this.referral = reason;
  }

  /**
   * Gets the referral details for the patient.
   *
   * @return the referral details
   */
  public String getReferral()
  {
    return referral;
  }
}
