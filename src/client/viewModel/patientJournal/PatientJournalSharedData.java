package client.viewModel.patientJournal;

import client.viewModel.loginSystem.LoginSharedData;
import client.viewModel.patients.PatientsSharedData;
import server.model.bookAppointment.NewDateTime;

public class PatientJournalSharedData
{
  private static PatientJournalSharedData instance;
  private String patientName;
  private String diagnosis;
  private int patientId;

  private PatientJournalSharedData()
  {
  }

  public static synchronized PatientJournalSharedData getInstance()
  {
    if (instance == null)
    {
      instance = new PatientJournalSharedData();
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
