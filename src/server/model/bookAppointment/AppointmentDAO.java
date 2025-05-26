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
          "INSERT INTO Appointment(dateTime,patientid,doctorid,mode) VALUES(?,?,?,?)",
          Statement.RETURN_GENERATED_KEYS);
      LocalDateTime localDateTime = LocalDateTime.of(dateTime.getYear(),
          dateTime.getMonth(), dateTime.getDay(), dateTime.getHour(),
          dateTime.getMinute());
      Timestamp.valueOf(localDateTime);
      statement.setTimestamp(1, Timestamp.valueOf(localDateTime));
      statement.setInt(2, patientID);
      statement.setInt(3, doctor.getDoctorID());
      statement.setString(4, mode);

      statement.executeUpdate();

      ResultSet rs = statement.getGeneratedKeys();
      int generatedId = -1;
      if (rs.next())
      {
        generatedId = rs.getInt(1);
      }
      Appointment appointment = new Appointment(dateTime, patientID, doctor,
          mode);
      appointment.setAppointmentID(generatedId);
      return appointment;
    }
  }

  public List<Appointment> getAppointmentsByPatientId(int patientId)
      throws SQLException
  {
    List<Appointment> appointments = new ArrayList<>();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT appointmentId, dateTime, patientId, doctorId, mode FROM Appointment WHERE patientId = ?");
      statement.setInt(1, patientId);

      ResultSet rs = statement.executeQuery();
      while (rs.next())
      {
        int appointmentId = rs.getInt("appointmentId");
        Timestamp timestamp = rs.getTimestamp("dateTime");
        int doctorId = rs.getInt("doctorId");
        String mode = rs.getString("mode");

        LocalDateTime ldt = timestamp.toLocalDateTime();
        NewDateTime dateTime = new NewDateTime(ldt.getDayOfMonth(),
            ldt.getMonthValue(), ldt.getYear(), ldt.getHour(), ldt.getMinute());

        Doctor doctor = DoctorDAO.getInstance().getDoctorById(doctorId);
        Appointment appointment = new Appointment(dateTime, patientId, doctor,
            mode);
        appointment.setAppointmentID(appointmentId);
        appointments.add(appointment);
      }
    }

    return appointments;
  }

  public List<Appointment> getAppointmentsByDoctorId(int doctorId)
      throws SQLException
  {
    List<Appointment> appointments = new ArrayList<>();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT dateTime, patientid, doctorid, mode FROM Appointment WHERE doctorid = ?");
      statement.setInt(1, doctorId);

      ResultSet rs = statement.executeQuery();
      while (rs.next())
      {
        Timestamp timestamp = rs.getTimestamp("dateTime");
        int patientId = rs.getInt("patientid");
        String mode = rs.getString("mode");

        LocalDateTime ldt = timestamp.toLocalDateTime();
        NewDateTime dateTime = new NewDateTime(ldt.getDayOfMonth(),
            ldt.getMonthValue(), ldt.getYear(), ldt.getHour(), ldt.getMinute());

        Doctor doctor = new Doctor(doctorId, null, null, null, null, null,
            null);
        appointments.add(new Appointment(dateTime, patientId, doctor, mode));
      }
    }

    return appointments;
  }

  public Appointment updateAppointment(int appointmentId,
      NewDateTime newDateTime, String newMode, int newDoctorId)
      throws SQLException
  {
    String sql = "UPDATE Appointment SET dateTime = ?, mode = ?, doctorid = ? WHERE appointmentid = ?";

    try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql))
    {
      LocalDateTime localDateTime = LocalDateTime.of(newDateTime.getYear(),
          newDateTime.getMonth(), newDateTime.getDay(), newDateTime.getHour(),
          newDateTime.getMinute());

      statement.setTimestamp(1, Timestamp.valueOf(localDateTime));
      statement.setString(2, newMode);
      statement.setInt(3, newDoctorId);
      statement.setInt(4, appointmentId);

      int rowsUpdated = statement.executeUpdate();

      if (rowsUpdated > 0)
      {
        String fetchSql = "SELECT dateTime, patientid, doctorid, mode FROM Appointment WHERE appointmentid = ?";
        try (PreparedStatement fetchStatement = connection.prepareStatement(
            fetchSql))
        {
          fetchStatement.setInt(1, appointmentId);
          ResultSet rs = fetchStatement.executeQuery();
          if (rs.next())
          {
            Timestamp timestamp = rs.getTimestamp("dateTime");
            int patientId = rs.getInt("patientid");
            String mode = rs.getString("mode");
            int doctorId = rs.getInt("doctorid");

            LocalDateTime ldt = timestamp.toLocalDateTime();
            NewDateTime dateTime = new NewDateTime(ldt.getDayOfMonth(),
                ldt.getMonthValue(), ldt.getYear(), ldt.getHour(),
                ldt.getMinute());

            Doctor doctor = DoctorDAO.getInstance().getDoctorById(doctorId);

            Appointment updatedAppointment = new Appointment(dateTime,
                patientId, doctor, mode);
            updatedAppointment.setAppointmentID(appointmentId);
            return updatedAppointment;
          }
        }
      }

      return null;
    }
  }

}


