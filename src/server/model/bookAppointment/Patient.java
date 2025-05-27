package server.model.bookAppointment;

import server.model.loginSystem.entities.User;
import server.model.patientJournal.Address;

/**
 * Patient class represents a patient in the healthcare system.
 * It extends the User class and contains patient-specific information such as ID, name, email, phone number, CPR, and address.
 */
public class Patient extends User
{
  private int patientID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String CPR;
  private Address address;

  /**
   * Default constructor for Patient.
   * Initializes the patient with default values.
   * @param patientID the unique identifier for the patient
   * @param firstName the first name of the patient
   * @param lastName the last name of the patient
   * @param email the email address of the patient
   * @param phoneNumber the phone number of the patient
   * @param userName the username for the patient
   * @param password the password for the patient
   * @param CPR the CPR number of the patient
   * @param address the address of the patient
   */
  public Patient(int patientID, String firstName, String lastName, String email,
      String phoneNumber, String userName, String password, String CPR,
      Address address)
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
   * Constructor for Patient without patientID.
   * Initializes the patient with provided values, excluding the patient ID.
   * @param firstName the first name of the patient
   * @param lastName the last name of the patient
   * @param email the email address of the patient
   * @param phoneNumber the phone number of the patient
   * @param userName the username for the patient
   * @param password the password for the patient
   * @param CPR the CPR number of the patient
   * @param address the address of the patient
   */
  public Patient(String firstName, String lastName, String email,
      String phoneNumber, String userName, String password, String CPR,
      Address address)
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
   * Gets the unique identifier for the patient.
   * @return the patient ID as an integer
   */
  public int getPatientID()
  {
    return patientID;
  }

  /**
   * Gets the full name of the patient.
   * @return the full name as a String
   */
  public String getName()
  {
    return firstName + " " + lastName;
  }
  /**
   * Gets the phone number of the patient.
   * @return the phone number as a String
   */

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  /**
   * Gets the last name of the patient.
   * @return the last name as a String
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Gets the password of the patient.
   * @return the password as a String
   */
  @Override public String getPassword()
  {
    return super.getPassword();
  }

  /**
   * Gets the username of the patient.
   * @return the username as a String
   */
  @Override public String getUsername()
  {
    return super.getUsername();
  }

  /**
   * Gets the first name of the patient.
   * @return the first name as a String
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Gets the email address of the patient.
   * @return the email address as a String
   */
  public String getEmail()
  {
    return email;
  }
  /**
   * Gets the CPR number of the patient.
   * @return the CPR number as a String
   */

  public String getCPR()
  {
    return CPR;
  }

  /**
   * Gets the address of the patient.
   * @return the address as an Address object
   */
  public Address getAddress()
  {
    return address;
  }

  /**
   * Sets the identifier for the patient.
   * @param patientID the unique identifier to set for the patient
   */
  public void setPatientID(int patientID)
  {
    this.patientID = patientID;
  }

  /**
   * Sets the full name of the patient.
   * @param name the full name of the patient as a String
   */
  public void setName(String name)
  {
    this.firstName = name.split(" ")[0];
    this.lastName = name.split(" ")[1];
  }

  /**
   * Returns a string representation of the Patient object.
   * @return a string containing the patient ID and name
   */
  @Override public String toString()
  {
    return "PatientID= " + patientID + ", Name= '" + getName() + '\'';
  }

  /**
   * Checks if two Patient objects are equal.
   * @param obj the object to compare with
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Patient other = (Patient) obj;
    return patientID == other.patientID && firstName.equals(other.firstName)
        && lastName.equals(other.lastName) && email.equals(other.email)
        && phoneNumber.equals(other.phoneNumber) && CPR.equals(other.CPR)
        && address.equals(other.address);
  }

  public String getEmail()
  {
    return email;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getCPR()
  {
    return CPR;
  }

  public Address getAddress()
  {
    return address;
  }
}
