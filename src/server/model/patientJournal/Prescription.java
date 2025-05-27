package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

import java.sql.SQLException;

/**
 * Prescription class represents a prescription in the healthcare system.
 * It contains details such as medicine name, dose amount, dose unit, start and end dates,
 * frequency, status, comment, doctor ID, and patient ID.
 */
public class Prescription
{
  private int prescriptionId;
  private String medicineName;
  private double doseAmount;
  private String doseUnit;
  private NewDateTime startDate;
  private NewDateTime endDate;
  private String frequency;
  private String status;
  private String comment;
  private int doctorId;
  private int patientId;


  /**
   * Default constructor for Prescription.
   * Initializes the prescription with default values.
   */
  public Prescription()
  {

  }

  /**
   * Constructor for Prescription with all parameters.
   * Initializes the prescription with provided values.
   *
   * @param medicineName the name of the prescribed medicine
   * @param doseAmount the amount of each dose
   * @param doseUnit the unit of measurement for the dose
   * @param startDate the start date of the prescription
   * @param endDate the end date of the prescription
   * @param frequency how often the medicine should be taken
   * @param status the status of the prescription
   * @param comment any additional notes
   * @param doctorId the ID of the prescribing doctor
   * @param patientId the ID of the patient
   */
  public Prescription(String medicineName, double doseAmount,
      String doseUnit, NewDateTime startDate, NewDateTime endDate, String frequency,
      String status, String comment, int doctorId, int patientId)
  {
    this.medicineName = medicineName;
    this.doseAmount = doseAmount;
    this.doseUnit = doseUnit;
    this.startDate = startDate;
    this.endDate = endDate;
    this.frequency = frequency;
    this.status = status;
    this.comment = comment;
    this.doctorId = doctorId;
    this.patientId = patientId;
  }

  /**
   * Gets the prescription ID.
   * @return the unique identifier for the prescription
   */
  public int getPrescriptionId()
  {
    return prescriptionId;
  }

  /**
   * Returns a string representation of the prescription.
   * @return a string containing the prescription ID
   */
  public String toString()
  {
    return "Prescription ID: " + prescriptionId;
  }

  /**
   * Sets the prescription ID.
   * @param prescriptionId the unique identifier for the prescription
   */
  public void setPrescriptionId(int prescriptionId) {
    this.prescriptionId = prescriptionId;
  }

  /**
   * Gets the patient ID.
   * @return the unique identifier for the patient
   */
  public int getPatientId()
  {
    return patientId;
  }

  /**
   * Gets the name of the medicine prescribed.
   * @return the name of the medicine as a String
   */
  public String getMedicineName()
  {
    return medicineName;
  }

  /**
   * Gets the amount of each dose prescribed.
   * @return the dose amount as a double
   */
  public double getDoseAmount()
  {
    return doseAmount;
  }

  /**
   * Gets the unit of measurement for the dose.
   * @return the dose unit as a String
   */
  public String getDoseUnit()
  {
    return doseUnit;
  }
  /**
   * Gets the start date of the prescription.
   * @return the start date as a NewDateTime object
   */
  public NewDateTime getStartDate()
  {
    return startDate;
  }

  /**
   * Gets the end date of the prescription.
   * @return the end date as a NewDateTime object
   */
  public NewDateTime getEndDate()
  {
    return endDate;
  }

  /**
   * Gets the frequency of the prescription.
   * @return the frequency as a String
   */
  public String getFrequency()
  {
    return frequency;
  }

  /**
   * Gets the status of the prescription.
   * @return the status as a String
   */
  public String getStatus()
  {
    return status;
  }

  /**
   * Gets any additional comments or notes about the prescription.
   * @return the comment as a String
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * Gets the ID of the doctor who prescribed the medication.
   * @return the ID of the doctor as an integer
   */
  public int getDoctorId()
  {
    return doctorId;
  }
}
