package client.view.loginSystem.patientView;

import client.model.clientBookAppointment.ClientAppointmentModel;
import client.model.clientBookAppointment.ClientAppointmentService;
import client.view.bookAppointment.BookAppointmentGUI;
import client.view.loginSystem.LoginSystemViewHandler;
import client.view.myPatient.PatientInformationGUI;
import client.view.myPatient.PatientInformationViewHandler;
import client.view.patientJournal.PatientJournalGUI;
import client.viewModel.bookAppointment.BookAppointmentFrontViewModel;
import client.viewModel.loginSystem.LoginSharedData;
import client.viewModel.loginSystem.LoginViewModel;
import client.viewModel.managePatients.PatientsViewModel;
import client.viewModel.myPatient.PatientInformationSharedData;
import client.viewModel.myPatient.PatientInformationViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.PatientDAO;

public class PatientViewController
{
  @FXML private Label patientName;
  @FXML private Button logout;
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
    ClientAppointmentModel model = new ClientAppointmentService();
    BookAppointmentFrontViewModel viewModel = new BookAppointmentFrontViewModel(model);
    startBookAppointmentGUI(viewModel);
  }

  @FXML private void onPatientJournalButtonClick() throws Exception
  {
    PatientsViewModel viewModel = new PatientsViewModel();
    startPatientJournalGUI(viewModel);
  }

  private void startBookAppointmentGUI(BookAppointmentFrontViewModel viewModel) throws Exception
  {
    BookAppointmentGUI bookAppointmentGUI = new BookAppointmentGUI();
    bookAppointmentGUI.start(new Stage());
  }

  private void startPatientJournalGUI(PatientsViewModel viewModel) throws Exception
  {
    PatientJournalGUI patientJournalGUI = new PatientJournalGUI();
    patientJournalGUI.start(new Stage());
  }

  @FXML private void onLogoutButtonClick()
  {
    LoginSystemViewHandler.showView(LoginSystemViewHandler.ViewType.FRONT);
  }

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
