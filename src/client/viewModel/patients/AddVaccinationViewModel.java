package client.viewModel.patients;

import client.clientNetwork.PatientClient;
import client.viewModel.patientJournal.PatientJournalSharedData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Vaccination;

public class AddVaccinationViewModel
{
  private final ObservableList<Vaccination> vaccinations = FXCollections.observableArrayList();

  private final PatientsSharedData patientsSharedData = PatientsSharedData.getInstance();
  private final PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  private final PatientClient patientClient;

  private final StringProperty patientName = new SimpleStringProperty();

  public AddVaccinationViewModel()
  {
    this.patientClient = new PatientClient();
    patientName.set(patientsSharedData.getPatientName());
  }

  public void addVaccination(String vaccinationName, NewDateTime dateTaken, boolean isRecommended,
      String comment, NewDateTime nextDoseDate, int doctorId, int patientId)
  {
    Vaccination vaccination = new Vaccination(vaccinationName, dateTaken, isRecommended, comment, nextDoseDate, doctorId, patientId);
    vaccinations.add(vaccination);
    patientClient.sendAddVaccination(vaccination);
    patientsSharedData.setVaccination(vaccinationName, dateTaken, isRecommended, comment, nextDoseDate);
    patientJournalSharedData.setPatientId(patientsSharedData.getPatientId());
  }

  public ObservableList<Vaccination> getVaccinations()
  {
    return vaccinations;
  }

  public String getVaccination()
  {
    return patientsSharedData.getVaccination();
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


}
