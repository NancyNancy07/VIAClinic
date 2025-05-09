package client.model;

import server.model.bookAppointment.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClientAppointmentModel implements AppointmentModel
{
  private AppointmentList appointmentList; // Filled by server response
  private DoctorList doctorList; // Filled by server response

  public ClientAppointmentModel()
  {
    this.appointmentList = new AppointmentList();
    this.doctorList = new DoctorList();
    // Fetch data from server here and fill the lists
  }

  @Override public Appointment bookAppointment(NewDateTime dateTime,
      int patientId, Doctor doctor, String mode)
  {
    // Send request to server, receive appointment, and return it
    // Update local list if needed

    return new Appointment(dateTime, patientId, doctor, mode); // Example stub
  }

  @Override public boolean cancelAppointment(int appointmentId)
  {
    return false;
  }

  @Override public Appointment modifyAppointment(int appointmentId,
      NewDateTime newDateTime, String newMode)
  {
    return null;
  }

  @Override public AppointmentList getAppointmentList()
  {
    return appointmentList;
  }

  @Override public DoctorList getDoctorList()
  {
    return doctorList;
  }
}
