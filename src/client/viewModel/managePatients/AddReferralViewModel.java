package client.viewModel.managePatients;

import client.clientNetwork.PatientClient;
import client.viewModel.patientJournal.PatientJournalSharedData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Referral;

public class AddReferralViewModel
{
  private ObservableList<Referral> referrals = FXCollections.observableArrayList();
  private final PatientsSharedData patientsSharedData = PatientsSharedData.getInstance();
  private final PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  private final PatientClient patientClient;

  private final StringProperty patientName = new SimpleStringProperty();

  public AddReferralViewModel()
  {
    patientClient = new PatientClient();
    patientName.set(patientsSharedData.getPatientName());
  }

  public void addReferral(NewDateTime dateCreated, String reason,
      String comment, int doctorId, int patientId)
  {
    Referral referral = new Referral(dateCreated, reason, comment, doctorId,
        patientId);
    referrals.add(referral);
    patientClient.sendAddReferral(referral);
    patientsSharedData.setReferral(dateCreated, reason, comment, doctorId,
        patientId);
    patientJournalSharedData.setPatientId(patientsSharedData.getPatientId());
  }

  public ObservableList<Referral> getReferrals()
  {
    return referrals;
  }

  public String getReferral()
  {
    return patientsSharedData.getReferral();
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
