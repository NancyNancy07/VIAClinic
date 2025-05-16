package shared;

import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Diagnosis;

import java.util.List;

public class ResponseObject
{
  private boolean success;
  private String status;
  private String message;
  private int patientId;
  private List<Appointment> appointments;
  private List<Doctor> doctors;
  private List<Patient> patients;
  private Diagnosis diagnosis;
  private List<Diagnosis> diagnoses;

  public ResponseObject()
  {
  }

  public ResponseObject(boolean success, String message, int patientId)
  {
    this.success = success;
    this.message = message;
    this.patientId = patientId;
  }

  public String getStatus()
  {
    return status;
  }

  public String getMessage()
  {
    return message;
  }

  public int getPatientId()
  {
    return patientId;
  }

  public boolean isSuccess()
  {
    return success;
  }

  public List<Appointment> getAppointments()
  {
    return appointments;
  }

  public void setAppointments(List<Appointment> appointments)
  {
    this.appointments = appointments;
  }

  public List<Doctor> getDoctors()
  {
    return doctors;
  }

  public void setDoctors(List<Doctor> doctors)
  {
    this.doctors = doctors;
  }

  public List<Patient> getPatients()
  {
    return patients;
  }

  public void setPatients(List<Patient> patients)
  {
    this.patients = patients;
  }

  public Diagnosis getDiagnosis()
  {
    return diagnosis;
  }

  public void setDiagnosis(Diagnosis diagnosis)
  {
    this.diagnosis = diagnosis;
  }

  public void setSuccess(boolean b)
  {
    this.success = b;
  }

  public void setMessage(String diagnosisReceivedByServer)
  {
    this.message = diagnosisReceivedByServer;
  }

  public List<Diagnosis> getDiagnoses()
  {
    return diagnoses;
  }

  public void setDiagnoses(List<Diagnosis> diagnoses)
  {
    this.diagnoses = diagnoses;
  }
}

  public Appointment getAppointment()
  {
    return getAppointment();
  }
  public void setAppointment(Appointment appointment)
  {
    this.appointments=appointments;
  }
  }

