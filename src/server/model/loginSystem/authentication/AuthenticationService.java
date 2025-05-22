package server.model.loginSystem.authentication;

import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.Vaccination;
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
  List<Vaccination> getVaccinationsForPatient(int patientId);
  Vaccination addVaccination(String vaccinationName, NewDateTime dateTaken,
      boolean isRecommended, String comment, NewDateTime nextDoseDate, int doctorId, int patientId);
}
