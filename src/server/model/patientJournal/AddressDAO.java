package server.model.patientJournal;

import java.sql.*;

public class AddressDAO
{
  private static AddressDAO instance;

  private AddressDAO() throws SQLException {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized AddressDAO getInstance() throws SQLException {
    if (instance == null) {
      instance = new AddressDAO();
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

  public Address create(String city, String postCode, String street) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO address (city, postCode, street) VALUES (?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS
      );

      statement.setString(1, city);
      statement.setString(2, postCode);
      statement.setString(3, street);

      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      int generatedId = -1;
      if (keys.next()) {
        generatedId = keys.getInt(1);
      }

      return new Address(generatedId, city, postCode, street);
    }
  }

  public Address getAddressById(int addressId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM address WHERE addressId = ?"
      );
      statement.setInt(1, addressId);

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String city = resultSet.getString("city");
        String postCode = resultSet.getString("postCode");
        String street = resultSet.getString("street");
        return new Address(addressId, city, postCode, street);
      }
    }
    return null;
  }
}
