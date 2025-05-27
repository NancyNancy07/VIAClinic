package server.model.patientJournal;

import server.model.bookAppointment.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO
{
  private static DoctorDAO instance;

  private DoctorDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized DoctorDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new DoctorDAO();
    }
    return instance;
  }

  private static Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=book_appointment",
        "postgres", "Via@123");
  }

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

  public Doctor getDoctorByUsername(String username) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM Doctor WHERE userName = ?");
      statement.setString(1, username);

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        return extractDoctor(resultSet);
      }
    }
    return null;
  }

  public Doctor getDoctorById(int doctorId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM Doctor WHERE doctorId = ?");
      statement.setInt(1, doctorId);

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        return extractDoctor(resultSet);
      }
    }
    return null;
  }

  public List<Doctor> getAllDoctors() throws SQLException
  {
    List<Doctor> doctors = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Doctor");
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next())
      {
        doctors.add(extractDoctor(resultSet));
      }
    }
    return doctors;
  }

  private Doctor extractDoctor(ResultSet resultSet) throws SQLException
  {
    int doctorId = resultSet.getInt("doctorId");
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
