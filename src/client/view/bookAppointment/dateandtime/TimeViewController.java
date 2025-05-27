package client.view.bookAppointment.dateandtime;

import client.viewModel.bookAppointment.BookAppointmentSharedData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.SelectDateTimeViewModel;

import java.util.List;

/**
 * Controller for the TimeView, which allows users to select a date and time for an appointment.
 * It initializes the view with the doctor's name and mode, sets available time slots,
 * and handles navigation to the next view or back to the previous view.
 */
public class TimeViewController
{
  private SelectDateTimeViewModel viewModel;

  @FXML private Label doctorName;
  @FXML private Label mode;
  @FXML private DatePicker date;
  @FXML private ComboBox<String> time;

  /**
   * Initializes the TimeViewController with the provided SelectDateTimeViewModel.
   * It sets the doctor's name, mode, and initializes the date and time fields.
   *
   * @param viewModel the view model containing data for the time selection view
   */
  public void init(SelectDateTimeViewModel viewModel)
  {
    this.viewModel = viewModel;

    doctorName.setText(viewModel.getDoctorName());
    mode.setText(viewModel.getMode());

    if (viewModel.getDate() != null)
    {
      date.setValue(viewModel.getDate());
    }
    if (viewModel.getTime() != null)
    {
      time.setValue(viewModel.getTime().toString());
    }

    setTimeSlots();
  }

  /**
   * Sets the available time slots in the ComboBox based on the doctor's availability.
   * The time slots are generated with a 15-minute gap between them from 8 AM to 5 PM.
   */
  private void setTimeSlots()
  {
    List<String> timeSlots = viewModel.generateTimeSlotsWith15MinGap(8, 17);
    if (time != null)
    {
      time.getItems().clear();
      time.getItems().addAll(timeSlots);

      if (!timeSlots.isEmpty())
      {
        time.setValue(timeSlots.get(0));
      }
    }
  }

  /**
   * Navigates to the next view after validating the selected date and time.
   * If both date and time are selected, it updates the shared data and shows the confirmation view.
   * If not, it displays a warning alert to the user.
   */
  public void nextView()
  {
    viewModel.setDate(date.getValue());
    viewModel.setTime(time.getValue());

    if (viewModel.getDate() != null && viewModel.getTime() != null)
    {
      BookAppointmentSharedData.getInstance()
          .setAppointmentTime(viewModel.getTime());
      BookAppointmentSharedData.getInstance()
          .setAppointmentDate(viewModel.getDate());

      BookAppointmentViewHandler.showView(
          BookAppointmentViewHandler.ViewType.CONFIRMATION);
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("No Time or Date Selected");
      alert.setHeaderText(null);
      alert.setContentText("Please select a date and time before continuing.");
      alert.showAndWait();
    }
  }

  /**
   * Navigates back to the previous view (Mode selection view).
   * This method is called when the user wants to go back to the mode selection.
   */
  public void goBack()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.MODE);
  }
}
