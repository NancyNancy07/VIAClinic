package server.model.bookAppointment;

import java.util.ArrayList;
import java.util.List;

/**
 * DoctorList manages a list of Doctor objects.
 * It provides methods to add, find, remove doctors, and retrieve the list of all doctors.
 */
public class DoctorList
{
  private  ArrayList<Doctor> doctors;

  /**
   * Constructor initializes the DoctorList with an empty list of doctors.
   */
  public DoctorList()
  {
    this.doctors = new ArrayList<>();
  }

  /**
   * Adds a doctor to the list.
   *
   * @param doctor The Doctor object to be added.
   */
  public void addDoctor(Doctor doctor)
  {
    doctors.add(doctor);
  }

  /**
   * Finds a doctor by their ID.
   *
   * @param doctorID The ID of the doctor to find.
   * @return The Doctor object if found, otherwise null.
   */
  public  Doctor findDoctorByID(int doctorID)
  {
    for (int i = 0; i < doctors.size(); i++)
    {
      Doctor doctor = doctors.get(i);
      if (doctor.getDoctorID() == doctorID)
      {
        return doctor;
      }
    }
    return null;
  }

  /**
   * Removes a doctor from the list.
   *
   * @param doctor The Doctor object to be removed.
   */
  public void removeDoctor(Doctor doctor)
  {
    doctors.remove(doctor);
  }

  /**
   * Removes a doctor from the list by index.
   *
   * @param index The index of the doctor to be removed.
   */
  public void removeDoctor(int index)
  {
    if (index >= 0 && index < doctors.size())
    {
      doctors.remove(index);
    }
  }

  /**
   * Gets the number of doctors in the list.
   *
   * @return The size of the doctors list.
   */
  public int getNumberOfDoctors()
  {
    return doctors.size();
  }

  /**
   * Retrieves the list of all doctors.
   *
   * @return A list of Doctor objects.
   */
  public List<Doctor> getAllDoctors()
  {
    return doctors;
  }

  /**
   * String representation of the DoctorList.
   * @return A string containing the list of doctors.
   */
  @Override public String toString()
  {
    return "DoctorList{" + "doctors=" + doctors + '}';
  }

  /**
   * Checks if two DoctorList objects are equal.
   *
   * @param obj The object to compare with.
   * @return true if both objects are of the same class and contain the same doctors, false otherwise.
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    DoctorList other = (DoctorList) obj;
    return doctors.equals(other.doctors);
  }
}
