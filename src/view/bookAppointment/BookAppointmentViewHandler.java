package view.bookAppointment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.bookAppointment.confirmation.ConfirmationViewController;
import view.bookAppointment.dateandtime.TimeViewController;
import view.bookAppointment.front.FrontViewController;
import view.bookAppointment.modeofconsultation.ModeViewController;
import view.bookAppointment.selectdoctor.DoctorViewController;
import viewModel.bookAppointment.BookAppointmentViewModel;
import viewModel.bookAppointment.SharedData;

import java.io.IOException;

public class BookAppointmentViewHandler
{

  public static void start(Stage s)
  {
    stage = s;
    showView(ViewType.FRONT);
    stage.show();
  }

  public enum ViewType
  {
    FRONT, DOCTOR, MODE, TIME, CONFIRMATION
  }

  private static Stage stage;
  private static BookAppointmentViewModel viewModel;
  private static SharedData sharedData;

  public BookAppointmentViewHandler(Stage stage,
      BookAppointmentViewModel viewModel, SharedData sharedData)
  {
    BookAppointmentViewHandler.stage = stage;
    BookAppointmentViewHandler.viewModel = viewModel;
    BookAppointmentViewHandler.sharedData = sharedData;
  }

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

  private static void showFrontView() throws IOException
  {
    FrontViewController controller = new FrontViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        BookAppointmentViewHandler.class.getResource(
            "./front/appointmentFront.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(viewModel,sharedData);
    stage.setTitle("Appointments");
    stage.setScene(scene);
  }

  private static void showDoctorView() throws IOException
  {
    DoctorViewController controller = new DoctorViewController();
    FXMLLoader fxmlLoader = new FXMLLoader(
        BookAppointmentViewHandler.class.getResource(
            "./selectdoctor/doctor.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(viewModel, sharedData);

    stage.setTitle("Select Doctor");
    stage.setScene(scene);
  }

  private static void showModeView() throws IOException
  {
    ModeViewController controller = new ModeViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        BookAppointmentViewHandler.class.getResource(
            "./modeofconsultation/mode.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(viewModel, sharedData);

    stage.setTitle("Select Mode of Consultation");
    stage.setScene(scene);
  }

  private static void showTimeView() throws IOException
  {
    TimeViewController controller = new TimeViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        BookAppointmentViewHandler.class.getResource(
            "./dateandtime/time.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(viewModel, sharedData);

    stage.setTitle("Select Date and Time");
    stage.setScene(scene);
  }

  private static void showConfirmationView() throws IOException
  {
    ConfirmationViewController controller = new ConfirmationViewController();

    FXMLLoader fxmlLoader = new FXMLLoader(
        BookAppointmentViewHandler.class.getResource(
            "./confirmation/confirmation.fxml"));

    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    controller.init(viewModel, sharedData);

    stage.setTitle("Confirmation");
    stage.setScene(scene);
  }
}
