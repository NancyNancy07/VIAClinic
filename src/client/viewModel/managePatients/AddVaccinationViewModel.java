package client.viewModel.managePatients;

import client.clientNetwork.PatientClient;
import client.viewModel.patientJournal.PatientJournalSharedData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Vaccination;

/**
 * AddVaccinationViewModel is responsible for managing the data and logic related to adding vaccinations
 * for a patient. It interacts with the PatientClient to send vaccination data and updates shared data
 * for patient information.
 */
public class AddVaccinationViewModel
{
  private final ObservableList<Vaccination> vaccinations = FXCollections.observableArrayList();

  private final PatientsSharedData patientsSharedData = PatientsSharedData.getInstance();
  private final PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  private final PatientClient patientClient;

  private final StringProperty patientName = new SimpleStringProperty();

  /**
   * Constructor initializes the PatientClient and sets the patient name from shared data.
   */
  public AddVaccinationViewModel()
  {
    this.patientClient = new PatientClient();
    patientName.set(patientsSharedData.getPatientName());
  }

  /**
   * Adds a new vaccination to the list and sends it to the server.
   *
   * @param vaccinationName the name of the vaccination
   * @param dateTaken the date when the vaccination was taken
   * @param isRecommended whether the vaccination is recommended
   * @param comment additional comments about the vaccination
   * @param nextDoseDate the date for the next dose, if applicable
   * @param doctorId the ID of the doctor administering the vaccination
   * @param patientId the ID of the patient receiving the vaccination
   */
  public void addVaccination(String vaccinationName, NewDateTime dateTaken,
      boolean isRecommended, String comment, NewDateTime nextDoseDate,
      int doctorId, int patientId)
  {
    Vaccination vaccination = new Vaccination(vaccinationName, dateTaken,
        isRecommended, comment, nextDoseDate, doctorId, patientId);
    vaccinations.add(vaccination);
    patientClient.sendAddVaccination(vaccination);
    patientsSharedData.setVaccination(vaccinationName, dateTaken, isRecommended,
        comment, nextDoseDate);
    patientJournalSharedData.setPatientId(patientsSharedData.getPatientId());
  }

  /**
   * Gets the list of vaccinations for the patient.
   * @return an observable list of vaccinations
   */
  public ObservableList<Vaccination> getVaccinations()
  {
    return vaccinations;
  }

  /**
   * Gets the Vaccination as a String.
   * @return the vaccination as a String
   */
  public String getVaccination()
  {
    return patientsSharedData.getVaccination();
  }

  /**
   * Sets the patient name in the shared data.
   * @param name the name of the patient
   */
  public void setPatientName(String name)
  {
    patientsSharedData.setPatientName(name);
  }

  /**
   * Gets the patient name from the shared data.
   * @return the name of the patient
   */
  public String getPatientName()
  {
    return patientsSharedData.getPatientName();
  }

  /**
   * Gets the patient ID from the shared data.
   * @return the ID of the patient
   */
  public int getPatientId()
  {
    return patientsSharedData.getPatientId();
  }

  /**
   * Gets the doctor ID from the shared data.
   * @return the ID of the doctor
   */
  public int getDoctorId()
  {
    return patientsSharedData.getDoctorId();
  }

}
