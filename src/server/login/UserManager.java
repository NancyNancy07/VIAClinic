package server.login;

import model.bookAppointment.Doctor;
import model.bookAppointment.Patient;
import model.loginSystem.User;

import java.util.HashMap;
/*DoctorManager is a class that manages doctor data in memory.
  Right now we are not using a database, so we need a place to store and search
  doctors by username. A HashMap lets us quickly find a doctor using their username
  like this.
  It’s fast and perfect for login systems when you want to validate credentials quickly.
  Later, if we use a database, this class can be refactored to fetch data from there.*/
public class UserManager
{
  private HashMap<String, User> users;

  public UserManager()
  {
    users = new HashMap<>();
    users.put("drsmith", new Doctor(1, "Dr. Smith", "drsmith", "pass123"));
    users.put("john123", new Patient(101, "John Doe", "john123", "mypassword"));
  }

  public boolean isValidLogin(String username, String password)
  {
    User user = users.get(username);
    return user != null && user.getPassword().equals(password);
  }

  public User getUser(String username)
  {
    return users.get(username);
  }
}
