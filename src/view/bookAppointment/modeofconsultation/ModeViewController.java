package view.bookAppointment.modeofconsultation;

import javafx.fxml.FXML;
import view.bookAppointment.BookAppointmentViewHandler;
import viewModel.BookAppointmentViewModel;

public class ModeViewController
{
  private BookAppointmentViewModel viewModel;

  public ModeViewController(BookAppointmentViewModel viewModel)
  {
    this.viewModel = viewModel;
  }

  @FXML public void initialize()
  {

  }

  public void nextView()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.TIME);
  }
}
