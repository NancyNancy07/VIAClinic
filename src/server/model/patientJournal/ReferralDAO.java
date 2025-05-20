package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

import java.sql.*;
import java.time.LocalDate;

public class ReferralDAO
{
  private static ReferralDAO instance;

  private ReferralDAO()
  {

  }

  public static synchronized ReferralDAO getInstance()
  {
    if (instance == null)
    {
      instance = new ReferralDAO();
    }
    return instance;
  }

  private static Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=book_appointment",
        "postgres", "Via@123");
  }

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
}
