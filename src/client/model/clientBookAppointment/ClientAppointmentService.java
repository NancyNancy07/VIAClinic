package client.model.clientBookAppointment;

import client.clientNetwork.PatientAppointmentClient;
import client.clientNetwork.PatientClient;
import server.model.bookAppointment.Patient;

import java.util.List;

public class ClientAppointmentService implements ClientAppointmentModel
{
  private final ClientAppointmentList appointmentList;
  private final ClientDoctorList doctorList;
  private final PatientAppointmentClient networkClient;
  private final PatientClient patientClient;

  public ClientAppointmentService()
  {
    this.appointmentList = new ClientAppointmentList();
    this.doctorList = new ClientDoctorList();
    this.networkClient = new PatientAppointmentClient();
    this.patientClient = new PatientClient();
  }

  @Override public ClientAppointment bookAppointment(ClientNewDateTime dateTime,
      int patientId, ClientDoctor doctor, String mode)
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patientId,
        doctor, mode);
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
      int patientId, ClientDoctor doctor, ClientNewDateTime newDateTime,
      String newMode)
  {
    return networkClient.modifyAppointment(appointmentId, patientId, doctor,
        newDateTime, newMode);
  }

  @Override public ClientAppointmentList getAppointmentList(int id)
  {
    return networkClient.getAppointmentByPatientId(id);
  }

  @Override public ClientAppointmentList getDoctorAppointmentList(int id)
  {
    ClientAppointmentList fetchedList = networkClient.getAppointmentByDoctorId(
        id);
    appointmentList.getAllAppointments().clear();
    appointmentList.getAllAppointments()
        .addAll(fetchedList.getAllAppointments());
    return appointmentList;
  }

  @Override public ClientDoctorList getDoctorList()
  {
    ClientDoctorList fetchedList = networkClient.getDoctorList();
    doctorList.getAllDoctors().clear();
    doctorList.getAllDoctors().addAll(fetchedList.getAllDoctors());
    return doctorList;
  }
}
