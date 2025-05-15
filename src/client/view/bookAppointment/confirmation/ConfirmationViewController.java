package client.view.bookAppointment.confirmation;

import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.BookAppointmentFrontViewModel;
import client.viewModel.bookAppointment.ConfirmationViewModel;
import client.viewModel.bookAppointment.SelectDoctorViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import server.model.bookAppointment.Doctor;

public class ConfirmationViewController
{
  private ConfirmationViewModel viewModel;
  @FXML private Label doctorName;
  @FXML private Label mode;
  @FXML private Label date;
  @FXML private Label time;

  public void init(BookAppointmentFrontViewModel frontViewModel, SelectDoctorViewModel selectDoctorViewModel)
  {
    this.viewModel = new ConfirmationViewModel(frontViewModel,selectDoctorViewModel);

    Doctor doctor = viewModel.getSelectedDoctor();
    doctorName.setText(doctor != null ? doctor.getName() : "Unknown");

    mode.setText(viewModel.getConsultationMode());
    date.setText(viewModel.getAppointmentDate().toString());
    time.setText(viewModel.getAppointmentTime().toString());
  }

  public void confirm()
  {
    if (viewModel.confirmAppointment())
    {
      BookAppointmentViewHandler.showView(
          BookAppointmentViewHandler.ViewType.FRONT);
    }
    else
    {
    }
  }

  public void cancel()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }
}
