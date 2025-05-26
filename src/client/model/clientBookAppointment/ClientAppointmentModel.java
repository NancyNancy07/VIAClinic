package client.model.clientBookAppointment;

public interface ClientAppointmentModel
{
  ClientAppointment bookAppointment(ClientNewDateTime dateTime, int patientID,
      ClientDoctor doctor, String mode);
  boolean cancelAppointment(int appointmentId);
  ClientAppointment modifyAppointment(int appointmentId, int patientId,
      ClientDoctor doctor, ClientNewDateTime newDateTime, String newMode);
  ClientDoctorList getDoctorList();
  ClientAppointmentList getAppointmentList(int id);
  ClientAppointmentList getDoctorAppointmentList(int id);
}
