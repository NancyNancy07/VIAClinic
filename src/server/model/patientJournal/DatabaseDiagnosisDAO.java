package server.model.patientJournal;

import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseDiagnosisDAO is a Data Access Object for managing Diagnosis records in the database.
 * It provides methods to create a new diagnosis and retrieve diagnoses by patient ID.
 */
public class DatabaseDiagnosisDAO
{
  private static DatabaseDiagnosisDAO instance;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * Registers the PostgreSQL driver for database connectivity.
   *
   * @throws SQLException if there is an error registering the driver
   */
  private DatabaseDiagnosisDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Returns the singleton instance of DatabaseDiagnosisDAO.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of DatabaseDiagnosisDAO
   * @throws SQLException if there is an error creating the connection
   */
  public static synchronized DatabaseDiagnosisDAO getInstance()
      throws SQLException
  {
    if (instance == null)
    {
      instance = new DatabaseDiagnosisDAO();
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
   * Creates a new diagnosis record in the database.
   *
   * @param diagnosisName the name of the diagnosis
   * @param status        the status of the diagnosis
   * @param dateDiagnosed the date when the diagnosis was made
   * @param comment       additional comments about the diagnosis
   * @param doctor        the doctor who made the diagnosis
   * @param patient       the patient who received the diagnosis
   * @param prescription  the prescription associated with the diagnosis
   * @return a Diagnosis object representing the created diagnosis
   * @throws SQLException if there is an error executing the SQL statement
   */
  public Diagnosis create(String diagnosisName, String status,
      NewDateTime dateDiagnosed, String comment, Doctor doctor, Patient patient,
      Prescription prescription) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO diagnosis (diagnosisName, status, dateDiagnosed, comment, doctorId, patientId, prescriptionId) "
              + "VALUES (?, ?, ?, ?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS);

      statement.setString(1, diagnosisName);
      statement.setString(2, status);
      statement.setDate(3, Date.valueOf(
          LocalDate.of(dateDiagnosed.getYear(), dateDiagnosed.getMonth(),
              dateDiagnosed.getDay())));
      if (comment == null)
        statement.setNull(4, Types.VARCHAR);
      else
        statement.setString(4, comment);

      statement.setInt(5, doctor.getDoctorID());
      statement.setInt(6, patient.getPatientID());
      statement.setInt(7, prescription.getPrescriptionId());

      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      int generatedId = -1;
      if (keys.next())
      {
        generatedId = keys.getInt(1);
      }

      Diagnosis diagnosis = new Diagnosis(diagnosisName, status, dateDiagnosed,
          comment, doctor.getDoctorID(), patient.getPatientID(), prescription);
      diagnosis.setDiagnosisId(generatedId);

      return diagnosis;
    }
  }

  /**
   * Retrieves a list of diagnoses for a specific patient by their ID.
   *
   * @param patientId the ID of the patient whose diagnoses are to be retrieved
   * @return a List of Diagnosis objects associated with the specified patient
   * @throws SQLException if there is an error executing the SQL statement
   */
  public List<Diagnosis> getByPatientId(int patientId) throws SQLException
  {
    System.out.println(patientId);
    List<Diagnosis> list = new ArrayList<>();

    try (Connection connection = getConnection())
    {
      PreparedStatement stmt = connection.prepareStatement(
          "SELECT * FROM Diagnosis WHERE patientid= ?");
      stmt.setInt(1, patientId);

      ResultSet rs = stmt.executeQuery();
      while (rs.next())
      {
        String diagnosisName = rs.getString("diagnosisName");
        String status = rs.getString("status");
        LocalDate date = rs.getDate("dateDiagnosed").toLocalDate();
        String comment = rs.getString("comment");
        int doctorId = rs.getInt("doctorId");
        int pid = rs.getInt("patientId");

        int prescriptionId = rs.getInt("prescriptionId");
        Prescription prescription = null;
        if (!rs.wasNull()) {
          prescription = PrescriptionDAO.getInstance().getPrescriptionById(prescriptionId);
        }
        Diagnosis d = new Diagnosis(diagnosisName, status,
            new NewDateTime(date.getDayOfMonth(), date.getMonthValue(),
                date.getYear(), 0, 0), comment, doctorId, pid, prescription
            // no Prescription for now
        );

        d.setDiagnosisId(rs.getInt("diagnosisId"));

        list.add(d);
      }
    }

    return list;
  }

}
