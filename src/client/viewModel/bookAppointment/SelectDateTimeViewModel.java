package client.viewModel.bookAppointment;

import client.clientNetwork.PatientAppointmentClient;
import client.model.clientBookAppointment.ClientDoctor;
import client.model.clientBookAppointment.ClientDoctorList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * SelectDateTimeViewModel is responsible for managing the date and time selection
 * for booking an appointment. It interacts with the shared data model to set and
 * retrieve appointment details.
 */
public class SelectDateTimeViewModel
{
  private final BookAppointmentSharedData sharedData = BookAppointmentSharedData.getInstance();

  /**
   * Sets the LocalDate for the appointment.
   * @param date
   */
  public void setDate(LocalDate date)
  {
    sharedData.setAppointmentDate(date);
  }

  /**
   * Sets the appointment time from a string in "HH:mm" format.
   * @param time the time string to set
   */
  public void setTime(String time)
  {
    if (time != null)
    {
      sharedData.setAppointmentTime(
          LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")));
    }
  }

  /**
   * Gets the currently selected date for the appointment.
   * @return the selected LocalDate
   */
  public LocalDate getDate()
  {
    return sharedData.getAppointmentDate();
  }

  /**
   * Gets the currently selected time for the appointment.
   * @return the selected LocalTime
   */
  public LocalTime getTime()
  {
    return sharedData.getAppointmentTime();
  }

  /**
   * Gets the consultation mode for the appointment.
   * @return the consultation mode as a String
   */
  public String getMode()
  {
    return sharedData.getConsultationMode();
  }

  /**
   * Gets the ID of the selected doctor for the appointment.
   * @return the selected doctor's ID
   */
  public int getSelectedDoctorId()
  {
    return sharedData.getSelectedDoctorId();
  }

  /**
   * Gets the name of the selected doctor for the appointment.
   * @return the full name of the selected doctor
   */
  public String getDoctorName()
  {
    PatientAppointmentClient client = new PatientAppointmentClient();
    ClientDoctorList doctors = client.getDoctorList();
    for (ClientDoctor doc : doctors.getAllDoctors())
    {
      if (doc.getDoctorID() == sharedData.getSelectedDoctorId())
      {
        return doc.getFirstName() + " " + doc.getLastName();
      }
    }
    return "Unknown";
  }

  /**
   * Generates a list of time slots with a 15-minute gap between each slot.
   * The slots are generated for the specified start and end hours.
   *
   * @param startHour the starting hour (0-23)
   * @param endHour the ending hour (0-23)
   * @return a list of time slots in "HH:mm" format
   */
  public List<String> generateTimeSlotsWith15MinGap(int startHour, int endHour)
  {
    List<String> timeSlots = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    for (int hour = startHour; hour <= endHour; hour++)
    {
      for (int minute = 0; minute < 60; minute += 15)
      {
        LocalTime time = LocalTime.of(hour, minute);
        timeSlots.add(time.format(formatter));
      }
    }
    return timeSlots;
  }
}
