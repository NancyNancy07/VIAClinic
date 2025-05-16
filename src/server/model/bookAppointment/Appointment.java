package server.model.bookAppointment;

import java.util.Random;

public class Appointment
{
  private NewDateTime dateTime;
  private int appointmentID;
  private String mode;
  private int patientID;
  private Doctor doctor;

  public Appointment(NewDateTime dateTime, int patientID, Doctor doctor,
      String mode)
  {
    this.dateTime = dateTime;
    this.appointmentID = appointmentId();
    this.doctor = doctor;
    this.mode = mode;
    this.doctor = doctor;
    this.patientID = patientID;
  }

  private int appointmentId()
  {
    return appointmentID;
  }

  public NewDateTime getDate()
  {
    return dateTime;
  }

  public NewDateTime getTime()
  {
    return dateTime;
  }

  public int getAppointmentID()
  {
    return appointmentID;
  }

  public int getDoctorID()
  {
    return doctor.getDoctorID();
  }

  public int getPatientID()
  {
    return patientID;
  }

  public void setDateTime(NewDateTime dateTime)
  {
    this.dateTime = dateTime;
  }

  public void setAppointmentID(int appointmentID)
  {
    this.appointmentID = appointmentID;
  }

  public void setDoctorID(int doctorID)
  {
    this.doctor.setDoctorID(doctorID);
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
    if (doctor != null)
    {
      return dateTime + ", Doctor= " + doctor.getName() + ", Mode='" + mode
          + "'\n";
    }
    else
    {
      return dateTime + ", Doctor= Unknown, Mode='" + mode + "'\n";
    }
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Appointment other = (Appointment) obj;
    return dateTime.equals(other.dateTime)
        && appointmentID == other.appointmentID && doctor == other.doctor
        && patientID == other.patientID && mode.equals(other.mode);
  }
}
