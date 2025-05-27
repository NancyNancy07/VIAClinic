package client.viewModel.bookAppointment;

import client.model.clientBookAppointment.*;
import client.viewModel.loginSystem.LoginSharedData;

public class BookAppointmentFrontViewModel
{
  private final ClientAppointmentModel model;

  public BookAppointmentFrontViewModel(ClientAppointmentModel model)
  {
    this.model = model;
  }

  public ClientAppointment addAppointment(ClientNewDateTime date, int patientId,
      ClientDoctor doctor, String mode)
  {
    return model.bookAppointment(date, patientId, doctor, mode);

  }

  public ClientAppointmentList getAppointmentList()
  {
    int patientId = LoginSharedData.getInstance().getId();
    return model.getAppointmentList(patientId);

  }


  public boolean cancelAppointment(int appointmentId)
  {
    return model.cancelAppointment(appointmentId);
  }
}
