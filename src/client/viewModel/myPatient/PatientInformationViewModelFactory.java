package client.viewModel.myPatient;

public class PatientInformationViewModelFactory
{
  private final PatientInformationViewModel patientInformationViewModel;

  public PatientInformationViewModelFactory()
  {
    this.patientInformationViewModel = new PatientInformationViewModel();
  }

  public PatientInformationViewModel getPatientInformationViewModel()
  {
    return patientInformationViewModel;
  }
}
