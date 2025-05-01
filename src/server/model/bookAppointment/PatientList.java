package server.model.bookAppointment;

import java.util.ArrayList;

public class PatientList
{
  private ArrayList<Patient> patients;

  public PatientList()
  {
    this.patients = new ArrayList<>();
  }

  public void addPatient(Patient patient)
  {
    patients.add(patient);
  }

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

  public void removePatient(Patient patient)
  {
    patients.remove(patient);
  }

  public void removePatient(int index)
  {
    if (index >= 0 && index < patients.size())
    {
      patients.remove(index);
    }
  }

  public int getNumberOfPatients()
  {
    return patients.size();
  }

  public Patient[] getAllPatients()
  {
    return patients.toArray(new Patient[0]);
  }

  @Override public String toString()
  {
    return "PatientList{" + "patients=" + patients + '}';
  }

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
