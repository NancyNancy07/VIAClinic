package view.bookAppointment.front;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.bookAppointment.BookAppointmentViewHandler;
import viewModel.BookAppointmentViewModel;

public class FrontViewController
{
  private BookAppointmentViewModel viewModel;

  public FrontViewController(BookAppointmentViewModel viewModel)
  {
    this.viewModel = viewModel;
  }

  public void addNewAppointment()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.DOCTOR);
  }
}
