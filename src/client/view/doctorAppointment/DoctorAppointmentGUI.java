package client.view.doctorAppointment;

import client.model.clientBookAppointment.ClientAppointmentModel;
import client.model.clientBookAppointment.ClientAppointmentService;
import client.view.doctorAppointment.front.DoctorAppointmentFrontController;
import client.viewModel.doctorAppointment.DoctorAppointmentViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DoctorAppointmentGUI extends Application
{
  private DoctorAppointmentViewModel viewModel;

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

  public static void main(String[] args)
  {
    launch();
  }
}
