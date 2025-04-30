package view.bookAppointment.confirmation;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import view.bookAppointment.BookAppointmentViewHandler;
import viewModel.bookAppointment.BookAppointmentViewModel;
import viewModel.bookAppointment.SharedData;

import java.time.LocalDate;

public class ConfirmationViewController
{
  private BookAppointmentViewModel viewModel;
  private SharedData sharedData;
  @FXML private Label doctorName;
  @FXML private Label mode;
  @FXML private Label date;

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
    date.setText(sharedData.getAppointmentDate().toString());
  }

  public void confirm()
  {
    String name = doctorName.textProperty().get();
    String modeC = mode.textProperty().get();
    LocalDate appointmentDate = LocalDate.parse(date.getText());
    int doctorId = -1;
    for (int i = 0; i < viewModel.getDoctorList().size(); i++)
    {
      if (name.equals(viewModel.getDoctorList().get(i).getName()))
      {
        doctorId = viewModel.getDoctorList().get(i).getDoctorID();
      }
    }
    viewModel.addAppointment(1, doctorId, appointmentDate, modeC);
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }

  public void cancel()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }
}
