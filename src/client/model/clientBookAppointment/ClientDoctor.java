package client.model.clientBookAppointment;

import client.model.clientLoginSystem.clientEntities.ClientUser;

public class ClientDoctor extends ClientUser
{
  private int doctorID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;

  public ClientDoctor(int doctorID, String firstName, String lastName,
      String email, String phoneNumber, String userName, String password)
  {

    super(userName, password);
    this.doctorID = doctorID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }
  public ClientDoctor(String firstName, String lastName, String email,
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
    ClientDoctor other = (ClientDoctor) obj;
    return doctorID == other.doctorID && getName().equals(other.getName());
  }
}
