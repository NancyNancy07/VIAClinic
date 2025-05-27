package client.view.myPatient;

import client.viewModel.loginSystem.ViewModelFactory;
import client.viewModel.myPatient.PatientInformationViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class PatientInformationGUI extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    PatientInformationViewModelFactory factory = new PatientInformationViewModelFactory();
    PatientInformationViewHandler viewHandler = new PatientInformationViewHandler(
        primaryStage, factory);
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args)
  {
    launch();
  }
}