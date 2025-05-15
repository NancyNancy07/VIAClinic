package client.view.loginSystem.doctorView;

import client.view.managePatient.PatientGUI;
import client.viewModel.loginSystem.LoginViewModel;
import client.viewModel.patientsJournal.PatientsViewModel;
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
}
