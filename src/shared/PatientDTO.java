package shared;

import server.model.patientJournal.Address;

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

  public int getPatientID()
  {
    return patientID;
  }

  public void setPatientID(int patientID)
  {
    this.patientID = patientID;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getCPR()
  {
    return CPR;
  }

  public void setCPR(String CPR)
  {
    this.CPR = CPR;
  }

  public Address getAddress()
  {
    return address;
  }

  public void setAddress(Address address)
  {
    this.address = address;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  @Override public String toString()
  {
    return "PatientDTO{" + "patientID=" + patientID + ", firstName='"
        + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='"
        + email + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", userName='"
        + userName + '\'' + ", CPR='" + CPR + '\'' + ", address=" + address
        + '}';
  }
}
