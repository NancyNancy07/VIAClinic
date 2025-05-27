package server.model.patientJournal;

import server.model.bookAppointment.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PatientDAO is responsible for managing database operations related to patients.
 * It provides methods to retrieve patient information from the database.
 */
public class PatientDAO
{
  private static PatientDAO instance;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * Registers the PostgreSQL driver for database connectivity.
   */
  private PatientDAO() {}

  /**
   * Returns the singleton instance of PatientDAO.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of PatientDAO
   */
  public static synchronized PatientDAO getInstance()
  {
    if (instance == null)
    {
      instance = new PatientDAO();
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
   * Retrieves a patient by their username from the database.
   *
   * @param username the username of the patient
   * @return the Patient object if found, otherwise null
   * @throws SQLException if there is an error executing the query
   */
  public Patient getPatientByUsername(String username) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM Patient WHERE userName = ?");
      statement.setString(1, username);

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        return extractPatient(resultSet);
      }
    }
    return null;
  }

  /**
   * Retrieves a patient by their ID from the database.
   *
   * @param patientId the ID of the patient
   * @return the Patient object if found, otherwise null
   * @throws SQLException if there is an error executing the query
   */
  public Patient getPatientById(int patientId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM Patient WHERE patientId = ?");
      statement.setInt(1, patientId);

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        return extractPatient(resultSet);
      }
    }
    return null;
  }

  /**
   * Retrieves all patients from the database.
   *
   * @return a list of Patient objects
   * @throws SQLException if there is an error executing the query
   */
  public List<Patient> getAllPatients() throws SQLException
  {
    List<Patient> patients = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Patient");
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next())
      {
        patients.add(extractPatient(resultSet));
      }
    }
    return patients;
  }

  /**
   * Extracts a Patient object from the ResultSet.
   *
   * @param resultSet the ResultSet containing patient data
   * @return a Patient object with the extracted data
   * @throws SQLException if there is an error accessing the ResultSet
   */
  private Patient extractPatient(ResultSet resultSet) throws SQLException
  {
    int id = resultSet.getInt("patientId");
    String firstName = resultSet.getString("firstName");
    String lastName = resultSet.getString("lastName");
    String email = resultSet.getString("email");
    String phoneNumber = resultSet.getString("phoneNumber");
    String username = resultSet.getString("userName");
    String password = resultSet.getString("password");
    String cpr = resultSet.getString("cpr");
    int addressId = resultSet.getInt("addressId");

    Address address = AddressDAO.getInstance().getAddressById(addressId);

    return new Patient(id, firstName, lastName, email, phoneNumber, username, password, cpr, address);
  }

}
