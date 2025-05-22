package client.viewModel.patients;

import client.viewModel.patientJournal.PatientVaccinationViewModel;

public class PatientJournalViewModelFactory
{
  private PatientsViewModel patientsViewModel;
  private AddDiagnosisViewModel addDiagnosisViewModel;
  private PatientsSharedData sharedData;
  private AddPrescriptionViewModel addPrescriptionViewModel;
  private AddVaccinationViewModel addVaccinationViewModel;
  private PatientVaccinationViewModel patientVaccinationViewModel;

  public PatientJournalViewModelFactory()
  {
    patientsViewModel = new PatientsViewModel();
    addDiagnosisViewModel = new AddDiagnosisViewModel();
    addPrescriptionViewModel = new AddPrescriptionViewModel();
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

  public AddVaccinationViewModel getAddVaccinationViewModel()
  {
    return addVaccinationViewModel;
  }

  public PatientVaccinationViewModel getPatientVaccinationViewModel()
  {
    return patientVaccinationViewModel;
  }
}
