package server.model.loginSystem.authentication;

import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.LabResult;

import shared.ResponseObject;

import java.util.List;

public interface AuthenticationService
{
  ResponseObject login(LoginRequest request);
  List<Doctor> getAllDoctors();
  List<Patient> getAllPatients();
  List<Appointment> getAppointmentsForPatient(int id);
  List<Diagnosis> getDiagnosesForPatient(int patientId);
  List<Prescription> getPrescriptionsForPatient(int patientId);
  void addPrescription(String medicineName, double doseAmount,
      String doseUnit, NewDateTime startDate, NewDateTime endDate, String frequency,
      String status, String comment, int doctorId, int patientId);
  List <LabResult> getLabResultsForPatient(int patientId);
  void addLabResult(String testName, String sampleType,NewDateTime dateCollected,
      String comment, int doctorId, int patientId);
}
