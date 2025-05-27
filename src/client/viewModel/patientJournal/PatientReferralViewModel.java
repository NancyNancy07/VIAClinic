package client.viewModel.patientJournal;

import client.clientNetwork.PatientClient;
import client.viewModel.loginSystem.LoginSharedData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import server.model.patientJournal.Referral;

import java.util.List;

/**
 * PatientReferralViewModel is responsible for managing the referral data
 * related to a patient. It interacts with the PatientClient to retrieve
 * and display referrals for a specific patient.
 */
public class PatientReferralViewModel
{
  private final PatientClient patientClient;
  private final ObservableList<Referral> referralList;
  private PatientJournalSharedData patientJournalSharedData = PatientJournalSharedData.getInstance();

  /**
   * Constructor for PatientReferralViewModel.
   * Initializes the PatientClient and the referral list.
   */
  public PatientReferralViewModel()
  {
    this.patientClient = new PatientClient();
    this.referralList = FXCollections.observableArrayList();
  }

  /**
   * Retrieves the list of referrals for a specific patient.
   * If no referrals are found, an informational alert is displayed.
   *
   * @param patientId the ID of the patient whose referrals are to be retrieved
   * @return an ObservableList of Referral objects
   */
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

  /**
   * Gets the patient ID from the shared login data.
   *
   * @return the ID of the currently logged-in patient
   */
  public int getPatientId()
  {
    return LoginSharedData.getInstance().getId();

  }

}
