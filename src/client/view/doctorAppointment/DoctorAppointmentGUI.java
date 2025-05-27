package client.view.doctorAppointment;

import client.model.clientBookAppointment.ClientAppointmentModel;
import client.model.clientBookAppointment.ClientAppointmentService;
import client.view.doctorAppointment.front.DoctorAppointmentFrontController;
import client.viewModel.doctorAppointment.DoctorAppointmentViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * DoctorAppointmentGUI is the main class for the Doctor Appointment GUI application.
 * It initializes the view model and starts the application.
 */
public class DoctorAppointmentGUI extends Application
{
  private DoctorAppointmentViewModel viewModel;

  /**
   * Initializes the DoctorAppointmentGUI application.
   *
   * @param primaryStage the primary stage for this application
   * @throws Exception if an error occurs during initialization
   */
  @Override public void start(Stage primaryStage) throws Exception
  {
    ClientAppointmentModel model = new ClientAppointmentService();
    viewModel = new DoctorAppointmentViewModel(model);
    DoctorAppointmentFrontController controller = new DoctorAppointmentFrontController();

    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("./front/front.fxml"));

    loader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(loader.load());
       controller.init(viewModel);

    primaryStage.setTitle("Login for VIAClinic");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * The main method to launch the Doctor Appointment GUI application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args)
  {
    launch();
  }
}
