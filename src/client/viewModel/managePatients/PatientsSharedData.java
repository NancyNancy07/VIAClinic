package client.viewModel.managePatients;

import client.viewModel.loginSystem.LoginSharedData;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Prescription;

public class PatientsSharedData
{
  private static PatientsSharedData instance;

  private String patientName;
  private String diagnosis;
  private String referral;
  private String prescription;
  private int patientId;

  private PatientsSharedData()
  {
  }

  public static synchronized PatientsSharedData getInstance()
  {
    if (instance == null)
    {
      instance = new PatientsSharedData();
    }
    return instance;
  }

  public void setPatientName(String patientName)
  {
    this.patientName = patientName;
  }

  public String getPatientName()
  {
    return patientName;
  }

  public int getPatientId()
  {
    return patientId;
  }

  public void setPatientId(int patientId)
  {
    this.patientId = patientId;
  }

  public void setDiagnosis(String diagnosis, String status, NewDateTime date,
      Prescription prescription)
  {
    this.diagnosis = diagnosis;
  }

  public String getDiagnosis()
  {
    return diagnosis;
  }

  public int getDoctorId()
  {
    return LoginSharedData.getInstance().getId();
  }

  public void setPrescription(String medicineName, double doseAmount,
      String doseUnit, NewDateTime startDate, NewDateTime endDate,
      String frequency, String status, String comment, int doctorId,
      int patientId)
  {
    this.prescription = medicineName;
  }

  public String getPrescription()
  {
    return prescription;
  }

  public void setReferral(NewDateTime date, String reason, String comment,
      int doctorId, int patientId)
  {
    this.referral = reason;
  }

  public String getReferral()
  {
    return referral;
  }
}
