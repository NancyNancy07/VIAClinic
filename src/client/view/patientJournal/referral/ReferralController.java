package client.view.patientJournal.referral;

import client.viewModel.loginSystem.LoginSharedData;
import client.viewModel.patientJournal.PatientReferralViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import server.model.patientJournal.Referral;

public class ReferralController
{
  @FXML private TableView<Referral> referralTableView;
  @FXML private TableColumn<Referral, String> referralColumn;
  @FXML private Label patientName;
  private PatientReferralViewModel viewModel;

  public void init(PatientReferralViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientName.setText(LoginSharedData.getInstance().getUsername());
    referralColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));
    int patientId = viewModel.getPatientId();
    referralTableView.setItems(viewModel.getReferralList(patientId));
  }
}
