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

public class TimeViewController
{
  private SelectDateTimeViewModel viewModel;

  @FXML private Label doctorName;
  @FXML private Label mode;
  @FXML private DatePicker date;
  @FXML private ComboBox<String> time;

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

  public void goBack()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.MODE);
  }
}
