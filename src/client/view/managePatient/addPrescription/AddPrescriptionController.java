package client.view.managePatient.addPrescription;

import client.view.managePatient.ManagePatientViewHandler;
import client.viewModel.managePatients.AddPrescriptionViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Prescription;

public class AddPrescriptionController
{
  @FXML private Label patientNameLabel;

  @FXML private TableView<Prescription> prescriptionTable;
  @FXML private TableColumn<Prescription, String> medicineColumn;
  @FXML private TableColumn<Prescription, String> doseColumn;
  @FXML private TableColumn<Prescription, String> frequencyColumn;
  @FXML private TableColumn<Prescription, String> startDateColumn;
  @FXML private TableColumn<Prescription, String> endDateColumn;
  @FXML private TableColumn<Prescription, String> statusColumn;
  @FXML private TableColumn<Prescription, String> commentColumn;

  @FXML private TextField medicineField;
  @FXML private TextField doseAmountField;
  @FXML private TextField doseUnitField;
  @FXML private TextField frequencyField;
  @FXML private TextField statusField;
  @FXML private TextField commentField;
  @FXML private DatePicker startDatePicker;
  @FXML private DatePicker endDatePicker;

  private AddPrescriptionViewModel viewModel;

  public void init(AddPrescriptionViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientNameLabel.setText(viewModel.getPatientName());
    prescriptionTable.setItems(viewModel.getPrescriptions());

    medicineColumn.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
    doseColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getDoseAmount() + " " + cellData.getValue().getDoseUnit()));
    frequencyColumn.setCellValueFactory(new PropertyValueFactory<>("frequency"));
    startDateColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getStartDate().toString()));
    endDateColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getEndDate().toString()));
    statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
  }

  @FXML private void onAddPrescription()
  {
    try
    {
      String medicine = medicineField.getText();
      double dose = Double.parseDouble(doseAmountField.getText());
      String unit = doseUnitField.getText();
      String frequency = frequencyField.getText();
      String status = statusField.getText();
      String comment = commentField.getText();

      NewDateTime start = toNewDateTime(startDatePicker.getValue());
      NewDateTime end = toNewDateTime(endDatePicker.getValue());

      //Prescription prescription = new Prescription(medicine, dose, unit, start, end,
      //    frequency, status, comment, 100, 100);

      viewModel.addPrescription(medicine, dose, unit, start, end, frequency, status, comment,
          viewModel.getDoctorId(), viewModel.getPatientId());
      clearForm();
    }
    catch (Exception e)
    {
      showAlert("Invalid input. Please make sure all fields are filled in correctly.");
    }
  }

  private NewDateTime toNewDateTime(java.time.LocalDate date)
  {
    return new NewDateTime(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), 0, 0);
  }

  private void clearForm()
  {
    medicineField.clear();
    doseAmountField.clear();
    doseUnitField.clear();
    frequencyField.clear();
    statusField.clear();
    commentField.clear();
    startDatePicker.setValue(null);
    endDatePicker.setValue(null);
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
