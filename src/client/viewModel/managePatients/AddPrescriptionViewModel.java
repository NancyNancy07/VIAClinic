package client.viewModel.managePatients;

import client.clientNetwork.PatientClient;
import client.clientNetwork.PrescriptionListener;
import client.viewModel.patientJournal.PatientJournalSharedData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Prescription;

public class AddPrescriptionViewModel implements PrescriptionListener
{
  private ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();

  private final PatientsSharedData patientsSharedData = PatientsSharedData.getInstance();
  private final PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  private final PatientClient patientClient;

  private final StringProperty patientName = new SimpleStringProperty();

  public AddPrescriptionViewModel()
  {
    patientClient = new PatientClient();
    patientName.set(patientsSharedData.getPatientName());
  }

  public void addPrescription(String medicineName, double doseAmount,
      String doseUnit, NewDateTime startDate, NewDateTime endDate, String frequency,
      String status, String comment, int doctorId, int patientId)
  {
    Prescription prescription = new Prescription(medicineName, doseAmount, doseUnit,
        startDate, endDate, comment, frequency, status, doctorId, patientId);
    prescriptions.add(prescription);
    patientClient.sendAddPrescription(prescription);
    patientsSharedData.setPrescription(medicineName, doseAmount, doseUnit,
        startDate, endDate, comment, frequency, status, doctorId, patientId);
    patientJournalSharedData.setPatientId(patientsSharedData.getPatientId());
  }

  public ObservableList<Prescription> getPrescriptions()
  {
    return prescriptions;
  }

  public String getPrescription()
  {
    return patientsSharedData.getPrescription();
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


  @Override public void onPrescriptionAdded(boolean success, String message)
  {
    System.out.println("Prescription result: " + message);
  }

  @Override public void addedPrescription(Prescription prescription)
  {
  }
}
