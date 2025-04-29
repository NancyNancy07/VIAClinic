package view.bookAppointment.confirmation;

import javafx.fxml.FXML;
import view.bookAppointment.BookAppointmentViewHandler;
import viewModel.BookAppointmentViewModel;

public class ConfirmationViewController
{
  private BookAppointmentViewModel viewModel;

  public ConfirmationViewController(BookAppointmentViewModel viewModel)
  {
    this.viewModel = viewModel;
  }

  @FXML public void initialize()
  {

  }

  public void confirm()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }

  public void cancel()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }
}
