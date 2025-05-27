package client.viewModel.bookAppointment;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * BookAppointmentSharedData is a singleton class that holds shared data for booking appointments.
 * It provides methods to set and get the selected doctor ID, consultation mode, appointment date,
 * and appointment time.
 */
public class BookAppointmentSharedData {
  private static BookAppointmentSharedData instance;

  private int selectedDoctorId;
  private String consultationMode;
  private LocalDate appointmentDate;
  private LocalTime appointmentTime;

  /**
   * Private constructor to prevent instantiation from outside the class.
   * This ensures that only one instance of BookAppointmentSharedData exists.
   */
  private BookAppointmentSharedData() {}

  /**
   * Returns the singleton instance of BookAppointmentSharedData.
   * If the instance is null, it creates a new instance.
   *
   * @return the singleton instance of BookAppointmentSharedData
   */
  public static synchronized BookAppointmentSharedData getInstance() {
    if (instance == null) {
      instance = new BookAppointmentSharedData();
    }
    return instance;
  }

  /**
   * Sets the selected doctor ID.
   *
   * @param id the ID of the selected doctor
   */
  public void setSelectedDoctorId(int id) {
    this.selectedDoctorId = id;
  }

  /**
   * Gets the selected doctor ID.
   *
   * @return the ID of the selected doctor
   */
  public int getSelectedDoctorId() {
    return selectedDoctorId;
  }


  /**
   * Gets the consultation mode.
   *
   * @return the consultation mode as a String
   */
  public String getConsultationMode() {
    return consultationMode;
  }

  public void setConsultationMode(String consultationMode) {
    this.consultationMode = consultationMode;
  }

  /**
   * Gets the appointment date.
   *
   * @return the appointment date as a LocalDate
   */
  public LocalDate getAppointmentDate() {
    return appointmentDate;
  }

  /**
   * Sets the appointment date.
   *
   * @param appointmentDate the date of the appointment as a LocalDate
   */
  public void setAppointmentDate(LocalDate appointmentDate) {
    this.appointmentDate = appointmentDate;
  }

  /**
   * Gets the appointment time.
   *
   * @return the appointment time as a LocalTime
   */
  public LocalTime getAppointmentTime() {
    return appointmentTime;
  }

  /**
   * Sets the appointment time.
   *
   * @param appointmentTime the time of the appointment as a LocalTime
   */
  public void setAppointmentTime(LocalTime appointmentTime) {
    this.appointmentTime = appointmentTime;
  }
}
