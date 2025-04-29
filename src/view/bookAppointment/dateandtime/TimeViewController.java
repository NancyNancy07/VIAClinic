package view.bookAppointment.dateandtime;

import javafx.fxml.FXML;
import view.bookAppointment.BookAppointmentViewHandler;
import viewModel.BookAppointmentViewModel;

public class TimeViewController
{
  private BookAppointmentViewModel viewModel;

  public TimeViewController(BookAppointmentViewModel viewModel)
  {
    this.viewModel = viewModel;
  }

  @FXML public void initialize()
  {

  }

  public void nextView()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.CONFIRMATION);
  }

  public void goBack()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.DOCTOR);
  }
}
