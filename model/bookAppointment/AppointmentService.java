package model.bookAppointment;

import model.DateTime;

public class AppointmentService implements AppointmentModel
{
  private AppointmentList appointmentList;
  private DoctorList doctorList;
  private PatientList patientList;

  public AppointmentService(DoctorList doctorList, PatientList patientList,
      AppointmentList appointmentList)
  {
    this.doctorList = doctorList;
    this.patientList = patientList;
    this.appointmentList = appointmentList;
  }

  @Override public Appointment bookAppointment(int patientID, int doctorID,
      DateTime dateTime, String mode)
  {
    if (dateTime == null || mode == null)
    {
      throw new IllegalArgumentException("Date/time and mode cannot be null.");
    }

    Doctor doctor = doctorList.findDoctorByID(doctorID);
    Patient patient = patientList.findPatientByID(patientID);
    if (doctor == null || patient == null)
    {
      return null;
    }

    int appointmentID = generateAppointmentID();
    Appointment newAppointment = new Appointment(dateTime, appointmentID,
        doctorID, mode, patientID);
    appointmentList.addAppointment(newAppointment);
    return newAppointment;
  }

  @Override public boolean cancelAppointment(int appointmentID)
  {
    Appointment appointment = appointmentList.findAppointmentByID(
        appointmentID);
    if (appointment != null)
    {
      appointmentList.removeAppointment(appointment);
      return true;
    }
    return false;
  }

  @Override public Appointment modifyAppointment(int appointmentID,
      DateTime newDateTime, String newMode)
  {
    if (newDateTime == null || newMode == null)
    {
      throw new IllegalArgumentException(
          "New date/time and mode cannot be null.");
    }

    Appointment appointment = appointmentList.findAppointmentByID(
        appointmentID);
    if (appointment != null)
    {
      appointment.setDateTime(newDateTime);
      appointment.setMode(newMode);
      return appointment;
    }
    return null;
  }

  private int generateAppointmentID()
  {
    return appointmentList.getSize() + 1;
  }
}
