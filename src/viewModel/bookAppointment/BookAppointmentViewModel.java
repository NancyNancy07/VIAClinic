package viewModel.bookAppointment;

import model.bookAppointment.Appointment;
import model.bookAppointment.AppointmentModel;
import model.bookAppointment.DateTime;
import model.bookAppointment.Doctor;

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
    LocalTime LocalTime = java.time.LocalTime.now();
    DateTime dateTime = new DateTime(date, LocalTime);
    return model.bookAppointment(patientId, doctorId, dateTime, mode);
  }

  public List<Appointment> getAppointmentList()
  {
    return model.getAppointmentList().getAllAppointments();
  }

  public List<Doctor> getDoctorList()
  {
    return model.getDoctorList().getAllDoctors();
  }
}
