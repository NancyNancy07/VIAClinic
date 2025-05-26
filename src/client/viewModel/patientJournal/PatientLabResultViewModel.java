package client.viewModel.patientJournal;

import client.clientNetwork.PatientClient;
import client.viewModel.loginSystem.LoginSharedData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import server.model.patientJournal.LabResult;

import java.util.List;

public class PatientLabResultViewModel
{
  private final PatientClient patientClient;
  private final ObservableList<LabResult> labResultList;

  public PatientLabResultViewModel()
  {
    this.patientClient = new PatientClient();
    this.labResultList = FXCollections.observableArrayList();
  }

  public ObservableList<LabResult> getLabResultList(int patientId)
  {
    List<LabResult> labResults = patientClient.getPatientLabResults(patientId);
    if (labResults == null || labResults.isEmpty())
    {
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Lab Result");
        alert.setHeaderText(null);
        alert.setContentText("No Lab Result found for this patient");
        alert.showAndWait();
      });
    }
    else
    {
      labResultList.setAll(labResults);
    }
    return labResultList;

  }

  public int getPatientId()
  {
    return LoginSharedData.getInstance().getId();

  }

}
