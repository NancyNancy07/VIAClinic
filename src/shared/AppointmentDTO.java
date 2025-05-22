package shared;

public class AppointmentDTO
{
  private String date;
  private String time;
  private int doctorId;
  private int patientId;
  private String mode;

  // Constructor
  public AppointmentDTO(String date, String time, int doctorId, int patientId,
      String mode)
  {
    this.date = date;
    this.time = time;
    this.doctorId = doctorId;
    this.patientId = patientId;
    this.mode = mode;
  }

  // Getters and setters
  public String getDate()
  {
    return date;
  }

  public void setDate(String date)
  {
    this.date = date;
  }

  public String getTime()
  {
    return time;
  }

  public void setTime(String time)
  {
    this.time = time;
  }

  public int getDoctorId()
  {
    return doctorId;
  }

  public void setDoctorId(int doctorId)
  {
    this.doctorId = doctorId;
  }

  public int getPatientId()
  {
    return patientId;
  }

  public void setPatientId(int patientId)
  {
    this.patientId = patientId;
  }

  public String getMode()
  {
    return mode;
  }

  public void setMode(String mode)
  {
    this.mode = mode;
  }
}
