package client.view.patientJournal.labResult;
import client.viewModel.loginSystem.LoginSharedData;
import client.viewModel.patientJournal.PatientDiagnosisViewModel;
import client.viewModel.patientJournal.PatientLabResultViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.LabResult;
import server.model.patientJournal.Prescription;
public class LabResultController
{ @FXML private TableView<LabResult> labResultTable;
  @FXML private TableColumn<LabResult, String> nameColumn;
  @FXML private Label patientName;
  private PatientLabResultViewModel viewModel;
  @FXML private TableColumn<LabResult, String> idColumn;
  @FXML private TableColumn<LabResult, String> testNameColumn;
  @FXML private TableColumn<LabResult, String> dateCollectedColumn;
  @FXML private TableColumn<LabResult, String> sampleTypeColumn;
  @FXML private TableColumn<LabResult, String> commentColumn;
  public void init(PatientLabResultViewModel viewModel)
  {
    this.viewModel = viewModel;
    labResultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    commentColumn.setMaxWidth(300);
    commentColumn.setPrefWidth(300);
    patientName.setText(LoginSharedData.getInstance().getUsername());

    idColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(String.valueOf(cellData.getValue().getLabResultId())));
    testNameColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getTestName()));
    sampleTypeColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(String.valueOf(cellData.getValue().getSampleType())));
    dateCollectedColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getDateCollected().toString()));

    commentColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getComment()));

    int patientId = viewModel.getPatientId();
    labResultTable.setItems(viewModel.getLabResultList(patientId));
  }


}




