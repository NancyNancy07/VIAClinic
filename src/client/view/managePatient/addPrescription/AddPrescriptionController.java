package client.view.managePatient.addPrescription;

import client.view.managePatient.ManagePatientViewHandler;
import client.viewModel.managePatients.AddPrescriptionViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Prescription;

/**
 * AddPrescriptionController handles the logic for adding prescriptions to a patient's record.
 * It allows doctors to input prescription details and view existing prescriptions.
 */
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

  /**
   * Initializes the controller with the provided view model.
   * It sets up the prescription table and binds the data from the view model.
   *
   * @param viewModel the view model containing prescription data and logic
   */
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

  /**
   * Handles the action of adding a new prescription.
   * It retrieves the input values, validates them, and calls the view model to add the prescription.
   */
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

  /**
   * Converts a LocalDate to a NewDateTime object.
   * @param date the LocalDate to convert
   * @return a NewDateTime object representing the date
   */
  private NewDateTime toNewDateTime(java.time.LocalDate date)
  {
    return new NewDateTime(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), 0, 0);
  }

  /**
   * Handles the action of viewing a selected prescription from the table.
   * It populates the form fields with the selected prescription's details.
   */
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

  /**
   * Displays an alert with the specified message.
   * This is used to inform the user of errors or important information.
   *
   * @param msg the message to display in the alert
   */
  private void showAlert(String msg)
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.showAndWait();
  }

  /**
   * Handles the action of going back to the previous view.
   * It navigates back to the front view of the Manage Patient section.
   */
  @FXML
  private void back()
  {
    ManagePatientViewHandler.showView(ManagePatientViewHandler.ViewType.FRONT);
  }
}
