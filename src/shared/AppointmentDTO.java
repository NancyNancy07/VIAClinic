package shared;

public class AppointmentDTO
{
  private String date;
  private String time;
  private DoctorDTO doctor;
  private int patientId;
  private String mode;
  private int id;
  private PatientDTO patientDTO;

  public AppointmentDTO(int id, String date, String time, DoctorDTO doctor,
      int patientId, String mode)
  {
    this.id = id;
    this.date = date;
    this.time = time;
    this.doctor = doctor;
    this.patientId = patientId;
    this.mode = mode;
  }

  public AppointmentDTO(int id, String date, String time, DoctorDTO doctor,
      PatientDTO patientDTO, String mode)
  {
    this.id = id;
    this.date = date;
    this.time = time;
    this.doctor = doctor;
    this.patientDTO = patientDTO;
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

  public DoctorDTO getDoctor()
  {
    return doctor;
  }

  public void setDoctor(DoctorDTO doctor)
  {
    this.doctor = doctor;
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

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public PatientDTO getPatientDTO()
  {
    return patientDTO;
  }

  public void setPatientDTO(PatientDTO patientDTO)
  {
    this.patientDTO = patientDTO;
  }
}
