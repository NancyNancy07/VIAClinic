package client.model.clientBookAppointment;

public interface ClientAppointmentModel
{
  ClientAppointment bookAppointment(ClientNewDateTime dateTime, int patientID,
      int doctorId, String mode);
  boolean cancelAppointment(int appointmentId);
  ClientAppointment modifyAppointment(int appointmentId, int patientId,
      int doctorId, ClientNewDateTime newDateTime, String newMode);
  ClientDoctorList getDoctorList();
  ClientAppointmentList getAppointmentList(int id);
  ClientAppointmentList getDoctorAppointmentList(int id);
}
