package client.viewModel.patients;

public class PatientJournalViewModelFactory
{
  private PatientsViewModel patientsViewModel;
  private AddDiagnosisViewModel addDiagnosisViewModel;
  private PatientsSharedData sharedData;

  public PatientJournalViewModelFactory()
  {
    patientsViewModel = new PatientsViewModel();
    addDiagnosisViewModel = new AddDiagnosisViewModel();
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
}
