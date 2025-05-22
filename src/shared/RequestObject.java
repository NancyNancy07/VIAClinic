package shared;

import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.Referral;
import server.model.patientJournal.Vaccination;

public class RequestObject
{
  private String type;
  private String username;
  private String password;
  private String userType;
  private int id;
  private Diagnosis diagnosis;
  private AppointmentDTO appointment;
  private Prescription prescription;
  private Referral referral;
  private Vaccination vaccination;

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

  public void setDiagnosis(Diagnosis diagnosis)
  {
    this.diagnosis = diagnosis;
  }

  public Diagnosis getDiagnosis()
  {
    return diagnosis;
  }

  public void setAppointment(AppointmentDTO appointment)
  {
    this.appointment = appointment;
  }

  public AppointmentDTO getAppointment()
  {
    return appointment;
  }

  public void setPrescription(Prescription prescription)
  {
    this.prescription = prescription;
  }

  public Prescription getPrescription()
  {
    return prescription;
  }

  public void setReferral(Referral referral)
  {
    this.referral = referral;
  }

  public Referral getReferral()
  {
    return referral;
  }

  public Vaccination getVaccination() {
    return vaccination;
  }

  public void setVaccination(Vaccination vaccination)
  {
    this.vaccination = vaccination;
  }
}
