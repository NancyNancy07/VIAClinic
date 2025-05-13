package server.model.bookAppointment;

import server.model.loginSystem.entities.User;

public class Patient extends User
{
  private int patientID;
  private String name;

  public Patient(int patientID, String name, String username, String password)
  {
    super(username, password);
    this.patientID = patientID;
    this.name = name;
  }

  public int getPatientID()
  {
    return patientID;
  }

  public String getName()
  {
    return name;
  }

  public void setPatientID(int patientID)
  {
    this.patientID = patientID;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @Override public String toString()
  {
    return "PatientID= " + patientID + ", Name= '" + name + '\'';
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Patient other = (Patient) obj;
    return patientID == other.patientID && name.equals(other.name);
  }
}
