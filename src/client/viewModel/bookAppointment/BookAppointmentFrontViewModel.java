package client.viewModel.bookAppointment;

import client.clientNetwork.PatientAppointmentClient;
import client.viewModel.loginSystem.LoginSharedData;
import server.model.bookAppointment.*;

import java.util.List;

public class BookAppointmentFrontViewModel
{
  private AppointmentModel model;
  private BookAppointmentSharedData sharedData = BookAppointmentSharedData.getInstance();

  public BookAppointmentFrontViewModel(AppointmentModel model)
  {
    this.sharedData = sharedData;
    this.model = model;
  }

  public Appointment addAppointment(NewDateTime date, int patientId,
      Doctor doctor, String mode)
  {
    Appointment appointment = new Appointment(date, patientId, doctor, mode);

    PatientAppointmentClient client = new PatientAppointmentClient();
    //    Appointment bookedAppointment = client.bookAppointment(appointment);

    // Return the response from the server (i.e., the created appointment)
    return appointment;

  }

  public List<Appointment> getAppointmentList()
  {
    PatientAppointmentClient client = new PatientAppointmentClient();
    int patientId = LoginSharedData.getInstance().getId();
    List<Appointment> appointments = client.getAppointmentByPatientId(
        patientId);
    System.out.println(appointments);
    if (appointments != null)
    {
      return appointments;
    }

    return null;
  }
}
