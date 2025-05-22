package client.model.clientBookAppointment;

import java.util.ArrayList;
import java.util.List;

public class ClientAppointmentList
{
  private ArrayList<ClientAppointment> appointments;

  public ClientAppointmentList()
  {
    this.appointments = new ArrayList<>();
  }

  public void addAppointment(ClientAppointment appointment)
  {
    appointments.add(appointment);
  }

  public int getSize()
  {
    return appointments.size();
  }

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

  public void removeAppointment(ClientAppointment appointment)
  {
    appointments.remove(appointment);
  }

  public List<ClientAppointment> getAllAppointments()
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
    ClientAppointmentList other = (ClientAppointmentList) obj;
    return appointments.equals(other.appointments);
  }
}
