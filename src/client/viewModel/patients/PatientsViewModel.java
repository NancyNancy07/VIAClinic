package client.viewModel.patients;

import client.clientNetwork.PatientClient;
import server.model.bookAppointment.Patient;

import java.util.List;

public class PatientsViewModel
{
  private PatientsSharedData patientsSharedData;

  public PatientsViewModel(PatientsSharedData patientsSharedData)
  {
    this.patientsSharedData = patientsSharedData;
  }

  public List<Patient> getPatientList()
  {
    PatientClient patientClient = new PatientClient();
    List<Patient> patients = patientClient.getPatientList();
    if (patients != null)
    {
      return patients;
    }

    return null;
  }

  public void setDiagnosis(String diagnosis)
  {
    patientsSharedData.setDiagnosis(diagnosis);
  }

  public String getDiagnosis()
  {
    return patientsSharedData.getDiagnosis();
  }

  public void setPatientName(String name)
  {
    patientsSharedData.setPatientName(name);
  }

  public String getPatientName()
  {
    return patientsSharedData.getPatientName();
  }
}
