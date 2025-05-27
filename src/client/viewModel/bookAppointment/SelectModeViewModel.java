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
public class SelectModeViewModel
{
  private final BookAppointmentSharedData sharedData = BookAppointmentSharedData.getInstance();

  /**
   * Sets the consultation mode for the appointment.
   * @param mode the consultation mode to set, e.g., "in-person".
   */
  public void setMode(String mode)
  {
    sharedData.setConsultationMode(mode);
  }

  /**
   * Gets the consultation mode currently set in the shared data.
   *
   * @return the consultation mode, e.g., "in-person"
   */
  public String getConsultationMode()
  {
    return sharedData.getConsultationMode();
  }

  /**
   * Gets the id of the selected doctor.
   * @return the id of the selected doctor
   */
  public int getSelectedDoctorId()
  {
    return sharedData.getSelectedDoctorId();
  }

  /**
   * Gets the selected doctor's name from the shared data.
   * @return the name of the selected doctor, or "Unknown" if not found
   */
  public String getSelectedDoctorName()
  {
    PatientAppointmentClient client = new PatientAppointmentClient();
    ClientDoctorList doctors = client.getDoctorList();
    if (doctors != null)
    {
      for (ClientDoctor doc : doctors.getAllDoctors())
      {
        if (doc.getDoctorID() == sharedData.getSelectedDoctorId())
        {
          return doc.getFirstName() + " " + doc.getLastName();
        }
      }
    }
    return "Unknown";
  }
}
