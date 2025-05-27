package shared;

import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.LabResult;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.Referral;
import server.model.patientJournal.Vaccination;

/**
 * RequestObject class represents a request object in the healthcare system.
 * It contains various fields such as type, username, password, userType,
 * id, diagnosis, appointment, prescription, labResult, referral, and vaccination.
 * * This class is used to encapsulate the data sent in requests to the server.
 */
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
  private LabResult labResult;
  private Referral referral;
  private Vaccination vaccination;

  /**
   * Gets the type of the request object.
   * @return the type of the request object as a String
   */
  public String getType()
  {
    return type;
  }

  /**
   * Gets the username of the request object.
   * @return the username of the request object as a String
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * Gets the password of the request object.
   * @return the password of the request object as a String
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Gets the user type of the request object.
   * @return the user type of the request object as a String
   */
  public String getUserType()
  {
    return userType;
  }

  /**
   * Sets the type of the request object.
   * @param type the type of the request object as a String
   */
  public void setType(String type)
  {
    this.type = type;
  }

  /**
   * Gets the unique identifier of the request object.
   * @return the unique identifier of the request object as an int
   */
  public int getId()
  {
    return id;
  }

  /**
   * Sets the username of the request object.
   * @param email the username of the request object as a String
   */
  public void setUsername(String email)
  {
    this.username = email;
  }

  /**
   * Sets the password of the request object.
   * @param password the password of the request object as a String
   */
  public void setPassword(String password)
  {
    this.password = password;
  }

  /**
   * Sets the user type of the request object.
   * @param userType the user type of the request object as a String
   */
  public void setUserType(String userType)
  {
    this.userType = userType;
  }

  /**
   * Sets the unique identifier of the request object.
   * @param id the unique identifier of the request object as an int
   */
  public void setId(int id)
  {
    this.id = id;
  }

  /**
   * Sets the diagnosis for the request object.
   * @param diagnosis the diagnosis to set for the request object
   */
  public void setDiagnosis(Diagnosis diagnosis)
  {
    this.diagnosis = diagnosis;
  }

  /**
   * Gets the diagnosis of the request object.
   * @return the diagnosis of the request object
   */
  public Diagnosis getDiagnosis()
  {
    return diagnosis;
  }

  /**
   * Sets the appointment for the request object.
   * @param appointment the appointment to set for the request object
   */
  public void setAppointment(AppointmentDTO appointment)
  {
    this.appointment = appointment;
  }

  /**
   * Gets the appointment of the request object.
   * @return the appointment of the request object
   */
  public AppointmentDTO getAppointment()
  {
    return appointment;
  }

  /**
   * Sets the prescription for the request object.
   * @param prescription the prescription to set for the request object
   */
  public void setPrescription(Prescription prescription)
  {
    this.prescription = prescription;
  }

  /**
   * Gets the prescription of the request object.
   * @return the prescription of the request object
   */
  public Prescription getPrescription()
  {
    return prescription;
  }

  /**
   * Sets the referral for the request object.
   * @param referral the referral to set for the request object
   */
  public void setReferral(Referral referral)
  {
    this.referral = referral;
  }

  /**
   * Gets the referral of the request object.
   * @return the referral of the request object
   */
  public Referral getReferral()
  {
    return referral;
  }

  /**
   * Gets the vaccination of the request object.
   * @return the vaccination of the request object
   */
  public Vaccination getVaccination() {
    return vaccination;
  }

  /**
   * Sets the vaccination for the request object.
   * @param vaccination the vaccination to set for the request object
   */
  public void setVaccination(Vaccination vaccination)
  {
    this.vaccination = vaccination;
  }


  /**
   * Sets the lab result for the request object.
   * @param labResult the lab result to set for the request object
   */
  public void setLabResult(LabResult labResult)
  {
    this.labResult=labResult;
  }
  /**
   * Gets the lab result of the request object.
   * @return the lab result of the request object
   */
  public LabResult getLabResult()
  {
    return labResult;
  }
}







