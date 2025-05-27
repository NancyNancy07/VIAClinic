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
public class ReferralDAO
{
  private static ReferralDAO instance;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * Registers the PostgreSQL driver for database connectivity.
   */
  private ReferralDAO()
  {

  }

  /**
   * Returns the singleton instance of ReferralDAO.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of ReferralDAO
   */
  public static synchronized ReferralDAO getInstance()
  {
    if (instance == null)
    {
      instance = new ReferralDAO();
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
   * Creates a new referral in the database.
   *
   * @param date       the date and time of the referral
   * @param reason     the reason for the referral
   * @param comment    additional comments about the referral (can be null)
   * @param doctorId   the ID of the doctor making the referral
   * @param patientId  the ID of the patient being referred
   * @return a Referral object representing the created referral
   * @throws SQLException if there is an error executing the SQL statement
   */
  public Referral create(NewDateTime date, String reason, String comment,
      int doctorId, int patientId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO referral (dateCreated, reason, comment, doctorId, patientId)"
              + "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

      statement.setDate(1, Date.valueOf(
          LocalDate.of(date.getDay(), date.getMonth(), date.getYear())));
      statement.setString(2, reason);

      if (comment == null)
      {
        statement.setNull(3, Types.VARCHAR);
      }
      else
      {
        statement.setString(3, comment);
      }

      statement.setInt(4, doctorId);
      statement.setInt(5, patientId);

      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      int generatedId = -1;
      if (keys.next())
      {
        generatedId = keys.getInt(1);
      }

      Referral referral = new Referral(date, reason, comment, doctorId,
          patientId);
      referral.setReferralId(generatedId);

      return referral;
    }
  }


  /**
   * Retrieves a list of referrals for a specific patient by their ID.
   *
   * @param patientId the ID of the patient whose referrals are to be retrieved
   * @return a List of Referral objects associated with the specified patient
   * @throws SQLException if there is an error executing the SQL statement
   */
  public List<Referral> getReferralsByPatientId(int patientId)
      throws SQLException
  {
    List<Referral> referrals = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM referral WHERE patientId = ?");
      statement.setInt(1, patientId);

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        int referralId = resultSet.getInt("referralId");
        String referralReason = resultSet.getString("reason");
        Timestamp dateCreatedTimeStamp = resultSet.getTimestamp("dateCreated");
        NewDateTime date = new NewDateTime(
            dateCreatedTimeStamp.toLocalDateTime().getDayOfMonth(),
            dateCreatedTimeStamp.toLocalDateTime().getMonthValue(),
            dateCreatedTimeStamp.toLocalDateTime().getYear(),
            dateCreatedTimeStamp.toLocalDateTime().getHour(),
            dateCreatedTimeStamp.toLocalDateTime().getMinute());
        String comment = resultSet.getString("comment");
        int doctorId = resultSet.getInt("doctorId");

        Referral referral = new Referral(date, referralReason, comment,
            doctorId, patientId);

        referral.setReferralId(referralId);

        referrals.add(referral);
      }
    }
    return referrals;
  }

}
