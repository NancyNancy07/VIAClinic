package client.viewModel.bookAppointment;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookAppointmentSharedData {
  private static BookAppointmentSharedData instance;

  private int selectedDoctorId;
  private String consultationMode;
  private LocalDate appointmentDate;
  private LocalTime appointmentTime;

  private BookAppointmentSharedData() {}

  public static synchronized BookAppointmentSharedData getInstance() {
    if (instance == null) {
      instance = new BookAppointmentSharedData();
    }
    return instance;
  }

  public void setSelectedDoctorId(int id) {
    this.selectedDoctorId = id;
  }

  public int getSelectedDoctorId() {
    return selectedDoctorId;
  }

  public String getConsultationMode() {
    return consultationMode;
  }

  public void setConsultationMode(String consultationMode) {
    this.consultationMode = consultationMode;
  }

  public LocalDate getAppointmentDate() {
    return appointmentDate;
  }

  public void setAppointmentDate(LocalDate appointmentDate) {
    this.appointmentDate = appointmentDate;
  }

  public LocalTime getAppointmentTime() {
    return appointmentTime;
  }

  public void setAppointmentTime(LocalTime appointmentTime) {
    this.appointmentTime = appointmentTime;
  }
}
