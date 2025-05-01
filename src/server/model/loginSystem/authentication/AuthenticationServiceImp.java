package server.model.loginSystem.authentication;

import server.model.bookAppointment.*;
import server.model.loginSystem.entities.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationServiceImp implements AuthenticationService
{
  private ArrayList<User> users = new ArrayList<>();
  private static AuthenticationServiceImp instance;
  private User loggedInUser;

  private DoctorList doctorList;
  private PatientList patientList;
  private AppointmentList appointmentList;

  private AuthenticationServiceImp()
  {
    // Sample setup
    doctorList = new DoctorList();
    patientList = new PatientList();
    appointmentList = new AppointmentList();

    // Sample doctors
    users.add(new Doctor(1, "Dr. Smith", "asd123", "1234567"));
    users.add((new Doctor(2, "Dr. Adams", "asf123", "12345678")));
    users.add((new Doctor(3, "Dr. Brown", "asg123", "123456789")));
    users.add((new Doctor(4, "Dr. Lee", "asa123", "123456")));

    // Sample patients
    users.add((new Patient(1, "John Doe", "asq123", "123")));
    users.add((new Patient(2, "Jane Doe", "asw123", "1234")));
    users.add((new Patient(3, "Bob Smith", "ase123", "12345")));
    users.add((new Patient(4, "Alice White", "asr123", "98765")));

    // Sample appointments
    LocalTime localTime = LocalTime.now();
    LocalDate localDate = LocalDate.now();
    DateTime dateTime = new DateTime(localDate, localTime);

    appointmentList.addAppointment(
        new Appointment(dateTime, 1, 1, "In-person", 1, doctorList));
    appointmentList.addAppointment(
        new Appointment(dateTime, 2, 2, "Online", 2, doctorList));
    appointmentList.addAppointment(
        new Appointment(dateTime, 3, 3, "In-person", 3, doctorList));
    appointmentList.addAppointment(
        new Appointment(dateTime, 4, 4, "Online", 4, doctorList));

    // You can also add patient logins similarly if needed

  }

  public static AuthenticationServiceImp getInstance()
  {
    if (instance == null)
    {
      instance = new AuthenticationServiceImp();
    }
    return instance;
  }

  @Override public String login(LoginRequest request)
  {
    for (User user : users)
    {
      if (request != null && user.getUsername() != null)
      {
        if (user.getUsername().equals(request.getUsername()))
        {
          if (user.getPassword().equals(request.getPassword()))
          {
            loggedInUser = user;
            return "Ok";
          }
          return "Incorrect Password";
        }
      }
    }
    for (int i = 0; i < users.size(); i++)
    {
      System.out.println(users.get(i).getUsername());
    }
    return "Email not found";
  }

  @Override public List<Doctor> getAllDoctors()
  {
    return doctorList.getAllDoctors();
  }

  @Override public List<Patient> getAllPatients()
  {
    return List.of(patientList.getAllPatients());
  }

  @Override public List<Appointment> getAllAppointments()
  {
    return appointmentList.getAllAppointments();
  }

  public List<Appointment> getAppointmentsForLoggedInUser()
  {
    if (loggedInUser == null)
    {
      return new ArrayList<>();
    }

    List<Appointment> result = new ArrayList<>();
    for (Appointment appointment : appointmentList.getAllAppointments())
    {
      if (loggedInUser instanceof Doctor
          && appointment.getDoctorID() == ((Doctor) loggedInUser).getDoctorID())
      {
        result.add(appointment);
      }
      else if (loggedInUser instanceof Patient && appointment.getPatientID()
          == ((Patient) loggedInUser).getPatientID())
      {
        result.add(appointment);
      }
    }
    return result;
  }

}
