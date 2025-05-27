package client.view.patientJournal.front;

import client.view.patientJournal.PatientJournalViewHandler;
import client.viewModel.loginSystem.LoginSharedData;
import client.viewModel.managePatients.PatientsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * PatientJournalController handles the patient's journal view in the application.
 * It initializes the patient's name and manages navigation to different sections of the journal.
 */
public class PatientJournalController
{
  @FXML private Button diagnosisBtn;
  @FXML private Button prescriptionBtn;
  @FXML private Button vaccinationBtn;
  @FXML private Button labResultBtn;
  @FXML private Button referralBtn;
  @FXML private Label patientName;

  private PatientsViewModel viewModel;

  /**
   * Default constructor for PatientJournalController.
   * Initializes the controller without any parameters.
   */
  public void init(PatientsViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientName.setText(LoginSharedData.getInstance().getUsername());
  }

  /**
   * Sets the diagnosis view by simulating a button click.
   * This method is called to navigate to the diagnosis view.
   *
   * @throws Exception If an error occurs while starting the diagnosis GUI.
   */
  @FXML private void diagnosisClick()
  {
    PatientJournalViewHandler.showView(
        PatientJournalViewHandler.ViewType.DIAGNOSIS);
  }

  /**
   * Sets the prescription view by simulating a button click.
   * This method is called to navigate to the prescription view.
   *
   * @throws Exception If an error occurs while starting the prescription GUI.
   */
  @FXML private void prescriptionClick()
  {
    PatientJournalViewHandler.showView(
        PatientJournalViewHandler.ViewType.PRESCRIPTION);
  }

  /**
   * Sets the lab result view by simulating a button click.
   * This method is called to navigate to the lab result view.
   *
   * @throws Exception If an error occurs while starting the lab result GUI.
   */
  @FXML private void labResultClick()
  {
    PatientJournalViewHandler.showView(
        PatientJournalViewHandler.ViewType.LABRESULT);
  }


  /**
   * Sets the referral view by simulating a button click.
   * This method is called to navigate to the referral view.
   *
   * @throws Exception If an error occurs while starting the referral GUI.
   */
  @FXML private void referralClick()
  {
    PatientJournalViewHandler.showView(
        PatientJournalViewHandler.ViewType.REFERRAL);
  }


  /**
   * Sets the vaccination view by simulating a button click.
   * This method is called to navigate to the vaccination view.
   *
   * @throws Exception If an error occurs while starting the vaccination GUI.
   */
  @FXML
  private void vaccinationClick()
  {
    PatientJournalViewHandler.showView(PatientJournalViewHandler.ViewType.VACCINATION);
  }
}
