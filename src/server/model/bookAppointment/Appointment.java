package server.model.bookAppointment;

public class Appointment
{
  private DateTime dateTime;
  private int appointmentID;
  private int doctorID;
  private String mode;
  private int patientID;
  private DoctorList doctorList;

  public Appointment(DateTime dateTime, int appointmentID, int doctorID,
      String mode, int patientID, DoctorList doctorList)
  {
    this.dateTime = dateTime;
    this.appointmentID = appointmentID;
    this.doctorID = doctorID;
    this.mode = mode;
    this.patientID = patientID;
    this.doctorList = doctorList;
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

  public String getDoctorName()
  {
    Doctor doctor = doctorList.findDoctorByID(
        this.doctorID);
    if (doctor != null)
    {
      return doctor.getName();
    }
    else
    {
      return "Doctor not found";
    }
  }

  @Override public String toString()
  {
    return dateTime + ", Doctor= " + getDoctorName() + ", Mode='" + mode + '\''
        + "\n";
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
