package shared;

import server.model.bookAppointment.Patient;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.LabResult;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.Referral;
import server.model.patientJournal.Vaccination;

import java.util.List;

/**
 * ResponseObject is a class that encapsulates the response data for various operations
 * in the healthcare system, including appointments, doctors, patients, diagnoses,
 * prescriptions, lab results, referrals, and vaccinations.
 * It contains fields for success status, status message,
 * patient ID, and lists of related objects.
 */
public class ResponseObject
{
  private boolean success;
  private String status;
  private String message;
  private int patientId;
  private AppointmentDTO appointment;
  private List<AppointmentDTO> appointments;
  private List<DoctorDTO> doctors;
  private List<Patient> patients;
  private Diagnosis diagnosis;
  private List<Diagnosis> diagnoses;
  private Prescription prescription;
  private List<Prescription> prescriptions;
  private LabResult labResult;
  private List<LabResult> labResults;
  private Referral referral;
  private List<Referral> referrals;
  private Vaccination vaccination;
  private List<Vaccination> vaccinations;

  /**
   * Default constructor for ResponseObject.
   * Initializes the object with default values.
   */
  public ResponseObject()
  {
  }

  /**
   * Constructor for ResponseObject with success status and message.
   * Initializes the object with provided values.
   *
   * @param success indicates whether the operation was successful
   * @param message a message providing additional information about the operation
   */
  public ResponseObject(boolean success, String message, int patientId)
  {
    this.success = success;
    this.message = message;
    this.patientId = patientId;
  }

  /**
   * Returns whether the operation was successful.
   * @return true if the operation was successful, false otherwise
   */
  public boolean isSuccess()
  {
    return success;
  }

  /**
   * Sets the success status of the operation.
   *
   * @param success true if the operation was successful, false otherwise
   */
  public void setSuccess(boolean success)
  {
    this.success = success;
  }

  /**
   * Gets the status of the response.
   *
   * @return the status of the response
   */
  public String getStatus()
  {
    return status;
  }

  /**
   * Sets the status of the response.
   *
   * @param status the status of the response
   */
  public void setStatus(String status)
  {
    this.status = status;
  }

  /**
   * Gets the message providing additional information about the operation.
   *
   * @return the message
   */
  public String getMessage()
  {
    return message;
  }

  /**
   * Sets the message providing additional information about the operation.
   *
   * @param message the message to set
   */
  public void setMessage(String message)
  {
    this.message = message;
  }

  /**
   * Gets the patient ID associated with the response.
   *
   * @return the patient ID
   */
  public int getPatientId()
  {
    return patientId;
  }

  /**
   * Sets the patient ID associated with the response.
   *
   * @param patientId the patient ID to set
   */
  public void setPatientId(int patientId)
  {
    this.patientId = patientId;
  }

  /**
   * Gets the appointment associated with the response.
   *
   * @return the appointment
   */
  public AppointmentDTO getAppointment()
  {
    return appointment;
  }

  /**
   * Sets the appointment associated with the response.
   *
   * @param appointment the appointment to set
   */
  public void setAppointment(AppointmentDTO appointment)
  {
    this.appointment = appointment;
  }

  /**
   * Gets the list of appointments associated with the response.
   *
   * @return the list of appointments
   */
  public List<AppointmentDTO> getAppointments()
  {
    return appointments;
  }

  /**
   * Sets the list of appointments associated with the response.
   *
   * @param appointments the list of appointments to set
   */
  public void setAppointments(List<AppointmentDTO> appointments)
  {
    this.appointments = appointments;
  }

  /**
   * Gets the list of doctors associated with the response.
   *
   * @return the list of doctors
   */
  public List<DoctorDTO> getDoctors()
  {
    return doctors;
  }

  /**
   * Sets the list of doctors associated with the response.
   *
   * @param doctors the list of doctors to set
   */
  public void setDoctors(List<DoctorDTO> doctors)
  {
    this.doctors = doctors;
  }

  /**
   * Gets the list of patients associated with the response.
   *
   * @return the list of patients
   */
  public List<Patient> getPatients()
  {
    return patients;
  }

