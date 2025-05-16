package server.model.patientJournal;

import java.sql.*;

public class DatabasePatientDAO
{
  private static DatabasePatientDAO instance;

  private DatabasePatientDAO() throws SQLException {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized DatabasePatientDAO getInstance() throws SQLException {
    if (instance == null) {
      instance = new DatabasePatientDAO();
    }
    return instance;
  }

  private static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=book_appointment",
        "postgres",
        "bangarang24"
    );
  }

  public DatabasePatient create(String firstName, String lastName, String email,
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

      DatabasePatient patient = new DatabasePatient(firstName, lastName, email, phoneNumber, userName, password, CPR, address);
      patient.setPatientID(generatedId);

      return patient;
    }
  }

  public DatabasePatient getPatientById(int patientId) throws SQLException
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

        DatabasePatient patient = new DatabasePatient(firstName, lastName, email, phoneNumber, userName, password, CPR, address);
        patient.setPatientID(patientId);

        return patient;
      }
    }
    return null;
  }
}
