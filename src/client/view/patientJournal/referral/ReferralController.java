package client.view.patientJournal.referral;

import client.view.patientJournal.PatientJournalViewHandler;
import client.viewModel.loginSystem.LoginSharedData;
import client.viewModel.patientJournal.PatientReferralViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import server.model.patientJournal.Referral;

/**
 * ReferralController is responsible for managing the referral view in the patient journal.
 * It initializes the referral table and displays the patient's name.
 */
public class ReferralController
{
  @FXML private TableView<Referral> referralTableView;
  @FXML private TableColumn<Referral, String> referralColumn;
  @FXML private Label patientName;
  private PatientReferralViewModel viewModel;

  /**
   * Initializes the controller with the provided view model.
   * It sets up the referral table and binds the data from the view model.
   *
   * @param viewModel the view model containing referral data and logic
   */
  public void init(PatientReferralViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientName.setText(LoginSharedData.getInstance().getUsername());
    referralColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));
    int patientId = viewModel.getPatientId();
    referralTableView.setItems(viewModel.getReferralList(patientId));
  }

  /**
   * Handles the action when the "Back" button is clicked.
   */
  @FXML
  private void onBackButtonClick() {
    PatientJournalViewHandler.showView(PatientJournalViewHandler.ViewType.FRONT);
  }
}
