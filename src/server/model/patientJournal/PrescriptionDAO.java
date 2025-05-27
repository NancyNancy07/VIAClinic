package server.model.patientJournal;

import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * PrescriptionDAO is a Data Access Object for managing prescriptions in the database.
 * It provides methods to create, retrieve, and manage prescriptions.
 */
public class PrescriptionDAO
{
  private static PrescriptionDAO instance;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * Registers the PostgreSQL driver for database connectivity.
   */
  private PrescriptionDAO()
  {

  }

  /**
   * Returns the singleton instance of PrescriptionDAO.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of PrescriptionDAO
   */
  public static synchronized PrescriptionDAO getInstance()
  {
    if (instance == null)
    {
      instance = new PrescriptionDAO();
    }
    return instance;
  }

  /**
   * Establishes a connection to the PostgreSQL database.
   *
   * @return a Connection object to the database
   * @throws SQLException if there is an error connecting to the database
   */
  private static Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=book_appointment",
        "postgres",
        "Via@123"
    );
  }

  /**
   * Creates a new prescription in the database.
   *
   * @param medicineName the name of the medicine
   * @param doseAmount the amount of the dose
   * @param doseUnit the unit of the dose
   * @param startDate the start date of the prescription
   * @param endDate the end date of the prescription
   * @param frequency the frequency of the prescription
   * @param status the status of the prescription
   * @param comment any additional comments for the prescription
   * @param doctorId the ID of the doctor issuing the prescription
   * @param patientId the ID of the patient receiving the prescription
   * @return a Prescription object representing the created prescription
   * @throws SQLException if there is an error creating the prescription in the database
   */
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

  /**
   * Retrieves a prescription by its ID from the database.
   *
   * @param prescriptionId the ID of the prescription to retrieve
   * @return a Prescription object representing the retrieved prescription, or null if not found
   * @throws SQLException if there is an error retrieving the prescription from the database
   */
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

  /**
   * Retrieves all prescriptions for a specific patient by their ID.
   *
   * @param patientId the ID of the patient whose prescriptions are to be retrieved
   * @return a List of Prescription objects for the specified patient
   * @throws SQLException if there is an error retrieving the prescriptions from the database
   */
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
