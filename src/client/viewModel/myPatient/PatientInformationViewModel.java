package client.viewModel.myPatient;

import client.viewModel.bookAppointment.BookAppointmentSharedData;
import server.model.bookAppointment.Patient;

public class PatientInformationViewModel
{
  private PatientInformationSharedData sharedData = PatientInformationSharedData.getInstance();

  public PatientInformationViewModel()
  {

  }

  public PatientInformationSharedData getSharedData()
  {
    return sharedData;
  }

  public void setSharedData(PatientInformationSharedData sharedData)
  {
    this.sharedData = sharedData;
  }
}
