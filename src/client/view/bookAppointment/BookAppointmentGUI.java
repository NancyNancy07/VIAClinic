package client.view.bookAppointment;

import client.model.ClientAppointmentModel;
import client.viewModel.bookAppointment.BookAppointmentViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import server.model.bookAppointment.*;
import client.viewModel.bookAppointment.BookAppointmentFrontViewModel;
import client.viewModel.bookAppointment.BookAppointmentSharedData;

public class BookAppointmentGUI extends Application
{
  private BookAppointmentFrontViewModel viewModel;

  @Override public void start(Stage primaryStage) throws Exception
  {
    AppointmentModel model = new ClientAppointmentModel();
    BookAppointmentSharedData sharedData = BookAppointmentSharedData.getInstance();
    viewModel = new BookAppointmentFrontViewModel(model);
    BookAppointmentViewModelFactory factory = new BookAppointmentViewModelFactory();
    BookAppointmentViewHandler viewHandler = new BookAppointmentViewHandler(
        primaryStage, factory);
    viewHandler.start(primaryStage, factory);
  }

  public static void main(String[] args)
  {
    launch();
  }
}
