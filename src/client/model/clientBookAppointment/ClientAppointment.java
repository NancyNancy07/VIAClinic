package client.model.clientBookAppointment;

/**
 * ClientAppointment represents an appointment in the client application.
 */
public class ClientAppointment
{
  private ClientNewDateTime dateTime;
  private int appointmentID;
  private String mode;
  private int patientID;
  //  private int doctorId;
  private ClientDoctor doctor;
  private ClientPatient patient;

  /**
   * Constructor for ClientAppointment with dateTime, patientID, doctor, and mode.
   *
   * @param dateTime the date and time of the appointment
   * @param patientID the ID of the patient
   * @param doctor the doctor for the appointment
   * @param mode the mode of the appointment (e.g., in-person)
   */
  public ClientAppointment(ClientNewDateTime dateTime, int patientID,
      ClientDoctor doctor, String mode)
  {
    this.dateTime = dateTime;
    this.doctor = doctor;
    this.mode = mode;
    this.patientID = patientID;
  }

  /**
   * Constructor for ClientAppointment with dateTime, patient, doctor, and mode.
   *
   * @param dateTime the date and time of the appointment
   * @param patient the patient for the appointment
   * @param doctor the doctor for the appointment
   * @param mode the mode of the appointment (e.g., in-person)
   */
  public ClientAppointment(ClientNewDateTime dateTime, ClientPatient patient,
      ClientDoctor doctor, String mode)
  {
    this.dateTime = dateTime;
    this.patient = patient;
    this.patientID = patient.getPatientID();
    this.doctor = doctor;
    this.mode = mode;
  }

  /**
   * Constructor for ClientAppointment with appointmentID, dateTime, patientID,
   * doctor, and mode.
   *
   * @param appointmentID the ID of the appointment
   * @param dateTime the date and time of the appointment
   * @param patientID the ID of the patient
   * @param doctor the doctor for the appointment
   * @param mode the mode of the appointment (e.g., in-person)
   */
  public ClientAppointment(int appointmentID, ClientNewDateTime dateTime,
      int patientID, ClientDoctor doctor, String mode)
  {
    this.appointmentID = appointmentID;
    this.dateTime = dateTime;
    this.doctor = doctor;
    this.mode = mode;
    this.patientID = patientID;
  }

  /**
   * Constructor for ClientAppointment with appointmentID, dateTime, patient,
   * doctor, and mode.
   *
   * @param appointmentID the ID of the appointment
   * @param dateTime the date and time of the appointment
   * @param patient the patient for the appointment
   * @param doctor the doctor for the appointment
   * @param mode the mode of the appointment (e.g., in-person)
   */
  public ClientAppointment(int appointmentID, ClientNewDateTime dateTime,
      ClientPatient patient, ClientDoctor doctor, String mode)
  {
    this.appointmentID = appointmentID;
    this.dateTime = dateTime;
    this.doctor = doctor;
    this.mode = mode;
    this.patient = patient;
  }

  /**
   * Gets the patient associated with this appointment.
   *
   * @return the patient
   */
  public ClientPatient getPatient()
  {
    return patient;
  }

  /**
   * Sets the patient for this appointment.
   *
   * @param patient the patient to set
   */
  public void setPatient(ClientPatient patient)
  {
    this.patient = patient;
    this.patientID = patient.getPatientID();
  }

  /**
   * Gets the date of the appointment in "dd/mm/yyyy" format.
   *
   * @return the date of the appointment
   */
  public String getDate()
  {
    return dateTime.getDay() + "/" + dateTime.getMonth() + "/"
        + dateTime.getYear();
  }

  /**
   * Gets the time of the appointment in "HH:mm" format.
   *
   * @return the time of the appointment
   */
  public String getTime()
  {
    return dateTime.getHour() + ":" + dateTime.getMinute();
  }


  /**
   * Gets the id of the appointment.
   *
   * @return the id of the appointment
   */
  public int getAppointmentID()
  {
    return appointmentID;
  }

  /**
   * Gets the doctor associated with this appointment.
   *
   * @return the doctor
   */
  public ClientDoctor getDoctor()
  {
    return doctor;
  }

  /**
   * Gets the ID of the patient associated with this appointment.
   *
   * @return the ID of the patient
   */
  public int getPatientID()
  {
    return patientID;
  }

  /**
   * Gets the date and time of the appointment.
   *
   * @return the date and time of the appointment
   */
  public void setDateTime(ClientNewDateTime dateTime)
  {
    this.dateTime = dateTime;
  }

  /**
   * Sets the appointment ID.
   *
   * @param appointmentID the ID to set
   */
  public void setAppointmentID(int appointmentID)
  {
    this.appointmentID = appointmentID;
  }

  /**
   * Sets the doctor for this appointment.
   *
   * @param doctor the doctor to set
   */
  public void setDoctorID(ClientDoctor doctor)
  {
    this.doctor = doctor;
  }

  /**
   * Sets the mode of the appointment.
   *
   * @param mode the mode to set (e.g., in-person)
   */
  public void setMode(String mode)
  {
    this.mode = mode;
  }

  /**
   * Sets the ID of the patient associated with this appointment.
   *
   * @param patientID the ID to set
   */
  public void setPatientID(int patientID)
  {
    this.patientID = patientID;
  }

  /**
   * Gets the mode of the appointment.
   *
   * @return the mode of the appointment
   */
  public String getMode()
  {
    return mode;
  }

  /**
   * Returns a string representation of the appointment.
   *
   * @return a string representation of the appointment
   */
  @Override public String toString()
  {
    return "Appointment ID: " + appointmentID + ", " + dateTime + ", Doctor= "
        + doctor.getName() + ", Mode='" + mode + "'\n";

  }

  /**
   * Checks if this appointment is equal to another object.
   *
   * @param obj the object to compare with
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    ClientAppointment other = (ClientAppointment) obj;
    return dateTime.equals(other.dateTime)
        && appointmentID == other.appointmentID && doctor == other.doctor
        && patientID == other.patientID && mode.equals(other.mode);
  }
}
