package client.viewModel.doctorAppointment;

import client.clientNetwork.PatientClient;
import client.model.clientBookAppointment.ClientAppointment;
import client.model.clientBookAppointment.ClientAppointmentList;
import client.model.clientBookAppointment.ClientAppointmentModel;
import client.model.clientBookAppointment.ClientNewDateTime;
import client.viewModel.bookAppointment.BookAppointmentSharedData;
import client.viewModel.loginSystem.LoginSharedData;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;

import java.util.List;

public class DoctorAppointmentViewModel
{
  private ClientAppointmentModel model;
  private BookAppointmentSharedData sharedData;
  private List<Patient> cachedPatientList;

  public DoctorAppointmentViewModel(ClientAppointmentModel model)
  {
    sharedData = BookAppointmentSharedData.getInstance();
    this.model = model;
    PatientClient client = new PatientClient();
    cachedPatientList = client.getPatientList();
  }

  public ClientAppointmentList getDoctorAppointmentList()
  {
    int doctorId = LoginSharedData.getInstance().getId();
    return model.getDoctorAppointmentList(doctorId);
  }

  public String getUsername()
  {
    return LoginSharedData.getInstance().getUsername();
  }

  public Patient getPatient(int id)
  {
    for (Patient patient : cachedPatientList)
    {
      if (patient.getPatientID() == id)
      {
        return patient;
      }
    }
    return null;
  }

  public void rescheduleAppointment(ClientAppointment appointment, String mode,
      ClientNewDateTime newDateTime)
  {
    appointment.setMode(mode);
    appointment.setDateTime(newDateTime);

    model.modifyAppointment(appointment.getAppointmentID(),
        appointment.getPatientID(), appointment.getDoctor(), newDateTime,
        mode);

  }
}