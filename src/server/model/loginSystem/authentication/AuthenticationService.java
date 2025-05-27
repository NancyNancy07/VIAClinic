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

import java.sql.SQLException;
import java.util.List;

public interface AuthenticationService
{
  ResponseObject login(LoginRequest request);
  List<Doctor> getAllDoctors();
  List<Patient> getAllPatients();
  List<Appointment> getAppointmentsForPatient(int id);
  void bookAppointment(Appointment appointment);
  boolean cancelAppointment(int appointmentId) throws SQLException;
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
