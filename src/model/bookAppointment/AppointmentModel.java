package model.bookAppointment;

import model.Appointment;
import model.DateTime;

public interface AppointmentModel
{
  Appointment bookAppointment(int patientId, int doctorId, DateTime dateTime, String mode);
  boolean cancelAppointment(int appointmentId);
  Appointment modifyAppointment(int appointmentId, DateTime newDateTime, String newMode);
}
