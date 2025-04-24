package model.bookAppointment;

import java.util.ArrayList;

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

  public Appointment[] getAllAppointments()
  {
    return appointments.toArray(new Appointment[0]);
  }

  @Override public String toString()
  {
    return "AppointmentList{" + "appointments=" + appointments + '}';
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
