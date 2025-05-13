package client.view.loginSystem.patientView;

import client.model.ClientAppointmentModel;
import client.view.bookAppointment.BookAppointmentGUI;
import client.viewModel.bookAppointment.BookAppointmentViewModel;
import client.viewModel.bookAppointment.BookAppointmentSharedData;
import client.viewModel.loginSystem.LoginSharedData;
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
    patientName.setText(LoginSharedData.getInstance().getUsername());
  }

  public void setAppointmentView() throws Exception
  {
    onAppointmentButtonClick();
  }

  @FXML private void onAppointmentButtonClick() throws Exception
  {
    AppointmentModel model = new ClientAppointmentModel();
    BookAppointmentSharedData sharedData = new BookAppointmentSharedData();
    BookAppointmentViewModel viewModel = new BookAppointmentViewModel(model,
        sharedData);
    startBookAppointmentGUI(viewModel, sharedData);
  }

  private void startBookAppointmentGUI(BookAppointmentViewModel viewModel,
      BookAppointmentSharedData sharedData) throws Exception
  {
    BookAppointmentGUI bookAppointmentGUI = new BookAppointmentGUI();
    bookAppointmentGUI.start(new Stage());
  }
}
