package server.model.patientJournal;

import server.model.bookAppointment.Doctor;

import java.sql.*;

/**
 * DatabaseDoctorDAO is a Data Access Object for managing Doctor records in the database.
 * It provides methods to create and retrieve Doctor objects.
 */
public class DatabaseDoctorDAO
{
  private static DatabaseDoctorDAO instance;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * Registers the PostgreSQL driver for database connectivity.
   *
   * @throws SQLException if there is an error registering the driver
   */
  private DatabaseDoctorDAO() throws SQLException {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Returns the singleton instance of DatabaseDoctorDAO.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of DatabaseDoctorDAO
   * @throws SQLException if there is an error creating the connection
   */
  public static synchronized DatabaseDoctorDAO getInstance() throws SQLException {
    if (instance == null) {
      instance = new DatabaseDoctorDAO();
    }
    return instance;
  }

  /**
   * Establishes a connection to the PostgreSQL database.
   *
   * @return a Connection object to the database
   * @throws SQLException if there is an error connecting to the database
   */
  private static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=book_appointment",
        "postgres",
        "Via@123"
    );
  }

  /**
   * Creates a new doctor in the database.
   *
   * @param firstName   the first name of the doctor
   * @param lastName    the last name of the doctor
   * @param email       the email address of the doctor
   * @param phoneNumber the phone number of the doctor
   * @param userName    the username for the doctor
   * @param password    the password for the doctor
   * @return a Doctor object representing the created doctor
   * @throws SQLException if there is an error executing the SQL statement
   */
  public Doctor create(String firstName, String lastName, String email,
      String phoneNumber, String userName, String password) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO doctor (firstName, lastName, email, phoneNumber, userName, password) " +
              "VALUES (?, ?, ?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS
      );

      statement.setString(1, firstName);
      statement.setString(2, lastName);
      statement.setString(3, email);
      statement.setString(4, phoneNumber);
      statement.setString(5, userName);
      statement.setString(6, password);

      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      int generatedId = -1;
      if (keys.next()) {
        generatedId = keys.getInt(1);
      }

      Doctor doctor = new Doctor(firstName, lastName, email, phoneNumber, userName, password);
      doctor.setDoctorID(generatedId);

      return doctor;
    }
  }

  /**
   * Retrieves a doctor by their ID from the database.
   *
   * @param doctorId the ID of the doctor to retrieve
   * @return a Doctor object representing the retrieved doctor, or null if not found
   * @throws SQLException if there is an error executing the SQL statement
   */
  public Doctor getDoctorById(int doctorId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM doctor WHERE doctorId = ?"
      );
      statement.setInt(1, doctorId);

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phoneNumber");
        String userName = resultSet.getString("userName");
        String password = resultSet.getString("password");

        Doctor doctor = new Doctor(firstName, lastName, email, phoneNumber, userName, password);
        doctor.setDoctorID(doctorId);

        return doctor;
      }
    }
    return null;
  }
}
