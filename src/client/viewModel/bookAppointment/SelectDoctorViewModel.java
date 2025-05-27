package client.viewModel.bookAppointment;

import client.clientNetwork.PatientAppointmentClient;
import client.model.clientBookAppointment.ClientDoctor;
import client.model.clientBookAppointment.ClientDoctorList;

import java.util.List;

/**
 * SelectDoctorViewModel is responsible for managing the selection of doctors
 * in the book appointment process. It interacts with the shared data model
 * to retrieve and set the selected doctor.
 */
public class SelectDoctorViewModel
{
  private final BookAppointmentSharedData sharedData = BookAppointmentSharedData.getInstance();

  /**
   * Initializes the SelectDoctorViewModel.
   * This method can be used to perform any necessary setup or initialization.
   */
  public List<ClientDoctor> getDoctorList()
  {
    PatientAppointmentClient client = new PatientAppointmentClient();
   ClientDoctorList doctors = client.getDoctorList();
    return (doctors != null) ? doctors.getAllDoctors() : List.of();
  }

  /**
   * Sets the selected doctor ID in the shared data model.
   *
   * @param doctorId The ID of the selected doctor.
   */
  public void setSelectedDoctor(int doctorId)
  {
    sharedData.setSelectedDoctorId(doctorId);
  }

  /**
   * Retrieves the ID of the currently selected doctor from the shared data model.
   *
   * @return The ID of the selected doctor.
   */
  public int getSelectedDoctorId()
  {
    return sharedData.getSelectedDoctorId();
  }
}
