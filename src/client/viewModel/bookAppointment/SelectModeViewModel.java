package client.viewModel.bookAppointment;

import client.clientNetwork.PatientAppointmentClient;
import client.model.clientBookAppointment.ClientDoctor;
import client.model.clientBookAppointment.ClientDoctorList;

import java.util.List;

public class SelectModeViewModel
{
  private final BookAppointmentSharedData sharedData = BookAppointmentSharedData.getInstance();

  public void setMode(String mode)
  {
    sharedData.setConsultationMode(mode);
  }

  public String getConsultationMode()
  {
    return sharedData.getConsultationMode();
  }

  public int getSelectedDoctorId()
  {
    return sharedData.getSelectedDoctorId();
  }

  public String getSelectedDoctorName()
  {
    PatientAppointmentClient client = new PatientAppointmentClient();
    ClientDoctorList doctors = client.getDoctorList();
    if (doctors != null)
    {
      for (ClientDoctor doc : doctors.getAllDoctors())
      {
        if (doc.getDoctorID() == sharedData.getSelectedDoctorId())
        {
          return doc.getFirstName() + " " + doc.getLastName();
        }
      }
    }
    return "Unknown";
  }
}
