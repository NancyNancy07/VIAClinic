package client.view.loginSystem;

import javafx.application.Application;
import javafx.stage.Stage;
import client.viewModel.loginSystem.ViewModelFactory;


/**
 * LoginSystemGUI is the main class for the Login System GUI application.
 * It initializes the view model and starts the application.
 */
public class LoginSystemGUI extends Application
{
  /**
   * Initializes the LoginSystemGUI application.
   *
   * @param primaryStage the primary stage for this application
   * @throws Exception if an error occurs during initialization
   */
  @Override public void start(Stage primaryStage) throws Exception
  {
    ViewModelFactory factory = new ViewModelFactory();
    LoginSystemViewHandler viewHandler = new LoginSystemViewHandler(
        primaryStage, factory);
    viewHandler.start(primaryStage);
  }

  /**
   * The main method to launch the Login System GUI application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args)
  {
    launch();
  }
}
