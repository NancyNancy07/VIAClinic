package client.view.managePatient.addDiagnosis;

import client.viewModel.patients.AddDiagnosisViewModel;
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

  public void init(AddDiagnosisViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientName.setText(viewModel.getPatientName());
    patientName2.setText(viewModel.getPatientName());
    diagnosisCol.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));

    diagnosisTableView.setItems(
        viewModel.getDiagnoses(viewModel.getPatientId()));

    prescription.setCellFactory(param -> new ListCell<>()
    {
      @Override protected void updateItem(Prescription item, boolean empty)
      {
        super.updateItem(item, empty);
        if (empty || item == null)
          setText(null);
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
        else
          setText(item.getMedicineName() + " - " + item.getDoseAmount()
              + item.getDoseUnit());
      }
    });

    prescription.getItems()
        .addAll(viewModel.getAllPrescriptions(viewModel.getPatientId()));
  }

  @FXML private void setDiagnosisName()
  {
    String diagnosis = diagnosisName.getText();
    String status = statusField.getText();
    String comment = commentField.getText();
    NewDateTime date = new NewDateTime(LocalDate.now().getDayOfMonth(),
        LocalDate.now().getMonthValue(), LocalDate.now().getYear(), 0, 0);

    NewDateTime startDate = new NewDateTime(
        startDatePicker.getValue().getDayOfMonth(),
        startDatePicker.getValue().getMonthValue(),
        startDatePicker.getValue().getYear(), 0, 0);
    NewDateTime endDate = new NewDateTime(
        endDatePicker.getValue().getDayOfMonth(),
        endDatePicker.getValue().getMonthValue(),
        endDatePicker.getValue().getYear(), 0, 0);

    Prescription selectedPrescription = prescription.getValue();

    Prescription prescription1 = new Prescription("Paracetamol", 500, "mg",
        startDate, endDate, "Twice a day", "Ongoing", "Take with food", 100,
        100);

    if (diagnosis.isEmpty() || status.isEmpty() || date == null
        || selectedPrescription == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Input Required");
      alert.setHeaderText(null);
      alert.setContentText("Field cannot be empty.");
      alert.showAndWait();
      return;
    }

    viewModel.addDiagnosis(diagnosis, status, date, selectedPrescription);
    patientName.clear();
    diagnosisName.clear();
    statusField.clear();
    prescriptionField.clear();
    startDatePicker.setValue(null);
    endDatePicker.setValue(null);

  }
}