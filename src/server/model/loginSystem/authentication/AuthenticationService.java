package server.model.loginSystem.authentication;

import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.LabResult;

import server.model.patientJournal.Referral;
import server.model.patientJournal.Vaccination;
import shared.ResponseObject;

import java.util.List;

/**
 * AuthenticationService interface defines the methods for user authentication
 * and management of healthcare-related data such as appointments, diagnoses,
 * prescriptions, lab results, vaccinations, and referrals.
 */
public interface AuthenticationService
{
  /**
   * Authenticates a user based on the provided login request.
   *
   * @param request the login request containing user credentials
   * @return a ResponseObject indicating the result of the authentication
   */
  ResponseObject login(LoginRequest request);
  /**
   * Retrieves all registered doctors.
   *
   * @return a list of all doctors
   */
  List<Doctor> getAllDoctors();

  /**
   * Retrieves all registered patients.
   *
   * @return a list of all patients
   */
  List<Patient> getAllPatients();

  /**
   * Retrieves all appointments for a specific patient.
   *
   * @param id the patient's ID
   * @return a list of appointments for the patient
   */
  List<Appointment> getAppointmentsForPatient(int id);

  /**
   * Retrieves all appointments for a specific doctor.
   *
   * @param id the doctor's ID
   * @return a list of appointments for the doctor
   */
  List<Appointment> getAppointmentsForDoctor(int id);

  /**
   * Books a new appointment.
   *
   * @param appointment the appointment to be booked
   * @return the booked appointment with updated information
   */
  Appointment bookAppointment(Appointment appointment);

  /**
   * Retrieves all diagnoses for a specific patient.
   *
   * @param patientId the ID of the patient
   * @return a list of diagnoses for the patient
   */
  List<Diagnosis> getDiagnosesForPatient(int patientId);

  /**
   * Adds a new diagnosis to the system.
   *
   * @param diagnosis the diagnosis to add
   */
  void addDiagnosis(Diagnosis diagnosis);

  /**
   * Retrieves all prescriptions for a specific patient.
   *
   * @param patientId the ID of the patient
   * @return a list of prescriptions for the patient
   */
  List<Prescription> getPrescriptionsForPatient(int patientId);

  /**
   * Adds a new prescription for a patient.
   *
   * @param medicineName the name of the prescribed medicine
   * @param doseAmount the amount of each dose
   * @param doseUnit the unit of measurement for the dose
   * @param startDate the start date of the prescription
   * @param endDate the end date of the prescription
   * @param frequency how often the medicine should be taken
   * @param status the status of the prescription
   * @param comment any additional notes
   * @param doctorId the ID of the prescribing doctor
   * @param patientId the ID of the patient
   */
  void addPrescription(String medicineName, double doseAmount, String doseUnit,
      NewDateTime startDate, NewDateTime endDate, String frequency,
      String status, String comment, int doctorId, int patientId);

  /**
   * Retrieves all lab results for a specific patient.
   *
   * @param patientId the ID of the patient
   * @return a list of lab results for the patient
   */
  List<LabResult> getLabResultsForPatient(int patientId);

  /**
   * Adds a new lab result for a patient.
   *
   * @param testName the name of the lab test
   * @param sampleType the type of sample taken
   * @param dateCollected the date the sample was collected
   * @param comment additional notes on the result
   * @param doctorId the ID of the responsible doctor
   * @param patientId the ID of the patient
   */
  void addLabResult(String testName, String sampleType,
      NewDateTime dateCollected, String comment, int doctorId, int patientId);

  /**
   * Retrieves all vaccinations for a specific patient.
   *
   * @param patientId the ID of the patient
   * @return a list of vaccinations for the patient
   */
  List<Vaccination> getVaccinationsForPatient(int patientId);

  /**
   * Adds a new vaccination record.
   *
   * @param vaccinationName the name of the vaccine
   * @param dateTaken the date the vaccine was administered
   * @param isRecommended whether the vaccine is recommended
   * @param comment additional notes
   * @param nextDoseDate the date of the next scheduled dose
   * @param doctorId the ID of the responsible doctor
   * @param patientId the ID of the patient
   * @return the newly added vaccination record
   */
  Vaccination addVaccination(String vaccinationName, NewDateTime dateTaken,
      boolean isRecommended, String comment, NewDateTime nextDoseDate,
      int doctorId, int patientId);

  /**
   * Adds a new referral for a patient.
   *
   * @param referral the referral to add
   */
  void addReferral(Referral referral);

  /**
   * Retrieves all referrals for a specific patient.
   *
   * @param patientId the ID of the patient
   * @return a list of referrals for the patient
   */
  List<Referral> getReferralsForPatient(int patientId);

  /**
   * Modifies an existing appointment.
   *
   * @param appointment the updated appointment information
   * @return the modified appointment
   */
  Appointment modifyAppointment(Appointment appointment);

}
/*
package server.model.loginSystem.authentication;

import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.LabResult;

import server.model.patientJournal.Referral;
import server.model.patientJournal.Vaccination;
import shared.ResponseObject;

import java.util.List;


public interface AuthenticationService
{
  ResponseObject login(LoginRequest request);
  List<Doctor> getAllDoctors();
  List<Patient> getAllPatients();
  List<Appointment> getAppointmentsForPatient(int id);
  List<Appointment> getAppointmentsForDoctor(int id);

  Appointment bookAppointment(Appointment appointment);
  List<Diagnosis> getDiagnosesForPatient(int patientId);
  void addDiagnosis(Diagnosis diagnosis);
  List<Prescription> getPrescriptionsForPatient(int patientId);
  void addPrescription(String medicineName, double doseAmount, String doseUnit,
      NewDateTime startDate, NewDateTime endDate, String frequency,
      String status, String comment, int doctorId, int patientId);
  List<LabResult> getLabResultsForPatient(int patientId);
  void addLabResult(String testName, String sampleType,
      NewDateTime dateCollected, String comment, int doctorId, int patientId);
  List<Vaccination> getVaccinationsForPatient(int patientId);
  Vaccination addVaccination(String vaccinationName, NewDateTime dateTaken,
      boolean isRecommended, String comment, NewDateTime nextDoseDate,
      int doctorId, int patientId);
  void addReferral(Referral referral);
  List<Referral> getReferralsForPatient(int patientId);
  Appointment modifyAppointment(Appointment appointment);
}
 */