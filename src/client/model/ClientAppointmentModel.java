package client.model;

import server.model.bookAppointment.*;

public class ClientAppointmentModel implements AppointmentModel
{
  private AppointmentList appointmentList;
  private DoctorList doctorList;

  public ClientAppointmentModel()
  {
    this.appointmentList = new AppointmentList();
    this.doctorList = new DoctorList();
  }

  @Override public Appointment bookAppointment(NewDateTime dateTime,
      int patientId, Doctor doctor, String mode)
  {
    return new Appointment(dateTime, patientId, doctor, mode);
  }

  @Override public boolean cancelAppointment(int appointmentId)
  {
    return false;
  }

  @Override public Appointment modifyAppointment(int appointmentId,
      NewDateTime newDateTime, String newMode)
  {
    return null;
  }

  @Override public AppointmentList getAppointmentList()
  {
    return appointmentList;
  }

  @Override public DoctorList getDoctorList()
  {
    return doctorList;
  }
}
