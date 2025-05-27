package client.viewModel.myPatient;

import client.viewModel.bookAppointment.BookAppointmentSharedData;
import server.model.bookAppointment.Patient;

/**
 * PatientInformationViewModel is responsible for managing the patient information view.
 * It interacts with the shared data model to retrieve and set patient information.
 */
public class PatientInformationViewModel
{
  private PatientInformationSharedData sharedData = PatientInformationSharedData.getInstance();

  /**
   * Default constructor for PatientInformationViewModel.
   * Initializes the shared data instance.
   */
  public PatientInformationViewModel()
  {

  }

  /**
   * Retrieves the patient information from the shared data model.
   *
   * @return the Patient object containing patient information
   */
  public PatientInformationSharedData getSharedData()
  {
    return sharedData;
  }

  /**
   * Sets the shared data model with the provided PatientInformationSharedData.
   *
   * @param sharedData the PatientInformationSharedData to set
   */
  public void setSharedData(PatientInformationSharedData sharedData)
  {
    this.sharedData = sharedData;
  }
}