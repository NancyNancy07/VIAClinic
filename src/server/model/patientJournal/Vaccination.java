package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

/**
 * Referral class represents a referral in the healthcare system.
 * It contains details such as referral ID, date created, reason, comment,
 * doctor ID, and patient ID.
 */
public class Vaccination
{
  private int vaccinationId;
  private String vaccinationName;
  private NewDateTime dateTaken;
  private boolean isRecommended;
  private String comment;
  private NewDateTime nextDoseDate;
  private int doctorId;
  private int patientId;

  /**
   * Default constructor for Vaccination.
   * Initializes the vaccination with default values.
   */
  public Vaccination()
  {
  }


  // Constructor for vaccinations without next dose

  /**
   * Constructor for Vaccination.
   * @param vaccinationName the name of the vaccine
   * @param dateTaken the date the vaccine was administered
   * @param isRecommended whether the vaccine is recommended
   * @param comment additional notes
   * @param doctorId the ID of the responsible doctor
   * @param patientId the ID of the patient
   */
  public Vaccination(String vaccinationName, NewDateTime dateTaken,
      boolean isRecommended, String comment, int doctorId, int patientId)
  {
    this.vaccinationId = vaccinationId;
    this.vaccinationName = vaccinationName;
    this.dateTaken = dateTaken;
    this.isRecommended = isRecommended;
    this.comment = comment;
    this.doctorId = doctorId;
    this.patientId = patientId;
  }


  // Constructor for vaccinations with next dose
  /**
   * Constructor for Vaccination with next dose date.
   * @param vaccinationName the name of the vaccine
   * @param dateTaken the date the vaccine was administered
   * @param isRecommended whether the vaccine is recommended
   * @param comment additional notes
   * @param nextDoseDate the date of the next scheduled dose
   * @param doctorId the ID of the responsible doctor
   * @param patientId the ID of the patient
   */
  public Vaccination(String vaccinationName, NewDateTime dateTaken,
      boolean isRecommended, String comment, NewDateTime nextDoseDate,
      int doctorId, int patientId)
  {
    this.vaccinationName = vaccinationName;
    this.dateTaken = dateTaken;
    this.isRecommended = isRecommended;
    this.comment = comment;
    this.nextDoseDate = nextDoseDate;
    this.doctorId = doctorId;
    this.patientId = patientId;
  }


//  public Vaccination(int vaccinationId, String vaccinationName, NewDateTime dateTaken,
//      boolean isRecommended, String comment,
//      NewDateTime nextDoseDate, int doctorId, int patientId)
//  {
//    this.vaccinationId = vaccinationId;
//    this.vaccinationName = vaccinationName;
//    this.dateTaken = dateTaken;
//    this.isRecommended = isRecommended;
//    this.comment = comment;
//    this.nextDoseDate = nextDoseDate;
//    this.doctorId = doctorId;
//    this.patientId = patientId;
//  }


  /**
   * Sets the vaccination ID.
   * @param vaccinationId the unique identifier for the vaccination
   */
  public void setVaccinationId(int vaccinationId)
  {
    this.vaccinationId = vaccinationId;
  }

  /**
   * Gets the vaccination ID.
   * @return the unique identifier for the vaccination
   */
  public int getVaccinationId()
  {
    return vaccinationId;
  }

  /**
   * Gets the name of the vaccination.
   * @return the name of the vaccination
   */
  public String getVaccinationName()
  {
    return vaccinationName;
  }

  /**
   * Gets the date when the vaccination was taken.
   * @return the date the vaccine was administered
   */
  public NewDateTime getDateTaken()
  {
    return dateTaken;
  }

  /**
   * Checks if the vaccination is recommended.
   * @return true if the vaccination is recommended, false otherwise
   */
  public boolean isRecommended()
  {
    return isRecommended;
  }

  /**
   * Gets the comment associated with the vaccination.
   * @return additional notes on the vaccination
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * Gets the date of the next scheduled dose.
   * @return the date of the next dose, or null if not applicable
   */
  public NewDateTime getNextDoseDate()
  {
    return nextDoseDate;
  }

  /**
   * Gets the ID of the doctor responsible for the vaccination.
   * @return the ID of the doctor
   */
  public int getDoctorId()
  {
    return doctorId;
  }

  /**
   * Gets the ID of the patient who received the vaccination.
   * @return the ID of the patient
   */
  public int getPatientId()
  {
    return patientId;
  }

  /**
   * Returns a string representation of the Vaccination object.
   * @return a string containing the vaccination details
   */
  @Override
  public String toString()
  {
    return "Vaccination{" + "vaccinationId=" + vaccinationId +
        ", vaccinationName='" + vaccinationName + '\'' + ", dateTaken=" + dateTaken +
        ", isRecommended=" + isRecommended + ", comment='" + comment + '\'' +
        ", nextDoseDate=" + nextDoseDate + ", doctorId=" + doctorId + ", patientId=" + patientId + '}';
  }
}
