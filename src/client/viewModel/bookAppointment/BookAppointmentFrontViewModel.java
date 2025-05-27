package client.viewModel.bookAppointment;

import client.model.clientBookAppointment.*;
import client.viewModel.loginSystem.LoginSharedData;

/**
 * BookAppointmentFrontViewModel is the view model for booking appointments.
 * It interacts with the ClientAppointmentModel to manage appointment data.
 */
public class BookAppointmentFrontViewModel
{
  private final ClientAppointmentModel model;

  /**
   * Constructor for BookAppointmentFrontViewModel.
   * Initializes the model used for booking appointments.
   *
   * @param model The ClientAppointmentModel instance to be used.
   */
  public BookAppointmentFrontViewModel(ClientAppointmentModel model)
  {
    this.model = model;
  }

  /**
   * Adds a new appointment for a patient.
   * This method interacts with the model to book an appointment based on the provided date, patient ID, doctor, and mode.
   *
   * @param date The date and time of the appointment.
   * @param patientId The ID of the patient booking the appointment.
   * @param doctor The doctor with whom the appointment is booked.
   * @param mode The mode of the appointment (e.g., in-person, telehealth).
   * @return The booked ClientAppointment object.
   */
  public ClientAppointment addAppointment(ClientNewDateTime date, int patientId,
      ClientDoctor doctor, String mode)
  {
    return model.bookAppointment(date, patientId, doctor, mode);

  }

  /**
   * Retrieves the list of appointments for the currently logged-in patient.
   * This method uses the model to fetch the appointment list based on the patient's ID.
   *
   * @return The ClientAppointmentList containing all appointments for the patient.
   */
  public ClientAppointmentList getAppointmentList()
  {
    int patientId = LoginSharedData.getInstance().getId();
    return model.getAppointmentList(patientId);

  }

}
