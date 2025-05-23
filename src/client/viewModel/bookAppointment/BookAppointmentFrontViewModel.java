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
    return model.bookAppointment(date, patientId, doctorId, mode);

  }

  public ClientAppointmentList getAppointmentList()
  {
    int patientId = LoginSharedData.getInstance().getId();
    return model.getAppointmentList(patientId);
  }

}
