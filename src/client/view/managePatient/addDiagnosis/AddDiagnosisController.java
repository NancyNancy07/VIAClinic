package client.view.managePatient.addDiagnosis;

import client.view.managePatient.ManagePatientViewHandler;
import client.viewModel.managePatients.AddDiagnosisViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;

import java.time.LocalDate;

public class AddDiagnosisController
{
  @FXML private TextField diagnosisName;
  @FXML private TextField patientName;
  @FXML private Label patientName2;
  @FXML private TextField statusField;
  @FXML private TextField prescriptionField;
  @FXML private DatePicker startDatePicker;
  @FXML private DatePicker endDatePicker;
  @FXML private ComboBox<Prescription> prescription;
  @FXML private TextField commentField;

  @FXML private TableView<Diagnosis> diagnosisTableView;
  @FXML private TableColumn<Diagnosis, String> diagnosisCol;

  private AddDiagnosisViewModel viewModel;
  private Prescription addNewPlaceholder;

  public void init(AddDiagnosisViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientName.setText(viewModel.getPatientName());
    patientName2.setText(viewModel.getPatientName());
    diagnosisCol.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));

    diagnosisTableView.setItems(
        viewModel.getDiagnoses(viewModel.getPatientId()));

    // Placeholder item
    addNewPlaceholder = new Prescription("➕ Add new prescription...", 0, "",
        null, null, "", "", "", 0, 0);

    // Set up ComboBox display
    prescription.setCellFactory(param -> new ListCell<>()
    {
      @Override protected void updateItem(Prescription item, boolean empty)
      {
        super.updateItem(item, empty);
        if (empty || item == null)
          setText(null);
        else if (item.getMedicineName().equals("➕ Add new prescription..."))
          setText(item.getMedicineName());
        else
          setText(item.getMedicineName() + " - " + item.getDoseAmount()
              + item.getDoseUnit());
      }
    });

    prescription.setButtonCell(new ListCell<>()
    {
      @Override protected void updateItem(Prescription item, boolean empty)
      {
        super.updateItem(item, empty);
        if (empty || item == null)
          setText(null);
        else if (item.getMedicineName().equals("➕ Add new prescription..."))
          setText(item.getMedicineName());
        else
          setText(item.getMedicineName() + " - " + item.getDoseAmount()
              + item.getDoseUnit());
      }
    });

    // Add items
    prescription.getItems().add(addNewPlaceholder);
    prescription.getItems()
        .addAll(viewModel.getAllPrescriptions(viewModel.getPatientId()));
    prescription.getSelectionModel().selectFirst();

    // Listener to detect "Add new prescription"
    prescription.setOnAction(e -> {
      Prescription selected = prescription.getValue();
      if (selected != null && selected.equals(addNewPlaceholder))
      {
        openNewPrescriptionDialog();
      }
    });
  }

  @FXML private void setDiagnosisName()
  {
    String diagnosis = diagnosisName.getText();
    String status = statusField.getText();
    String comment = commentField.getText();
    NewDateTime date = new NewDateTime(LocalDate.now().getDayOfMonth(),
        LocalDate.now().getMonthValue(), LocalDate.now().getYear(), 0, 0);

    if (startDatePicker.getValue() == null || endDatePicker.getValue() == null)
    {
      showAlert("Please select start and end date.");
      return;
    }

    NewDateTime startDate = new NewDateTime(
        startDatePicker.getValue().getDayOfMonth(),
        startDatePicker.getValue().getMonthValue(),
        startDatePicker.getValue().getYear(), 0, 0);
    NewDateTime endDate = new NewDateTime(
        endDatePicker.getValue().getDayOfMonth(),
        endDatePicker.getValue().getMonthValue(),
        endDatePicker.getValue().getYear(), 0, 0);

    Prescription selectedPrescription = prescription.getValue();

    if (diagnosis.isEmpty() || status.isEmpty() || selectedPrescription == null)
    {
      showAlert("Field cannot be empty.");
      return;
    }

    viewModel.addDiagnosis(diagnosis, status, date, selectedPrescription);

    // Clear fields
    patientName.clear();
    diagnosisName.clear();
    statusField.clear();
    startDatePicker.setValue(null);
    endDatePicker.setValue(null);
  }

  private void showAlert(String message)
  {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Input Required");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  // Simulate dialog to enter new prescription
  private void openNewPrescriptionDialog()
  {
    ManagePatientViewHandler.showView(
        ManagePatientViewHandler.ViewType.PRESCRIPTION);
  }
  @FXML
  private void back()
  {
    ManagePatientViewHandler.showView(ManagePatientViewHandler.ViewType.FRONT);
  }
}