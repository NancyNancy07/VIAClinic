package client.viewModel.loginSystem;

import java.util.List;
import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.AppointmentModel;

public class AppointmentViewModel {
  private AppointmentModel model;

  public AppointmentViewModel(AppointmentModel model) {
    this.model = model;
  }

  public List<Appointment> getAppointmentList() {
    return this.model.getAppointmentList().getAllAppointments();
  }
}
