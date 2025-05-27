package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Diagnosis class represents a medical diagnosis in the patient journal system.
 * It contains details such as diagnosis name, status, date diagnosed, comments,
 * doctor ID, patient ID, and associated prescription.
 */
public class Diagnosis implements Serializable
{
  private int diagnosisId;
  private String diagnosisName;
  private String status;
  private NewDateTime dateDiagnosed;
  private String comment;
  private int doctorId;
  private int patientId;
  private Prescription prescription;

  /**
   * Default constructor for Diagnosis.
   * Initializes the diagnosis with default values.
   */
  public Diagnosis()
  {
  }

  /**
   * Constructor for Diagnosis with all fields.
   * Initializes the diagnosis with provided values.
   *
   * @param diagnosisName the name of the diagnosis
   * @param status the status of the diagnosis
   * @param dateDiagnosed the date when the diagnosis was made
   * @param doctorId the ID of the doctor who made the diagnosis
   * @param patientId the ID of the patient who received the diagnosis
   * @param prescription the prescription associated with the diagnosis
   */
  public Diagnosis(String diagnosisName, String status,
      NewDateTime dateDiagnosed, int doctorId, int patientId,
      Prescription prescription)
  {
    this.diagnosisName = diagnosisName;
    this.status = status;
    this.dateDiagnosed = dateDiagnosed;
    this.doctorId = doctorId;
    this.patientId = patientId;
    this.prescription = prescription;
  }

  /**
   * Constructor for Diagnosis with all fields including comment.
   * Initializes the diagnosis with provided values.
   *
   * @param diagnosisName the name of the diagnosis
   * @param status the status of the diagnosis
   * @param dateDiagnosed the date when the diagnosis was made
   * @param comment additional comments about the diagnosis
   * @param doctorId the ID of the doctor who made the diagnosis
   * @param patientId the ID of the patient who received the diagnosis
   * @param prescription the prescription associated with the diagnosis
   */
  public Diagnosis(String diagnosisName, String status,
      NewDateTime dateDiagnosed, String comment, int doctorId, int patientId,
      Prescription prescription)
  {
    this.diagnosisName = diagnosisName;
    this.status = status;
    this.dateDiagnosed = dateDiagnosed;
    this.comment = comment;
    this.doctorId = doctorId;
    this.patientId = patientId;
    this.prescription = prescription;
  }

  /**
   * Gets the diagnosis ID.
   * @return the unique identifier for the diagnosis
   */
  public int getDiagnosisId()
  {
    return diagnosisId;
  }

  /**
   * Sets the diagnosis ID.
   * @param diagnosisId the unique identifier for the diagnosis
   */
  public void setDiagnosisId(int diagnosisId)
  {
    this.diagnosisId = diagnosisId;
  }

  /**
   * Gets the name of the diagnosis.
   * @return the name of the diagnosis
   */
  public String getDiagnosisName()
  {
    return diagnosisName;
  }

  /**
   * Sets the name of the diagnosis.
   * @param diagnosisName the name of the diagnosis
   */
  public void setDiagnosisName(String diagnosisName)
  {
    this.diagnosisName = diagnosisName;
  }

  /**
   * Gets the status of the diagnosis.
   * @return the status of the diagnosis
   */
  public String getStatus()
  {
    return status;
  }

  /**
   * Sets the status of the diagnosis.
   * @param status the status of the diagnosis
   */
  public void setStatus(String status)
  {
    this.status = status;
  }

  /**
   * Gets the date when the diagnosis was made.
   * @return the date diagnosed as a NewDateTime object
   */
  public NewDateTime getDateDiagnosed()
  {
    return dateDiagnosed;
  }

  /**
   * Sets the date when the diagnosis was made.
   * @param dateDiagnosed the date diagnosed as a NewDateTime object
   */
  public void setDateDiagnosed(NewDateTime dateDiagnosed)
  {
    this.dateDiagnosed = dateDiagnosed;
  }

  /**
   * Gets additional comments about the diagnosis.
   * @return the comment as a String
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * Sets additional comments about the diagnosis.
   * @param comment the comment to set
   */
  public void setComment(String comment)
  {
    this.comment = comment;
  }

  /**
   * Gets the ID of the doctor who made the diagnosis.
   * @return the doctor ID as an integer
   */
  public int getDoctorId()
  {
    return doctorId;
  }

  /**
   * Sets the ID of the doctor who made the diagnosis.
   * @param doctorId the doctor ID to set
   */
  public void setDoctorId(int doctorId)
  {
    this.doctorId = doctorId;
  }

  /**
   * Gets the ID of the patient who received the diagnosis.
   * @return the patient ID as an integer
   */
  public int getPatientId()
  {
    return patientId;
  }

  /**
   * Sets the ID of the patient who received the diagnosis.
   * @param patientId the patient ID to set
   */
  public void setPatientId(int patientId)
  {
    this.patientId = patientId;
  }

  /**
   * Gets the prescription associated with the diagnosis.
   * @return the Prescription object associated with this diagnosis
   */
  public Prescription getPrescription()
  {
    return prescription;
  }

  /**
   * Gets the name of the medicine prescribed.
   * @return the name of the medicine as a String
   */
  public String getMedicineName()
  {
    return prescription.getMedicineName();
  }

  /**
   * Sets the prescription associated with the diagnosis.
   * @param prescription the Prescription object to set
   */
  public void setPrescription(Prescription prescription)
  {
    this.prescription = prescription;
  }

  /**
   * Returns a string representation of the Diagnosis object.
   * @return a string containing the diagnosis name, status, and date diagnosed
   */
  public String toString()
  {
    return diagnosisName + ", (" + status + "), on " + dateDiagnosed.getDay()
        + "/" + dateDiagnosed.getMonth() + "/" + dateDiagnosed.getYear();
  }
}