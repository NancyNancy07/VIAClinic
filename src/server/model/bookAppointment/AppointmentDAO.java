package server.model.bookAppointment;

import java.sql.*;
import java.time.LocalDateTime;

public class AppointmentDAO
{
  private static AppointmentDAO instance;

  private AppointmentDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized AppointmentDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new AppointmentDAO();

    }
    return instance;
  }

  private static Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc",
        "postgres", "admin");
  }

  public Appointment create( NewDateTime dateTime,
      String mode, int patientID, Doctor doctor) throws SQLException
  {
    try (Connection connection = getConnection())
    {
            PreparedStatement statement =
            connection.prepareStatement(" INSERT INTO Appointment(dateTime,patientid,doctorid,mode) Values(?,?,?,?)");
            LocalDateTime localDateTime = LocalDateTime.of(dateTime.getYear(),
          dateTime.getMonth(), dateTime.getDay(), dateTime.getHour(),
          dateTime.getMinute());
            Timestamp.valueOf(localDateTime);
      statement.setTimestamp(1, Timestamp.valueOf(localDateTime));
      statement.setString(2, mode);
       statement.setInt(3, patientID);
      statement.setInt( 4, doctor.getDoctorID());
           
      statement.executeUpdate();
      return new Appointment(dateTime,patientID,doctor,mode );


    }
  }
}




