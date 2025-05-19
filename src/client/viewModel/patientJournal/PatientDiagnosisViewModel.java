package client.viewModel.patientJournal;

import client.clientNetwork.PatientClient;
import client.viewModel.loginSystem.LoginSharedData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;

import java.util.List;

public class PatientDiagnosisViewModel
{
  private final PatientClient patientClient;
  private final ObservableList<Diagnosis> diagnosisList;
  private final ObservableList<Prescription> prescriptionList;
  private PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  public PatientDiagnosisViewModel()
  {
    this.patientClient = new PatientClient();
    this.diagnosisList = FXCollections.observableArrayList();
    this.prescriptionList = FXCollections.observableArrayList();
  }

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

  public ObservableList<Prescription> getPrescriptionList(int patientId)
  {
    List<Prescription> prescriptions = patientClient.getPatientPrescriptions(patientId);
    prescriptionList.setAll(prescriptions);
    return prescriptionList;
  }

  public void loadDiagnosesForPatient(int patientId)
  {

  }

  public int getPatientId()
  {
    return LoginSharedData.getInstance().getId();

  }
}
