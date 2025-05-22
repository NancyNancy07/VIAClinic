package server.model.patientJournal;

import server.model.bookAppointment.NewDateTime;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VaccinationDAO
{
  private static VaccinationDAO instance;


  private VaccinationDAO()
  {
  }


  public static synchronized VaccinationDAO getInstance()
  {
    if (instance == null)
    {
      instance = new VaccinationDAO();
    }
    return instance;
  }


  private static Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=book_appointment",
        "postgres", "Via@123");
  }


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
