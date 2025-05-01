package server.model.bookAppointment;

import server.model.loginSystem.entities.User;

public class Doctor extends User
{
  private int doctorID;
  private String name;

  public Doctor(int doctorID, String name, String username, String password)
  {
    super(username, password);
    this.doctorID = doctorID;
    this.name = name;
  }

  public int getDoctorID()
  {
    return doctorID;
  }

  public String getName()
  {
    return name;
  }

  public void setDoctorID(int doctorID)
  {
    this.doctorID = doctorID;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @Override public String toString()
  {
    return "Name: '" + name + '\'';
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Doctor other = (Doctor) obj;
    return doctorID == other.doctorID && name.equals(other.name);
  }
}
