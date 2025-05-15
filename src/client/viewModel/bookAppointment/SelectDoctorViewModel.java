package client.viewModel.bookAppointment;

import client.clientNetwork.PatientAppointmentClient;
import server.model.bookAppointment.Doctor;

import java.util.List;

public class SelectDoctorViewModel
{
  private final BookAppointmentSharedData sharedData = BookAppointmentSharedData.getInstance();

  public List<Doctor> getDoctorList()
  {
    PatientAppointmentClient client = new PatientAppointmentClient();
    List<Doctor> doctors = client.getDoctorList();
    return (doctors != null) ? doctors : List.of();
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
