package server.model.bookAppointment;

import server.model.patientJournal.DoctorDAO;
import server.model.patientJournal.PatientDAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * AppointmentDAO is responsible for managing database operations related to
 * appointments. It provides methods to create, retrieve, and update appointments
 * in the database.
 */
public class AppointmentDAO
{
  private static AppointmentDAO instance;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * Registers the PostgreSQL driver for database connections.
   *
   * @throws SQLException if there is an error registering the driver
   */
  private AppointmentDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Returns the singleton instance of AppointmentDAO.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of AppointmentDAO
   * @throws SQLException if there is an error creating the instance
   */
  public static synchronized AppointmentDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new AppointmentDAO();

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
   * Creates a new appointment in the database.
   *
   * @param dateTime   the date and time of the appointment
   * @param mode       the mode of the appointment (e.g., in-person, online)
   * @param patientID  the ID of the patient for whom the appointment is created
   * @param doctor     the Doctor object representing the doctor for the appointment
   * @return an Appointment object representing the created appointment
   * @throws SQLException if there is an error creating the appointment
   */
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

  /**
   * Retrieves a list of appointments for a specific patient by their ID.
   *
   * @param patientId the ID of the patient whose appointments are to be retrieved
   * @return a List of Appointment objects for the specified patient
   * @throws SQLException if there is an error retrieving the appointments
   */
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
        Patient patient1 = PatientDAO.getInstance().getPatientById(patientId);
        appointment.setPatient(patient1);
        appointment.setAppointmentID(appointmentId);
        appointments.add(appointment);
      }
    }

    return appointments;
  }

  /**
   * Retrieves a list of appointments for a specific doctor by their ID.
   *
   * @param doctorId the ID of the doctor whose appointments are to be retrieved
   * @return a List of Appointment objects for the specified doctor
   * @throws SQLException if there is an error retrieving the appointments
   */
  public List<Appointment> getAppointmentsByDoctorId(int doctorId)
      throws SQLException
  {
    List<Appointment> appointments = new ArrayList<>();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT appointmentId, dateTime, patientId, doctorId, mode FROM Appointment WHERE doctorId = ?");
      statement.setInt(1, doctorId);

      ResultSet rs = statement.executeQuery();
      while (rs.next())
      {
        int appointmentId = rs.getInt("appointmentId");
        Timestamp timestamp = rs.getTimestamp("dateTime");
        int patientId = rs.getInt("patientId");
        String mode = rs.getString("mode");

        LocalDateTime ldt = timestamp.toLocalDateTime();
        NewDateTime dateTime = new NewDateTime(ldt.getDayOfMonth(),
            ldt.getMonthValue(), ldt.getYear(), ldt.getHour(), ldt.getMinute());

        Doctor doctor = new Doctor(doctorId, null, null, null, null, null,
            null);

        Appointment appointment = new Appointment(dateTime, patientId, doctor,
            mode);
        Patient patient = PatientDAO.getInstance().getPatientById(patientId);
        appointment.setPatient(patient);
        appointment.setAppointmentID(appointmentId);

        appointments.add(appointment);
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

  /**
   * Updates an existing appointment in the database.
   *
   * @param appointmentId the ID of the appointment to be updated
   * @param newDateTime   the new date and time for the appointment
   * @param newMode       the new mode for the appointment
   * @param newDoctorId   the ID of the doctor for the appointment
   * @return an Appointment object representing the updated appointment, or null if no update occurred
   * @throws SQLException if there is an error updating the appointment
   */
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
            Patient patient = PatientDAO.getInstance()
                .getPatientById(patientId);
            updatedAppointment.setPatient(patient);
            updatedAppointment.setAppointmentID(appointmentId);
            return updatedAppointment;
          }
        }
      }

      return null;
    }
  }

}
