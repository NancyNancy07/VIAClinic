package client.view.bookAppointment.confirmation;

import client.viewModel.loginSystem.LoginSharedData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.BookAppointmentViewModel;
import client.viewModel.bookAppointment.BookAppointmentSharedData;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConfirmationViewController
{
  private BookAppointmentViewModel viewModel;
  private BookAppointmentSharedData sharedData;
  @FXML private Label doctorName;
  @FXML private Label mode;
  @FXML private Label date;
  @FXML private Label time;

  public void init(BookAppointmentViewModel viewModel, BookAppointmentSharedData sharedData)
  {
    this.viewModel = viewModel;
    this.sharedData = sharedData;

    Doctor selectedDoctor = null;
    for (Doctor doctor : viewModel.getDoctorList())
    {
      if (doctor.getDoctorID() == sharedData.getSelectedDoctorId())
      {
        selectedDoctor = doctor;
        break;
      }
    }

    if (selectedDoctor != null)
    {
      doctorName.setText(selectedDoctor.getName());
    }

    mode.setText(sharedData.getConsultationMode());
    date.setText(sharedData.getAppointmentDate().toString());
    time.setText(sharedData.getAppointmentTime().toString());
  }

  public void confirm()
  {
    String name = doctorName.textProperty().get();
    String modeC = mode.textProperty().get();
    LocalDate appointmentDate = LocalDate.parse(date.getText());
    LocalTime appointmentTime = LocalTime.parse(time.getText());
    NewDateTime newDateTime = new NewDateTime(appointmentDate.getDayOfMonth(),
        appointmentDate.getMonthValue(), appointmentDate.getYear(),
        appointmentTime.getHour(), appointmentTime.getMinute());

    Doctor selectedDoctor = null;
    for (Doctor doctor : viewModel.getDoctorList())
    {
      if (name.equals(doctor.getName()))
      {
        selectedDoctor = doctor;
        break;
      }
    }

    if (selectedDoctor != null)
    {
      int patientId = LoginSharedData.getInstance().getId();
      viewModel.addAppointment(newDateTime, patientId, selectedDoctor, modeC);
    }

    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }

  public void cancel()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }
}
