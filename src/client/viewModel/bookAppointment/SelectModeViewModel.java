package client.viewModel.bookAppointment;

import client.clientNetwork.PatientAppointmentClient;
import server.model.bookAppointment.Doctor;

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
    List<Doctor> doctors = client.getDoctorList();
    if (doctors != null)
    {
      for (Doctor doc : doctors)
      {
        if (doc.getDoctorID() == sharedData.getSelectedDoctorId())
        {
          return doc.getName();
        }
      }
    }
    return "Unknown";
  }
}
