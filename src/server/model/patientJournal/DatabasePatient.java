package server.model.patientJournal;

import java.sql.SQLException;

public class DatabasePatient
{
  private int patientID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String userName;
  private String password;
  private String CPR;
  private Address address;

  public DatabasePatient(int patientID, String firstName, String lastName,
      String email, String phoneNumber, String userName, String password, String CPR, Address address)
  {
    this.patientID = patientID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.userName = userName;
    this.password = password;
    this.CPR = CPR;
    this.address = address;
  }

  public DatabasePatient(String firstName, String lastName, String email,
      String phoneNumber, String userName, String password, String CPR, Address address)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.userName = userName;
    this.password = password;
    this.CPR = CPR;
    this.address = address;
  }

  public DatabasePatient()
  {
  }

  public int getPatientID()
  {
    return patientID;
  }

  public Address getAddress()
  {
    return address;
  }

  public void setPatientID(int patientID)
  {
    this.patientID = patientID;
  }
}
