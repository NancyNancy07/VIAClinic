package client.viewModel.doctorAppointment;

import client.clientNetwork.PatientClient;
import client.model.clientBookAppointment.*;
import client.viewModel.bookAppointment.BookAppointmentSharedData;
import client.viewModel.loginSystem.LoginSharedData;
import server.model.bookAppointment.Patient;

import java.util.List;

/**
 * DoctorAppointmentViewModel is responsible for managing the doctor's appointment view.
 * It interacts with the ClientAppointmentModel to retrieve and modify appointment data.
 */
public class DoctorAppointmentViewModel
{
  private ClientAppointmentModel model;
  private BookAppointmentSharedData sharedData;
  private List<Patient> cachedPatientList;

  /**
   * Constructor for DoctorAppointmentViewModel.
   * Initializes the model and retrieves the cached patient list.
   *
   * @param model the ClientAppointmentModel used to manage appointments
   */
  public DoctorAppointmentViewModel(ClientAppointmentModel model)
  {
    sharedData = BookAppointmentSharedData.getInstance();
    this.model = model;
    PatientClient client = new PatientClient();
    cachedPatientList = client.getPatientList();
  }

  /**
   * Retrieves the list of Doctor appointments.
   * @return ClientAppointmentList containing appointments for the logged-in doctor.
   */
  public ClientAppointmentList getDoctorAppointmentList()
  {
    int doctorId = LoginSharedData.getInstance().getId();
    return model.getDoctorAppointmentList(doctorId);
  }

  /**
   * Gets username of the logged-in doctor.
   * @return the username of the doctor.
   */
  public String getUsername()
  {
    return LoginSharedData.getInstance().getUsername();
  }

  /**
   * Reschedules an appointment for a patient.
   * @param appointment the ClientAppointment to be rescheduled
   * @param mode the mode of the appointment (e.g., in-person)
   * @param newDateTime  the new date and time for the appointment
   */
  public void rescheduleAppointment(ClientAppointment appointment, String mode,
      ClientNewDateTime newDateTime)
  {
    appointment.setMode(mode);
    appointment.setDateTime(newDateTime);

    model.modifyAppointment(appointment.getAppointmentID(),
        appointment.getPatientID(), appointment.getDoctor(), newDateTime, mode);

  }
}