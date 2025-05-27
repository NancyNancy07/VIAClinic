package server.model.patientJournal;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * LabResultDAO is a Data Access Object for managing lab results in the database.
 * It provides methods to create, retrieve, and manage lab results associated with patients.
 */
public class LabResultDAO
{
  private static LabResultDAO instance;
  private NewDateTime dateCollected;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * Registers the PostgreSQL driver for database connectivity.
   */
  private LabResultDAO()
  {

  }

  /**
   * Returns the singleton instance of LabResultDAO.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of LabResultDAO
   */
  public static synchronized LabResultDAO getInstance()
  {
    if (instance == null)
    {
      instance = new LabResultDAO();
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
        "postgres", "Via@123");
  }

  /**
   * Creates a new lab result in the database.
   *
   * @param testName      the name of the lab test
   * @param sampleType    the type of sample collected
   * @param dateCollected the date and time when the sample was collected
   * @param comment       any additional comments regarding the lab result
   * @param doctorId      the ID of the doctor who ordered the test
   * @param patientId     the ID of the patient for whom the test was conducted
   * @return a LabResult object representing the created lab result
   * @throws SQLException if there is an error executing the SQL statement
   */
  public LabResult create(String testName, String sampleType,
      NewDateTime dateCollected, String comment, int doctorId, int patientId)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO labResult (testName,sampleType,dateCollected,comment, doctorId, patientId) "
              + "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, testName);
      statement.setString(2, sampleType);
      statement.setDate(3, Date.valueOf(
          LocalDate.of(dateCollected.getYear(), dateCollected.getMonth(),
              dateCollected.getDay())));
      if (comment == null)
      {
        statement.setNull(4, Types.VARCHAR);
      }
      else
      {
        statement.setString(4, comment);
      }

      statement.setInt(5, doctorId);
      statement.setInt(6, patientId);

      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      int generatedId = -1;
      if (keys.next())
      {
        generatedId = keys.getInt(1);
      }
      LabResult labResult = new LabResult(
          testName,sampleType, dateCollected, comment, doctorId, patientId
      );
      labResult.setLabResultId(generatedId);

      return labResult;
    }
  }

  /**
   * Retrieves a lab result by its ID.
   *
   * @param labResultId the ID of the lab result to retrieve
   * @return a LabResult object if found, otherwise null
   * @throws SQLException if there is an error executing the SQL statement
   */
  public LabResult getLabResultById(int labResultId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          " SELECT * FROM labResult WHERE labResultId = ?");
      statement.setInt(1, labResultId);

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String testName = resultSet.getString("testName");
        String sampleType = resultSet.getString("sampleType");
        Timestamp dateCollectedTimestamp = resultSet.getTimestamp("dateCollected");
        NewDateTime dateCollected = new NewDateTime(dateCollectedTimestamp.getDay(),
            dateCollectedTimestamp.getMonth(), dateCollectedTimestamp.getYear(),
            dateCollectedTimestamp.getHours(), dateCollectedTimestamp.getMinutes());;
            String comment = resultSet.getString("comment");
        int doctorId = resultSet.getInt("doctorId");
        int patientId = resultSet.getInt("patientId");

        return new LabResult(testName, sampleType,dateCollected,
              comment, doctorId, patientId);
      }
    }
    return null;
  }

  /**
   * Retrieves all lab results for a specific patient by their ID.
   *
   * @param patientId the ID of the patient whose lab results are to be retrieved
   * @return a list of LabResult objects for the specified patient
   * @throws SQLException if there is an error executing the SQL statement
   */
  public List<LabResult> getLabResultsByPatientId(int patientId) throws SQLException
  {
    List<LabResult> labResults = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM labResult WHERE patientId = ?");
      statement.setInt(1, patientId);

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        int labResultId = resultSet.getInt("labResultId");

        String testName = resultSet.getString("testName");
        String sampleType = resultSet.getString("sampleType");

        Timestamp dateCollectedTimestamp = resultSet.getTimestamp("dateCollected");
        NewDateTime dateCollected = new NewDateTime(
            dateCollectedTimestamp.toLocalDateTime().getDayOfMonth(),
            dateCollectedTimestamp.toLocalDateTime().getMonthValue(),
            dateCollectedTimestamp.toLocalDateTime().getYear(),
            dateCollectedTimestamp.toLocalDateTime().getHour(),
            dateCollectedTimestamp.toLocalDateTime().getMinute()
        );


        String comment = resultSet.getString("comment");
        int doctorId = resultSet.getInt("doctorId");


        LabResult labResult = new LabResult(testName, sampleType,
            dateCollected,  comment, doctorId, patientId);
        labResult.setLabResultId(labResultId);

        labResults.add(labResult);
      }
    }
    return labResults;
  }

}

       






