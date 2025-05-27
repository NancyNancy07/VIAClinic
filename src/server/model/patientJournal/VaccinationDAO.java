package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ReferralDAO is a Data Access Object for managing Referral records in the database.
 * It provides methods to create a new referral and retrieve referrals by patient ID.
 */
public class VaccinationDAO
{
  private static VaccinationDAO instance;


  /**
   * Private constructor to prevent instantiation from outside the class.
   * Registers the PostgreSQL driver for database connectivity.
   */
  private VaccinationDAO()
  {
  }


  /**
   * Returns the singleton instance of VaccinationDAO.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of VaccinationDAO
   */
  public static synchronized VaccinationDAO getInstance()
  {
    if (instance == null)
    {
      instance = new VaccinationDAO();
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
   * Creates a new vaccination record in the database.
   *
   * @param vaccinationName the name of the vaccination
   * @param dateTaken       the date when the vaccination was taken
   * @param isRecommended   whether the vaccination is recommended
   * @param comment         any additional comments about the vaccination
   * @param nextDoseDate    the date for the next dose, if applicable
   * @param doctorId        the ID of the doctor administering the vaccination
   * @param patientId       the ID of the patient receiving the vaccination
   * @return a Vaccination object representing the created vaccination record
   * @throws SQLException if there is an error creating the vaccination record
   */
  public Vaccination create(String vaccinationName, NewDateTime dateTaken,
      boolean isRecommended, String comment, NewDateTime nextDoseDate,
      int doctorId, int patientId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO vaccination(vaccinationName, dateTaken, isRecommended, comment, nextDoseDate, doctorId, patientId)" +
              "VALUES(?, ?, ?, ?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS
      );

      statement.setString(1, vaccinationName);
      statement.setDate(2, Date.valueOf(LocalDate.of(dateTaken.getYear(), dateTaken.getMonth(), dateTaken.getDay())));
      statement.setBoolean(3, isRecommended);
      if (comment == null)
      {
        statement.setNull(4, Types.VARCHAR);
      }
      else
      {
        statement.setString(4, comment);
      }

      if (nextDoseDate == null) {
        statement.setNull(5, Types.TIMESTAMP);
      } else {
        statement.setDate(5, Date.valueOf(LocalDate.of(nextDoseDate.getYear(), nextDoseDate.getMonth(), nextDoseDate.getDay())));
      }

      statement.setInt(6, doctorId);
      statement.setInt(7, patientId);

      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      int generatedId = -1;
      if (keys.next())
      {
        generatedId = keys.getInt(1);
      }

      Vaccination vaccination = new Vaccination(vaccinationName, dateTaken, isRecommended, comment, nextDoseDate, doctorId, patientId);
      vaccination.setVaccinationId(generatedId);

      return vaccination;
    }
  }



  /**
   * Retrieves a vaccination record by its ID.
   *
   * @param vaccinationId the ID of the vaccination to retrieve
   * @return a Vaccination object representing the retrieved vaccination, or null if not found
   * @throws SQLException if there is an error executing the SQL statement
   */
  public Vaccination getVaccinationById(int vaccinationId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM vaccination WHERE vaccinationId = ?");
      statement.setInt(1, vaccinationId);

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String vaccinationName = resultSet.getString("vaccinationName");

        Timestamp dateTakenTimeStamp = resultSet.getTimestamp("dateTaken");
        NewDateTime dateTaken = new NewDateTime(dateTakenTimeStamp.getDay(),
            dateTakenTimeStamp.getMonth(), dateTakenTimeStamp.getYear(),
            dateTakenTimeStamp.getHours(), dateTakenTimeStamp.getMinutes());

        boolean isRecommended = resultSet.getBoolean("isRecommended");
        String comment = resultSet.getString("comment");

        Timestamp nextDoseDateTimestamp = resultSet.getTimestamp("nextDoseDate");
        NewDateTime nextDoseDate = null;
        if (nextDoseDateTimestamp != null)
        {
          nextDoseDate = new NewDateTime(
              nextDoseDateTimestamp.toLocalDateTime().getDayOfMonth(),
              nextDoseDateTimestamp.toLocalDateTime().getMonthValue(),
              nextDoseDateTimestamp.toLocalDateTime().getYear(),
              nextDoseDateTimestamp.toLocalDateTime().getHour(),
              nextDoseDateTimestamp.toLocalDateTime().getMinute()
          );
        }

        int doctorId = resultSet.getInt("doctorId");
        int patientId = resultSet.getInt("patientId");

        Vaccination vaccination;
        if (nextDoseDate == null) {
          vaccination = new Vaccination(vaccinationName, dateTaken, isRecommended, comment, doctorId, patientId);
        } else {
          vaccination = new Vaccination(vaccinationName, dateTaken, isRecommended, comment, nextDoseDate, doctorId, patientId);
        }

        return vaccination;
      }
    }
    return null;
  }


  /**
   * Retrieves all vaccinations for a specific patient by their ID.
   *
   * @param patientId the ID of the patient whose vaccinations are to be retrieved
   * @return a list of Vaccination objects representing the patient's vaccinations
   * @throws SQLException if there is an error executing the SQL statement
   */
  public List<Vaccination> getVaccinationByPatientId(int patientId) throws SQLException
  {
    List<Vaccination> vaccinations = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM vaccination WHERE patientId = ?");
      statement.setInt(1, patientId);

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        int vaccinationId = resultSet.getInt("vaccinationId");

        String vaccinationName = resultSet.getString("vaccinationName");
        Timestamp dateTakenTimestamp = resultSet.getTimestamp("dateTaken");
        NewDateTime dateTaken = new NewDateTime(
            dateTakenTimestamp.toLocalDateTime().getDayOfMonth(),
            dateTakenTimestamp.toLocalDateTime().getMonthValue(),
            dateTakenTimestamp.toLocalDateTime().getYear(),
            dateTakenTimestamp.toLocalDateTime().getHour(),
            dateTakenTimestamp.toLocalDateTime().getMinute()
        );
        boolean isRecommended = resultSet.getBoolean("isRecommended");
        String comment = resultSet.getString("comment");

        Timestamp nextDoseDateTimestamp = resultSet.getTimestamp("nextDoseDate");
        NewDateTime nextDoseDate = null;
        if (nextDoseDateTimestamp != null)
        {
          nextDoseDate = new NewDateTime(
              nextDoseDateTimestamp.toLocalDateTime().getDayOfMonth(),
              nextDoseDateTimestamp.toLocalDateTime().getMonthValue(),
              nextDoseDateTimestamp.toLocalDateTime().getYear(),
              nextDoseDateTimestamp.toLocalDateTime().getHour(),
              nextDoseDateTimestamp.toLocalDateTime().getMinute()
          );
        }

        int doctorId = resultSet.getInt("doctorId");

        Vaccination vaccination;
        if (nextDoseDate == null) {
          vaccination = new Vaccination(vaccinationName, dateTaken, isRecommended, comment, doctorId, patientId);
        } else {
          vaccination = new Vaccination(vaccinationName, dateTaken, isRecommended, comment, nextDoseDate, doctorId, patientId);
        }

        vaccination.setVaccinationId(vaccinationId);
        vaccinations.add(vaccination);
      }
    }
    return vaccinations;
  }

}
