package client.view.bookAppointment;

import client.viewModel.bookAppointment.BookAppointmentViewModelFactory;
import client.view.bookAppointment.confirmation.ConfirmationViewController;
import client.view.bookAppointment.dateandtime.TimeViewController;
import client.view.bookAppointment.front.FrontViewController;
import client.view.bookAppointment.modeofconsultation.ModeViewController;
import client.view.bookAppointment.selectdoctor.SelectDoctorViewController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * BookAppointmentViewHandler is responsible for managing the different views
 * related to booking an appointment. It uses a factory to get the appropriate
 * ViewModel for each view and handles the transitions between them.
 */
public class BookAppointmentViewHandler
{
  private static Stage stage;
  private static BookAppointmentViewModelFactory factory;

  /**
   * Enum representing the different types of views in the appointment booking
   * process.
   */
  public enum ViewType
  {
    FRONT, DOCTOR, MODE, TIME, CONFIRMATION
  }

  // Constructor now takes factory instead of single ViewModel
  /**
   * Constructor for BookAppointmentViewHandler.
   *
   * @param stage   The primary stage for the application.
   * @param factory The factory to create ViewModels for the different views.
   */
  public BookAppointmentViewHandler(Stage stage,
      BookAppointmentViewModelFactory factory)
  {
    BookAppointmentViewHandler.stage = stage;
    BookAppointmentViewHandler.factory = factory;
  }

  /**
   * Starts the application by showing the front view.
   *
   * @param s       The primary stage for the application.
   * @param factory The factory to create ViewModels for the different views.
   */
  public static void start(Stage s, BookAppointmentViewModelFactory factory)
  {
    stage = s;
    BookAppointmentViewHandler.factory = factory;
    showView(ViewType.FRONT);
    stage.show();
  }

  /**
   * Displays the specified view based on the ViewType.
   *
   * @param view The type of view to display.
   */
  public static void showView(ViewType view)
  {
    try
    {
      switch (view)
      {
        case FRONT -> showFrontView();
        case DOCTOR -> showDoctorView();
        case MODE -> showModeView();
        case TIME -> showTimeView();
        case CONFIRMATION -> showConfirmationView();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Displays the front view for booking appointments.
   *
   * @throws IOException If there is an error loading the FXML file.
   */
  private static void showFrontView() throws IOException
  {
    FrontViewController controller = new FrontViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        BookAppointmentViewHandler.class.getResource(
            "./front/appointmentFront.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getFrontViewModel());

    stage.setTitle("Appointments");
    stage.setScene(scene);
  }

  /**
   * Displays the doctor selection view.
   *
   * @throws IOException If there is an error loading the FXML file.
   */
  private static void showDoctorView() throws IOException
  {
    SelectDoctorViewController controller = new SelectDoctorViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        BookAppointmentViewHandler.class.getResource(
            "./selectdoctor/doctor.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(factory.getDoctorViewModel());

    stage.setTitle("Select Doctor");
    stage.setScene(scene);
  }

  /**
   * Displays the mode of consultation selection view.
   *
   * @throws IOException If there is an error loading the FXML file.
   */
  private static void showModeView() throws IOException
  {
    ModeViewController controller = new ModeViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        BookAppointmentViewHandler.class.getResource(
            "./modeofconsultation/mode.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());

    controller.init(factory.getModeViewModel());

    stage.setTitle("Select Mode of Consultation");
    stage.setScene(scene);
  }

  /**
   * Displays the date and time selection view.
   *
   * @throws IOException If there is an error loading the FXML file.
   */
  private static void showTimeView() throws IOException
  {
    TimeViewController controller = new TimeViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        BookAppointmentViewHandler.class.getResource(
            "./dateandtime/time.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());

    controller.init(factory.getDateTimeViewModel());

    stage.setTitle("Select Date and Time");
    stage.setScene(scene);
  }

  /**
   * Displays the confirmation view after booking an appointment.
   *
   * @throws IOException If there is an error loading the FXML file.
   */
  private static void showConfirmationView() throws IOException
  {
    ConfirmationViewController controller = new ConfirmationViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        BookAppointmentViewHandler.class.getResource(
            "./confirmation/confirmation.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());

    controller.init(factory.getFrontViewModel(), factory.getDoctorViewModel());

    stage.setTitle("Confirmation");
    stage.setScene(scene);
  }
}
