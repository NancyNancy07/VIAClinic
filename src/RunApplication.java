import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.bookAppointment.BookAppointmentController;

public class RunApplication extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    BookAppointmentController controller = new BookAppointmentController();
    FXMLLoader fxmlLoader = new FXMLLoader(
        RunApplication.class.getResource(
            "view/bookAppointment/bookAppointment.fxml"));
    fxmlLoader.setControllerFactory(e -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    primaryStage.setTitle("Book Appointment");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
