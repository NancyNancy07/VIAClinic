package client.viewModel.managePatients;

import client.viewModel.patientJournal.PatientVaccinationViewModel;

/**
 * ManagePatientViewModelFactory is responsible for creating and managing
 * the view models used in the patient management process.
 */
public class ManagePatientViewModelFactory
{
  private PatientsViewModel patientsViewModel;
  private AddDiagnosisViewModel addDiagnosisViewModel;
  private PatientsSharedData sharedData;
  private AddPrescriptionViewModel addPrescriptionViewModel;
  private AddLabResultViewModel addLabResultViewModel;
  private AddReferralViewModel addReferralViewModel;
  private AddVaccinationViewModel addVaccinationViewModel;

  /**
   * Default constructor for ManagePatientViewModelFactory.
   * Initializes the shared data and creates instances of the view models
   * required for managing patients.
   */
  public ManagePatientViewModelFactory()
  {
    patientsViewModel = new PatientsViewModel();
    addDiagnosisViewModel = new AddDiagnosisViewModel();
    addPrescriptionViewModel = new AddPrescriptionViewModel();
    addLabResultViewModel = new AddLabResultViewModel();
    addReferralViewModel = new AddReferralViewModel();
    addVaccinationViewModel = new AddVaccinationViewModel();
    sharedData = PatientsSharedData.getInstance();
  }

  /**
   * Gets the AddDiagnosisViewModel.
   * @return the view model used for adding diagnoses
   */
  public AddDiagnosisViewModel getAddDiagnosisViewModel()
  {
    return addDiagnosisViewModel;
  }

  /**
   * Gets the PatientsViewModel.
   * @return the view model used for managing patients
   */
  public PatientsViewModel getPatientsViewModel()
  {
    return patientsViewModel;
  }

  /**
   * Gets the AddPrescriptionViewModel.
   * @return the view model used for adding prescriptions
   */
  public AddPrescriptionViewModel getAddPrescriptionViewModel()
  {
    return addPrescriptionViewModel;
  }

  /**
   * Gets the add Lab Result ViewModel.
   * @return the view model used for adding lab results
   */
  public AddLabResultViewModel getAddLabResultViewModel() {
    if (addLabResultViewModel == null) {
      addLabResultViewModel = new AddLabResultViewModel();
    }
    return addLabResultViewModel;
  }

  /**
   * Gets the add Referral ViewModel.
   * @return the view model used for adding referrals
   */
  public AddReferralViewModel getAddReferralViewModel()
  {
    return addReferralViewModel;
  }

  /**
   * Gets the add Vaccination ViewModel.
   * @return the view model used for adding vaccinations
   */
  public AddVaccinationViewModel getAddVaccinationViewModel()
  {
    return addVaccinationViewModel;
  }
}
