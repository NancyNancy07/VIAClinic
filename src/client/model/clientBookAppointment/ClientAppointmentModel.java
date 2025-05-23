package client.model.clientBookAppointment;

public interface ClientAppointmentModel
{
  default ClientAppointment bookAppointment(ClientNewDateTime dateTime, int patientID,
      int doctorId, String mode)
  {
    return null;
  }
  boolean cancelAppointment(int appointmentId);
  ClientAppointment modifyAppointment(int appointmentId, ClientNewDateTime newDateTime,
      String newMode);
  ClientDoctorList getDoctorList();
  ClientAppointmentList getAppointmentList(int id);
  ClientAppointmentList getDoctorAppointmentList(int id);
}
