package client.model.clientBookAppointment;

import java.util.ArrayList;
import java.util.List;

public class ClientDoctorList
{
  private ArrayList<ClientDoctor> doctors;

  public ClientDoctorList()
  {
    this.doctors = new ArrayList<>();
  }

  public void addDoctor(ClientDoctor doctor)
  {
    doctors.add(doctor);
  }

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

  public void removeDoctor(ClientDoctor doctor)
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

  public List<ClientDoctor> getAllDoctors()
  {
    return doctors;
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
    ClientDoctorList other = (ClientDoctorList) obj;
    return doctors.equals(other.doctors);
  }
}
