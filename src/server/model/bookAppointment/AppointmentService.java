package server.model.bookAppointment;

public class AppointmentService implements AppointmentModel {
  private AppointmentList appointmentList;
  private DoctorList doctorList;
  private PatientList patientList;

  public AppointmentService(DoctorList doctorList, PatientList patientList,
      AppointmentList appointmentList) {
    this.doctorList = doctorList;
    this.patientList = patientList;
    this.appointmentList = appointmentList;
  }

  @Override
  public Appointment bookAppointment(NewDateTime dateTime, int patientID, Doctor doctor, String mode) {
    if (dateTime == null || mode == null) {
      throw new IllegalArgumentException("Date/time and mode cannot be null.");
    }

    Patient patient = patientList.findPatientByID(patientID);
    if (doctor == null || patient == null) {
      return null;
    }

    Appointment newAppointment = new Appointment(dateTime, patientID, doctor, mode);
    newAppointment.setDoctorID(doctor.getDoctorID()); // Ensure doctorID is set properly
    appointmentList.addAppointment(newAppointment);
    return newAppointment;
  }

  @Override
  public boolean cancelAppointment(int appointmentID) {
    Appointment appointment = appointmentList.findAppointmentByID(appointmentID);
    if (appointment != null) {
      appointmentList.removeAppointment(appointment);
      return true;
    }
    return false;
  }

  @Override
  public Appointment modifyAppointment(int appointmentID, NewDateTime newDateTime, String newMode) {
    if (newDateTime == null || newMode == null) {
      throw new IllegalArgumentException("New date/time and mode cannot be null.");
    }

    Appointment appointment = appointmentList.findAppointmentByID(appointmentID);
    if (appointment != null) {
      appointment.setDateTime(newDateTime);
      appointment.setMode(newMode);
      return appointment;
    }
    return null;
  }

  @Override
  public DoctorList getDoctorList() {
    return doctorList;
  }

  @Override
  public AppointmentList getAppointmentList() {
    return appointmentList;
  }
}
