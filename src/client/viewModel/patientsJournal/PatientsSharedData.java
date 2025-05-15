package client.viewModel.patientsJournal;

import client.viewModel.loginSystem.LoginSharedData;
import server.model.bookAppointment.NewDateTime;

public class PatientsSharedData
{
  private static PatientsSharedData instance;

  private String patientName;
  private String diagnosis;
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
      String prescription)
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
}
