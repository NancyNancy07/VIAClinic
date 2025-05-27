package server.model.bookAppointment;

import java.util.ArrayList;

/**
 * PatientList class manages a list of patients in the healthcare system.
 * It provides methods to add, find, remove, and retrieve patients.
 */
public class PatientList
{
  private ArrayList<Patient> patients;

  /**
   * Default constructor for PatientList.
   * Initializes the patients list as an empty ArrayList.
   */
  public PatientList()
  {
    this.patients = new ArrayList<>();
  }

  /**
   * Add a patient to the list.
   * @param patient the Patient object to be added
   */
  public void addPatient(Patient patient)
  {
    patients.add(patient);
  }

  /**
   * Find a patient by their ID.
   * @param patientID the ID of the patient to find
   * @return the Patient object if found, otherwise null
   */
  public Patient findPatientByID(int patientID)
  {
    for (int i = 0; i < patients.size(); i++)
    {
      Patient patient = patients.get(i);
      if (patient.getPatientID() == patientID)
      {
        return patient;
      }
    }
    return null;
  }

  /**
   * Remove a patient from the list.
   * @param patient the Patient object to be removed
   */
  public void removePatient(Patient patient)
  {
    patients.remove(patient);
  }

  /**
   * Remove a patient by their index in the list.
   * @param index the index of the patient to be removed
   */
  public void removePatient(int index)
  {
    if (index >= 0 && index < patients.size())
    {
      patients.remove(index);
    }
  }

  /**
   * Get the number of patients in the list.
   * @return the size of the patients list
   */
  public int getNumberOfPatients()
  {
    return patients.size();
  }

  /**
   * Get all patients in the list.
   * @return an array of Patient objects
   */
  public Patient[] getAllPatients()
  {
    return patients.toArray(new Patient[0]);
  }

  /**
   * Returns a string representation of the PatientList.
   * @return a string containing the list of patients
   */
  @Override public String toString()
  {
    return "PatientList{" + "patients=" + patients + '}';
  }

  /**
   * Checks if this PatientList is equal to another object.
   * Two PatientLists are considered equal if they contain the same patients.
   * @param obj the object to compare with
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    PatientList other = (PatientList) obj;
    return patients.equals(other.patients);
  }
}
