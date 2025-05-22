package server.model.loginSystem.authentication;

import server.model.bookAppointment.*;
import server.model.loginSystem.entities.User;
import server.model.patientJournal.*;
import shared.ResponseObject;
import server.model.patientJournal.PatientDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthenticationServiceImp implements AuthenticationService
{
  private ArrayList<User> users = new ArrayList<>();
  private static AuthenticationServiceImp instance;
  private User loggedInUser;
  private List<Diagnosis> allDiagnoses;
  private List<Prescription> allPrescriptions;
  private List<Vaccination> allVaccinations;

  private AppointmentList appointmentList;

  private AuthenticationServiceImp()
  {
    appointmentList = new AppointmentList();


    // Sample doctors
    Doctor doctor1 = new Doctor(1, "Dr. Smith", "Smith","tobias@gmail.com","87654321", "drsmith", "doctorpassword");
//    Doctor doctor2 = new Doctor(2, "Dr. Adams", "asf123", "12345678");
//    Doctor doctor3 = new Doctor(3, "Dr. Brown", "asg123", "123456789");
//    Doctor doctor4 = new Doctor(4, "Dr. Lee", "asa123", "123456");
    users.add(doctor1);
//    users.add(doctor2);
//    users.add(doctor3);
//    users.add(doctor4);

    // Sample patients
    Address address1 = new Address("Horsens", "8700", "Street 1");
    Patient patient1 = new Patient(1, "John", "Doe", "asdasd@gmail.com", "12345678", "asq123", "123", "1234567890", address1);

    users.add(patient1);
//    users.add(new Patient(5, "John Doe", "asq123", "123"));
//    users.add(new Patient(6, "Jane Doe", "asw123", "1234"));
//    users.add(new Patient(7, "Bob Smith", "ase123", "12345"));
//    users.add(new Patient(8, "Alice White", "asr123", "98765"));

    // Sample appointments
    // Create sample NewDateTime objects
    NewDateTime dateTime1 = new NewDateTime(9, 5, 2025, 12, 17);
    NewDateTime dateTime2 = new NewDateTime(9, 5, 2025, 13, 30);

    appointmentList.addAppointment(
        new Appointment(dateTime1, patient1.getPatientID(), doctor1, "In-person"));
//    appointmentList.addAppointment(
//        new Appointment(dateTime2, 5, doctor2, "Online"));
//    appointmentList.addAppointment(
//        new Appointment(dateTime1, 6, doctor3, "In-person"));
//    appointmentList.addAppointment(
//        new Appointment(dateTime2, 6, doctor4, "Online"));

    allDiagnoses = new ArrayList<>();
    NewDateTime dateTime3 = new NewDateTime(9, 5, 2025, 12, 17);
    NewDateTime dateTime4 = new NewDateTime(9, 5, 2025, 13, 30);

    allVaccinations = new ArrayList<>();
    Vaccination vaccination1 = new Vaccination("COVID-19",
        dateTime3, true, "2nd dose done", dateTime4,
        doctor1.getDoctorID(), patient1.getPatientID()
    );
    allVaccinations.add(vaccination1);

    Vaccination vaccination2 = new Vaccination(
        "Tetanus", dateTime3,
        true, "Completed - No follow-up needed",
        doctor1.getDoctorID(), patient1.getPatientID()
    );
    allVaccinations.add(vaccination2);


    allPrescriptions = new ArrayList<>();
    Prescription prescription1 = new Prescription("Paracetamol", 500, "mg",
        dateTime3, dateTime4, "Twice a day", "Ongoing", "Take with food",
        doctor1.getDoctorID(), patient1.getPatientID());
//    allDiagnoses.add(
//        new Diagnosis("Flu", "Ongoing", dateTime3, 1, 5, "Rest and hydration"));
//    allDiagnoses.add(
//        new Diagnosis("Cold", "Resolved", dateTime4, 2, 5, "Paracetamol"));
    allDiagnoses.add(new Diagnosis("Fracture", "Healing", dateTime3, 1, patient1.getPatientID(),
        prescription1));
  }

  public static AuthenticationServiceImp getInstance()
  {
    if (instance == null)
    {
      instance = new AuthenticationServiceImp();
    }
    return instance;
  }

  @Override public ResponseObject login(LoginRequest request)
  {
    String username = request.getUsername();
    String password = request.getPassword();
    String userType = request.getUserType();

    if ("doctor".equalsIgnoreCase(userType))
    {
      return loginDoctor(username, password);
    }
    else if ("patient".equalsIgnoreCase(userType))
    {
      return loginPatient(username, password);
    }
    else
    {
      return new ResponseObject(false, "Invalid user type", -1);
    }
  }

  private ResponseObject loginDoctor(String username, String password)
  {
    for (User user : users)
    {
      if (user instanceof Doctor)
      {
        if (user.getUsername().equals(username))
        {
          if (user.getPassword().equals(password))
          {
            loggedInUser = user;
            return new ResponseObject(true, "Doctor login successful",
                ((Doctor) user).getDoctorID());
          }
          return new ResponseObject(false, "Incorrect password for doctor", -1);
        }
      }
    }
    return new ResponseObject(false, "Doctor username not found", -1);
  }

  private ResponseObject loginPatient(String username, String password)
  {
    try
    {
      Patient patient = PatientDAO.getInstance().getPatientByUsername(username);

      if (patient == null)
      {
        return new ResponseObject(false, "Patient username not found", -1);
      }

      if (patient.getPassword().equals(password))
      {
        loggedInUser = patient;
        return new ResponseObject(true, "Patient login successful", patient.getPatientID());
      }
      else
      {
        return new ResponseObject(false, "Incorrect password for patient", -1);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return new ResponseObject(false, "Database error", -1);
    }
  }

  @Override public List<Doctor> getAllDoctors()
  {
    DoctorList doctors = new DoctorList();
    for (User user : users)
    {
      if (user instanceof Doctor)
      {
        doctors.addDoctor((Doctor) user);
      }
    }
    return doctors.getAllDoctors();
  }

  @Override public List<Patient> getAllPatients()
  {
    PatientList patients = new PatientList();
    for (User user : users)
    {
      if (user instanceof Patient)
      {
        patients.addPatient((Patient) user);
      }
    }
    return Arrays.asList(patients.getAllPatients());
  }

  @Override public List<Appointment> getAppointmentsForPatient(int id)
  {
    List<Appointment> result = new ArrayList<>();
    for (Appointment appointment : appointmentList.getAllAppointments())
    {
      if (appointment.getPatientID() == id)
      {
        result.add(appointment);
      }
    }
    return result;
  }

  @Override public List<Diagnosis> getDiagnosesForPatient(int patientId)
  {
    List<Diagnosis> patientDiagnoses = new ArrayList<>();
    for (Diagnosis diagnosis : allDiagnoses)
    {
      if (diagnosis.getPatientId() == patientId)
      {
        patientDiagnoses.add(diagnosis);
      }
    }
    return patientDiagnoses;
  }

  @Override public List<Prescription> getPrescriptionsForPatient(int patientId)
  {
    PrescriptionDAO prescriptionDAO = PrescriptionDAO.getInstance();
    try
    {
      return prescriptionDAO.getPrescriptionsByPatientId(patientId);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override public void addPrescription(String medicineName, double doseAmount,
      String doseUnit, NewDateTime startDate, NewDateTime endDate, String frequency,
      String status, String comment, int doctorId, int patientId)
  {
    Prescription prescription = new Prescription(medicineName, doseAmount, doseUnit,
        startDate, endDate, frequency, status, comment, doctorId, patientId);


    allPrescriptions.add(prescription);
    PrescriptionDAO prescriptionDAO = PrescriptionDAO.getInstance();
    try
    {
      prescriptionDAO.create(prescription.getMedicineName(), prescription.getDoseAmount(),
          prescription.getDoseUnit(), prescription.getStartDate(),
          prescription.getEndDate(), prescription.getFrequency(),
          prescription.getStatus(), prescription.getComment(),
          prescription.getDoctorId(), prescription.getPatientId());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public List<Vaccination> getVaccinationsForPatient(int patientId) {
    VaccinationDAO vaccinationDAO = VaccinationDAO.getInstance();
    try {
      return vaccinationDAO.getVaccinationByPatientId(patientId);
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override
  public Vaccination addVaccination(String vaccinationName, NewDateTime dateTaken, boolean isRecommended,
      String comment, NewDateTime nextDoseDate, int doctorId, int patientId)
  {
    Vaccination vaccination;

    if (nextDoseDate == null) {
      vaccination = new Vaccination(vaccinationName, dateTaken, isRecommended, comment, doctorId, patientId);
    } else {
      vaccination = new Vaccination(vaccinationName, dateTaken, isRecommended, comment, nextDoseDate, doctorId, patientId);
    }

    allVaccinations.add(vaccination);

    try {
      VaccinationDAO.getInstance().create(
          vaccination.getVaccinationName(),
          vaccination.getDateTaken(),
          vaccination.isRecommended(),
          vaccination.getComment(),
          vaccination.getNextDoseDate(),
          vaccination.getDoctorId(),
          vaccination.getPatientId()
      );
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return vaccination;
  }

  public void addDiagnosis(Diagnosis diagnosis)
  {
    allDiagnoses.add(diagnosis);
  }

}
