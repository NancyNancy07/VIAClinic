package server.model.bookAppointment;

/**
 * Appointment class represents an appointment in the healthcare system.
 * It contains details such as date, time, patient ID, doctor, and mode of appointment.
 */
public class Appointment
{
  private NewDateTime dateTime;
  private int appointmentID;
  private String mode;
  private int patientID;
  private Doctor doctor;
  private Patient patient;

  /**
   * Default constructor for Appointment.
   * Initializes the dateTime and mode to default values.
   *
   * @param dateTime the date and time of the appointment
   * @param patientID the ID of the patient
   * @param doctor the doctor associated with the appointment
   * @param mode the mode of the appointment (e.g., in-person, telehealth)
   */
  public Appointment(NewDateTime dateTime, int patientID, Doctor doctor,
      String mode)
  {
    this.dateTime = dateTime;
    this.mode = mode;
    this.doctor = doctor;
    this.patientID = patientID;
  }

  /**
   * Constructor for Appointment with a Patient object.
   * Initializes the dateTime, mode, doctor, and patient.
   *
   * @param dateTime the date and time of the appointment
   * @param patient the patient associated with the appointment
   * @param doctor the doctor associated with the appointment
   * @param mode the mode of the appointment (e.g., in-person, telehealth)
   */
  public Appointment(NewDateTime dateTime, Patient patient, Doctor doctor,
      String mode)
  {
    this.dateTime = dateTime;
    this.mode = mode;
    this.doctor = doctor;
    this.patient = patient;
  }

  /**
   * Gets patient associated with the appointment.
   * @return  the Patient object associated with this appointment
   */
  public Patient getPatient()
  {
    return patient;
  }

  /**
   * Sets the patient for this appointment.
   * @param patient the Patient object to set
   */
  public void setPatient(Patient patient)
  {
    this.patient = patient;
  }

  /**
   * Gets the date and time of the appointment.
   * @return the NewDateTime object representing the date and time
   */
  public String getDate()
  {
    return dateTime.getDay() + "/" + dateTime.getMonth() + "/"
        + dateTime.getYear();
  }

  /**
   * Gets the time of the appointment in "HH:mm" format.
   * @return the time as a String
   */
  public String getTime()
  {
    return dateTime.getHour() + ":" + dateTime.getMinute();
  }

  /**
   * Gets appointment ID.
   * @return the appointment ID as an integer
   */
  public int getAppointmentID()
  {
    return appointmentID;
  }

  /**
   * Gets doctor associated with the appointment.
   * @return the Doctor object associated with this appointment
   */
  public Doctor getDoctor()
  {
    return doctor;
  }

  /**
   * Gets patient ID.
   * @return the patient ID as an integer
   */
  public int getPatientID()
  {
    return patientID;
  }

  /**
   * Gets the date and time of the appointment as a NewDateTime object.
   * @return the NewDateTime object representing the date and time
   */
  public void setDateTime(NewDateTime dateTime)
  {
    this.dateTime = dateTime;
  }

  /**
   * Sets the appointment ID.
   * @param appointmentID the appointment ID to set
   */
  public void setAppointmentID(int appointmentID)
  {
    this.appointmentID = appointmentID;
  }

  /**
   * Sets the doctorId for this appointment.
   * @param doctorID the Doctor object to set
   */
  public void setDoctorID(int doctorID)
  {
    this.doctor.setDoctorID(doctorID);
  }

  /**
   * Sets the mode of the appointment.
   * @param mode the mode of the appointment (e.g., in-person, telehealth)
   */
  public void setMode(String mode)
  {
    this.mode = mode;
  }

  /**
   * Sets the patient ID for this appointment.
   * @param patientID the ID of the patient to set
   */
  public void setPatientID(int patientID)
  {
    this.patientID = patientID;
  }

  /**
   * Gets the mode of the appointment.
   * @return the mode of the appointment as a String
   */
  public String getMode()
  {
    return mode;
  }

  /**
   * Gets the String representation of the appointment.
   * @return a String containing appointment details
   */
  @Override public String toString()
  {
    if (doctor != null)
    {
      return "Appointment ID: " + appointmentID + ", " + dateTime + ", Doctor= "
          + doctor.getName() + ", Mode='" + mode + "'\n";
    }
    else
    {
      return dateTime + ", Doctor= Unknown, Mode='" + mode + "'\n";
    }
  }

  /**
   * Checks if two Appointment objects are equal.
   * @param obj the object to compare with
   * @return true if both appointments are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Appointment other = (Appointment) obj;
    return dateTime.equals(other.dateTime)
        && appointmentID == other.appointmentID && doctor == other.doctor
        && patientID == other.patientID && mode.equals(other.mode);
  }
}
