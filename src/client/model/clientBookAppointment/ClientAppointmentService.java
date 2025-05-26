package client.model.clientBookAppointment;

import client.clientNetwork.PatientAppointmentClient;

public class ClientAppointmentService implements ClientAppointmentModel
{
  private final ClientAppointmentList appointmentList;
  private final ClientDoctorList doctorList;
  private final PatientAppointmentClient networkClient;

  public ClientAppointmentService()
  {
    this.appointmentList = new ClientAppointmentList();
    this.doctorList = new ClientDoctorList();
    this.networkClient = new PatientAppointmentClient();
  }

  @Override public ClientAppointment bookAppointment(ClientNewDateTime dateTime,
      int patientId, int doctorId, String mode)
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patientId,
        doctorId, mode);
    return networkClient.bookAppointment(appointment);
  }

//  @Override public boolean cancelAppointment(int appointmentId)
//  {
//    return false;
//  }

  @Override
  public boolean cancelAppointment(int appointmentId)
  {
    return networkClient.cancelAppointment(appointmentId);
  }

  @Override public ClientAppointment modifyAppointment(int appointmentId,
      ClientNewDateTime newDateTime, String newMode)
  {
    return null;
  }

  @Override public ClientAppointmentList getAppointmentList(int id)
  {
    return networkClient.getAppointmentByPatientId(id);
  }

  @Override public ClientDoctorList getDoctorList()
  {
    return networkClient.getDoctorList();
  }
}
