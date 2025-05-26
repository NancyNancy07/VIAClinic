package client.model.clientBookAppointment;

import java.util.ArrayList;
import java.util.List;

/**
 * ClientAppointmentList is a class that manages a list of ClientAppointment objects.
 * It provides methods to add, find, remove appointments, and retrieve the size of the list.
 */
public class ClientAppointmentList
{
  private ArrayList<ClientAppointment> appointments;

  /**
   * Constructor to initialize the ClientAppointmentList with an empty list.
   */
  public ClientAppointmentList()
  {
    this.appointments = new ArrayList<>();
  }

  /**
   * Adds a ClientAppointment to the list.
   *
   * @param appointment The ClientAppointment to be added.
   */
  public void addAppointment(ClientAppointment appointment)
  {
    appointments.add(appointment);
  }

  /**
   * Returns the number of appointments in the list.
   *
   * @return The size of the appointment list.
   */
  public int getSize()
  {
    return appointments.size();
  }

  /**
   * Finds a ClientAppointment by its ID.
   *
   * @param appointmentID The ID of the appointment to find.
   * @return The ClientAppointment with the specified ID, or null if not found.
   */
  public ClientAppointment findAppointmentByID(int appointmentID)
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
   * Removes a ClientAppointment from the list.
   *
   * @param appointment The ClientAppointment to be removed.
   */
  public void removeAppointment(ClientAppointment appointment)
  {
    appointments.remove(appointment);
  }

  /**
   * Returns a list of all ClientAppointments.
   *
   * @return A List containing all ClientAppointments.
   */
  public List<ClientAppointment> getAllAppointments()
  {
    return appointments;
  }

  /**
   * Returns a string representation of the ClientAppointmentList.
   *
   * @return A string containing all appointments in the list.
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
   * Checks if this ClientAppointmentList is equal to another object.
   *
   * @param obj The object to compare with.
   * @return true if the objects are equal, false otherwise.
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    ClientAppointmentList other = (ClientAppointmentList) obj;
    return appointments.equals(other.appointments);
  }
}
