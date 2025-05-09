package client.view.loginSystem;

import javafx.application.Application;
import javafx.stage.Stage;
import client.viewModel.loginSystem.ViewModelFactory;


public class LoginSystemGUI extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    ViewModelFactory factory = new ViewModelFactory();
    LoginSystemViewHandler viewHandler = new LoginSystemViewHandler(
        primaryStage, factory);
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args)
  {
    launch();
  }
}
