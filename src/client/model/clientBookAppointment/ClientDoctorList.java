package client.model.clientBookAppointment;

import java.util.ArrayList;
import java.util.List;

/**
 * ClientAppointmentList is a class that manages a list of ClientAppointment objects.
 * It provides methods to add, find, remove appointments, and retrieve the size of the list.
 */
public class ClientDoctorList
{
  private ArrayList<ClientDoctor> doctors;

  /**
   * Constructor for ClientDoctorList.
   * Initializes an empty list of ClientDoctor objects.
   */
  public ClientDoctorList()
  {
    this.doctors = new ArrayList<>();
  }

  /**
   * Adds a ClientDoctor to the list.
   *
   * @param doctor The ClientDoctor to be added.
   */
  public void addDoctor(ClientDoctor doctor)
  {
    doctors.add(doctor);
  }

  /**
   * Finds a ClientDoctor by their ID.
   *
   * @param doctorID The ID of the doctor to find.
   * @return The ClientDoctor with the specified ID, or null if not found.
   */
  public ClientDoctor findDoctorByID(int doctorID)
  {
    for (int i = 0; i < doctors.size(); i++)
    {
      ClientDoctor doctor = doctors.get(i);
      if (doctor.getDoctorID() == doctorID)
      {
        return doctor;
      }
    }
    return null;
  }

  /**
   * Removes a ClientDoctor from the list.
   *
   * @param doctor The ClientDoctor to be removed.
   */
  public void removeDoctor(ClientDoctor doctor)
  {
    doctors.remove(doctor);
  }

  /**
   * Removes a ClientDoctor at a specific index from the list.
   *
   * @param index The index of the ClientDoctor to be removed.
   */
  public void removeDoctor(int index)
  {
    if (index >= 0 && index < doctors.size())
    {
      doctors.remove(index);
    }
  }

  /**
   * Gets the number of ClientDoctors in the list.
   *
   * @return The size of the list.
   */
  public int getNumberOfDoctors()
  {
    return doctors.size();
  }

  /**
   * Retrieves all ClientDoctors in the list.
   *
   * @return A list of all ClientDoctors.
   */
  public List<ClientDoctor> getAllDoctors()
  {
    return doctors;
  }

  /**
   * Converts the list of ClientDoctors to a string representation.
   *
   * @return A string representation of the ClientDoctorList.
   */
  @Override public String toString()
  {
    return "DoctorList{" + "doctors=" + doctors + '}';
  }

  /**
   * Checks if this ClientDoctorList is equal to another object.
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
    ClientDoctorList other = (ClientDoctorList) obj;
    return doctors.equals(other.doctors);
  }
}
