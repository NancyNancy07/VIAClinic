package client.viewModel.bookAppointment;

import client.clientNetwork.PatientAppointmentClient;
import client.viewModel.loginSystem.LoginSharedData;
import server.model.bookAppointment.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookAppointmentViewModel
{
  private AppointmentModel model;
  private BookAppointmentSharedData sharedData;

  public BookAppointmentViewModel(AppointmentModel model, BookAppointmentSharedData sharedData)
  {
    this.sharedData = sharedData;
    this.model = model;
  }

  public Appointment addAppointment(NewDateTime date, int patientId,
      Doctor doctor, String mode)
  {
    Appointment appointment = new Appointment(date, patientId, doctor, mode);

    PatientAppointmentClient client = new PatientAppointmentClient();
    //    Appointment bookedAppointment = client.bookAppointment(appointment);

    // Return the response from the server (i.e., the created appointment)
    return appointment;

  }

  public List<Appointment> getAppointmentList()
  {
    PatientAppointmentClient client = new PatientAppointmentClient();
    int patientId = LoginSharedData.getInstance().getId();
    List<Appointment> appointments = client.getAppointmentByPatientId(
        patientId);
    System.out.println(appointments);
    if (appointments != null)
    {
      return appointments;
    }

    return null;
  }

  public void setSelectedDoctor(int doctorId)
  {
    sharedData.setSelectedDoctorId(doctorId);
  }

  public void setMode(String mode)
  {
    sharedData.setConsultationMode(mode);
  }

  public void setDate(LocalDate date)
  {
    sharedData.setAppointmentDate(date);
  }

  public void setTime(String time)
  {
    sharedData.setAppointmentTime(LocalTime.parse(time));
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

  public void setSelectedTime(String selectedTime)
  {
    if (selectedTime != null)
    {
      LocalTime time = LocalTime.parse(selectedTime,
          DateTimeFormatter.ofPattern("HH:mm"));
      sharedData.setAppointmentTime(time);  // Store the selected time
    }
  }

  public List<Doctor> getDoctorList()
  {
    PatientAppointmentClient client = new PatientAppointmentClient();
    List<Doctor> doctors = client.getDoctorList();
    if (doctors != null)
    {
      return doctors;
    }
    return null;
  }
}
