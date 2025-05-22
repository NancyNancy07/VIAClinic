package client.viewModel.patientJournal;

import client.clientNetwork.PatientClient;
import client.viewModel.loginSystem.LoginSharedData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import server.model.patientJournal.Referral;

import java.util.List;

public class PatientReferralViewModel
{
  private final PatientClient patientClient;
  private final ObservableList<Referral> referralList;
  private PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  public PatientReferralViewModel()
  {
    this.patientClient = new PatientClient();
    this.referralList = FXCollections.observableArrayList();
  }

  public ObservableList<Referral> getReferralList(int patientId)
  {

    List<Referral> referrals = patientClient.getPatientReferral(patientId);
    if (referrals == null || referrals.isEmpty())
    {
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Referrals");
        alert.setHeaderText(null);
        alert.setContentText("No referrals found for this patient.");
        alert.showAndWait();
      });
    }
    else
    {
      referralList.setAll(referrals);
    }

    return referralList;

  }

  public int getPatientId()
  {
    return LoginSharedData.getInstance().getId();

  }

}
