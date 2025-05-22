package server.model.patientJournal;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LabResultDAO
{
  private static LabResultDAO instance;
  private NewDateTime dateCollected;

  private LabResultDAO()
  {

  }

  public static synchronized LabResultDAO getInstance()
  {
    if (instance == null)
    {
      instance = new LabResultDAO();
    }
    return instance;
  }

  private static Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=book_appointment",
        "postgres", "Via@123");
  }

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

       






