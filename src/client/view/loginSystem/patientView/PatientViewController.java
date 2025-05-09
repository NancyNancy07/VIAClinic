package client.view.loginSystem.patientView;

import client.model.ClientAppointmentModel;
import client.view.bookAppointment.BookAppointmentGUI;
import client.viewModel.bookAppointment.BookAppointmentViewModel;
import client.viewModel.bookAppointment.SharedData;
import client.viewModel.loginSystem.AppointmentViewModel;
import client.viewModel.loginSystem.LoginDataStore;
import client.viewModel.loginSystem.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import server.model.bookAppointment.AppointmentModel;

public class PatientViewController
{
  @FXML private Label patientName;
  private LoginViewModel loginViewModel;

  public void init(LoginViewModel loginViewModel)
  {
    this.loginViewModel = loginViewModel;
    patientName.setText(LoginDataStore.getInstance().getUsername());
  }

  public void setAppointmentView() throws Exception
  {
    onAppointmentButtonClick();
  }

  @FXML private void onAppointmentButtonClick() throws Exception
  {
    AppointmentModel model = new ClientAppointmentModel();
    SharedData sharedData = new SharedData();
    BookAppointmentViewModel viewModel = new BookAppointmentViewModel(model,
        sharedData);
    startBookAppointmentGUI(viewModel, sharedData);
  }

  private void startBookAppointmentGUI(BookAppointmentViewModel viewModel,
      SharedData sharedData) throws Exception
  {
    BookAppointmentGUI bookAppointmentGUI = new BookAppointmentGUI();
    bookAppointmentGUI.start(new Stage());
  }
}
