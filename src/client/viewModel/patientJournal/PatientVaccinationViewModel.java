package client.viewModel.patientJournal;

import client.clientNetwork.PatientClient;
import client.viewModel.loginSystem.LoginSharedData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.Vaccination;

import java.util.List;

/**
 * PatientVaccinationViewModel is responsible for managing the vaccination data
 * for a patient. It interacts with the PatientClient to retrieve and send vaccination data.
 */
public class PatientVaccinationViewModel
{
  private PatientClient patientClient;
  private final ObservableList<Vaccination> vaccinationList = FXCollections.observableArrayList();

  /**
   * Constructor for PatientVaccinationViewModel.
   */
  public PatientVaccinationViewModel()
  {
    this.patientClient = new PatientClient();
  }


  /**
   * Retrieves the list of vaccinations for a specific patient.
   * If the list is null, it clears the current vaccination list.
   *
   * @param patientId the ID of the patient whose vaccinations are to be retrieved
   * @return an ObservableList of Vaccination objects
   */
  public ObservableList<Vaccination> getVaccinationList(int patientId)
  {
    List<Vaccination> vaccination = patientClient.getPatientVaccination(
        patientId);

    if (vaccination == null)
    {
      vaccinationList.clear(); // or just return empty list
    }
    else
    {
      vaccinationList.setAll(vaccination);
    }

    return vaccinationList;
  }

  /**
   * Adds a new vaccination to the patient's record and sends it to the server.
   *
   * @param vaccination the Vaccination object to be added
   */
  public void addVaccination(Vaccination vaccination)
  {
    patientClient.sendAddVaccination(vaccination);
  }

  /**
   * Gets patient id from the LoginSharedData.
   * @return the ID of the patient currently logged in
   */
  public int getPatientId()
  {
    return LoginSharedData.getInstance().getId();

  }
}
