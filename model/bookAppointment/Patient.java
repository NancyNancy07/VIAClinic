package model.bookAppointment;

public class Patient
{
  private int patientID;
  private String name;

  public Patient(int patientID, String name) {
    this.patientID = patientID;
    this.name = name;
  }

  public int getPatientID() {
    return patientID;
  }

  public String getName() {
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
    return "Patient{" + "patientID=" + patientID + ", name='" + name + '\''
        + '}';
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
