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

  public void init(PatientDiagnosisViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientName.setText(LoginSharedData.getInstance().getUsername());
    diagnosisId.setCellValueFactory(cellData -> new SimpleStringProperty(
        String.valueOf(cellData.getValue().getDiagnosisId())));
    diagnosisName.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getDiagnosisName()));
    doctorId.setCellValueFactory(cellData -> new SimpleStringProperty(
        String.valueOf(cellData.getValue().getDiagnosisId())));
    status.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
    dateDiagnosed.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getDateDiagnosed().toString()));

    prescription.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getMedicineName()));

    int patientId = viewModel.getPatientId();
    diagnosisTable.setItems(viewModel.getDiagnosisList(patientId));
  }

  @FXML private void onBackButtonClick()
  {
    PatientJournalViewHandler.showView(
        PatientJournalViewHandler.ViewType.FRONT);
  }
}
