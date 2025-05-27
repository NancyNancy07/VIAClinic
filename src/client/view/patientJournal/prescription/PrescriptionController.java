package client.view.patientJournal.prescription;

import client.view.patientJournal.PatientJournalViewHandler;
import client.viewModel.loginSystem.LoginSharedData;
import client.viewModel.patientJournal.PatientDiagnosisViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;

/**
 * PrescriptionController is responsible for managing the prescription view in the patient journal.
 * It initializes the prescription table and binds the data from the view model.
 */
public class PrescriptionController
{
  @FXML private TableView<Prescription> prescriptionTable;
  @FXML private TableColumn<Prescription, String> nameColumn;
  @FXML private Label patientName;
  private PatientDiagnosisViewModel viewModel;
  @FXML private TableColumn<Prescription, String> idColumn;
  @FXML private TableColumn<Prescription, String> medicineColumn;
  @FXML private TableColumn<Prescription, String> doseAmountColumn;
  @FXML private TableColumn<Prescription, String> doseUnitColumn;
  @FXML private TableColumn<Prescription, String> startDateColumn;
  @FXML private TableColumn<Prescription, String> endDateColumn;
  @FXML private TableColumn<Prescription, String> frequencyColumn;
  @FXML private TableColumn<Prescription, String> statusColumn;
  @FXML private TableColumn<Prescription, String> commentColumn;

  /**
   * Initializes the PrescriptionController with the provided view model.
   * It sets up the prescription table and binds the data from the view model.
   *
   * @param viewModel the view model containing prescription data and logic
   */
  public void init(PatientDiagnosisViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientName.setText(LoginSharedData.getInstance().getUsername());

    idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        String.valueOf(cellData.getValue().getPrescriptionId())));
    medicineColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getMedicineName()));
    doseAmountColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        String.valueOf(
            cellData.getValue().getDoseAmount() + " " + cellData.getValue()
                .getDoseUnit())));
    startDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getStartDate().toString()));
    endDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getEndDate().toString()));
    frequencyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getFrequency()));
    statusColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
    commentColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getComment()));

    int patientId = viewModel.getPatientId();
    prescriptionTable.setItems(viewModel.getPrescriptionList(patientId));
  }

  /**
   * Handles the action when the "Back" button is clicked.
   * It navigates back to the front view of the patient journal.
   */
  @FXML private void onBackButtonClick()
  {
    PatientJournalViewHandler.showView(
        PatientJournalViewHandler.ViewType.FRONT);
  }
}
