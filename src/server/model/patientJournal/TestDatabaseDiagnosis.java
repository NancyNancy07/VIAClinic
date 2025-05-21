package server.model.patientJournal;

import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;

public class TestDatabaseDiagnosis
{
  public static void main(String[] args)
  {
    try
    {
      AddressDAO addressDAO = AddressDAO.getInstance();

      String city = "Copenhagen";
      String postCode = "1000";
      String street = "Main St 1";

      Address address = addressDAO.create(city, postCode, street); //ID is serial so it becomes 1

      DatabasePatientDAO databasePatientDAO = DatabasePatientDAO.getInstance();

      String firstName = "John";
      String lastName = "Doe";
      String email = "asdasd@gmail.com";
      String phoneNumber = "12345678";
      String CPR = "123456-7890";
      String userName = "johndoe";
      String password = "password123";

      Patient patient = databasePatientDAO.create(firstName, lastName, email,
          phoneNumber, userName, password, CPR, address);


      DatabaseDoctorDAO databaseDoctorDAO = DatabaseDoctorDAO.getInstance();

      String doctorFirstName = "Dr. Smith";
      String doctorLastName = "Smith";
      String doctorEmail = "tobias@gmail.com";
      String doctorPhoneNumber = "87654321";
      String doctorUserName = "drsmith";
      String doctorPassword = "doctorpassword";

      Doctor doctor = databaseDoctorDAO.create(doctorFirstName, doctorLastName, doctorEmail,
          doctorPhoneNumber, doctorUserName, doctorPassword);

      PrescriptionDAO prescriptionDAO = PrescriptionDAO.getInstance();

      String medicineName = "Aspirin";
      double doseAmount = 2.0;
      String doseUnit = "Pill";
      NewDateTime prescriptionStartDate = new NewDateTime(1, 10, 2023, 12, 0);
      NewDateTime prescriptionEndDate = new NewDateTime(1, 11, 2023, 12, 0);
      String frequency = "Once a day";
      String status = "Active";
      String comment = "Take with food";


      Prescription prescription = prescriptionDAO.create(medicineName, doseAmount, doseUnit, prescriptionStartDate, prescriptionEndDate, frequency, status, comment, doctor.getDoctorID(), patient.getPatientID());


      DatabaseDiagnosisDAO diagnosisDAO = DatabaseDiagnosisDAO.getInstance();

      NewDateTime dateDiagnosed = new NewDateTime(1, 10, 2023, 12, 0);

      Diagnosis diagnosis = diagnosisDAO.create("Flu", "Active", dateDiagnosed, "Patient is sick", doctor, patient, prescription);
      System.out.println("Diagnosis created: " + diagnosis.getDiagnosisName());
      LabResultDAO labResultDAO = LabResultDAO.getInstance();

      String testName = "Hiv";
      String sampleType ="Blood";
      NewDateTime dateCollected = new NewDateTime(1, 10, 2023, 12, 0);



      LabResult labResult = labResultDAO.create(testName, sampleType, dateCollected, comment, doctor.getDoctorID(), patient.getPatientID());


    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
