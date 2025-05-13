package client.view.bookAppointment.dateandtime;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.BookAppointmentViewModel;
import client.viewModel.bookAppointment.BookAppointmentSharedData;

import java.util.List;

public class TimeViewController
{
  private BookAppointmentViewModel viewModel;
  private BookAppointmentSharedData sharedData;
  @FXML private Label doctorName;
  @FXML private Label mode;
  @FXML private DatePicker date;
  @FXML private ComboBox<String> time;

  public void init(BookAppointmentViewModel viewModel, BookAppointmentSharedData sharedData)
  {
    this.viewModel = viewModel;
    this.sharedData = sharedData;
    String name = "";
    for (int i = 0; i < viewModel.getDoctorList().size(); i++)
    {
      if (viewModel.getDoctorList().get(i).getDoctorID()
          == sharedData.getSelectedDoctorId())
      {
        name = viewModel.getDoctorList().get(i).getName();
      }

    }
    doctorName.setText(name);
    mode.setText(sharedData.getConsultationMode());

    if (sharedData.getAppointmentDate() != null)
    {
      date.setValue(sharedData.getAppointmentDate());
    }
    if (sharedData.getAppointmentTime() != null)
    {
      time.setValue((sharedData.getAppointmentTime().toString()));
    }
    setTimeSlots();
  }

  private void setTimeSlots()
  {
    // Generate time slots based on ViewModel logic
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

    if (sharedData.getAppointmentDate() != null
        && sharedData.getAppointmentTime() != null)
    {
      BookAppointmentViewHandler.showView(
          BookAppointmentViewHandler.ViewType.CONFIRMATION);
    }

  }

  public void goBack()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.DOCTOR);
  }
}
