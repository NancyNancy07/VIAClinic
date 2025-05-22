package server.model.bookAppointment;

import server.model.loginSystem.entities.User;

public class Doctor extends User
{
  private int doctorID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;

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

  public Doctor(String firstName, String lastName, String email,
      String phoneNumber, String userName, String password)
  {
    super(userName, password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
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

  @Override public String getPassword()
  {
    return super.getPassword();
  }

  @Override public String getUsername()
  {
    return super.getUsername();
  }

  public String getName()
  {
    return firstName + " " + lastName;
  }

  public void setDoctorID(int doctorID)
  {
    this.doctorID = doctorID;
  }

  public void setName(String name)
  {
    this.firstName = name.split(" ")[0];
    this.lastName = name.split(" ")[1];
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  @Override public String toString()
  {
    return "Name: '" + getName() + '\'';
  }

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
