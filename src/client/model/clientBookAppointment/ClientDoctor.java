package client.model.clientBookAppointment;

import client.model.clientLoginSystem.clientEntities.ClientUser;

/**
 * Represents a doctor in the client application.
 * This class extends ClientUser and contains additional information specific to doctors.
 */
public class ClientDoctor extends ClientUser
{
  private int doctorID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;

  /**
   * Constructs a ClientDoctor with the specified details.
   *
   * @param doctorID    The unique identifier for the doctor.
   * @param firstName   The first name of the doctor.
   * @param lastName    The last name of the doctor.
   * @param email       The email address of the doctor.
   * @param phoneNumber The phone number of the doctor.
   * @param userName    The username for the doctor's account.
   * @param password    The password for the doctor's account.
   */
  public ClientDoctor(int doctorID, String firstName, String lastName,
      String email, String phoneNumber, String userName, String password)
  {

    super(userName, password);
    this.doctorID = doctorID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  /**
   * Constructs a ClientDoctor with the specified details without a doctor ID.
   *
   * @param firstName   The first name of the doctor.
   * @param lastName    The last name of the doctor.
   * @param email       The email address of the doctor.
   * @param phoneNumber The phone number of the doctor.
   * @param userName    The username for the doctor's account.
   * @param password    The password for the doctor's account.
   */
  public ClientDoctor(String firstName, String lastName, String email,
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
   * @return The doctor's unique identifier.
   */
  public int getDoctorID()
  {
    return doctorID;
  }

  /**
   * Gets the full name of the doctor.
   * @return The doctor's full name in the format "FirstName LastName".
   */
  public String getName()
  {
    return firstName + " " + lastName;
  }

  /**
   * Sets the unique identifier for the doctor.
   * @param doctorID The doctor's unique identifier to set.
   */
  public void setDoctorID(int doctorID)
  {
    this.doctorID = doctorID;
  }

  /**
   * Sets the full name of the doctor.
   * The name should be in the format "FirstName LastName".
   * @param name The full name of the doctor to set.
   */
  public void setName(String name)
  {
    this.firstName = name.split(" ")[0];
    this.lastName = name.split(" ")[1];
  }

  /**
   * Gets the email address of the doctor.
   * @return The doctor's email address.
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * Gets the first name of the doctor.
   * @return The doctor's first name.
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Gets the last name of the doctor.
   * @return The doctor's last name.
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Gets the phone number of the doctor.
   * @return The doctor's phone number.
   */
  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  /**
   * Sets the email address of the doctor.
   * @param lastName The email address to set for the doctor.
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * Sets the first name of the doctor.
   * @param firstName The first name to set for the doctor.
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * Sets the email address of the doctor.
   * @param email The email address to set for the doctor.
   */
  public void setEmail(String email)
  {
    this.email = email;
  }

  /**
   * Sets the phone number of the doctor.
   * @param phoneNumber The phone number to set for the doctor.
   */
  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Returns a string representation of the ClientDoctor object.
   * @return A string containing the doctor's name.
   */
  @Override public String toString()
  {
    return "Name: '" + getName() + '\'';
  }

  /**
   * Compares this ClientDoctor object with another object for equality.
   * @param obj The object to compare with.
   * @return true if the objects are equal, false otherwise.
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    ClientDoctor other = (ClientDoctor) obj;
    return doctorID == other.doctorID && firstName.equals(other.firstName)
        && lastName.equals(other.lastName);
  }
}
