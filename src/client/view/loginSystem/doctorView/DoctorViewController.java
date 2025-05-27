package client.view.loginSystem.doctorView;

import client.view.bookAppointment.BookAppointmentViewHandler;
import client.view.loginSystem.LoginSystemViewHandler;
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

/**
 * DoctorViewController handles the doctor's view in the login system.
 * It initializes the doctor's name and manages navigation to patient and appointment views.
 */
public class DoctorViewController
{
  @FXML private Label doctorName;
  @FXML private Button patients;
  @FXML private Button appointments;
  @FXML private Button message;
  @FXML private Button logout;

  private LoginViewModel loginViewModel;

  /**
   * Default constructor for DoctorViewController.
   * Initializes the controller without any parameters.
   */
  public DoctorViewController()
  {
  }

  /**
   * Initializes the DoctorViewController with the provided LoginViewModel.
   * Sets the doctor's name in the view.
   *
   * @param loginViewModel The view model containing login user information.
   */
  public void init(LoginViewModel loginViewModel)
  {
    this.loginViewModel = loginViewModel;
    doctorName.setText(loginViewModel.getLoginUser());
  }

  /**
   * Sets the patient view by simulating a button click.
   * This method is called to navigate to the patient view.
   *
   * @throws Exception If an error occurs while starting the patient GUI.
   */
  public void setPatientView() throws Exception
  {
    onPatientButtonClick();
  }

  /**
   * Sets the appointment view by simulating a button click.
   * This method is called to navigate to the appointment view.
   *
   * @throws Exception If an error occurs while starting the appointment GUI.
   */
  @FXML private void onPatientButtonClick() throws Exception
  {
    PatientsViewModel viewModel = new PatientsViewModel();
    startPatientGUI(viewModel);
  }

  /**
   * Handles the logout button click event.
   * Navigates back to the front view of the login system.
   */
  @FXML private void onLogoutButtonClick()
  {
    LoginSystemViewHandler.showView(LoginSystemViewHandler.ViewType.FRONT);
  }

  /**
   * Starts the Patient GUI with the provided PatientsViewModel.
   * This method is responsible for initializing and displaying the patient management interface.
   *
   * @param viewModel The view model containing patient data and logic.
   * @throws Exception If an error occurs while starting the GUI.
   */
  private void startPatientGUI(PatientsViewModel viewModel) throws Exception
  {
    PatientGUI patientGUI = new PatientGUI();
    patientGUI.start(new Stage());
  }

  /**
   * Handles the appointment button click event.
   * Navigates to the book appointment GUI.
   *
   * @throws Exception If an error occurs while starting the appointment GUI.
   */
  @FXML private void onAppointmentButtonClick() throws Exception
  {
    startBookAppointmentGUI();
  }

  /**
   * Starts the Book Appointment GUI.
   * This method initializes and displays the book appointment interface for doctors.
   *
   * @throws Exception If an error occurs while starting the GUI.
   */
  private void startBookAppointmentGUI() throws Exception
  {
    DoctorAppointmentGUI doctorAppointmentGUI = new DoctorAppointmentGUI();
    doctorAppointmentGUI.start(new Stage());
  }

}
