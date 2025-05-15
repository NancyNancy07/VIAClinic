package server.model.patientJournal;

import server.model.bookAppointment.*;

import java.sql.*;
import java.time.LocalDateTime;

public class DiagnosisDAO
{

  private static DiagnosisDAO instance;

  private DiagnosisDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized DiagnosisDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new DiagnosisDAO();
    }
    return instance;
  }

  private static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=book_appointment",
        "postgres",
        "bangarang24"
    );
  }
  public Diagnosis create(String diagnosisName,
      String status, NewDateTime dateDiagnosed, String comment,
      DatabaseDoctor doctor, DatabasePatient patient, Prescription prescription) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement =
          connection.prepareStatement(" INSERT INTO diagnosis(diagnosisName,status,dateDiagnosed,comment,doctorId,patientId,prescriptionId) Values(?,?,?,?,?,?,?)");

      LocalDateTime localDateTime = LocalDateTime.of(dateDiagnosed.getYear(),
          dateDiagnosed.getMonth(), dateDiagnosed.getDay(), dateDiagnosed.getHour(),
          dateDiagnosed.getMinute());


      statement.setString(1, diagnosisName);
      statement.setString(2, status);
      statement.setTimestamp(3, Timestamp.valueOf(localDateTime));
      statement.setString(4, comment);
      statement.setInt(5, doctor.getDoctorID());
      statement.setInt(6, patient.getPatientID());
      statement.setInt(7, prescription.getPrescriptionId());

      statement.executeUpdate();
      return new Diagnosis(diagnosisName, status, dateDiagnosed, comment,
          doctor.getDoctorID(), patient.getPatientID(), prescription.toString());
    }
  }

  public int getId(String diagnosisName,
      String status, NewDateTime dateDiagnosed, String comment,
      DatabaseDoctor doctor, DatabasePatient patient, Prescription prescription) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement =
          connection.prepareStatement(" SELECT id FROM diagnosis WHERE diagnosisName = ? AND status = ? AND dateDiagnosed = ? AND comment = ? AND doctorId = ? AND patientId = ? AND prescriptionId = ?");
      LocalDateTime localDateTime = LocalDateTime.of(dateDiagnosed.getYear(),
          dateDiagnosed.getMonth(), dateDiagnosed.getDay(), dateDiagnosed.getHour(),
          dateDiagnosed.getMinute());

      statement.setString(1, diagnosisName);
      statement.setString(2, status);
      statement.setTimestamp(3, Timestamp.valueOf(localDateTime));
      statement.setString(4, comment);
      statement.setInt(5, doctor.getDoctorID());
      statement.setInt(6, patient.getPatientID());
      statement.setInt(7, prescription.getPrescriptionId());

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        return resultSet.getInt("diagnosisId");
      }
    }
    return -1;
  }

  public DatabaseDiagnosis createDatabaseDiagnosis(String diagnosisName,
      String status, NewDateTime dateDiagnosed, String comment,
      DatabaseDoctor doctor, DatabasePatient patient, Prescription prescription) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement =
          connection.prepareStatement(" INSERT INTO diagnosis(diagnosisName,status,dateDiagnosed,comment,doctorId,patientId,prescriptionId) Values(?,?,?,?,?,?,?)");

      LocalDateTime localDateTime = LocalDateTime.of(dateDiagnosed.getYear(),
          dateDiagnosed.getMonth(), dateDiagnosed.getDay(), dateDiagnosed.getHour(),
          dateDiagnosed.getMinute());


      statement.setString(1, diagnosisName);
      statement.setString(2, status);
      statement.setTimestamp(3, Timestamp.valueOf(localDateTime));
      statement.setString(4, comment);
      statement.setInt(5, doctor.getDoctorID());
      statement.setInt(6, patient.getPatientID());
      statement.setInt(7, prescription.getPrescriptionId());

      statement.executeUpdate();
      return new DatabaseDiagnosis(diagnosisName, status, dateDiagnosed, comment,
          doctor.getDoctorID(), patient.getPatientID(), prescription);
    }
  }
}
