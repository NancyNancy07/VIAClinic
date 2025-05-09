package server.model.bookAppointment;

public interface AppointmentModel
{
  Appointment bookAppointment(NewDateTime dateTime, int patientID,
      Doctor doctor, String mode);
  boolean cancelAppointment(int appointmentId);
  Appointment modifyAppointment(int appointmentId, NewDateTime newDateTime,
      String newMode);
  DoctorList getDoctorList();
  AppointmentList getAppointmentList();
}
