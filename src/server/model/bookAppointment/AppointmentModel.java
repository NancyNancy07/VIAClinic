package server.model.bookAppointment;

/**
 * Appointment class represents an appointment in the healthcare system.
 * It contains details such as date, time, patient ID, doctor, and mode of appointment.
 */
public interface AppointmentModel
{
  /**
   * Books an appointment with the specified details.
   *
   * @param id         the ID of the appointment
   * @param dateTime   the date and time of the appointment
   * @param patientID  the ID of the patient
   * @param doctor     the doctor for the appointment
   * @param mode       the mode of the appointment (e.g., in-person, telehealth)
   * @return          an Appointment object representing the booked appointment
   */
  default Appointment bookAppointment(int id, NewDateTime dateTime, int patientID,
      Doctor doctor, String mode)
  {
    return null;
  }
  /**
   * Cancels an appointment by its ID.
   *
   * @param appointmentId the ID of the appointment to cancel
   * @return true if the appointment was successfully cancelled, false otherwise
   */
  boolean cancelAppointment(int appointmentId);

  /**
   * Modifies an existing appointment with new details.
   *
   * @param appointmentId the ID of the appointment to modify
   * @param newDateTime   the new date and time for the appointment
   * @param newMode       the new mode of the appointment (e.g., in-person, telehealth)
   * @return an Appointment object representing the modified appointment
   */
  Appointment modifyAppointment(int appointmentId, NewDateTime newDateTime,
      String newMode);

  /**
   * Gets doctor list.
   * @return DoctorList containing all doctors
   */
  DoctorList getDoctorList();
  /**
   * Gets appointment list.
   * @return AppointmentList containing all appointments
   */
  AppointmentList getAppointmentList();
}
