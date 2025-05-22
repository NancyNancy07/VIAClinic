package client.viewModel.managePatients;

import client.viewModel.patientJournal.PatientVaccinationViewModel;

public class ManagePatientViewModelFactory
{
  private PatientsViewModel patientsViewModel;
  private AddDiagnosisViewModel addDiagnosisViewModel;
  private PatientsSharedData sharedData;
  private AddPrescriptionViewModel addPrescriptionViewModel;
  private AddLabResultViewModel addLabResultViewModel;
  private AddReferralViewModel addReferralViewModel;
  private AddVaccinationViewModel addVaccinationViewModel;

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



  public AddLabResultViewModel getAddLabResultViewModel() {
    if (addLabResultViewModel == null) {
      addLabResultViewModel = new AddLabResultViewModel();
    }
    return addLabResultViewModel;
  }

  public AddReferralViewModel getAddReferralViewModel()
  {
    return addReferralViewModel;
  }

  public AddVaccinationViewModel getAddVaccinationViewModel()
  {
    return addVaccinationViewModel;
  }
}
