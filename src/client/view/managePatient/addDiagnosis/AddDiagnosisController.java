package client.view.managePatient.addDiagnosis;

import client.viewModel.patientsJournal.AddDiagnosisViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Diagnosis;

public class AddDiagnosisController
{
  @FXML private TextField diagnosisName;
  @FXML private TextField patientName;
  @FXML private Label patientName2;
  @FXML private TextField statusField;
  @FXML private TextField prescriptionField;
  @FXML private DatePicker dateAddedField;
  @FXML private TableView<Diagnosis> diagnosisTableView;
  @FXML private TableColumn<Diagnosis, String> diagnosisCol;
  private AddDiagnosisViewModel viewModel;

  public void init(AddDiagnosisViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientName.setText(viewModel.getPatientName());
    patientName2.setText(viewModel.getPatientName());
    diagnosisCol.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));

    diagnosisTableView.setItems(viewModel.getDiagnoses());
  }

  @FXML private void setDiagnosisName()
  {
    String diagnosis = diagnosisName.getText();
    String status = statusField.getText();

    NewDateTime date = new NewDateTime(dateAddedField.getValue().getYear(),
        dateAddedField.getValue().getMonth().getValue(),
        dateAddedField.getValue().getDayOfMonth(), 0, 0);
    String prescription = (prescriptionField.getText());

    if (diagnosis.isEmpty() || status.isEmpty() || date == null
        || prescription.isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Input Required");
      alert.setHeaderText(null);
      alert.setContentText("Field cannot be empty.");
      alert.showAndWait();
      return;
    }

    viewModel.addDiagnosis(diagnosis, status, date, prescription);
    diagnosisName.clear();
    statusField.clear();
    prescriptionField.clear();
    dateAddedField.setValue(null);
  }
}