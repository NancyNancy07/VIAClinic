package server.model.patientJournal;

import server.model.bookAppointment.Patient;

import java.sql.*;

/**
 * DatabasePatientDAO is a Data Access Object (DAO) for managing Patient entities in the database.
 * It provides methods to create and retrieve Patient records.
 */
public class DatabasePatientDAO
{
  private static DatabasePatientDAO instance;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * Registers the PostgreSQL driver for database connectivity.
   *
   * @throws SQLException if there is an error registering the driver
   */
  private DatabasePatientDAO() throws SQLException {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Returns the singleton instance of DatabasePatientDAO.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of DatabasePatientDAO
   * @throws SQLException if there is an error creating the connection
   */
  public static synchronized DatabasePatientDAO getInstance() throws SQLException {
    if (instance == null) {
      instance = new DatabasePatientDAO();
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
   * Creates a new Patient record in the database.
   *
   * @param firstName    the first name of the patient
   * @param lastName     the last name of the patient
   * @param email        the email address of the patient
   * @param phoneNumber  the phone number of the patient
   * @param userName     the username for the patient
   * @param password     the password for the patient
   * @param CPR          the CPR number of the patient
   * @param address      the address of the patient
   * @return a Patient object representing the created patient
   * @throws SQLException if there is an error executing the SQL statement
   */
  public Patient create(String firstName, String lastName, String email,
      String phoneNumber, String userName, String password, String CPR, Address address) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO patient (firstName, lastName, email, phoneNumber, cpr, addressId, userName, password) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS
      );

      statement.setString(1, firstName);
      statement.setString(2, lastName);
      statement.setString(3, email);
      statement.setString(4, phoneNumber);
      statement.setString(5, CPR);
      statement.setInt(6, address.getAddressId());
      statement.setString(7, userName);
      statement.setString(8, password);

      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      int generatedId = -1;
      if (keys.next()) {
        generatedId = keys.getInt(1);
      }

      Patient patient = new Patient(firstName, lastName, email, phoneNumber, userName, password, CPR, address);
      patient.setPatientID(generatedId);

      return patient;
    }
  }

  /**
   * Retrieves a Patient by their ID from the database.
   *
   * @param patientId the unique identifier of the patient
   * @return a Patient object representing the retrieved patient, or null if not found
   * @throws SQLException if there is an error executing the SQL statement
   */
  public Patient getPatientById(int patientId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM patient WHERE patientId = ?"
      );
      statement.setInt(1, patientId);

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phoneNumber");
        String userName = resultSet.getString("userName");
        String password = resultSet.getString("password");
        String CPR = resultSet.getString("cpr");
        int addressId = resultSet.getInt("addressId");

        Address address = AddressDAO.getInstance().getAddressById(addressId);

        Patient patient = new Patient(firstName, lastName, email, phoneNumber, userName, password, CPR, address);
        patient.setPatientID(patientId);

        return patient;
      }
    }
    return null;
  }
}
