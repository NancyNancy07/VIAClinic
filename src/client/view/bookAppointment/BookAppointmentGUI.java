package client.view.bookAppointment;

import client.model.clientBookAppointment.ClientAppointmentModel;
import client.model.clientBookAppointment.ClientAppointmentService;
import client.viewModel.bookAppointment.BookAppointmentViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import client.viewModel.bookAppointment.BookAppointmentFrontViewModel;
import client.viewModel.bookAppointment.BookAppointmentSharedData;

public class BookAppointmentGUI extends Application
{
  private BookAppointmentFrontViewModel viewModel;

  @Override public void start(Stage primaryStage) throws Exception
  {
    ClientAppointmentModel model = new ClientAppointmentService();
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
