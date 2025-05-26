package client.model.clientBookAppointment;

import server.model.bookAppointment.Patient;

/**
 * ClientAppointmentModel interface defines the methods for booking, canceling,
 * modifying appointments, and retrieving doctor and appointment lists.
 */
public interface ClientAppointmentModel
{
  /**
   * Books an appointment with the specified date, patient ID, doctor, and mode.
   *
   * @param dateTime the date and time of the appointment
   * @param patientID the ID of the patient
   * @param doctor the doctor for the appointment
   * @param mode the mode of the appointment (e.g., in-person, telehealth)
   * @return a ClientAppointment object representing the booked appointment
   */
  ClientAppointment bookAppointment(ClientNewDateTime dateTime, int patientID,
      ClientDoctor doctor, String mode);
  /**
   * Cancels an appointment with the specified appointment ID.
   * @param appointmentId the ID of the appointment to cancel
   * @return true if the appointment was successfully canceled, false otherwise
   */
  boolean cancelAppointment(int appointmentId);

  /**
   * Modifies an existing appointment with the specified appointment ID,
   * patient ID, new date and time, and new mode.
   *
   * @param appointmentId the ID of the appointment to modify
   * @param patientId the ID of the patient
   * @param doctor the doctor for the appointment
   * @param newDateTime the new date and time for the appointment
   * @param newMode the new mode of the appointment
   * @return a ClientAppointment object representing the modified appointment
   */
  ClientAppointment modifyAppointment(int appointmentId, int patientId,
      ClientDoctor doctor, ClientNewDateTime newDateTime, String newMode);

  /**
   * Retrieves the list of doctors available for appointments.
   * @return a ClientDoctorList containing the list of doctors
   */
  ClientDoctorList getDoctorList();

  /**
   * Retrieves the list of appointments for a specific patient or doctor.
   * @param id the ID of the patient or doctor
   * @return a ClientAppointmentList containing the list of appointments
   */
  ClientAppointmentList getAppointmentList(int id);

  /**
   * Retrieves the list of appointments for a specific doctor.
   * @param id the ID of the doctor
   * @return a ClientAppointmentList containing the list of appointments for the doctor
   */
  ClientAppointmentList getDoctorAppointmentList(int id);
}
