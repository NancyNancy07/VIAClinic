package client.model.clientBookAppointment;

import server.model.loginSystem.entities.User;
import server.model.patientJournal.Address;

public class ClientPatient extends User
{
  private int patientID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String CPR;
  private Address address;

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


  public int getPatientID()
  {
    return patientID;
  }

  public String getName()
  {
    return firstName + " " + lastName;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  @Override public String getUsername()
  {
    return super.getUsername();
  }

  @Override public String getPassword()
  {
    return super.getPassword();
  }

  public String getEmail()
  {
    return email;
  }

  public void setPatientID(int patientID)
  {
    this.patientID = patientID;
  }

  public void setName(String name)
  {
    this.firstName = name.split(" ")[0];
    this.lastName = name.split(" ")[1];
  }

  @Override public String toString()
  {
    return "PatientID= " + patientID + ", Name= '" + getName() + '\'';
  }

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
