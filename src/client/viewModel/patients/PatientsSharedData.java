package client.viewModel.patients;

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

  public void setDiagnosis(String diagnosis)
  {
    this.diagnosis = diagnosis;
    System.out.println(diagnosis);

  }

  public String getDiagnosis()
  {
    return diagnosis;
  }

}
