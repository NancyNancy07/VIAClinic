package server.model.bookAppointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentList
{
  private ArrayList<Appointment> appointments;

  public AppointmentList()
  {
    this.appointments = new ArrayList<>();
  }

  public void addAppointment(Appointment appointment)
  {
    appointments.add(appointment);
  }

  public int getSize()
  {
    return appointments.size();
  }

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

  public void removeAppointment(Appointment appointment)
  {
    appointments.remove(appointment);
  }

  public List<Appointment> getAllAppointments()
  {
    return appointments;
  }

  @Override public String toString()
  {
    String rtnString = "";

    for (int i = 0; i < appointments.size(); i++)
    {
      rtnString += appointments.get(i);
    }
    return rtnString;
  }

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
