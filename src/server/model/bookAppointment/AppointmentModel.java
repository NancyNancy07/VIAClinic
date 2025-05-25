package server.model.bookAppointment;

public interface AppointmentModel
{
  default Appointment bookAppointment(int id, NewDateTime dateTime, int patientID,
      Doctor doctor, String mode)
  {
    return null;
  }
  boolean cancelAppointment(int appointmentId);
  Appointment modifyAppointment(int appointmentId, NewDateTime newDateTime,
      String newMode);
  DoctorList getDoctorList();
  AppointmentList getAppointmentList();
}
