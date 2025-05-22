package client.viewModel.managePatients;

import client.viewModel.patientJournal.PatientVaccinationViewModel;

public class PatientJournalViewModelFactory
{
  private PatientsViewModel patientsViewModel;
  private AddDiagnosisViewModel addDiagnosisViewModel;
  private PatientsSharedData sharedData;
  private AddPrescriptionViewModel addPrescriptionViewModel;
  private AddReferralViewModel addReferralViewModel;
  private AddVaccinationViewModel addVaccinationViewModel;
  private PatientVaccinationViewModel patientVaccinationViewModel;

  public PatientJournalViewModelFactory()
  {
    patientsViewModel = new PatientsViewModel();
    addDiagnosisViewModel = new AddDiagnosisViewModel();
    addPrescriptionViewModel = new AddPrescriptionViewModel();
    addReferralViewModel = new AddReferralViewModel();
    addVaccinationViewModel = new AddVaccinationViewModel();
    sharedData = PatientsSharedData.getInstance();
    patientVaccinationViewModel = new PatientVaccinationViewModel();
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

  public AddVaccinationViewModel getAddVaccinationViewModel()
  {
    return addVaccinationViewModel;
  }

  public PatientVaccinationViewModel getPatientVaccinationViewModel()
  {
    return patientVaccinationViewModel;
  }
}
