package client.viewModel.patientJournal;

import client.clientNetwork.PatientClient;
import client.viewModel.loginSystem.LoginSharedData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.Vaccination;

import java.util.List;

public class PatientVaccinationViewModel
{
  private PatientClient patientClient;
  private final ObservableList<Vaccination> vaccinationList = FXCollections.observableArrayList();

  public PatientVaccinationViewModel()
  {
    this.patientClient = new PatientClient();
  }

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

  public void addVaccination(Vaccination vaccination)
  {
    patientClient.sendAddVaccination(vaccination);
  }

  public int getPatientId()
  {
    return LoginSharedData.getInstance().getId();

  }
}
