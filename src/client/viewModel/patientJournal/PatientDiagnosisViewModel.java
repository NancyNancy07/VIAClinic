package client.viewModel.patientJournal;

import client.clientNetwork.DiagnosisListener;
import client.clientNetwork.PatientClient;
import client.viewModel.loginSystem.LoginSharedData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;

import java.util.List;

/**
 * PatientDiagnosisViewModel is responsible for managing the patient's diagnosis and prescription data.
 * It interacts with the PatientClient to retrieve and manipulate diagnosis and prescription information.
 */
public class PatientDiagnosisViewModel implements DiagnosisListener
{
  private final PatientClient patientClient;
  private final ObservableList<Diagnosis> diagnosisList;
  private final ObservableList<Prescription> prescriptionList;

  /**
   * Constructor for PatientDiagnosisViewModel.
   * Initializes the PatientClient and observable lists for diagnoses and prescriptions.
   */
  public PatientDiagnosisViewModel()
  {
    this.patientClient = new PatientClient();
    this.diagnosisList = FXCollections.observableArrayList();
    this.prescriptionList = FXCollections.observableArrayList();
  }

  /**
   * Retrieves the list of diagnoses for a specific patient.
   *
   * @param patientId the ID of the patient whose diagnoses are to be retrieved
   * @return an ObservableList containing the patient's diagnoses
   */
  public ObservableList<Diagnosis> getDiagnosisList(int patientId)
  {

    List<Diagnosis> diagnoses = patientClient.getPatientDiagnosis(patientId);
    if (diagnoses == null || diagnoses.isEmpty())
    {
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Diagnoses");
        alert.setHeaderText(null);
        alert.setContentText("No diagnoses found for this patient.");
        alert.showAndWait();
      });
    }
    else
    {
      diagnosisList.setAll(diagnoses);
    }

    return diagnosisList;

  }

  /**
   * Retrieves the list of prescriptions for a specific patient.
   *
   * @param patientId the ID of the patient whose prescriptions are to be retrieved
   * @return an ObservableList containing the patient's prescriptions
   */
  public ObservableList<Prescription> getPrescriptionList(int patientId)
  {
    List<Prescription> prescriptions = patientClient.getPatientPrescriptions(
        patientId);
    prescriptionList.setAll(prescriptions);
    return prescriptionList;
  }

  /**
   * Gets the patient ID from the shared login data.
   * @return the patient ID as an int
   */
  public int getPatientId()
  {
    return LoginSharedData.getInstance().getId();

  }

  /**
   * Prints the result of adding a diagnosis.
   * @param success Indicates whether the operation was successful.
   * @param message A message providing additional details about the operation.
   */
  @Override public void onDiagnosisAdded(boolean success, String message)
  {
    System.out.println("Diagnosis result: " + message);
  }

}
