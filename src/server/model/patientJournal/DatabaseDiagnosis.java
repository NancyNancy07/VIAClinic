package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

import java.io.Serializable;

public class DatabaseDiagnosis implements Serializable
{
  private int diagnosisId;
  private String diagnosisName;
  private String status;
  private NewDateTime dateDiagnosed;
  private String comment;
  private int doctorId;
  private int patientId;
  private Prescription prescription;

  public DatabaseDiagnosis() {}

  public DatabaseDiagnosis(String diagnosisName, String status,
      NewDateTime dateDiagnosed, int doctorId, int patientId,
      Prescription prescription) {
    this(diagnosisName, status, dateDiagnosed, null, doctorId, patientId, prescription);
  }

  public DatabaseDiagnosis(String diagnosisName, String status,
      NewDateTime dateDiagnosed, String comment, int doctorId,
      int patientId, Prescription prescription) {
    this.diagnosisName = diagnosisName;
    this.status = status;
    this.dateDiagnosed = dateDiagnosed;
    this.comment = comment;
    this.doctorId = doctorId;
    this.patientId = patientId;
    this.prescription = prescription;
  }

  public int getDiagnosisId() {
    return diagnosisId;
  }

  public void setDiagnosisId(int diagnosisId) {
    this.diagnosisId = diagnosisId;
  }

  public String getDiagnosisName() {
    return diagnosisName;
  }

  public void setDiagnosisName(String diagnosisName) {
    this.diagnosisName = diagnosisName;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public NewDateTime getDateDiagnosed() {
    return dateDiagnosed;
  }

  public void setDateDiagnosed(NewDateTime dateDiagnosed) {
    this.dateDiagnosed = dateDiagnosed;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public int getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(int doctorId) {
    this.doctorId = doctorId;
  }

  public int getPatientId() {
    return patientId;
  }

  public void setPatientId(int patientId) {
    this.patientId = patientId;
  }

  public Prescription getPrescription() {
    return prescription;
  }

  public void setPrescription(Prescription prescription) {
    this.prescription = prescription;
  }

  @Override
  public String toString() {
    return diagnosisName + " (" + status + "), on " +
        dateDiagnosed.getDay() + "/" +
        dateDiagnosed.getMonth() + "/" +
        dateDiagnosed.getYear();
  }
}
