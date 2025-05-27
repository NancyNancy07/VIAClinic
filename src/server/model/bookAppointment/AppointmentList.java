package server.model.bookAppointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentList
{
  private ArrayList<Appointment> appointments;

  /**
   * Default constructor for AppointmentList.
   * Initializes the appointments list.
   */
  public AppointmentList()
  {
    this.appointments = new ArrayList<>();
  }

  /**
   * Adds an appointment to the list.
   * @param appointment the Appointment object to be added
   */
  public void addAppointment(Appointment appointment)
  {
    appointments.add(appointment);
  }

  /**
   * Gets the number of appointments in the list.
   * @return the size of the appointments list
   */
  public int getSize()
  {
    return appointments.size();
  }

  /**
   * Finds an appointment by its ID.
   * @param appointmentID the ID of the appointment to find
   * @return the Appointment object if found, otherwise null
   */
  public Appointment findAppointmentByID(int appointmentID)
  {
    for (int i = 0; i < appointments.size(); i++)
    {
      if (appointments.get(i).getAppointmentID() == appointmentID)
      {
        return appointments.get(i);
      }
    }
    return null;
  }

  /**
   * Removes an appointment from the list.
   * @param appointment the Appointment object to be removed
   */
  public void removeAppointment(Appointment appointment)
  {
    appointments.remove(appointment);
  }

  /**
   * Retrieves all appointments in the list.
   * @return a List of Appointment objects
   */
  public List<Appointment> getAllAppointments()
  {
    return appointments;
  }

  /**
   * Retrieves String representation of the appointment list.
   * @return a String containing all appointments
   */
  @Override public String toString()
  {
    String rtnString = "";

    for (int i = 0; i < appointments.size(); i++)
    {
      rtnString += appointments.get(i);
    }
    return rtnString;
  }

  /**
   * Checks if two AppointmentList objects are equal.
   * @param obj the object to compare with
   * @return true if both objects are of the same class and contain the same appointments, false otherwise
   *
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    AppointmentList other = (AppointmentList) obj;
    return appointments.equals(other.appointments);
  }
}
