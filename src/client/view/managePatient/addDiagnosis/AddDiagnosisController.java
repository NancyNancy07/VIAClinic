package client.view.managePatient.addDiagnosis;

import client.viewModel.patients.PatientsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddDiagnosisController
{
  @FXML private TextField diagnosisName;
  @FXML private Label patientName;
  @FXML private Label patientName2;

  private PatientsViewModel viewModel;

  public void init(PatientsViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientName.setText(viewModel.getPatientName());
    patientName2.setText(viewModel.getPatientName());

  }

  @FXML private void setDiagnosisName()
  {
    String diagnosis = diagnosisName.getText();

    if (diagnosis.isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Input Required");
      alert.setHeaderText(null);
      alert.setContentText("Diagnosis name cannot be empty.");
      alert.showAndWait();
      return;
    }

    viewModel.setDiagnosis(diagnosis);
    diagnosisName.clear();
  }
}
