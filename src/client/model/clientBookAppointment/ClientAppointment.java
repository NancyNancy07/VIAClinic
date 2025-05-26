package client.model.clientBookAppointment;

public class ClientAppointment
{
  private ClientNewDateTime dateTime;
  private int appointmentID;
  private String mode;
  private int patientID;
  private int doctorId;
  private ClientDoctor doctor;

  public ClientAppointment(ClientNewDateTime dateTime, int patientID,
      int doctorId, String mode)
  {
    this.dateTime = dateTime;
    this.appointmentID = appointmentId();
    this.doctorId = doctorId;
    this.mode = mode;
    //    this.doctor = doctor;
    this.patientID = patientID;
  }

  private int appointmentId()
  {
    return appointmentID;
  }

  public String getDate()
  {
    return dateTime.getDay() + "/" + dateTime.getMonth() + "/"
        + dateTime.getYear();
  }

  public String getTime()
  {
    return dateTime.getHour() + ":" + dateTime.getMinute();
  }

  public int getAppointmentID()
  {
    return appointmentID;
  }

  public int getDoctorID()
  {
    return doctorId;
  }

  public int getPatientID()
  {
    return patientID;
  }

  public void setDateTime(ClientNewDateTime dateTime)
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
    ClientAppointment other = (ClientAppointment) obj;
    return dateTime.equals(other.dateTime)
        && appointmentID == other.appointmentID && doctor == other.doctor
        && patientID == other.patientID && mode.equals(other.mode);
  }

  public int getAppointmentId()
  {
    return appointmentID;
  }
}
