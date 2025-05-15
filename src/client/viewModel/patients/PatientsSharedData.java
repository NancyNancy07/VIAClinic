package client.viewModel.patients;

import client.viewModel.loginSystem.LoginSharedData;
import server.model.bookAppointment.NewDateTime;

public class PatientsSharedData {
  private static PatientsSharedData instance;

  private String patientName;
  private String diagnosis;

  // Private constructor to prevent instantiation
  private PatientsSharedData() {}

  // Synchronized for thread safety
  public static synchronized PatientsSharedData getInstance() {
    if (instance == null) {
      instance = new PatientsSharedData();
    }
    return instance;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public String getPatientName() {
    return patientName;
  }

  public void setDiagnosis(String diagnosis, String status, NewDateTime date, String prescription) {
    this.diagnosis = diagnosis;
    // Optionally store status, date, or prescription if needed later
  }

  public String getDiagnosis() {
    return diagnosis;
  }

  public int getDoctorId() {
    return LoginSharedData.getInstance().getId();
  }
}
