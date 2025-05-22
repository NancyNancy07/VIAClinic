package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

public class Referral
{
  private int referralId;
  private NewDateTime dateCreated;
  private String reason;
  private String comment;
  private int doctorId;
  private int patientId;

  // Constructor with all fields
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
  public int getReferralId()
  {
    return referralId;
  }

  public void setReferralId(int referralId)
  {
    this.referralId = referralId;
  }

  public NewDateTime getDateCreated()
  {
    return dateCreated;
  }

  public void setDateCreated(NewDateTime dateCreated)
  {
    this.dateCreated = dateCreated;
  }

  public String getReason()
  {
    return reason;
  }

  public void setReason(String reason)
  {
    this.reason = reason;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public int getDoctorId()
  {
    return doctorId;
  }

  public void setDoctorId(int doctorId)
  {
    this.doctorId = doctorId;
  }

  public int getPatientId()
  {
    return patientId;
  }

  public void setPatientId(int patientId)
  {
    this.patientId = patientId;
  }

  @Override public String toString()
  {
    return "Referral for " + reason + " " + comment + ", created on "
        + dateCreated;
  }
}
