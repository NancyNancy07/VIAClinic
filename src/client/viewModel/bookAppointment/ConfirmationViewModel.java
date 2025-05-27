package client.viewModel.bookAppointment;

import client.model.clientBookAppointment.ClientAppointment;
import client.model.clientBookAppointment.ClientDoctor;
import client.model.clientBookAppointment.ClientNewDateTime;
import client.viewModel.loginSystem.LoginSharedData;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * ConfirmationViewModel handles the logic for confirming an appointment in the booking system.
 * It retrieves the selected doctor, consultation mode, appointment date, and time,
 * and confirms the appointment by interacting with the front view model.
 */
public class ConfirmationViewModel
{
  private final BookAppointmentSharedData sharedData = BookAppointmentSharedData.getInstance();
  private final BookAppointmentFrontViewModel frontViewModel;
  private final SelectDoctorViewModel selectDoctorViewModel;

  /**
   * Constructor for ConfirmationViewModel.
   * Initializes the view model with the provided front view model and select doctor view model.
   *
   * @param frontViewModel the front view model for booking appointments
   * @param selectDoctorViewModel the view model for selecting doctors
   */
  public ConfirmationViewModel(BookAppointmentFrontViewModel frontViewModel,
      SelectDoctorViewModel selectDoctorViewModel)
  {
    this.frontViewModel = frontViewModel;
    this.selectDoctorViewModel = selectDoctorViewModel;
  }

  /**
   * Retrieves the selected doctor from the shared data.
   *
   * @return the selected ClientDoctor, or null if no doctor is selected
   */
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

  /**
   * Retrieves the consultation mode from the shared data.
   *
   * @return the consultation mode as a String, or null if not set
   */
  public String getConsultationMode()
  {
    return sharedData.getConsultationMode();
  }

  /**
   * Retrieves the appointment date from the shared data.
   *
   * @return the appointment date as a LocalDate, or null if not set
   */
  public LocalDate getAppointmentDate()
  {
    return sharedData.getAppointmentDate();
  }

  /**
   * Retrieves the appointment time from the shared data.
   *
   * @return the appointment time as a LocalTime, or null if not set
   */
  public LocalTime getAppointmentTime()
  {
    return sharedData.getAppointmentTime();
  }

  /**
   * Confirms the appointment by creating a new ClientAppointment with the selected doctor,
   * consultation mode, appointment date, and time.
   *
   * @return true if the appointment was successfully confirmed, false otherwise
   */
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
