package client.viewModel.bookAppointment;

import client.viewModel.loginSystem.LoginSharedData;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.NewDateTime;
import server.model.bookAppointment.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ConfirmationViewModel
{
  private final BookAppointmentSharedData sharedData = BookAppointmentSharedData.getInstance();
  private final BookAppointmentFrontViewModel frontViewModel;
  private final SelectDoctorViewModel selectDoctorViewModel;

  public ConfirmationViewModel(BookAppointmentFrontViewModel frontViewModel,
      SelectDoctorViewModel selectDoctorViewModel)
  {
    this.frontViewModel = frontViewModel;
    this.selectDoctorViewModel = selectDoctorViewModel;
  }

  public Doctor getSelectedDoctor()
  {
    int selectedDoctorId = sharedData.getSelectedDoctorId();
    List<Doctor> doctors = selectDoctorViewModel.getDoctorList();
    for (Doctor doctor : doctors)
    {
      if (doctor.getDoctorID() == selectedDoctorId)
      {
        return doctor;
      }
    }
    return null;
  }

  public String getConsultationMode()
  {
    return sharedData.getConsultationMode();
  }

  public LocalDate getAppointmentDate()
  {
    return sharedData.getAppointmentDate();
  }

  public LocalTime getAppointmentTime()
  {
    return sharedData.getAppointmentTime();
  }

  public boolean confirmAppointment()
  {
    Doctor doctor = getSelectedDoctor();
    if (doctor == null || getConsultationMode() == null
        || getAppointmentDate() == null || getAppointmentTime() == null)
      return false;

    NewDateTime newDateTime = new NewDateTime(
        getAppointmentDate().getDayOfMonth(),
        getAppointmentDate().getMonthValue(), getAppointmentDate().getYear(),
        getAppointmentTime().getHour(), getAppointmentTime().getMinute());

    int patientId = LoginSharedData.getInstance().getId();

    Appointment appointment = frontViewModel.addAppointment(newDateTime,
        patientId, doctor, getConsultationMode());
    return appointment != null;
  }
}
