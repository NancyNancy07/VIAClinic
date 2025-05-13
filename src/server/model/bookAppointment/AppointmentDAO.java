package server.model.bookAppointment;

import java.sql.*;

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


  public Appointment create(DateTime dateTime, int appointmentID, int doctorID,
      String mode, int patientID, DoctorList doctorList) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement =
      connection.prepareStatement(" INSERT INTO Appointment(DateTime,appointment_id,doctor_id,patient_id,doctorlist,mode) Values(?,?,?,?,?,?)");
      statement.setInt(1, appointmentID);
      statement.setTimestamp(2, (DateTime));
      statement.setInt(3, doctorID);
      statement.setInt(4, patientID);
      statement.setString(5, mode);
statement.executeUpdate();
return new Appointment(dateTime, appointmentID, doctorID, mode, patientID, doctorList);
    }
  }
  }




