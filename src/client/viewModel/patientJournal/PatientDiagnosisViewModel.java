package client.viewModel.patientJournal;

import client.clientNetwork.DiagnosisListener;
import client.clientNetwork.PatientClient;
import client.viewModel.loginSystem.LoginSharedData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import server.model.patientJournal.Diagnosis;

import server.model.patientJournal.LabResult;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.Vaccination;


import java.util.List;

public class PatientDiagnosisViewModel implements DiagnosisListener
{
  private final PatientClient patientClient;
  private final ObservableList<Diagnosis> diagnosisList;
  private final ObservableList<Prescription> prescriptionList;
  private final ObservableList<LabResult> labResultList;
  private PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  public PatientDiagnosisViewModel()
  {
    this.patientClient = new PatientClient();
    this.diagnosisList = FXCollections.observableArrayList();
    this.prescriptionList = FXCollections.observableArrayList();
    this.labResultList=FXCollections.observableArrayList();
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
    List<Prescription> prescriptions = patientClient.getPatientPrescriptions(
        patientId);
    prescriptionList.setAll(prescriptions);
    return prescriptionList;
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


  public void loadDiagnosesForPatient(int patientId)
  {

  }

  public int getPatientId()
  {
    return LoginSharedData.getInstance().getId();

  }

  @Override public void onDiagnosisAdded(boolean success, String message)
  {
    System.out.println("Diagnosis result: " + message);
  }


}
