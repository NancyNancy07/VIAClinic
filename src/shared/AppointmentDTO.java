package shared;

/**
 * AppointmentDTO class represents a Data Transfer Object for appointments.
 * It contains information about the appointment such as date, time, doctor,
 * patient ID, mode of appointment, and an optional PatientDTO object.
 */
public class AppointmentDTO
{
  private String date;
  private String time;
  private DoctorDTO doctor;
  private int patientId;
  private String mode;
  private int id;
  private PatientDTO patientDTO;

  /**
   * Default constructor for AppointmentDTO.
   * Initializes the fields to default values.
   * @param id the unique identifier for the appointment
   *  @param date the date of the appointment
   * @param time the time of the appointment
   * @param doctor the doctor associated with the appointment
   * @param patientId the ID of the patient associated with the appointment
   * @param mode the mode of the appointment (e.g., in-person, telehealth)
   */
  public AppointmentDTO(int id, String date, String time, DoctorDTO doctor,
      int patientId, String mode)
  {
    this.id = id;
    this.date = date;
    this.time = time;
    this.doctor = doctor;
    this.patientId = patientId;
    this.mode = mode;
  }

  /**
   * Constructor for AppointmentDTO with PatientDTO.
   * Initializes the fields including a PatientDTO object.
   * @param id the unique identifier for the appointment
   * @param date the date of the appointment
   * @param time the time of the appointment
   * @param doctor the doctor associated with the appointment
   * @param patientDTO the PatientDTO object associated with the appointment
   * @param mode the mode of the appointment (e.g., in-person, telehealth)
   */
  public AppointmentDTO(int id, String date, String time, DoctorDTO doctor,
      PatientDTO patientDTO, String mode)
  {
    this.id = id;
    this.date = date;
    this.time = time;
    this.doctor = doctor;
    this.patientDTO = patientDTO;
    this.mode = mode;
  }

  // Getters and setters
  /**
   * Gets the date of the appointment.
   * @return the date as a String
   */
  public String getDate()
  {
    return date;
  }

  /**
   * Sets the date of the appointment.
   * @param date the date to set
   */
  public void setDate(String date)
  {
    this.date = date;
  }

  /**
   * Gets the time of the appointment.
   * @return the time as a String
   */
  public String getTime()
  {
    return time;
  }

  /**
   * Sets the time of the appointment.
   * @param time the time to set
   */
  public void setTime(String time)
  {
    this.time = time;
  }

  /**
   * Gets the doctor associated with the appointment.
   * @return the DoctorDTO object
   */
  public DoctorDTO getDoctor()
  {
    return doctor;
  }

  /**
   * Sets the doctor associated with the appointment.
   * @param doctor the DoctorDTO object to set
   */
  public void setDoctor(DoctorDTO doctor)
  {
    this.doctor = doctor;
  }

  /**
   * Gets the ID of the patient associated with the appointment.
   * @return the patient ID as an integer
   */
  public int getPatientId()
  {
    return patientId;
  }

  /**
   * Sets the ID of the patient associated with the appointment.
   * @param patientId the patient ID to set
   */
  public void setPatientId(int patientId)
  {
    this.patientId = patientId;
  }

  /**
   * Gets the mode of the appointment.
   * @return the mode as a String
   */
  public String getMode()
  {
    return mode;
  }

  /**
   * Sets the mode of the appointment.
   * @param mode the mode to set (e.g., in-person, telehealth)
   */
  public void setMode(String mode)
  {
    this.mode = mode;
  }

  /**
   * Gets the unique identifier for the appointment.
   * @return the appointment ID as an integer
   */
  public int getId()
  {
    return id;
  }

  /**
   * Sets the unique identifier for the appointment.
   * @param id the appointment ID to set
   */
  public void setId(int id)
  {
    this.id = id;
  }

  /**
   * Gets the PatientDTO object associated with the appointment.
   * @return the PatientDTO object
   */
  public PatientDTO getPatientDTO()
  {
    return patientDTO;
  }

  /**
   * Sets the PatientDTO object associated with the appointment.
   * @param patientDTO the PatientDTO object to set
   */
  public void setPatientDTO(PatientDTO patientDTO)
  {
    this.patientDTO = patientDTO;
  }
}
