package client.viewModel.bookAppointment;

import java.time.LocalDate;

public class SharedData {
  private int selectedDoctorId;
  public String consultationMode;
  private LocalDate appointmentDate;

  public SharedData() {
  }

  public void setSelectedDoctorId(int id) {
    this.selectedDoctorId = id;
  }

  public int getSelectedDoctorId() {
    return this.selectedDoctorId;
  }

  public String getConsultationMode() {
    return this.consultationMode;
  }

  public void setConsultationMode(String consultationMode) {
    this.consultationMode = consultationMode;
  }

  public LocalDate getAppointmentDate() {
    return this.appointmentDate;
  }

  public void setAppointmentDate(LocalDate appointmentDate) {
    this.appointmentDate = appointmentDate;
  }
}
