package client.viewModel.bookAppointment;

import client.model.clientBookAppointment.ClientAppointment;
import client.model.clientBookAppointment.ClientAppointmentList;
import client.model.clientBookAppointment.ClientAppointmentModel;
import client.model.clientBookAppointment.ClientNewDateTime;
import client.viewModel.loginSystem.LoginSharedData;

public class BookAppointmentFrontViewModel
{
  private ClientAppointmentModel model;
  private BookAppointmentSharedData sharedData;

  public BookAppointmentFrontViewModel(ClientAppointmentModel model)
  {
    sharedData = BookAppointmentSharedData.getInstance();
    this.model = model;
  }

  public ClientAppointment addAppointment(ClientNewDateTime date, int patientId,
      int doctorId, String mode)
  {
    //    PatientAppointmentClient client = new PatientAppointmentClient();
    //    ClientAppointment bookedAppointment = client.bookAppointment(appointment);

    // Return the response from the server (i.e., the created appointment)
    return model.bookAppointment(date, patientId, doctorId, mode);

  }

  public ClientAppointmentList getAppointmentList()
  {
    //    PatientAppointmentClient client = new PatientAppointmentClient();
    //    int patientId = LoginSharedData.getInstance().getId();
    //    List<ClientAppointment> appointments = client.getAppointmentByPatientId(
    //        patientId);
    //    System.out.println(appointments);
    //    if (appointments != null)
    //    {
    //      return appointments;
    //    }
    //
    //    return null;

    int patientId = LoginSharedData.getInstance().getId();
    return model.getAppointmentList(patientId);
  }
}
