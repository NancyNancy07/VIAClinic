package server.model.loginSystem.authentication;

import server.model.bookAppointment.*;
import server.model.loginSystem.entities.User;
import server.model.patientJournal.*;
import server.model.patientJournal.DoctorDAO;
import shared.ResponseObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AuthenticationServiceImp is the implementation of the AuthenticationService interface.
 * It handles user authentication, appointment management, and patient/doctor data retrieval.
 * It provides methods for logging in users, retrieving doctors and patients,
 * booking appointments, and managing patient records such as diagnoses, prescriptions, vaccinations, and lab results.
 */
public class AuthenticationServiceImp implements AuthenticationService
{
  private ArrayList<User> users = new ArrayList<>();
  private static AuthenticationServiceImp instance;
  private User loggedInUser;
  private List<Diagnosis> allDiagnoses;
  private List<Prescription> allPrescriptions;
  private List<LabResult> allLabResults;
  private List<Vaccination> allVaccinations;

  private AppointmentList appointmentList;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * Initializes sample data for doctors, patients, appointments, diagnoses, prescriptions, vaccinations, and lab results.
   */
  private AuthenticationServiceImp()
  {
    appointmentList = new AppointmentList();

    // Sample doctors
    Doctor doctor1 = new Doctor(1, "Dr. Smith", "Smith", "tobias@gmail.com",
        "87654321", "drsmith", "doctorpassword");
    //    Doctor doctor2 = new Doctor(2, "Dr. Adams", "asf123", "12345678");
    //    Doctor doctor3 = new Doctor(3, "Dr. Brown", "asg123", "123456789");
    //    Doctor doctor4 = new Doctor(4, "Dr. Lee", "asa123", "123456");
    users.add(doctor1);
    //    users.add(doctor2);
    //    users.add(doctor3);
    //    users.add(doctor4);

    // Sample patients
    Address address1 = new Address("Horsens", "8700", "Street 1");
    Patient patient1 = new Patient(1, "John", "Doe", "asdasd@gmail.com",
        "12345678", "asq123", "123", "1234567890", address1);

    users.add(patient1);
    //    users.add(new Patient(5, "John Doe", "asq123", "123"));
    //    users.add(new Patient(6, "Jane Doe", "asw123", "1234"));
    //    users.add(new Patient(7, "Bob Smith", "ase123", "12345"));
    //    users.add(new Patient(8, "Alice White", "asr123", "98765"));

    // Sample appointments
    // Create sample NewDateTime objects
    NewDateTime dateTime1 = new NewDateTime(9, 5, 2025, 12, 17);
    NewDateTime dateTime2 = new NewDateTime(9, 5, 2025, 13, 30);

    allDiagnoses = new ArrayList<>();
    NewDateTime dateTime3 = new NewDateTime(9, 5, 2025, 12, 17);
    NewDateTime dateTime4 = new NewDateTime(9, 5, 2025, 13, 30);

    allVaccinations = new ArrayList<>();
    Vaccination vaccination1 = new Vaccination("COVID-19", dateTime3, true,
        "2nd dose done", dateTime4, doctor1.getDoctorID(),
        patient1.getPatientID());
    allVaccinations.add(vaccination1);

    Vaccination vaccination2 = new Vaccination("Tetanus", dateTime3, true,
        "Completed - No follow-up needed", doctor1.getDoctorID(),
        patient1.getPatientID());
    allVaccinations.add(vaccination2);
    allLabResults = new ArrayList<>();
    LabResult labResult1 = new LabResult("HIV", "blood", dateTime1, "safe",
        doctor1.getDoctorID(), patient1.getPatientID());

    allPrescriptions = new ArrayList<>();
    Prescription prescription1 = new Prescription("Paracetamol", 500, "mg",
        dateTime3, dateTime4, "Twice a day", "Ongoing", "Take with food",
        doctor1.getDoctorID(), patient1.getPatientID());
    //    allDiagnoses.add(
    //        new Diagnosis("Flu", "Ongoing", dateTime3, 1, 5, "Rest and hydration"));
    //    allDiagnoses.add(
    //        new Diagnosis("Cold", "Resolved", dateTime4, 2, 5, "Paracetamol"));
    allDiagnoses.add(new Diagnosis("Fracture", "Healing", dateTime3, 1,
        patient1.getPatientID(), prescription1));

  }

