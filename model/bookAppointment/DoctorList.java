package model.bookAppointment;

import model.Doctor;

import java.util.ArrayList;

public class DoctorList
{
  private ArrayList<Doctor> doctors;

  public DoctorList()
  {
    this.doctors = new ArrayList<>();
  }

  public void addDoctor(Doctor doctor)
  {
    doctors.add(doctor);
  }

  public Doctor findDoctorByID(int doctorID)
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

  public void removeDoctor(Doctor doctor)
  {
    doctors.remove(doctor);
  }

  public void removeDoctor(int index)
  {
    if (index >= 0 && index < doctors.size())
    {
      doctors.remove(index);
    }
  }

  public int getNumberOfDoctors()
  {
    return doctors.size();
  }

  public Doctor[] getAllDoctors()
  {
    return doctors.toArray(new Doctor[0]);
  }

  @Override public String toString()
  {
    return "DoctorList{" + "doctors=" + doctors + '}';
  }

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
