package client.viewModel.bookAppointment;

import client.model.clientBookAppointment.ClientAppointment;
import client.model.clientBookAppointment.ClientDoctor;
import client.model.clientBookAppointment.ClientNewDateTime;
import client.viewModel.loginSystem.LoginSharedData;

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

  public ClientDoctor getSelectedDoctor()
  {
    int selectedDoctorId = sharedData.getSelectedDoctorId();
    List<ClientDoctor> doctors = selectDoctorViewModel.getDoctorList();
    for (ClientDoctor doctor : doctors)
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
    ClientDoctor doctor = getSelectedDoctor();
    if (doctor == null || getConsultationMode() == null
        || getAppointmentDate() == null || getAppointmentTime() == null)
      return false;

    ClientNewDateTime newDateTime = new ClientNewDateTime(
        getAppointmentDate().getDayOfMonth(),
        getAppointmentDate().getMonthValue(), getAppointmentDate().getYear(),
        getAppointmentTime().getHour(), getAppointmentTime().getMinute());

    int patientId = LoginSharedData.getInstance().getId();

    ClientAppointment appointment = frontViewModel.addAppointment(newDateTime,
        patientId, doctor, getConsultationMode());
    return appointment != null;
  }
}
