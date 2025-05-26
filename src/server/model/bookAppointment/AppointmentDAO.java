package server.model.bookAppointment;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=book_appointment",
        "postgres", "Via@123");
  }

  public Appointment create(NewDateTime dateTime, String mode, int patientID,
      Doctor doctor) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          " INSERT INTO Appointment(dateTime,patientid,doctorid,mode) Values(?,?,?,?)");
      LocalDateTime localDateTime = LocalDateTime.of(dateTime.getYear(),
          dateTime.getMonth(), dateTime.getDay(), dateTime.getHour(),
          dateTime.getMinute());
      Timestamp.valueOf(localDateTime);
      statement.setTimestamp(1, Timestamp.valueOf(localDateTime));
      statement.setInt(2, patientID);
      statement.setInt(3, doctor.getDoctorID());
      statement.setString(4, mode);

      statement.executeUpdate();
      return new Appointment(dateTime, patientID, doctor, mode);
    }
  }

  public List<Appointment> getAppointmentsByPatientId(int patientId)
      throws SQLException
  {
    List<Appointment> appointments = new ArrayList<>();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT dateTime, patientid, doctorid, mode FROM Appointment WHERE patientid = ?");
      statement.setInt(1, patientId);

      ResultSet rs = statement.executeQuery();
      while (rs.next())
      {
        Timestamp timestamp = rs.getTimestamp("dateTime");
        int doctorId = rs.getInt("doctorid");
        String mode = rs.getString("mode");

        LocalDateTime ldt = timestamp.toLocalDateTime();
        NewDateTime dateTime = new NewDateTime(ldt.getDayOfMonth(),
            ldt.getMonthValue(), ldt.getYear(), ldt.getHour(), ldt.getMinute());

        // Create Appointment with doctorId only (doctor object can be null or placeholder)
        Doctor doctor = new Doctor(doctorId, null, null, null, null, null,
            null);
        appointments.add(new Appointment(dateTime, patientId, doctor, mode));
      }
    }

    return appointments;
  }

  public boolean deleteAppointment(int appointmentId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM Appointment WHERE appointmentId = ?");
      statement.setInt(1, appointmentId);
      int affectedRows = statement.executeUpdate();
      return affectedRows == 1;
    }
  }

}




