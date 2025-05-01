package client.view.bookAppointment.dateandtime;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.BookAppointmentViewModel;
import client.viewModel.bookAppointment.SharedData;

public class TimeViewController
{
  private BookAppointmentViewModel viewModel;
  private SharedData sharedData;
  @FXML private Label doctorName;
  @FXML private Label mode;
  @FXML private DatePicker date;

  public void init(BookAppointmentViewModel viewModel, SharedData sharedData)
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
  }

  public void nextView()
  {
    sharedData.setAppointmentDate(date.getValue());

    if (sharedData.getAppointmentDate() != null)
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
