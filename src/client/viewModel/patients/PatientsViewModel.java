package client.viewModel.patients;

import client.clientNetwork.DiagnosisListener;
import client.clientNetwork.PatientClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;

import java.util.List;

public class PatientsViewModel implements DiagnosisListener
{
  private PatientsSharedData patientsSharedData = PatientsSharedData.getInstance();
  private List<Patient> patients;
  private int patientId;
  private PatientClient patientClient;
  private ObservableList<Diagnosis> diagnoses = FXCollections.observableArrayList();

  public PatientsViewModel()
  {
    this.patientsSharedData = patientsSharedData;
    patientClient = new PatientClient();
    patientClient.setDiagnosisListener(this); // Register as listener

  }

  public List<Patient> getPatientList()
  {
    PatientClient patientClient = new PatientClient();
    this.patients = patientClient.getPatientList();
    if (patients != null)
    {
      return patients;
    }

    return null;
  }

  public void setPatientId(Patient patient)
  {
    for (int i = 0; i < patients.size(); i++)
    {
      if (patients.get(i).equals(patient))
      {
        patientId = patients.get(i).getPatientID();
        patientsSharedData.setPatientId(patientId);
      }
    }
  }

  public int getPatientId()
  {
    return patientId;
  }

  public void addDiagnosis(String diagnosisName, String status,
      NewDateTime date, Prescription prescription)
  {
    Diagnosis diagnosis = new Diagnosis(diagnosisName, status, date,
        patientsSharedData.getDoctorId(), getPatientId(), prescription);
    diagnoses.add(diagnosis);
    patientClient.sendAddDiagnosis(diagnosis);
    patientsSharedData.setDiagnosis(diagnosisName, status, date, prescription);
  }

  public ObservableList<Diagnosis> getDiagnoses()
  {
    return diagnoses;
  }

  public String getDiagnosis()
  {
    return patientsSharedData.getDiagnosis();
  }

  public void setPatientName(String name)
  {
    patientsSharedData.setPatientName(name);
  }

  public String getPatientName()
  {
    return patientsSharedData.getPatientName();
  }

  @Override public void onDiagnosisAdded(boolean success, String message)
  {
    System.out.println("Diagnosis result: " + message);
  }

}
