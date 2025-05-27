package server.model.patientJournal;

import java.sql.*;

/**
 * AddressDAO is responsible for managing database operations related to addresses.
 * It provides methods to create and retrieve addresses in the database.
 */
public class AddressDAO
{
  private static AddressDAO instance;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * Registers the PostgreSQL driver for database connectivity.
   *
   * @throws SQLException if there is an error registering the driver
   */
  private AddressDAO() throws SQLException {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Returns the singleton instance of AddressDAO.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of AddressDAO
   * @throws SQLException if there is an error creating the connection
   */
  public static synchronized AddressDAO getInstance() throws SQLException {
    if (instance == null) {
      instance = new AddressDAO();
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
   * Creates a new address in the database.
   *
   * @param city     the city of the address
   * @param postCode the postal code of the address
   * @param street   the street of the address
   * @return an Address object representing the created address
   * @throws SQLException if there is an error executing the SQL statement
   */
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

  /**
   * Retrieves an address by its ID from the database.
   *
   * @param addressId the ID of the address to retrieve
   * @return an Address object representing the retrieved address, or null if not found
   * @throws SQLException if there is an error executing the SQL statement
   */
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
