package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

/**
 * Referral class represents a referral in the healthcare system.
 * It contains details such as referral ID, date created, reason, comment,
 * doctor ID, and patient ID.
 */
public class Referral
{
  private int referralId;
  private NewDateTime dateCreated;
  private String reason;
  private String comment;
  private int doctorId;
  private int patientId;

  // Constructor with all fields

  /**
   * Creates a new instance of Referral with the specified parameters.
   * @param referralId the unique identifier for the referral
   * @param dateCreated the date and time when the referral was created
   * @param reason the reason for the referral
   * @param comment additional comments regarding the referral
   * @param doctorId the ID of the doctor making the referral
   * @param patientId the ID of the patient being referred
   */
  public Referral(int referralId, NewDateTime dateCreated, String reason,
      String comment, int doctorId, int patientId)
  {
    this.referralId = referralId;
    this.dateCreated = dateCreated;
    this.reason = reason;
    this.comment = comment;
    this.doctorId = doctorId;
    this.patientId = patientId;
  }

  // Constructor without referralId (for creation)
  /**
   * Creates a new instance of Referral without a referral ID.
   * This constructor is typically used when creating a new referral.
   * @param dateCreated the date and time when the referral was created
   * @param reason the reason for the referral
   * @param comment additional comments regarding the referral
   * @param doctorId the ID of the doctor making the referral
   * @param patientId the ID of the patient being referred
   */
  public Referral(NewDateTime dateCreated, String reason, String comment,
      int doctorId, int patientId)
  {
    this.dateCreated = dateCreated;
    this.reason = reason;
    this.comment = comment;
    this.doctorId = doctorId;
    this.patientId = patientId;
  }

  // Getters and setters
  /**
   * Gets the referral ID.
   * @return the unique identifier for the referral
   */
  public int getReferralId()
  {
    return referralId;
  }

  /**
   * Sets the referral ID.
   * @param referralId the unique identifier for the referral
   */
  public void setReferralId(int referralId)
  {
    this.referralId = referralId;
  }

  /**
   * Gets the date when the referral was created.
   * @return the date and time when the referral was created
   */
  public NewDateTime getDateCreated()
  {
    return dateCreated;
  }

  /**
   * Sets the date when the referral was created.
   * @param dateCreated the date and time when the referral was created
   */
  public void setDateCreated(NewDateTime dateCreated)
  {
    this.dateCreated = dateCreated;
  }

  /**
   * Gets the reason for the referral.
   * @return the reason for the referral
   */
  public String getReason()
  {
    return reason;
  }

  /**
   * Sets the reason for the referral.
   * @param reason the reason for the referral
   */
  public void setReason(String reason)
  {
    this.reason = reason;
  }

  /**
   * Gets additional comments regarding the referral.
   * @return additional comments regarding the referral
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * Sets additional comments regarding the referral.
   * @param comment additional comments regarding the referral
   */
  public void setComment(String comment)
  {
    this.comment = comment;
  }

  /**
   * Gets the ID of the doctor making the referral.
   * @return the ID of the doctor making the referral
   */
  public int getDoctorId()
  {
    return doctorId;
  }

  /**
   * Sets the ID of the doctor making the referral.
   * @param doctorId the ID of the doctor making the referral
   */
  public void setDoctorId(int doctorId)
  {
    this.doctorId = doctorId;
  }

  /**
   * Gets the ID of the patient being referred.
   * @return the ID of the patient being referred
   */
  public int getPatientId()
  {
    return patientId;
  }

  /**
   * Sets the ID of the patient being referred.
   * @param patientId the ID of the patient being referred
   */
  public void setPatientId(int patientId)
  {
    this.patientId = patientId;
  }

  /**
   * Returns a string representation of the Referral object.
   * @return a string describing the referral
   */
  @Override public String toString()
  {
    return "Referral for " + reason + " " + comment + ", created on "
        + dateCreated;
  }
}
