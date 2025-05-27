package shared;

import server.model.patientJournal.Address;

/**
 * PatientDTO class represents a Data Transfer Object for a patient.
 * It contains patient-specific information such as ID, name, email, phone number, CPR, address, and password.
 */
public class PatientDTO
{
  private int patientID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String userName;
  private String CPR;
  private Address address;
  private String password;

  public PatientDTO()
  {
    // default constructor for JSON serialization/deserialization
  }

  /**
   * Constructor for PatientDTO with all fields.
   * Initializes the patient with provided values.
   *
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
  public PatientDTO(int patientID, String firstName, String lastName,
      String email, String phoneNumber, String userName, String password,
      String CPR, Address address)
  {
    this.patientID = patientID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.userName = userName;
    this.CPR = CPR;
    this.address = address;
    this.password = password;
  }

  // Getters and setters

  /**
   * Gets the unique identifier for the patient.
   * @return the unique identifier for the patient
   */
  public int getPatientID()
  {
    return patientID;
  }

  /**
   * Sets the unique identifier for the patient.
   * @param patientID the unique identifier for the patient
   */
  public void setPatientID(int patientID)
  {
    this.patientID = patientID;
  }

  /**
   * Gets the first name of the patient.
   * @return the first name of the patient
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Sets the first name of the patient.
   * @param firstName the first name of the patient
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * Gets the last name of the patient.
   * @return the last name of the patient
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Sets the last name of the patient.
   * @param lastName the last name of the patient
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * Gets the email address of the patient.
   * @return the email address of the patient
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * Sets the email address of the patient.
   * @param email the email address of the patient
   */
  public void setEmail(String email)
  {
    this.email = email;
  }

  /**
   * Gets the phone number of the patient.
   * @return the phone number of the patient
   */
  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  /**
   * Sets the phone number of the patient.
   * @param phoneNumber the phone number of the patient
   */
  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Gets the username of the patient.
   * @return the username of the patient
   */
  public String getUserName()
  {
    return userName;
  }

  /**
   * Sets the username of the patient.
   * @param userName the username of the patient
   */
  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  /**
   * Gets the CPR number of the patient.
   * @return the CPR number of the patient
   */
  public String getCPR()
  {
    return CPR;
  }

  /**
   * Sets the CPR number of the patient.
   * @param CPR the CPR number of the patient
   */
  public void setCPR(String CPR)
  {
    this.CPR = CPR;
  }

  /**
   * Gets the address of the patient.
   * @return the address of the patient
   */
  public Address getAddress()
  {
    return address;
  }

  /**
   * Sets the address of the patient.
   * @param address the address of the patient
   */
  public void setAddress(Address address)
  {
    this.address = address;
  }

  /**
   * Gets the password of the patient.
   * @return the password of the patient
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Sets the password of the patient.
   * @param password the password of the patient
   */
  public void setPassword(String password)
  {
    this.password = password;
  }

  /**
   * Returns a string representation of the PatientDTO.
   * @return a string containing patient details
   */
  @Override public String toString()
  {
    return "PatientDTO{" + "patientID=" + patientID + ", firstName='"
        + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='"
        + email + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", userName='"
        + userName + '\'' + ", CPR='" + CPR + '\'' + ", address=" + address
        + '}';
  }
}
