package client.viewModel.patientJournal;

import client.viewModel.managePatients.PatientsViewModel;

public class PatientJournalViewModelFactory
{
  private final PatientDiagnosisViewModel patientDiagnosisViewModel;
  private final PatientReferralViewModel patientReferralViewModel;
  private final PatientVaccinationViewModel patientVaccinationViewModel;
  private final PatientsViewModel patientsViewModel;
  private final PatientLabResultViewModel patientLabResultsViewModel;

  public PatientJournalViewModelFactory()
  {
    this.patientDiagnosisViewModel = new PatientDiagnosisViewModel();
    this.patientReferralViewModel = new PatientReferralViewModel();
    this.patientVaccinationViewModel = new PatientVaccinationViewModel();
    this.patientLabResultsViewModel = new PatientLabResultViewModel();
    this.patientsViewModel = new PatientsViewModel();
  }

  public PatientDiagnosisViewModel getPatientDiagnosisViewModel()
  {
    return patientDiagnosisViewModel;
  }

  public PatientReferralViewModel getPatientReferralViewModel()
  {
    return patientReferralViewModel;
  }

  public PatientVaccinationViewModel getPatientVaccinationViewModel()
  {
    return patientVaccinationViewModel;
  }

  public PatientLabResultViewModel getPatientLabResultsViewModel()
  {
    return patientLabResultsViewModel;
  }

  public PatientsViewModel getPatientsViewModel()
  {
    return patientsViewModel;
  }
}
