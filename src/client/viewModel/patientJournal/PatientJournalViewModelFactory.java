package client.viewModel.patientJournal;

import client.viewModel.managePatients.PatientsViewModel;

public class PatientJournalViewModelFactory
{
  private PatientDiagnosisViewModel patientDiagnosisViewModel;
  private PatientReferralViewModel patientReferralViewModel;
  private PatientVaccinationViewModel patientVaccinationViewModel;
  private PatientsViewModel patientsViewModel;
  private PatientLabResultViewModel patientLabResultsViewModel;

  public PatientJournalViewModelFactory()
  {
    this.patientDiagnosisViewModel = new PatientDiagnosisViewModel();
    this.patientReferralViewModel = new PatientReferralViewModel();
    this.patientVaccinationViewModel = new PatientVaccinationViewModel();
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
