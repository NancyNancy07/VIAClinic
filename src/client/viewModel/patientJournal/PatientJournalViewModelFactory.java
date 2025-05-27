package client.viewModel.patientJournal;

import client.viewModel.managePatients.PatientsViewModel;

/**
 * PatientJournalViewModelFactory is responsible for creating and providing instances
 * of view models used in the patient journal system. It initializes shared data
 * and individual view models for different aspects of patient management,
 * such as diagnosis, referral, vaccination, and lab results.
 */
public class PatientJournalViewModelFactory
{
  private final PatientDiagnosisViewModel patientDiagnosisViewModel;
  private final PatientReferralViewModel patientReferralViewModel;
  private final PatientVaccinationViewModel patientVaccinationViewModel;
  private final PatientsViewModel patientsViewModel;
  private final PatientLabResultViewModel patientLabResultsViewModel;

  /**
   * Default constructor for PatientJournalViewModelFactory.
   * Initializes the shared data and creates instances of the view models
   * required for managing patient journals.
   */
  public PatientJournalViewModelFactory()
  {
    this.patientDiagnosisViewModel = new PatientDiagnosisViewModel();
    this.patientReferralViewModel = new PatientReferralViewModel();
    this.patientVaccinationViewModel = new PatientVaccinationViewModel();
    this.patientLabResultsViewModel = new PatientLabResultViewModel();
    this.patientsViewModel = new PatientsViewModel();
  }

  /**
   * Gets the PatientDiagnosisViewModel.
   *
   * @return the view model used for managing patient diagnoses
   */
  public PatientDiagnosisViewModel getPatientDiagnosisViewModel()
  {
    return patientDiagnosisViewModel;
  }

  /**
   * Gets the PatientReferralViewModel.
   *
   * @return the view model used for managing patient referrals
   */
  public PatientReferralViewModel getPatientReferralViewModel()
  {
    return patientReferralViewModel;
  }

  /**
   * Gets the PatientVaccinationViewModel.
   *
   * @return the view model used for managing patient vaccinations
   */
  public PatientVaccinationViewModel getPatientVaccinationViewModel()
  {
    return patientVaccinationViewModel;
  }

  /**
   * Gets the PatientLabResultViewModel.
   *
   * @return the view model used for managing patient lab results
   */
  public PatientLabResultViewModel getPatientLabResultsViewModel()
  {
    return patientLabResultsViewModel;
  }

  /**
   * Gets the PatientsViewModel.
   *
   * @return the view model used for managing patients
   */
  public PatientsViewModel getPatientsViewModel()
  {
    return patientsViewModel;
  }
}
