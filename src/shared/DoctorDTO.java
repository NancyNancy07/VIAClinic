package shared;

public class DoctorDTO
{
  private int doctorID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String userName;
  private String password;

  public DoctorDTO()
  {
    // no-arg constructor for serialization
  }

  public DoctorDTO(int doctorID, String firstName, String lastName,
      String email, String phoneNumber,
      String userName, String password)
  {
    this.doctorID = doctorID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.userName = userName;
    this.password = password;
  }

  public int getDoctorID()
  {
    return doctorID;
  }

  public void setDoctorID(int doctorID)
  {
    this.doctorID = doctorID;
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

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }
}
