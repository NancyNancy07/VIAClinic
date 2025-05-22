package client.viewModel.bookAppointment;

import client.clientNetwork.PatientAppointmentClient;
import client.model.clientBookAppointment.ClientDoctor;
import client.model.clientBookAppointment.ClientDoctorList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SelectDateTimeViewModel
{
  private final BookAppointmentSharedData sharedData = BookAppointmentSharedData.getInstance();

  public void setDate(LocalDate date)
  {
    sharedData.setAppointmentDate(date);
  }

  public void setTime(String time)
  {
    if (time != null)
    {
      sharedData.setAppointmentTime(LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")));
    }
  }

  public LocalDate getDate()
  {
    return sharedData.getAppointmentDate();
  }

  public LocalTime getTime()
  {
    return sharedData.getAppointmentTime();
  }

  public String getMode()
  {
    return sharedData.getConsultationMode();
  }

  public int getSelectedDoctorId()
  {
    return sharedData.getSelectedDoctorId();
  }

  public String getDoctorName()
  {
    PatientAppointmentClient client = new PatientAppointmentClient();
    ClientDoctorList doctors = client.getDoctorList();
    for (ClientDoctor doc : doctors.getAllDoctors())
    {
      if (doc.getDoctorID() == sharedData.getSelectedDoctorId())
      {
        return doc.getName();
      }
    }
    return "Unknown";
  }

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
