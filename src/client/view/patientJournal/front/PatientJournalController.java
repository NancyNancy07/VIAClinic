package client.view.patientJournal.front;

import client.viewModel.patientsJournal.PatientsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PatientJournalController
{
  @FXML private Button diagnosisBtn;
  @FXML private Button prescriptionBtn;
  @FXML private Button vaccinationBtn;
  @FXML private Button labResultBtn;
  @FXML private Button referralBtn;
  @FXML private Label patientName;

  private PatientsViewModel viewModel;

  public void init(PatientsViewModel viewModel)
  {
    this.viewModel = viewModel;
    //    ObservableList<Patient> patients = FXCollections.observableArrayList(
    //        viewModel.getPatientList());
    //
    //    patient.setCellValueFactory(
    //        cellData -> new SimpleStringProperty(cellData.getValue().toString()));
    //
    //    patientsTable.setItems(patients);
    //
    //    patientsTable.setOnMouseClicked(event -> managePatientData());
  }

  @FXML private void diagnosisClick()
  {

  }
}
