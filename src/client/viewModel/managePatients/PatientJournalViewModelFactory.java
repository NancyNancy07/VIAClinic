package client.viewModel.managePatients;

public class PatientJournalViewModelFactory
{
  private PatientsViewModel patientsViewModel;
  private AddDiagnosisViewModel addDiagnosisViewModel;
  private PatientsSharedData sharedData;
  private AddPrescriptionViewModel addPrescriptionViewModel;
  private AddReferralViewModel addReferralViewModel;

  public PatientJournalViewModelFactory()
  {
    patientsViewModel = new PatientsViewModel();
    addDiagnosisViewModel = new AddDiagnosisViewModel();
    addPrescriptionViewModel = new AddPrescriptionViewModel();
    addReferralViewModel = new AddReferralViewModel();
    sharedData = PatientsSharedData.getInstance();
  }

  public AddDiagnosisViewModel getAddDiagnosisViewModel()
  {
    return addDiagnosisViewModel;
  }

  public PatientsViewModel getPatientsViewModel()
  {
    return patientsViewModel;
  }

  public AddPrescriptionViewModel getAddPrescriptionViewModel()
  {
    return addPrescriptionViewModel;
  }

  public AddReferralViewModel getAddReferralViewModel()
  {
    return addReferralViewModel;
  }
}
