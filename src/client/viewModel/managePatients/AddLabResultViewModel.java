package client.viewModel.managePatients;

import client.clientNetwork.LabResultListener;

import client.clientNetwork.PatientClient;
import client.viewModel.patientJournal.PatientJournalSharedData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.LabResult;

public class AddLabResultViewModel implements LabResultListener
{
  private ObservableList<LabResult> labResults = FXCollections.observableArrayList();

  private final PatientsSharedData patientsSharedData = PatientsSharedData.getInstance();
  private final PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  private final PatientClient patientClient;

  private final StringProperty patientName = new SimpleStringProperty();

  public AddLabResultViewModel()
  {
    patientClient = new PatientClient();
    patientName.set(patientsSharedData.getPatientName());
  }

  public void addLabResult(String testName, String sampleType,
      NewDateTime dateCollected, String comment, int doctorId, int patientId)
  {
       LabResult labResult = new LabResult(testName, sampleType, dateCollected,comment,
           doctorId, patientId);
           labResults.add(labResult);
        System.out.println("Lab result added: " + testName);
    System.out.println("Labresult class: "+labResult.getClass());
    System.out.println("Patient Id for lab result: " + patientId);
    System.out.println("lab result: " + labResult.getTestName() + " " +labResult.getPatientId());
       patientClient.sendaddLabResult(labResult);
      patientsSharedData.setLabResult(testName, sampleType,dateCollected,comment,doctorId,patientId);
       patientJournalSharedData.setPatientId(patientsSharedData.getPatientId());
  }

  public ObservableList<LabResult> getLabResults()
  {
    return labResults;
  }

  public String getLabResult()
  {
    return patientsSharedData.getLabResult();
  }

  public void setPatientName(String name)
  {
    patientsSharedData.setPatientName(name);
  }

  public String getPatientName()
  {
    return patientsSharedData.getPatientName();
  }

  public int getPatientId()
  {
    return patientsSharedData.getPatientId();
  }

  public int getDoctorId()
  {
    return patientsSharedData.getDoctorId();
  }

  @Override public void onLabResultAdded(boolean success, String message)
  {System.out.println("lab Result: " + message);
  }
}


