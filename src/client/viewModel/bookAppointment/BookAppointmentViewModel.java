package client.viewModel.bookAppointment;

import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.AppointmentModel;
import server.model.bookAppointment.DateTime;
import server.model.bookAppointment.Doctor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BookAppointmentViewModel
{
  private AppointmentModel model;

  public BookAppointmentViewModel(AppointmentModel model)
  {
    this.model = model;
  }

  public Appointment addAppointment(int patientId, int doctorId, LocalDate date,
      String mode)
  {
    LocalTime localTime = LocalTime.now();
    DateTime dateTime = new DateTime(date, localTime);
    return this.model.bookAppointment(patientId, doctorId, dateTime, mode);
  }

  public List<Appointment> getAppointmentList()
  {
    return this.model.getAppointmentList().getAllAppointments();
  }

  public List<Doctor> getDoctorList()
  {
    return this.model.getDoctorList().getAllDoctors();
  }
}

