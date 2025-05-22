package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

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

  public Vaccination()
  {
  }


  // Constructor for vaccinations without next dose
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


  public void setVaccinationId(int vaccinationId)
  {
    this.vaccinationId = vaccinationId;
  }

  public int getVaccinationId()
  {
    return vaccinationId;
  }

  public String getVaccinationName()
  {
    return vaccinationName;
  }

  public NewDateTime getDateTaken()
  {
    return dateTaken;
  }

  public boolean isRecommended()
  {
    return isRecommended;
  }

  public String getComment()
  {
    return comment;
  }

  public NewDateTime getNextDoseDate()
  {
    return nextDoseDate;
  }

  public int getDoctorId()
  {
    return doctorId;
  }

  public int getPatientId()
  {
    return patientId;
  }

  @Override
  public String toString()
  {
    return "Vaccination{" + "vaccinationId=" + vaccinationId +
        ", vaccinationName='" + vaccinationName + '\'' + ", dateTaken=" + dateTaken +
        ", isRecommended=" + isRecommended + ", comment='" + comment + '\'' +
        ", nextDoseDate=" + nextDoseDate + ", doctorId=" + doctorId + ", patientId=" + patientId + '}';
  }
}
