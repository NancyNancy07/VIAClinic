package client.model.clientBookAppointment;

import server.model.loginSystem.entities.User;
import server.model.patientJournal.Address;

/**
 * Represents a patient in the client application.
 * This class extends the User class and includes additional patient-specific attributes.
 */
public class ClientPatient extends User
{
  private int patientID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String CPR;
  private Address address;

  /**
   * Constructs a ClientPatient with the specified attributes.
   *
   * @param patientID   The unique identifier for the patient.
   * @param firstName   The first name of the patient.
   * @param lastName    The last name of the patient.
   * @param email       The email address of the patient.
   * @param phoneNumber The phone number of the patient.
   * @param userName    The username for the patient account.
   * @param password    The password for the patient account.
   * @param CPR         The CPR number of the patient.
   * @param address     The address of the patient.
   */
  public ClientPatient(int patientID, String firstName, String lastName,
      String email, String phoneNumber, String userName, String password,
      String CPR, Address address)
  {
    super(userName, password);
    this.patientID = patientID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.CPR = CPR;
    this.address = address;
  }

  /**
   * Constructs a ClientPatient with the specified attributes, excluding patientID.
   *
   * @param firstName   The first name of the patient.
   * @param lastName    The last name of the patient.
   * @param email       The email address of the patient.
   * @param phoneNumber The phone number of the patient.
   * @param userName    The username for the patient account.
   * @param password    The password for the patient account.
   * @param CPR         The CPR number of the patient.
   * @param address     The address of the patient.
   */
  public ClientPatient(String firstName, String lastName,
      String email,
      String phoneNumber, String userName,
      String password, String CPR, Address address)
  {
    super(userName, password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.CPR = CPR;
    this.address = address;
  }

  /**
   * Returns the unique identifier for the patient.
   * @return the patient ID
   */
  public int getPatientID()
  {
    return patientID;
  }

  /**
   * Returns the full name of the patient.
   * @return the full name in the format "FirstName LastName"
   */
  public String getName()
  {
    return firstName + " " + lastName;
  }

  /**
   * Returns the first name of the patient.
   * @return the first name
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Returns the last name of the patient.
   * @return the last name
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Returns the phone number of the patient.
   * @return the phone number
   */
  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  /**
   * Returns the username of the patient.
   * @return the username
   */
  @Override public String getUsername()
  {
    return super.getUsername();
  }

  /**
   * Returns the password of the patient.
   * @return the password
   */
  @Override public String getPassword()
  {
    return super.getPassword();
  }

  /**
   * Returns the email of the patient.
   * @return the email
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * Sets the identifier for the patient.
   * @param patientID the unique identifier to set
   */
  public void setPatientID(int patientID)
  {
    this.patientID = patientID;
  }

  /**
   * Sets the first and last name of the patient from a full name string.
   * The full name should be in the format "FirstName LastName".
   *
   * @param name the full name of the patient
   */
  public void setName(String name)
  {
    this.firstName = name.split(" ")[0];
    this.lastName = name.split(" ")[1];
  }

  /**
   * Returns string representation of the ClientPatient.
   * @return a string containing the patient ID and full name
   */
  @Override public String toString()
  {
    return "PatientID= " + patientID + ", Name= '" + getName() + '\'';
  }

  /**
   * Checks if this ClientPatient is equal to another object.
   * Two ClientPatients are considered equal if their patientID, firstName,
   * lastName, email, phoneNumber, CPR, and address are all the same.
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
    ClientPatient other = (ClientPatient) obj;
    return patientID == other.patientID && firstName.equals(other.firstName)
        && lastName.equals(other.lastName) && email.equals(other.email)
        && phoneNumber.equals(other.phoneNumber) && CPR.equals(other.CPR)
        && address.equals(other.address);
  }
}
