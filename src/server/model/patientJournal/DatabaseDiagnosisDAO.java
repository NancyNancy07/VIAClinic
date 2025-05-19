package server.model.patientJournal;

import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDiagnosisDAO
{
  private static DatabaseDiagnosisDAO instance;

  private DatabaseDiagnosisDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized DatabaseDiagnosisDAO getInstance()
      throws SQLException
  {
    if (instance == null)
    {
      instance = new DatabaseDiagnosisDAO();
    }
    return instance;
  }

  private static Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=book_appointment",
        "postgres", "Via@123");
  }

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

  public List<Diagnosis> getByPatientId(int patientId) throws SQLException
  {
    System.out.println(patientId);
    List<Diagnosis> list = new ArrayList<>();

    try (Connection connection = getConnection())
    {
      PreparedStatement stmt = connection.prepareStatement(
          "SELECT * FROM Diagnosis WHERE \"patientId\" = ?");
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

        Diagnosis d = new Diagnosis(diagnosisName, status,
            new NewDateTime(date.getYear(), date.getMonthValue(),
                date.getDayOfMonth(), 0, 0), comment, doctorId, pid, null
            // no Prescription for now
        );

        d.setDiagnosisId(rs.getInt("diagnosisId"));

        list.add(d);
      }
    }

    return list;
  }

}
