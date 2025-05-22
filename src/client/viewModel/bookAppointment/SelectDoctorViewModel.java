package client.viewModel.bookAppointment;

import client.clientNetwork.PatientAppointmentClient;
import client.model.clientBookAppointment.ClientDoctor;
import client.model.clientBookAppointment.ClientDoctorList;

import java.util.List;

public class SelectDoctorViewModel
{
  private final BookAppointmentSharedData sharedData = BookAppointmentSharedData.getInstance();

  public List<ClientDoctor> getDoctorList()
  {
    PatientAppointmentClient client = new PatientAppointmentClient();
   ClientDoctorList doctors = client.getDoctorList();
    return (doctors != null) ? doctors.getAllDoctors() : List.of();
  }

  public void setSelectedDoctor(int doctorId)
  {
    sharedData.setSelectedDoctorId(doctorId);
  }

  public int getSelectedDoctorId()
  {
    return sharedData.getSelectedDoctorId();
  }
}
