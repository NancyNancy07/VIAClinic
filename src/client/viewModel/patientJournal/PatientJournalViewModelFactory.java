package client.viewModel.patientJournal;

public class PatientJournalViewModelFactory
{
  private PatientDiagnosisViewModel patientDiagnosisViewModel;
  private PatientReferralViewModel patientReferralViewModel;

  public PatientJournalViewModelFactory()
  {
    this.patientDiagnosisViewModel = new PatientDiagnosisViewModel();
    this.patientReferralViewModel = new PatientReferralViewModel();
  }

  public PatientDiagnosisViewModel getPatientDiagnosisViewModel()
  {
    return patientDiagnosisViewModel;
  }

  public PatientReferralViewModel getPatientReferralViewModel()
  {
    return patientReferralViewModel;
  }
}
