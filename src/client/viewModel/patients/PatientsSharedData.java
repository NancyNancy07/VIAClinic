package client.viewModel.patients;

import client.viewModel.loginSystem.LoginSharedData;
import server.model.bookAppointment.NewDateTime;

import java.time.LocalDate;

public class PatientsSharedData
{
  private String patientName;
  private String diagnosis;

  public void setPatientName(String patientName)
  {
    this.patientName = patientName;
  }

  public String getPatientName()
  {
    return patientName;
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
