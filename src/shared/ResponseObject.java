package shared;

import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;

import java.util.List;

public class ResponseObject
{
  private boolean success;
  private String status;
  private String message;
  private int patientId;
  private Appointment appointment;
  private List<Appointment> appointments;
  private List<Doctor> doctors;
  private List<Patient> patients;
  private Diagnosis diagnosis;
  private List<Diagnosis> diagnoses;
  private Prescription prescription;
  private List<Prescription> prescriptions;

  public ResponseObject()
  {
  }

  public ResponseObject(boolean success, String message, int patientId)
  {
    this.success = success;
    this.message = message;
    this.patientId = patientId;
  }

  public boolean isSuccess()
  {
    return success;
  }

  public void setSuccess(boolean success)
  {
    this.success = success;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public int getPatientId()
  {
    return patientId;
  }

  public void setPatientId(int patientId)
  {
    this.patientId = patientId;
  }

  public Appointment getAppointment()
  {
    return appointment;
  }

  public void setAppointment(Appointment appointment)
  {
    this.appointment = appointment;
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

  public List<Diagnosis> getDiagnoses()
  {
    return diagnoses;
  }

  public void setDiagnoses(List<Diagnosis> diagnoses)
  {
    this.diagnoses = diagnoses;
  }

  public List<Prescription> getPrescriptions()
  {
    return prescriptions;
  }

  public void setPrescriptions(List<Prescription> prescriptions)
  {
    this.prescriptions = prescriptions;
  }

  public Prescription getPrescription()
  {
    return prescription;
  }

  public void setPrescription(Prescription prescription)
  {
    this.prescription = prescription;
  }
}
