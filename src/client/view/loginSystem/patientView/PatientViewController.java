package client.view.loginSystem.patientView;

import client.model.clientBookAppointment.ClientAppointmentModel;
import client.model.clientBookAppointment.ClientAppointmentService;
import client.view.bookAppointment.BookAppointmentGUI;
import client.view.patientJournal.PatientJournalGUI;
import client.viewModel.bookAppointment.BookAppointmentFrontViewModel;
import client.viewModel.loginSystem.LoginSharedData;
import client.viewModel.loginSystem.LoginViewModel;
import client.viewModel.managePatients.PatientsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import client.view.loginSystem.LoginSystemViewHandler;
import client.view.myPatient.PatientInformationGUI;
import client.viewModel.myPatient.PatientInformationSharedData;

import javafx.scene.control.Button;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.PatientDAO;

/**
 * PatientViewController handles the patient's view in the login system.
 * It initializes the patient's name and manages navigation to appointment and journal views.
 */
public class PatientViewController
{
  @FXML private Label patientName;
  @FXML private Button logout;
  private LoginViewModel loginViewModel;

  /**
   * Default constructor for PatientViewController.
   * Initializes the controller without any parameters.
   *
   * @param loginViewModel The view model containing login user information.
   */
  public void init(LoginViewModel loginViewModel)
  {
    this.loginViewModel = loginViewModel;
    patientName.setText(LoginSharedData.getInstance().getUsername());
  }

  /**
   * Sets the appointment view by simulating a button click.
   * This method is called to navigate to the appointment view.
   *
   * @throws Exception If an error occurs while starting the appointment GUI.
   */
  public void setAppointmentView() throws Exception
  {
    onAppointmentButtonClick();
  }

  /**
   * Sets the patient journal view by simulating a button click.
   * This method is called to navigate to the patient journal view.
   *
   * @throws Exception If an error occurs while starting the patient journal GUI.
   */
  @FXML private void onAppointmentButtonClick() throws Exception
  {
    ClientAppointmentModel model = new ClientAppointmentService();
    BookAppointmentFrontViewModel viewModel = new BookAppointmentFrontViewModel(
        model);
    startBookAppointmentGUI(viewModel);
  }

  /**
   * Sets the patient journal view by simulating a button click.
   * This method is called to navigate to the patient journal view.
   *
   * @throws Exception If an error occurs while starting the patient journal GUI.
   */
  @FXML private void onPatientJournalButtonClick() throws Exception
  {
    PatientsViewModel viewModel = new PatientsViewModel();
    startPatientJournalGUI(viewModel);
  }

  /**
   * Initializes the PatientInformationViewHandler and starts the PatientInformationGUI.
   * This method is called to display the patient's information.
   *
   * @throws Exception If an error occurs while starting the patient information GUI.
   */
  private void startBookAppointmentGUI(BookAppointmentFrontViewModel viewModel)
      throws Exception
  {
    BookAppointmentGUI bookAppointmentGUI = new BookAppointmentGUI();
    bookAppointmentGUI.start(new Stage());
  }

  /**
   * Initializes the PatientJournalGUI and starts it.
   * This method is called to display the patient's journal.
   *
   * @param viewModel The view model containing patient data.
   * @throws Exception If an error occurs while starting the patient journal GUI.
   */
  private void startPatientJournalGUI(PatientsViewModel viewModel)
      throws Exception
  {
    PatientJournalGUI patientJournalGUI = new PatientJournalGUI();
    patientJournalGUI.start(new Stage());
  }

  /**
   * Handles the logout button click event.
   * Navigates back to the front view of the login system.
   */
  @FXML private void onLogoutButtonClick()
  {
    loginViewModel.clearCredentials();
    LoginSystemViewHandler.showView(LoginSystemViewHandler.ViewType.FRONT);

  }

  /**
   * Sets the patient information view by simulating a button click.
   * This method is called to navigate to the patient information view.
   *
   * @throws Exception If an error occurs while starting the patient information GUI.
   */
  @FXML private void setPatientInformationView() throws Exception
  {
    int patientId = LoginSharedData.getInstance().getId();
    Patient patient = PatientDAO.getInstance().getPatientById(patientId);
    if (patient != null)
    {
      PatientInformationSharedData shared = PatientInformationSharedData.getInstance();
      shared.setPatientName(patient.getName());
      shared.setEmail(patient.getEmail());
      shared.setPhoneNumber(patient.getPhoneNumber());
      shared.setCpr(patient.getCPR());
      shared.setAddress(patient.getAddress());
    }

    PatientInformationGUI gui = new PatientInformationGUI();
    gui.start(new Stage());
  }
}
