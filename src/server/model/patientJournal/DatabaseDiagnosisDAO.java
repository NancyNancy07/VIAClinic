package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseDiagnosisDAO
{
  private static DatabaseDiagnosisDAO instance;

  private DatabaseDiagnosisDAO() throws SQLException {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized DatabaseDiagnosisDAO getInstance() throws SQLException {
    if (instance == null) {
      instance = new DatabaseDiagnosisDAO();
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

  public DatabaseDiagnosis create(String diagnosisName, String status,
      NewDateTime dateDiagnosed, String comment,
      DatabaseDoctor doctor, DatabasePatient patient,
      Prescription prescription) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO diagnosis (diagnosisName, status, dateDiagnosed, comment, doctorId, patientId, prescriptionId) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS
      );

      statement.setString(1, diagnosisName);
      statement.setString(2, status);
      statement.setDate(3, Date.valueOf(LocalDate.of(
          dateDiagnosed.getYear(), dateDiagnosed.getMonth(), dateDiagnosed.getDay()
      )));
      if (comment == null)
        statement.setNull(4, Types.VARCHAR);
      else
        statement.setString(4, comment);

      statement.setInt(5, doctor.getDoctorID());
      statement.setInt(6, patient.getPatientID());
      statement.setInt(7, prescription.getPrescriptionId());

      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      int generatedId = -1;
      if (keys.next()) {
        generatedId = keys.getInt(1);
      }

      DatabaseDiagnosis diagnosis = new DatabaseDiagnosis(
          diagnosisName, status, dateDiagnosed, comment,
          doctor.getDoctorID(), patient.getPatientID(), prescription
      );
      diagnosis.setDiagnosisId(generatedId);

      return diagnosis;
    }
  }
}
