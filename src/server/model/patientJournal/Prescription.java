package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

import java.sql.SQLException;

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


  public Prescription()
  {

  }

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


  public int getPrescriptionId()
  {
    return prescriptionId;
  }

  public String toString()
  {
    return "Prescription ID: " + prescriptionId;
  }

  public void setPrescriptionId(int prescriptionId) {
    this.prescriptionId = prescriptionId;
  }

  public int getPatientId()
  {
    return patientId;
  }

  public String getMedicineName()
  {
    return medicineName;
  }

  public double getDoseAmount()
  {
    return doseAmount;
  }

  public String getDoseUnit()
  {
    return doseUnit;
  }

  public NewDateTime getStartDate()
  {
    return startDate;
  }

  public NewDateTime getEndDate()
  {
    return endDate;
  }

  public String getFrequency()
  {
    return frequency;
  }

  public String getStatus()
  {
    return status;
  }

  public String getComment()
  {
    return comment;
  }

  public int getDoctorId()
  {
    return doctorId;
  }
}
