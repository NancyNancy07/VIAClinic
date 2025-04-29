package view.bookAppointment.selectdoctor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.bookAppointment.BookAppointmentViewHandler;
import viewModel.BookAppointmentViewModel;

public class DoctorViewController
{
  private BookAppointmentViewModel viewModel;

  public DoctorViewController(BookAppointmentViewModel viewModel)
  {
    this.viewModel = viewModel;
  }

  @FXML public void initialize()
  {

  }

  public void nextView()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.MODE);
  }

    public void goBack()
    {
      BookAppointmentViewHandler.showView(
          BookAppointmentViewHandler.ViewType.FRONT);
    }
}
