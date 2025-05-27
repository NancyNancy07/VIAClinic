package client.model.clientBookAppointment;

import client.clientNetwork.PatientAppointmentClient;
import client.clientNetwork.PatientClient;
import server.model.bookAppointment.Patient;

import java.util.List;

/**
 * ClientAppointmentService is responsible for managing client-side appointment
 * operations, including booking, modifying, and retrieving appointments and
 * doctor lists.
 */
public class ClientAppointmentService implements ClientAppointmentModel
{
  private final ClientAppointmentList appointmentList;
  private final ClientDoctorList doctorList;
  private final PatientAppointmentClient networkClient;
  private final PatientClient patientClient;

  /**
   * Constructor initializes the appointment list, doctor list, and network
   * clients.
   */
  public ClientAppointmentService()
  {
    this.appointmentList = new ClientAppointmentList();
    this.doctorList = new ClientDoctorList();
    this.networkClient = new PatientAppointmentClient();
    this.patientClient = new PatientClient();
  }

  /**
   * Books an appointment for a patient with the specified date and time,
   * patient ID, doctor, and mode.
   *
   * @param dateTime the date and time of the appointment
   * @param patientId the ID of the patient
   * @param doctor the doctor for the appointment
   * @param mode the mode of the appointment (e.g., in-person)
   * @return
   */
  @Override public ClientAppointment bookAppointment(ClientNewDateTime dateTime,
      int patientId, ClientDoctor doctor, String mode)
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patientId,
        doctor, mode);
    return networkClient.bookAppointment(appointment);
  }

  /**
   * Cancels an appointment by its ID.
   *
   * @param appointmentId the ID of the appointment to cancel
   * @return true if the appointment was successfully canceled, false otherwise
   */
  @Override public boolean cancelAppointment(int appointmentId)
  {
    return networkClient.cancelAppointment(appointmentId);
  }

  /**
   * Modifies an existing appointment with a new date, time, and mode.
   *
   * @param appointmentId the ID of the appointment to modify
   * @param patientId the ID of the patient
   * @param doctor the doctor for the appointment
   * @param newDateTime the new date and time for the appointment
   * @param newMode the new mode of the appointment
   * @return the modified appointment
   */
  @Override public ClientAppointment modifyAppointment(int appointmentId,
      int patientId, ClientDoctor doctor, ClientNewDateTime newDateTime,
      String newMode)
  {
    return networkClient.modifyAppointment(appointmentId, patientId, doctor,
        newDateTime, newMode);
  }

  /**
   * Retrieves a list of appointments for a specific patient by their ID.
   *
   * @param id the ID of the patient
   * @return a list of appointments for the patient
   */
  @Override public ClientAppointmentList getAppointmentList(int id)
  {
    return networkClient.getAppointmentByPatientId(id);
  }

  /**
   * Retrieves a list of appointments for a specific doctor by their ID.
   *
   * @param id the ID of the doctor
   * @return a list of appointments for the doctor
   */
  @Override public ClientAppointmentList getDoctorAppointmentList(int id)
  {
    ClientAppointmentList fetchedList = networkClient.getAppointmentByDoctorId(
        id);
    appointmentList.getAllAppointments().clear();
    appointmentList.getAllAppointments()
        .addAll(fetchedList.getAllAppointments());
    return appointmentList;
  }

  /**
   * Retrieves a list of all doctors.
   *
   * @return a list of all doctors
   */
  @Override public ClientDoctorList getDoctorList()
  {
    ClientDoctorList fetchedList = networkClient.getDoctorList();
    doctorList.getAllDoctors().clear();
    doctorList.getAllDoctors().addAll(fetchedList.getAllDoctors());
    return doctorList;
  }
}
