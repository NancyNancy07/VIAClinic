package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