  /**
   * Singleton pattern to ensure only one instance of AuthenticationServiceImp exists.
   *
   * @return the single instance of AuthenticationServiceImp
   */
  public static AuthenticationServiceImp getInstance()
  {
    if (instance == null)
    {
      instance = new AuthenticationServiceImp();
    }
    return instance;
  }

  /**
   * Returns the currently logged-in user.
   *
   * @return the logged-in user
   */
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

  /**
   * Returns the currently logged-in user.
   *
   * @return the logged-in user
   */
  private ResponseObject loginDoctor(String username, String password)
  {
    try
    {
      Doctor doctor = DoctorDAO.getInstance().getDoctorByUsername(username);
      if (doctor == null)
      {
        return new ResponseObject(false, "Doctor username not found", -1);
      }

      if (doctor.getPassword().equals(password))
      {
        loggedInUser = doctor;
        return new ResponseObject(true, "Doctor login successful", doctor.getDoctorID());
      }
      else
      {
        return new ResponseObject(false, "Incorrect password for doctor", -1);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return new ResponseObject(false, "Database error", -1);
    }
  }

  /**
   * Logs in a patient with the provided username and password.
   * If the patient is found and the password matches, it sets the logged-in user.
   * Otherwise, it returns an error response.
   *
   * @param username the patient's username
   * @param password the patient's password
   * @return a ResponseObject indicating success or failure
   */
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
        return new ResponseObject(true, "Patient login successful",
            patient.getPatientID());
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

  /**
   * Gets all doctors from the database.
   * @return a list of all doctors or an empty list if an error occurs
   */
  @Override public List<Doctor> getAllDoctors()
  {
    try
    {
      return DoctorDAO.getInstance().getAllDoctors();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  /**
   * Gets all patients from the database.
   * @return a list of all patients or an empty list if an error occurs
   */
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

  /**
   * Gets all appointments for a specific patient by their ID.
   * @param id the patient's ID
   * @return a list of appointments for the specified patient or an empty list if an error occurs
   */
  @Override public List<Appointment> getAppointmentsForPatient(int id)
  {
    try
    {
      return AppointmentDAO.getInstance().getAppointmentsByPatientId(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  /**
   * Gets all appointments for a specific doctor by their ID.
   * @param id the doctor's ID
   * @return a list of appointments for the specified doctor or an empty list if an error occurs
   */
  @Override public List<Appointment> getAppointmentsForDoctor(int id)
  {
    try
    {
      return AppointmentDAO.getInstance().getAppointmentsByDoctorId(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  /**
   * Books an appointment for a patient and doctor with the specified details.
   * @param appointment the Appointment object containing the details of the appointment
   * @return the created Appointment object or null if an error occurs
   */
  @Override public Appointment bookAppointment(Appointment appointment)
  {
    try
    {
      String date = appointment.getDate();
      String time = appointment.getTime();
      String[] dateParts = date.split("/");
      String[] timeParts = time.split(":");

      int day = Integer.parseInt(dateParts[0]);
      int month = Integer.parseInt(dateParts[1]);
      int year = Integer.parseInt(dateParts[2]);

      int hour = Integer.parseInt(timeParts[0]);
      int minute = Integer.parseInt(timeParts[1]);

      NewDateTime dateTime = new NewDateTime(day, month, year, hour, minute);

      Appointment createdAppointment = AppointmentDAO.getInstance()
          .create(dateTime, appointment.getMode(), appointment.getPatientID(),
              appointment.getDoctor());

      appointment.setAppointmentID(createdAppointment.getAppointmentID());
      appointmentList.addAppointment(appointment);
      return createdAppointment;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean cancelAppointment(int appointmentId) throws SQLException
  {
    return AppointmentDAO.getInstance().deleteAppointment(appointmentId);
  }

  /**
   * Get doctor by ID.
   * @param doctorId
   * @return Doctor object if found, otherwise null
   */
  public Doctor getDoctorById(int doctorId)
  {
    for (User user : users)
    {
      if (user instanceof Doctor)
      {
        Doctor doctor = (Doctor) user;
        if (doctor.getDoctorID() == doctorId)
        {
          return doctor;
        }
      }
    }
    return null;
  }

  /**
   * Get patient by ID.
   * @param patientId
   * @return Patient object if found, otherwise null
   */
  private Patient getPatientById(int patientId)
  {
    for (User user : users)
    {
      if (user instanceof Patient)
      {
        Patient patient = (Patient) user;
        if (patient.getPatientID() == patientId)
        {
          return patient;
        }
      }
    }
    return null;
  }

  /**
   * Get all diagnoses for a specific patient by their ID from database.
   * @param patientId the ID of the patient
   * @return a list of Diagnosis objects for the specified patient or an empty list if an error occurs
   */
  @Override public List<Diagnosis> getDiagnosesForPatient(int patientId)
  {
    try
    {
      return DatabaseDiagnosisDAO.getInstance().getByPatientId(patientId);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return new ArrayList<>();
    }

    //    List<Diagnosis> patientDiagnoses = new ArrayList<>();
    //
    //    for (Diagnosis diagnosis : allDiagnoses)
    //    {
    //      if (diagnosis.getPatientId() == patientId)
    //      {
    //        patientDiagnoses.add(diagnosis);
    //      }
    //    }
    //    return patientDiagnoses;
  }

  /**
   * Adds a diagnosis for a patient to the database.
   * @param diagnosis the Diagnosis object to be added
   */
  @Override public void addDiagnosis(Diagnosis diagnosis)
  {
    try
    {
      DatabaseDiagnosisDAO.getInstance()
          .create(diagnosis.getDiagnosisName(), diagnosis.getStatus(),
              diagnosis.getDateDiagnosed(), diagnosis.getComment(),
              getDoctorById(diagnosis.getDoctorId()),
              getPatientById(diagnosis.getPatientId()),
              diagnosis.getPrescription());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Retrieves all prescriptions for a specific patient by their ID from the database.
   * @param patientId the ID of the patient
   * @return a list of Prescription objects for the specified patient or an empty list if an error occurs
   */
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

  /**
   * Adds a prescription for a patient to the database.
   * @param medicineName the name of the medicine
   * @param doseAmount the amount of the dose
   * @param doseUnit the unit of the dose
   * @param startDate the start date of the prescription
   * @param endDate the end date of the prescription
   * @param frequency the frequency of the dose
   * @param status the status of the prescription
   * @param comment any additional comments
   * @param doctorId the ID of the doctor prescribing the medicine
   * @param patientId the ID of the patient receiving the prescription
   */
  @Override public void addPrescription(String medicineName, double doseAmount,
      String doseUnit, NewDateTime startDate, NewDateTime endDate,
      String frequency, String status, String comment, int doctorId,
      int patientId)
  {
    Prescription prescription = new Prescription(medicineName, doseAmount,
        doseUnit, startDate, endDate, frequency, status, comment, doctorId,
        patientId);

    allPrescriptions.add(prescription);
    PrescriptionDAO prescriptionDAO = PrescriptionDAO.getInstance();
    try
    {
      prescriptionDAO.create(prescription.getMedicineName(),
          prescription.getDoseAmount(), prescription.getDoseUnit(),
          prescription.getStartDate(), prescription.getEndDate(),
          prescription.getFrequency(), prescription.getStatus(),
          prescription.getComment(), prescription.getDoctorId(),
          prescription.getPatientId());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Retrieves all vaccinations for a specific patient by their ID from the database.
   * @param patientId the ID of the patient
   * @return a list of Vaccination objects for the specified patient or an empty list if an error occurs
   */
  @Override public List<Vaccination> getVaccinationsForPatient(int patientId)
  {
    VaccinationDAO vaccinationDAO = VaccinationDAO.getInstance();
    try
    {
      return vaccinationDAO.getVaccinationByPatientId(patientId);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  /**
   * Retrieves all lab results for a specific patient by their ID from the database.
   * @param patientId the ID of the patient
   * @return a list of LabResult objects for the specified patient or an empty list if an error occurs
   */
  @Override public List<LabResult> getLabResultsForPatient(int patientId)
  {
    {
      LabResultDAO labResultDAO = LabResultDAO.getInstance();
      try
      {
        return labResultDAO.getLabResultsByPatientId(patientId);
      }
      catch (SQLException e)
      {
        e.printStackTrace();
        return new ArrayList<>();
      }
    }

  }

  /**
   * Adds a lab result for a patient to the database.
   * @param testName the name of the lab test
   * @param sampleType the type of sample collected
   * @param dateCollected the date and time when the sample was collected
   * @param comment any additional comments about the lab result
   * @param doctorId the ID of the doctor who ordered the lab test
   * @param patientId the ID of the patient who underwent the lab test
   */
  @Override public void addLabResult(String testName, String sampleType,
      NewDateTime dateCollected, String comment, int doctorId, int patientId)
  {
    LabResult labResult = new LabResult(testName, sampleType, dateCollected,
        comment, doctorId, patientId);

    allLabResults.add(labResult);
    LabResultDAO labResultDAO = LabResultDAO.getInstance();
    try
    {
      labResultDAO.create(labResult.getTestName(), labResult.getSampleType(),
          labResult.getDateCollected(), labResult.getComment(),
          labResult.getDoctorId(), labResult.getPatientId());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Adds a vaccination for a patient to the database.
   * @param vaccinationName the name of the vaccination
   * @param dateTaken the date when the vaccination was administered
   * @param isRecommended whether the vaccination is recommended
   * @param comment any additional comments about the vaccination
   * @param nextDoseDate the date for the next dose, if applicable
   * @param doctorId the ID of the doctor administering the vaccination
   * @param patientId the ID of the patient receiving the vaccination
   * @return the created Vaccination object
   */
  @Override public Vaccination addVaccination(String vaccinationName,
      NewDateTime dateTaken, boolean isRecommended, String comment,
      NewDateTime nextDoseDate, int doctorId, int patientId)
  {
    Vaccination vaccination;

    if (nextDoseDate == null)
    {
      vaccination = new Vaccination(vaccinationName, dateTaken, isRecommended,
          comment, doctorId, patientId);
    }
    else
    {
      vaccination = new Vaccination(vaccinationName, dateTaken, isRecommended,
          comment, nextDoseDate, doctorId, patientId);
    }

    allVaccinations.add(vaccination);

    try
    {
      VaccinationDAO.getInstance()
          .create(vaccination.getVaccinationName(), vaccination.getDateTaken(),
              vaccination.isRecommended(), vaccination.getComment(),
              vaccination.getNextDoseDate(), vaccination.getDoctorId(),
              vaccination.getPatientId());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return vaccination;
  }

  /**
   * Adds a referral for a patient to the database.
   * @param referral the Referral object containing the details of the referral
   */
  @Override public void addReferral(Referral referral)
  {
    try
    {
      ReferralDAO.getInstance()
          .create(referral.getDateCreated(), referral.getReason(),
              referral.getComment(), referral.getDoctorId(),
              referral.getPatientId());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Retrieves all referrals for a specific patient by their ID from the database.
   * @param patientId the ID of the patient
   * @return a list of Referral objects for the specified patient or an empty list if an error occurs
   */
  @Override public List<Referral> getReferralsForPatient(int patientId)
  {
    ReferralDAO referralDAO = ReferralDAO.getInstance();
    try
    {
      return referralDAO.getReferralsByPatientId(patientId);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  /**
   * Modifies an existing appointment in the database.
   * @param appointment the Appointment object containing the updated details
   * @return the updated Appointment object or throws an exception if not found
   */
  @Override public Appointment modifyAppointment(Appointment appointment)
  {
    try
    {
      AppointmentDAO dao = AppointmentDAO.getInstance();
      int appointmentId = appointment.getAppointmentID();

      String[] dateParts = appointment.getDate().split("/");
      String[] timeParts = appointment.getTime().split(":");

      int day = Integer.parseInt(dateParts[0]);
      int month = Integer.parseInt(dateParts[1]);
      int year = Integer.parseInt(dateParts[2]);

      int hour = Integer.parseInt(timeParts[0]);
      int minute = Integer.parseInt(timeParts[1]);

      NewDateTime dateTime = new NewDateTime(day, month, year, hour, minute);

      String newMode = appointment.getMode();
      int newDoctorId = appointment.getDoctor().getDoctorID();

      Appointment updatedAppointment = dao.updateAppointment(appointmentId,
          dateTime, newMode, newDoctorId);

      if (updatedAppointment == null)
      {
        throw new IllegalArgumentException(
            "Appointment to modify not found in DB");
      }
      return updatedAppointment;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Database error while modifying appointment",
          e);
    }
  }

}