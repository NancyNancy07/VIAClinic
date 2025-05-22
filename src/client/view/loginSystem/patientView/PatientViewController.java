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
}
