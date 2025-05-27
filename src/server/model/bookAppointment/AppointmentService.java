package server.model.bookAppointment;

/**
 * Appointment class represents an appointment in the healthcare system.
 * It contains details such as date, time, patient ID, doctor, and mode of appointment.
 */
public class AppointmentService implements AppointmentModel
{
  private AppointmentList appointmentList;
  private DoctorList doctorList;
  private PatientList patientList;



  /**
   * Constructor for AppointmentService.
   * Initializes the service with the provided lists of doctors, patients, and appointments.
   *
   * @param doctorList the list of doctors
   * @param patientList the list of patients
   * @param appointmentList the list of appointments
   */
  public AppointmentService(DoctorList doctorList, PatientList patientList,
      AppointmentList appointmentList) {
    this.doctorList = doctorList;
    this.patientList = patientList;
    this.appointmentList = appointmentList;
  }

  /**
   * Books an appointment for a patient with a specified doctor and mode.
   * @param id         the ID of the appointment
   * @param dateTime   the date and time of the appointment
   * @param patientID  the ID of the patient
   * @param doctor     the doctor for the appointment
   * @param mode       the mode of the appointment (e.g., in-person, telehealth)
   * @return the newly created Appointment object, or null if the appointment could not be booked
   */
  @Override
  public Appointment bookAppointment(int id, NewDateTime dateTime, int patientID, Doctor doctor, String mode) {
    if (dateTime == null || mode == null) {
      throw new IllegalArgumentException("Date/time and mode cannot be null.");
    }

    Patient patient = patientList.findPatientByID(patientID);
    if (doctor == null || patient == null) {
      return null;
    }

    Appointment newAppointment = new Appointment(dateTime, patientID, doctor, mode);
    newAppointment.setDoctorID(doctor.getDoctorID());
    appointmentList.addAppointment(newAppointment);
    return newAppointment;
  }

  /**
   * Cancels an appointment by its ID.
   * @param appointmentID the ID of the appointment to cancel
   * @return true if the appointment was successfully canceled, false otherwise
   */
  @Override
  public boolean cancelAppointment(int appointmentID) {
    Appointment appointment = appointmentList.findAppointmentByID(appointmentID);
    if (appointment != null) {
      appointmentList.removeAppointment(appointment);
      return true;
    }
    return false;
  }

  /**
   * Modifies an existing appointment by updating its date/time and mode.
   * @param appointmentID the ID of the appointment to modify
   * @param newDateTime   the new date and time for the appointment
   * @param newMode       the new mode for the appointment
   * @return the modified Appointment object, or null if the appointment could not be found
   */
  @Override
  public Appointment modifyAppointment(int appointmentID, NewDateTime newDateTime, String newMode) {
    if (newDateTime == null || newMode == null) {
      throw new IllegalArgumentException("New date/time and mode cannot be null.");
    }

    Appointment appointment = appointmentList.findAppointmentByID(appointmentID);
    if (appointment != null) {
      appointment.setDateTime(newDateTime);
      appointment.setMode(newMode);
      return appointment;
    }
    return null;
  }

  /**
   * Retrieves the list of all doctors.
   * @return the doctor list containing all doctors
   */
  @Override
  public DoctorList getDoctorList() {
    return doctorList;
  }

  /**
   * Retrieves the list of all appointments.
   * @return the appointment list containing all appointments
   */
  @Override
  public AppointmentList getAppointmentList() {
    return appointmentList;
  }
}
