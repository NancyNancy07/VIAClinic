package client.view.bookAppointment;

import client.model.clientBookAppointment.ClientAppointmentModel;
import client.model.clientBookAppointment.ClientAppointmentService;
import client.viewModel.bookAppointment.BookAppointmentViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import client.viewModel.bookAppointment.BookAppointmentFrontViewModel;
import client.viewModel.bookAppointment.BookAppointmentSharedData;

/**
 * BookAppointmentGUI is the main class for the Book Appointment GUI application.
 * It initializes the view model and starts the application.
 */
public class BookAppointmentGUI extends Application
{
  private BookAppointmentFrontViewModel viewModel;

  /**
   * Initializes the BookAppointmentGUI application.
   *
   * @param primaryStage the primary stage for this application
   * @throws Exception if an error occurs during initialization
   */
  @Override public void start(Stage primaryStage) throws Exception
  {
    ClientAppointmentModel model = new ClientAppointmentService();
    viewModel = new BookAppointmentFrontViewModel(model);
    BookAppointmentViewModelFactory factory = new BookAppointmentViewModelFactory();
    BookAppointmentViewHandler viewHandler = new BookAppointmentViewHandler(
        primaryStage, factory);
    viewHandler.start(primaryStage, factory);
  }

  /**
   * The main method to launch the Book Appointment GUI application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args)
  {
    launch();
  }
}
