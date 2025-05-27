package client.view.loginSystem.doctorView;

import client.model.clientBookAppointment.ClientAppointment;
import client.model.clientBookAppointment.ClientAppointmentModel;
import client.model.clientBookAppointment.ClientAppointmentService;
import client.view.bookAppointment.BookAppointmentGUI;
import client.view.doctorAppointment.DoctorAppointmentGUI;
import client.view.managePatient.PatientGUI;
import client.viewModel.bookAppointment.BookAppointmentFrontViewModel;
import client.viewModel.loginSystem.LoginViewModel;
import client.viewModel.managePatients.PatientsViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DoctorViewController
{
  @FXML private Label doctorName;
  @FXML private Button patients;
  @FXML private Button appointments;
  @FXML private Button message;

  private LoginViewModel loginViewModel;

  public DoctorViewController()
  {
  }

  public void init(LoginViewModel loginViewModel)
  {
    this.loginViewModel = loginViewModel;
    doctorName.setText(loginViewModel.getLoginUser());
  }

  public void setPatientView() throws Exception
  {
    onPatientButtonClick();
  }

  @FXML private void onPatientButtonClick() throws Exception
  {
    PatientsViewModel viewModel = new PatientsViewModel();
    startPatientGUI(viewModel);
  }

  private void startPatientGUI(PatientsViewModel viewModel) throws Exception
  {
    PatientGUI patientGUI = new PatientGUI();
    patientGUI.start(new Stage());
  }

  @FXML private void onAppointmentButtonClick() throws Exception
  {
    startBookAppointmentGUI();
  }

  private void startBookAppointmentGUI() throws Exception
  {
    DoctorAppointmentGUI doctorAppointmentGUI = new DoctorAppointmentGUI();
    doctorAppointmentGUI.start(new Stage());
  }

}
