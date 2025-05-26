package server.model.patientJournal;

import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO
{
  private static PrescriptionDAO instance;

  private PrescriptionDAO()
  {

  }

  public static synchronized PrescriptionDAO getInstance()
  {
    if (instance == null)
    {
      instance = new PrescriptionDAO();
    }
    return instance;
  }

  private static Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=book_appointment",
        "postgres",
        "Via@123"
    );
  }

  public Prescription create(String medicineName, double doseAmount,
      String doseUnit, NewDateTime startDate, NewDateTime endDate,
      String frequency, String status, String comment,
      int doctorId, int patientId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO prescription (medicineName, doseAmount, doseUnit, startDate, endDate, frequency, status, comment, doctorId, patientId) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS
      );

      statement.setString(1, medicineName);
      statement.setDouble(2, doseAmount);
      statement.setString(3, doseUnit);

      statement.setDate(4, Date.valueOf(LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDay())));
      statement.setDate(5, Date.valueOf(LocalDate.of(endDate.getYear(), endDate.getMonth(), endDate.getDay())));

      statement.setString(6, frequency);
      statement.setString(7, status);

      if (comment == null) {
        statement.setNull(8, Types.VARCHAR);
      } else {
        statement.setString(8, comment);
      }

      statement.setInt(9, doctorId);
      statement.setInt(10, patientId);

      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      int generatedId = -1;
      if (keys.next()) {
        generatedId = keys.getInt(1);
      }

      Prescription prescription = new Prescription(
          medicineName, doseAmount, doseUnit,
          startDate, endDate, frequency, status,
          comment, doctorId, patientId
      );
      prescription.setPrescriptionId(generatedId);

      return prescription;
    }
  }

  public Prescription getPrescriptionById(int prescriptionId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          " SELECT * FROM prescription WHERE prescriptionId = ?");
      statement.setInt(1, prescriptionId);

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String medicineName = resultSet.getString("medicineName");
        double doseAmount = resultSet.getDouble("doseAmount");
        String doseUnit = resultSet.getString("doseUnit");
        Timestamp startDateTimestamp = resultSet.getTimestamp("startDate");
        NewDateTime startDate = new NewDateTime(startDateTimestamp.getDay(),
            startDateTimestamp.getMonth(), startDateTimestamp.getYear(),
            startDateTimestamp.getHours(), startDateTimestamp.getMinutes());;
        Timestamp endDateTimestamp = resultSet.getTimestamp("endDate");
        NewDateTime endDate = new NewDateTime(endDateTimestamp.getDay(),
            endDateTimestamp.getMonth(), endDateTimestamp.getYear(),
            endDateTimestamp.getHours(), endDateTimestamp.getMinutes());
        String frequency = resultSet.getString("frequency");
        String status = resultSet.getString("status");
        String comment = resultSet.getString("comment");
        int doctorId = resultSet.getInt("doctorId");
        int patientId = resultSet.getInt("patientId");

        return new Prescription(medicineName, doseAmount, doseUnit, startDate,
            endDate, frequency, status, comment, doctorId, patientId);
      }
    }
    return null;
  }

  public List<Prescription> getPrescriptionsByPatientId(int patientId) throws SQLException
  {
    List<Prescription> prescriptions = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM prescription WHERE patientId = ?");
      statement.setInt(1, patientId);

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        int prescriptionId = resultSet.getInt("prescriptionId");

        String medicineName = resultSet.getString("medicineName");
        double doseAmount = resultSet.getDouble("doseAmount");
        String doseUnit = resultSet.getString("doseUnit");

        Timestamp startDateTimestamp = resultSet.getTimestamp("startDate");
        NewDateTime startDate = new NewDateTime(
            startDateTimestamp.toLocalDateTime().getDayOfMonth(),
            startDateTimestamp.toLocalDateTime().getMonthValue(),
            startDateTimestamp.toLocalDateTime().getYear(),
            startDateTimestamp.toLocalDateTime().getHour(),
            startDateTimestamp.toLocalDateTime().getMinute()
        );

        Timestamp endDateTimestamp = resultSet.getTimestamp("endDate");
        NewDateTime endDate = new NewDateTime(
            endDateTimestamp.toLocalDateTime().getDayOfMonth(),
            endDateTimestamp.toLocalDateTime().getMonthValue(),
            endDateTimestamp.toLocalDateTime().getYear(),
            endDateTimestamp.toLocalDateTime().getHour(),
            endDateTimestamp.toLocalDateTime().getMinute()
        );

        String frequency = resultSet.getString("frequency");
        String status = resultSet.getString("status");
        String comment = resultSet.getString("comment");
        int doctorId = resultSet.getInt("doctorId");

        Prescription prescription = new Prescription(medicineName, doseAmount, doseUnit, startDate,
            endDate, frequency, status, comment, doctorId, patientId);

        prescription.setPrescriptionId(prescriptionId);

        prescriptions.add(prescription);
      }
    }
    return prescriptions;
  }

}
