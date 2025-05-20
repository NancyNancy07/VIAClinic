package client.view.patientJournal.front;

import client.view.patientJournal.PatientJournalViewHandler;
import client.viewModel.loginSystem.LoginSharedData;
import client.viewModel.managePatients.PatientsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PatientJournalController
{
  @FXML private Button diagnosisBtn;
  @FXML private Button prescriptionBtn;
  @FXML private Button vaccinationBtn;
  @FXML private Button labResultBtn;
  @FXML private Button referralBtn;
  @FXML private Label patientName;

  private PatientsViewModel viewModel;

  public void init(PatientsViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientName.setText(LoginSharedData.getInstance().getUsername());
  }

  @FXML private void diagnosisClick()
  {
    PatientJournalViewHandler.showView(
        PatientJournalViewHandler.ViewType.DIAGNOSIS);
  }

  @FXML private void prescriptionClick()
  {
    PatientJournalViewHandler.showView(
        PatientJournalViewHandler.ViewType.PRESCRIPTION);
  }

  @FXML private void referralClick()
  {
    PatientJournalViewHandler.showView(
        PatientJournalViewHandler.ViewType.REFERRAL);
  }
}
