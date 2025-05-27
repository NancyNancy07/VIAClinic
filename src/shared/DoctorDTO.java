package shared;

/**
 * AppointmentDTO class represents a Data Transfer Object for appointments.
 * It contains information about the appointment such as date, time, doctor,
 * patient ID, mode of appointment, and an optional PatientDTO object.
 */
public class DoctorDTO
{
  private int doctorID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String userName;
  private String password;


  public DoctorDTO()
  {
    // no-arg constructor for serialization
  }

  /**
   * Constructor for DoctorDTO with all fields.
   *
   * @param doctorID    The unique identifier for the doctor.
   * @param firstName   The first name of the doctor.
   * @param lastName    The last name of the doctor.
   * @param email       The email address of the doctor.
   * @param phoneNumber The phone number of the doctor.
   * @param userName    The username for the doctor's account.
   * @param password    The password for the doctor's account.
   */
  public DoctorDTO(int doctorID, String firstName, String lastName,
      String email, String phoneNumber,
      String userName, String password)
  {
    this.doctorID = doctorID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.userName = userName;
    this.password = password;
  }

  /**
   * Gets the unique identifier for the doctor.
   * @return the doctor ID
   */
  public int getDoctorID()
  {
    return doctorID;
  }

  /**
   * Sets the unique identifier for the doctor.
   * @param doctorID the doctor ID to set
   */
  public void setDoctorID(int doctorID)
  {
    this.doctorID = doctorID;
  }

  /**
   * Gets the first name of the doctor.
   * @return the first name as a String
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Sets the first name of the doctor.
   * @param firstName the first name to set
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * Gets the last name of the doctor.
   * @return the last name as a String
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Sets the last name of the doctor.
   * @param lastName the last name to set
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * Gets the email address of the doctor.
   * @return the email address as a String
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * Sets the email address of the doctor.
   * @param email the email address to set
   */
  public void setEmail(String email)
  {
    this.email = email;
  }

  /**
   * Gets the phone number of the doctor.
   * @return the phone number as a String
   */
  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  /**
   * Sets the phone number of the doctor.
   * @param phoneNumber the phone number to set
   */
  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Gets the username of the doctor.
   * @return the username as a String
   */
  public String getUserName()
  {
    return userName;
  }

  /**
   * Sets the username of the doctor.
   * @param userName the username to set
   */
  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  /**
   * Gets the password of the doctor.
   * @return the password as a String
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Sets the password of the doctor.
   * @param password the password to set
   */
  public void setPassword(String password)
  {
    this.password = password;
  }
}
