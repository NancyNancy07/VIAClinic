package client.viewModel.doctorAppointment;

import client.model.clientBookAppointment.ClientAppointmentList;
import client.model.clientBookAppointment.ClientAppointmentModel;
import client.viewModel.bookAppointment.BookAppointmentSharedData;
import client.viewModel.loginSystem.LoginSharedData;

public class DoctorAppointmentViewModel
{
  private ClientAppointmentModel model;
  private BookAppointmentSharedData sharedData;

  public DoctorAppointmentViewModel(ClientAppointmentModel model)
  {
    sharedData = BookAppointmentSharedData.getInstance();
    this.model = model;
  }

  public ClientAppointmentList getDoctorAppointmentList()
  {
    int doctorId = LoginSharedData.getInstance().getId();
    return model.getDoctorAppointmentList(doctorId);
  }
  public  String getUsername(){
    return LoginSharedData.getInstance().getUsername();
  }
}
