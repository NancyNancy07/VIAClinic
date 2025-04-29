package viewModel.bookAppointment;

import java.time.LocalDate;

public class SharedData
{
  private int selectedDoctorId;
  public String consultationMode;
  private LocalDate appointmentDate;

  public void setSelectedDoctorId(int id)
  {
    this.selectedDoctorId = id;
  }

  public int getSelectedDoctorId()
  {
    return selectedDoctorId;
  }

  public String getConsultationMode()
  {
    return consultationMode;
  }

  public void setConsultationMode(String consultationMode)
  {
    this.consultationMode = consultationMode;
  }

  public LocalDate getAppointmentDate()
  {
    return appointmentDate;
  }

  public void setAppointmentDate(LocalDate appointmentDate)
  {
    this.appointmentDate = appointmentDate;
  }
}
