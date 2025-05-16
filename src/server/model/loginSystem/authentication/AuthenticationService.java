package server.model.loginSystem.authentication;

import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Diagnosis;
import shared.ResponseObject;

import java.util.List;

public interface AuthenticationService
{
  ResponseObject login(LoginRequest request);
  List<Doctor> getAllDoctors();
  List<Patient> getAllPatients();
  List<Appointment> getAppointmentsForPatient(int id);
  List<Diagnosis> getDiagnosesForPatient(int patientId);
}
