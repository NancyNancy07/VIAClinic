package client.viewModel.myPatient;

import server.model.patientJournal.Address;

/**
 * PatientInformationSharedData is a singleton class that holds shared data related to patient information.
 * It provides methods to get and set patient details such as name, email, phone number, CPR, and address.
 */
public class PatientInformationSharedData
{
  private static PatientInformationSharedData instance;
  private String patientName;
  private String email;
  private String phoneNumber;
  private String cpr;
  private Address address;


  /**
   * Private constructor to prevent instantiation from outside the class.
   * This ensures that only one instance of PatientInformationSharedData exists (singleton pattern).
   */
  private PatientInformationSharedData()
  {
  }

  /**
   * Returns the singleton instance of PatientInformationSharedData.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of PatientInformationSharedData
   */
  public static synchronized PatientInformationSharedData getInstance()
  {
    if (instance == null)
    {
      instance = new PatientInformationSharedData();
    }
    return instance;
  }

  /**
   * Gets the address of the patient.
   * @return the Address object containing the patient's address
   */
  public Address getAddress()
  {
    return address;
  }

  /**
   * Sets the address of the patient.
   * @param address the Address object to set
   */
  public void setAddress(Address address)
  {
    this.address = address;
  }

  /**
   * Gets the CPR number of the patient.
   * @return the CPR number as a String
   */
  public String getCpr()
  {
    return cpr;
  }

  /**
   * Sets the CPR number of the patient.
   * @param cpr the CPR number to set
   */
  public void setCpr(String cpr)
  {
    this.cpr = cpr;
  }

  /**
   * Gets the email of the patient.
   * @return the email as a String
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * Sets the email of the patient.
   * @param email the email to set
   */
  public void setEmail(String email)
  {
    this.email = email;
  }

  /**
   * Gets the name of the patient.
   * @return the name as a String
   */
  public String getPatientName()
  {
    return patientName;
  }

  /**
   * Sets the name of the patient.
   * @param patientName the name to set
   */
  public void setPatientName(String patientName)
  {
    this.patientName = patientName;
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
   * Sets the phone number of the patient.
   * @param phoneNumber the phone number to set
   */
  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }
}