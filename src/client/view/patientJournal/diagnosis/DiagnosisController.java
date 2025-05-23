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
  private PatientDiagnosisViewModel viewModel;

  public void init(PatientDiagnosisViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientName.setText(LoginSharedData.getInstance().getUsername());
    nameColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));
    int patientId = viewModel.getPatientId();
    diagnosisTable.setItems(viewModel.getDiagnosisList(patientId));
  }
  @FXML private void onBackButtonClick()
  {
    PatientJournalViewHandler.showView(
        PatientJournalViewHandler.ViewType.FRONT);
  }
}
