package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

import java.io.Serializable;
import java.time.LocalDate;

public class Diagnosis implements Serializable
{
  private int diagnosisId;
  private String diagnosisName;
  private String status;
  private NewDateTime dateDiagnosed;
  private String comment;
  private int doctorId;
  private int patientId;
  private String prescription;

  public Diagnosis()
  {
  }

  public Diagnosis(String diagnosisName, String status,
      NewDateTime dateDiagnosed, int doctorId, int patientId,
      String prescription)
  {
    this.diagnosisName = diagnosisName;
    this.status = status;
    this.dateDiagnosed = dateDiagnosed;
    this.doctorId = doctorId;
    this.patientId = patientId;
    this.prescription = prescription;
  }

  public int getDiagnosisId()
  {
    return diagnosisId;
  }

  public void setDiagnosisId(int diagnosisId)
  {
    this.diagnosisId = diagnosisId;
  }

  public String getDiagnosisName()
  {
    return diagnosisName;
  }

  public void setDiagnosisName(String diagnosisName)
  {
    this.diagnosisName = diagnosisName;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public NewDateTime getDateDiagnosed()
  {
    return dateDiagnosed;
  }

  public void setDateDiagnosed(NewDateTime dateDiagnosed)
  {
    this.dateDiagnosed = dateDiagnosed;
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

  public String getPrescription()
  {
    return prescription;
  }

  public void setPrescription(String prescription)
  {
    this.prescription = prescription;
  }

  public String toString()
  {
    return diagnosisName + ", (" + status + "), on " + dateDiagnosed.getDay()
        + "/" + dateDiagnosed.getMonth() + "/" + dateDiagnosed.getYear();
  }
}