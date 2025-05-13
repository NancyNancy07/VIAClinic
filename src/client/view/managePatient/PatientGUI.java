package client.view.managePatient;

import client.viewModel.patients.PatientsSharedData;
import client.viewModel.patients.PatientsViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class PatientGUI extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    PatientsSharedData sharedData = new PatientsSharedData();
    PatientsViewModel viewModel = new PatientsViewModel(sharedData);
    ManagePatientViewHandler viewHandler = new ManagePatientViewHandler(
        primaryStage, viewModel);
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args)
  {
    launch();
  }
}
