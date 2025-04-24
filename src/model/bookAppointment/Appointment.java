package model.bookAppointment;

import model.DateTime;

public class Appointment
{
  private DateTime dateTime;
  private int appointmentID;
  private int doctorID;
  private String mode;
  private int patientID;

  public Appointment(DateTime dateTime, int appointmentID, int doctorID,
      String mode, int patientID)
  {
    this.dateTime = dateTime;
    this.appointmentID = appointmentID;
    this.doctorID = doctorID;
    this.mode = mode;
    this.patientID = patientID;
  }

  public DateTime getDate()
  {
    return dateTime;
  }

  public DateTime getTime()
  {
    return dateTime;
  }

  public int getAppointmentID()
  {
    return appointmentID;
  }

  public int getDoctorID()
  {
    return doctorID;
  }

  public int getPatientID()
  {
    return patientID;
  }

  public void setDateTime(DateTime dateTime)
  {
    this.dateTime = dateTime;
  }

  public void setAppointmentID(int appointmentID)
  {
    this.appointmentID = appointmentID;
  }

  public void setDoctorID(int doctorID)
  {
    this.doctorID = doctorID;
  }

  public void setMode(String mode)
  {
    this.mode = mode;
  }

  public void setPatientID(int patientID)
  {
    this.patientID = patientID;
  }

  public String getMode()
  {
    return mode;
  }

  @Override public String toString()
  {
    return "Appointment{" + "dateTime=" + dateTime + ", appointmentID="
        + appointmentID + ", doctorID=" + doctorID + ", mode='" + mode + '\''
        + ", patientID=" + patientID + '}';
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Appointment other = (Appointment) obj;
    return dateTime.equals(other.dateTime)
        && appointmentID == other.appointmentID && doctorID == other.doctorID
        && patientID == other.patientID && mode.equals(other.mode);
  }
}
