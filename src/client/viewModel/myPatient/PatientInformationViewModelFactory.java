package client.viewModel.myPatient;

/**
 * PatientInformationViewModelFactory is responsible for creating and providing
 * an instance of PatientInformationViewModel. It encapsulates the logic for
 * managing patient information in the application.
 */
public class PatientInformationViewModelFactory
{
  private final PatientInformationViewModel patientInformationViewModel;

  /**
   * Default constructor for PatientInformationViewModelFactory.
   * Initializes the PatientInformationViewModel instance.
   */
  public PatientInformationViewModelFactory()
  {
    this.patientInformationViewModel = new PatientInformationViewModel();
  }

  /**
   * Gets the PatientInformationViewModel instance.
   *
   * @return The PatientInformationViewModel instance.
   */
  public PatientInformationViewModel getPatientInformationViewModel()
  {
    return patientInformationViewModel;
  }
}