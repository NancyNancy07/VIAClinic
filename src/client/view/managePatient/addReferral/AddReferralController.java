package client.view.managePatient.addReferral;

import client.view.managePatient.ManagePatientViewHandler;
import client.viewModel.managePatients.AddReferralViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Referral;

import java.time.LocalDate;

public class AddReferralController
{
  @FXML private Label patientNameLabel;

  @FXML private TableView<Referral> referralTable;
  @FXML private TableColumn<Referral, String> reasonColumn;
  @FXML private TableColumn<Referral, String> commentColumn;
  @FXML private TableColumn<Referral, String> dateAddedColumn;

  @FXML private TextField reasonField;
  @FXML private TextField commentField;

  private AddReferralViewModel viewModel;

  public void init(AddReferralViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientNameLabel.setText(viewModel.getPatientName());
    referralTable.setItems(viewModel.getReferrals());

    reasonColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getReason()));

    commentColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getComment()));

    dateAddedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getDateCreated().toString()));
  }

  @FXML private void onAddReferral()
  {
    try
    {
      String reason = reasonField.getText();
      String comment = commentField.getText();
      NewDateTime date = new NewDateTime(LocalDate.now().getYear(),
          LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), 0,
          0);
      viewModel.addReferral(date, reason, comment, viewModel.getDoctorId(),
          viewModel.getPatientId());

      reasonField.clear();
      commentField.clear();
    }
    catch (Exception e)
    {
      showAlert(
          "Invalid input. Please make sure all fields are filled in correctly.");
    }
  }

  private void showAlert(String msg)
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.showAndWait();
  }
  @FXML
  private void back()
  {
    ManagePatientViewHandler.showView(ManagePatientViewHandler.ViewType.FRONT);
  }
}