  /**
   * Sets the list of patients associated with the response.
   *
   * @param patients the list of patients to set
   */
  public void setPatients(List<Patient> patients)
  {
    this.patients = patients;
  }

  /**
   * Gets the diagnosis associated with the response.
   *
   * @return the diagnosis
   */
  public Diagnosis getDiagnosis()
  {
    return diagnosis;
  }

  /**
   * Sets the diagnosis associated with the response.
   *
   * @param diagnosis the diagnosis to set
   */
  public void setDiagnosis(Diagnosis diagnosis)
  {
    this.diagnosis = diagnosis;
  }

  /**
   * Gets the list of diagnoses associated with the response.
   *
   * @return the list of diagnoses
   */
  public List<Diagnosis> getDiagnoses()
  {
    return diagnoses;
  }

  /**
   * Sets the list of diagnoses associated with the response.
   *
   * @param diagnoses the list of diagnoses to set
   */
  public void setDiagnoses(List<Diagnosis> diagnoses)
  {
    this.diagnoses = diagnoses;
  }

  /**
   * Gets the prescription associated with the response.
   *
   * @return the prescription
   */
  public List<Prescription> getPrescriptions()
  {
    return prescriptions;
  }

  /**
   * Sets the list of prescriptions associated with the response.
   *
   * @param prescriptions the list of prescriptions to set
   */
  public void setPrescriptions(List<Prescription> prescriptions)
  {
    this.prescriptions = prescriptions;
  }

  /**
   * Gets the prescription associated with the response.
   *
   * @return the prescription
   */
  public Prescription getPrescription()
  {
    return prescription;
  }

  /**
   * Sets the prescription associated with the response.
   *
   * @param prescription the prescription to set
   */
  public void setPrescription(Prescription prescription)
  {
    this.prescription = prescription;
  }

/**
   * Gets the lab result associated with the response.
   *
   * @return the lab result
   */
  public LabResult getLabResult()

  {
    return labResult;
  }

  /**
   * Sets the lab result associated with the response.
   *
   * @param labResults the lab result to set
   */
  public void setLabResult(LabResult labResults)
  {
    this.labResult = labResults;
  }

  /**
   * Gets the list of lab results associated with the response.
   *
   * @return the list of lab results
   */
  public List<LabResult> getLabResults()
  {
    return labResults;
  }

  /**
   * Sets the list of lab results associated with the response.
   *
   * @param labResults the list of lab results to set
   */
  public void setLabResults(List<LabResult> labResults)
  {
    this.labResults = labResults;
  }

  /**
   * Sets the list of referrals associated with the response.
   *
   * @param referrals the list of referrals to set
   */
  public void setReferrals(List<Referral> referrals)
  {
    this.referrals = referrals;
  }

  /**
   * Gets the list of referrals associated with the response.
   *
   * @return the list of referrals
   */
  public List<Referral> getReferrals()
  {
    return referrals;
  }

  /**
   * Sets the referral associated with the response.
   *
   * @param referral the referral to set
   */
  public void setReferral(Referral referral)
  {
    this.referral = referral;
  }

  /**
   * Gets the referral associated with the response.
   *
   * @return the referral
   */
  public Referral getReferral()
  {
    return referral;
  }

  /**
   * Sets the list of vaccinations associated with the response.
   *
   * @param vaccinations the list of vaccinations to set
   */
  public void setVaccinations(List<Vaccination> vaccinations)
  {
    this.vaccinations = vaccinations;
  }

  /**
   * Gets the list of vaccinations associated with the response.
   *
   * @return the list of vaccinations
   */
  public List<Vaccination> getVaccinations()
  {
    return vaccinations;
  }

  /**
   * Sets the vaccination associated with the response.
   *
   * @param vaccination the vaccination to set
   */
  public void setVaccination(Vaccination vaccination)
  {
    this.vaccination = vaccination;
  }

  /**
   * Gets the vaccination associated with the response.
   *
   * @return the vaccination
   */
  public Vaccination getVaccination()
  {
    return vaccination;
  }
}

