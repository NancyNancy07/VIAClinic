package client.view.bookAppointment.confirmation;

import client.model.clientBookAppointment.ClientDoctor;
import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.BookAppointmentFrontViewModel;
import client.viewModel.bookAppointment.ConfirmationViewModel;
import client.viewModel.bookAppointment.SelectDoctorViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * ConfirmationViewController is responsible for displaying the confirmation
 * details of an appointment, including the doctor's name, consultation mode,
 * date, and time. It also handles the confirmation and cancellation of the
 * appointment.
 */
public class ConfirmationViewController
{
  private ConfirmationViewModel viewModel;
  @FXML private Label doctorName;
  @FXML private Label mode;
  @FXML private Label date;
  @FXML private Label time;

  /**
   * Initializes the ConfirmationViewController with the provided
   * BookAppointmentFrontViewModel and SelectDoctorViewModel.
   *
   * @param frontViewModel The view model containing front-end appointment data.
   * @param selectDoctorViewModel The view model for selecting a doctor.
   */
  public void init(BookAppointmentFrontViewModel frontViewModel, SelectDoctorViewModel selectDoctorViewModel)
  {
    this.viewModel = new ConfirmationViewModel(frontViewModel,selectDoctorViewModel);

    ClientDoctor doctor = viewModel.getSelectedDoctor();
    doctorName.setText(doctor != null ? doctor.getFirstName() : "Unknown");

    mode.setText(viewModel.getConsultationMode());
    date.setText(viewModel.getAppointmentDate().toString());
    time.setText(viewModel.getAppointmentTime().toString());
  }

  /**
   * Confirms the appointment by calling the confirmAppointment method in the
   * view model. If successful, it navigates to the front view of the
   * BookAppointmentViewHandler.
   */
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

  /**
   * Cancels the appointment by navigating back to the front view of the
   * BookAppointmentViewHandler.
   */
  public void cancel()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }
}
