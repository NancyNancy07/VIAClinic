package model.bookAppointment;

public interface AppointmentModel
{
  Appointment bookAppointment(int patientId, int doctorId, DateTime dateTime,
      String mode);
  boolean cancelAppointment(int appointmentId);
  Appointment modifyAppointment(int appointmentId, DateTime newDateTime,
      String newMode);
  DoctorList getDoctorList();
  AppointmentList getAppointmentList();
}
