package view.bookAppointment;

import javafx.application.Application;
import javafx.stage.Stage;
import viewModel.BookAppointmentViewModel;

public class BookAppointmentGUI extends Application
{
  private static BookAppointmentViewModel viewModel;

  @Override public void start(Stage primaryStage) throws Exception
  {
    BookAppointmentViewHandler viewHandler = new BookAppointmentViewHandler(
        primaryStage, viewModel);
    viewHandler.start(primaryStage);
  }
}
