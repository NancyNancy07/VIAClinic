package client.view.bookAppointment;

import client.model.ClientAppointmentModel;
import javafx.application.Application;
import javafx.stage.Stage;
import server.model.bookAppointment.*;
import client.viewModel.bookAppointment.BookAppointmentViewModel;
import client.viewModel.bookAppointment.SharedData;

public class BookAppointmentGUI extends Application
{
  private BookAppointmentViewModel viewModel;

  @Override public void start(Stage primaryStage) throws Exception
  {
    AppointmentModel model = new ClientAppointmentModel();
    SharedData sharedData = new SharedData();
    viewModel = new BookAppointmentViewModel(model, sharedData);

    BookAppointmentViewHandler viewHandler = new BookAppointmentViewHandler(
        primaryStage, viewModel, sharedData);
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args)
  {
    launch();
  }
}
