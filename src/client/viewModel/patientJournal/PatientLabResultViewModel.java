package client.viewModel.patientJournal;

import client.clientNetwork.PatientClient;
import client.viewModel.loginSystem.LoginSharedData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import server.model.patientJournal.LabResult;

import java.util.List;

/**
 * PatientLabResultViewModel is responsible for managing the lab results of a patient.
 * It interacts with the PatientClient to retrieve lab results and provides methods
 * to access the lab result list.
 */
public class PatientLabResultViewModel
{
  private final PatientClient patientClient;
  private final ObservableList<LabResult> labResultList;

  /**
   * Constructor initializes the PatientClient and the observable list for lab results.
   */
  public PatientLabResultViewModel()
  {
    this.patientClient = new PatientClient();
    this.labResultList = FXCollections.observableArrayList();
  }

  /**
   * Retrieves the list of lab results for a specific patient.
   * If no lab results are found, an information alert is displayed.
   *
   * @param patientId the ID of the patient whose lab results are to be retrieved
   * @return an ObservableList containing the lab results for the specified patient
   */
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

  /**
   * Retrieves the ID of the currently logged-in patient.
   *
   * @return the ID of the patient
   */
  public int getPatientId()
  {
    return LoginSharedData.getInstance().getId();

  }

}
