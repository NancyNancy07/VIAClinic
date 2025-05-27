package client.view.patientJournal.diagnosis;

import client.view.patientJournal.PatientJournalViewHandler;
import client.viewModel.loginSystem.LoginSharedData;
import client.viewModel.patientJournal.PatientDiagnosisViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import server.model.patientJournal.Diagnosis;

/**
 * DiagnosisController is responsible for displaying a patient's diagnosis information.
 * It initializes the view with the patient's name and populates the diagnosis table
 * with relevant data from the PatientDiagnosisViewModel.
 */
public class DiagnosisController
{
  @FXML private TableView<Diagnosis> diagnosisTable;
  @FXML private TableColumn<Diagnosis, String> nameColumn;
  @FXML private Label patientName;
  @FXML private TableColumn<Diagnosis, String> diagnosisId;
  @FXML private TableColumn<Diagnosis, String> diagnosisName;
  @FXML private TableColumn<Diagnosis, String> status;
  @FXML private TableColumn<Diagnosis, String> dateDiagnosed;
  @FXML private TableColumn<Diagnosis, String> doctorId;
  @FXML private TableColumn<Diagnosis, String> prescription;
  private PatientDiagnosisViewModel viewModel;

  /**
   * Initializes the DiagnosisController with the provided view model.
   * Sets up the UI components and populates the diagnosis table with data.
   *
   * @param viewModel the view model containing data and logic for patient diagnoses
   */
  public void init(PatientDiagnosisViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientName.setText(LoginSharedData.getInstance().getUsername());
    diagnosisId.setCellValueFactory(cellData -> new SimpleStringProperty(
        String.valueOf(cellData.getValue().getDiagnosisId())));
    diagnosisName.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getDiagnosisName()));
  /*  doctorId.setCellValueFactory(cellData -> new SimpleStringProperty(
        String.valueOf(cellData.getValue().getDiagnosisId())));*/
    status.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
    dateDiagnosed.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getDateDiagnosed().toString()));

    prescription.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getMedicineName()));

    int patientId = viewModel.getPatientId();
    diagnosisTable.setItems(viewModel.getDiagnosisList(patientId));
  }

  /**
   * Handles the action when the back button is clicked.
   * It navigates back to the front view of the patient journal.
   */
  @FXML private void onBackButtonClick()
  {
    PatientJournalViewHandler.showView(
        PatientJournalViewHandler.ViewType.FRONT);
  }
}
