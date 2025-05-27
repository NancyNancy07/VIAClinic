package server.model.bookAppointment;

import server.model.loginSystem.entities.User;

/**
 * The Doctor class represents a doctor in the appointment system.
 * It extends the User class and contains additional attributes specific to doctors.
 */
public class Doctor extends User
{
  private int doctorID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;

  /**
   * Constructor for the Doctor class.
   * Initializes a new instance of Doctor with the specified parameters.
   *
   * @param doctorID    The unique identifier for the doctor.
   * @param firstName   The first name of the doctor.
   * @param lastName    The last name of the doctor.
   * @param email       The email address of the doctor.
   * @param phoneNumber The phone number of the doctor.
   * @param userName    The username for the doctor's account.
   * @param password    The password for the doctor's account.
   */
  public Doctor(int doctorID, String firstName, String lastName, String email,
      String phoneNumber, String userName, String password)
  {

    super(userName, password);
    this.doctorID = doctorID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  /**
   * Constructor for the Doctor class without a doctorID.
   * Initializes a new instance of Doctor with the specified parameters.
   *
   * @param firstName   The first name of the doctor.
   * @param lastName    The last name of the doctor.
   * @param email       The email address of the doctor.
   * @param phoneNumber The phone number of the doctor.
   * @param userName    The username for the doctor's account.
   * @param password    The password for the doctor's account.
   */
  public Doctor(String firstName, String lastName, String email,
      String phoneNumber, String userName, String password)
  {
    super(userName, password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
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
   * Gets the first name of the doctor.
   * @return the first name
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Gets the last name of the doctor.
   * @return the last name
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Gets the email address of the doctor.
   * @return the email address
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * Gets the phone number of the doctor.
   * @return the phone number
   */
  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  /**
   * Gets the password for the doctor's account.
   * @return the password
   */
  @Override public String getPassword()
  {
    return super.getPassword();
  }

  /**
   * Gets the username for the doctor's account.
   * @return the username
   */
  @Override public String getUsername()
  {
    return super.getUsername();
  }

  /**
   * Gets the full name of the doctor by combining first and last names.
   * @return the full name as a String
   */
  public String getName()
  {
    return firstName + " " + lastName;
  }

  /**
   * Sets the unique identifier for the doctor.
   * @param doctorID the ID to set
   */
  public void setDoctorID(int doctorID)
  {
    this.doctorID = doctorID;
  }

  /**
   * Sets the full name of the doctor by splitting the provided name into first and last names.
   * @param name the full name to set
   */
  public void setName(String name)
  {
    this.firstName = name.split(" ")[0];
    this.lastName = name.split(" ")[1];
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
   * Sets the email address of the doctor.
   * @param email the email address to set
   */
  public void setEmail(String email)
  {
    this.email = email;
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
   * Sets the last name of the doctor.
   * @param lastName the last name to set
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * Returns a string representation of the Doctor object.
   * @return a string containing the doctor's name
   */
  @Override public String toString()
  {
    return "Name: '" + getName() + '\'';
  }

  /**
   * Checks if this Doctor object is equal to another object.
   * Two Doctor objects are considered equal if they have the same doctorID and name.
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
    Doctor other = (Doctor) obj;
    return doctorID == other.doctorID && getName().equals(other.getName());
  }
}
