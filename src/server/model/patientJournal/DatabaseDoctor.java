package server.model.patientJournal;

import java.sql.SQLException;

public class DatabaseDoctor
{
  private int doctorID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String userName;
  private String password;

  public DatabaseDoctor(int doctorID, String firstName, String lastName,
      String email, String phoneNumber, String userName, String password)
  {
    this.doctorID = doctorID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.userName = userName;
    this.password = password;
  }
  public DatabaseDoctor(String firstName, String lastName, String email,
      String phoneNumber, String userName, String password)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.userName = userName;
    this.password = password;
  }

  public DatabaseDoctor()
  {

  }

  public int getDoctorID()
  {
    return doctorID;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getUserName()
  {
    return userName;
  }


  public void setDoctorID(int doctorID)
  {
    this.doctorID = doctorID;
  }
}
