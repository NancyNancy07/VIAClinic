package client.model.clientBookAppointment;

public class ClientAppointment
{
  private ClientNewDateTime dateTime;
  private int appointmentID;
  private String mode;
  private int patientID;
  //  private int doctorId;
  private ClientDoctor doctor;
  private ClientPatient patient;

  public ClientAppointment(ClientNewDateTime dateTime, int patientID,
      ClientDoctor doctor, String mode)
  {
    this.dateTime = dateTime;
    this.doctor = doctor;
    this.mode = mode;
    this.patientID = patientID;
  }

  public ClientAppointment(ClientNewDateTime dateTime, ClientPatient patient,
      ClientDoctor doctor, String mode)
  {
    this.dateTime = dateTime;
    this.patient = patient;
    this.patientID = patient.getPatientID();
    this.doctor = doctor;
    this.mode = mode;
  }

  public ClientAppointment(int appointmentID, ClientNewDateTime dateTime,
      int patientID, ClientDoctor doctor, String mode)
  {
    this.appointmentID = appointmentID;
    this.dateTime = dateTime;
    this.doctor = doctor;
    this.mode = mode;
    this.patientID = patientID;
  }

  public ClientAppointment(int appointmentID, ClientNewDateTime dateTime,
      ClientPatient patient, ClientDoctor doctor, String mode)
  {
    this.appointmentID = appointmentID;
    this.dateTime = dateTime;
    this.doctor = doctor;
    this.mode = mode;
    this.patient = patient;
  }


  public ClientPatient getPatient()
  {
    return patient;
  }

  public void setPatient(ClientPatient patient)
  {
    this.patient = patient;
    this.patientID = patient.getPatientID();
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

  public ClientDoctor getDoctor()
  {
    return doctor;
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

  public void setDoctorID(ClientDoctor doctor)
  {
    this.doctor = doctor;
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
    return "Appointment ID: " + appointmentID + ", " + dateTime + ", Doctor= "
        + doctor.getName() + ", Mode='" + mode + "'\n";

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
}
