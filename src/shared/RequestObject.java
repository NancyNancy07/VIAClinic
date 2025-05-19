package shared;

import server.model.bookAppointment.Appointment;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;

public class RequestObject
{
  private String type;
  private String username;
  private String password;
  private String userType;
  private int id;
  private Diagnosis diagnosis;
  private Appointment appointment;
  private Prescription prescription;

  public String getType()
  {
    return type;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public String getUserType()
  {
    return userType;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public int getId()
  {
    return id;
  }

  public void setUsername(String email)
  {
    this.username = email;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public void setUserType(String userType)
  {
    this.userType = userType;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public void setDiagnosis(Diagnosis diagnosis) {
    this.diagnosis = diagnosis;
  }

  public Diagnosis getDiagnosis() {
    return diagnosis;
  }

  public void setAppointment(Appointment appointment)
  {
    this.appointment=appointment;
  }

  public Appointment getAppointment()
  {
    return appointment;
  }

  public void setPrescription(Prescription prescription)
  {
    this.prescription=prescription;
  }

  public Prescription getPrescription()
  {
    return prescription;
  }
}
