package server.model.patientJournal;

import server.model.bookAppointment.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO
{
  private static PatientDAO instance;

  private PatientDAO() {}

  public static synchronized PatientDAO getInstance()
  {
    if (instance == null)
    {
      instance = new PatientDAO();
    }
    return instance;
  }

  private static Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=book_appointment",
        "postgres", "Via@123");
  }

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
