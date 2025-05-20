package server.model.loginSystem.authentication;

import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.Referral;
import shared.ResponseObject;

import java.util.List;

public interface AuthenticationService
{
  ResponseObject login(LoginRequest request);
  List<Doctor> getAllDoctors();
  List<Patient> getAllPatients();
  List<Appointment> getAppointmentsForPatient(int id);
  void bookAppointment(Appointment appointment);
  List<Diagnosis> getDiagnosesForPatient(int patientId);
  void addDiagnosis(Diagnosis diagnosis);
  List<Prescription> getPrescriptionsForPatient(int patientId);
  void addPrescription(String medicineName, double doseAmount, String doseUnit,
      NewDateTime startDate, NewDateTime endDate, String frequency,
      String status, String comment, int doctorId, int patientId);
  void addReferral(Referral referral);
}
