package client.model.clientBookAppointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ClientDoctorTest {

  private int doctorID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String username;
  private String password;
  private ClientDoctor clientDoctor;

  @BeforeEach void setUp()
  {
    doctorID = 1;
    firstName = "John";
    lastName = "Doe";
    email = "asdasd@gmail.com";
    phoneNumber = "1234567890";
    username = "docUser";
    password = "pass123";
    clientDoctor = new ClientDoctor(doctorID, firstName, lastName, email, phoneNumber, username, password);
  }

  @Test
  void constructorWithoutId()
  {
    ClientDoctor clientDoctorWithoutId = new ClientDoctor(firstName, lastName, email, phoneNumber, username, password);
    assertEquals(firstName, clientDoctorWithoutId.getFirstName());
    assertEquals(lastName, clientDoctorWithoutId.getLastName());
    assertEquals(email, clientDoctorWithoutId.getEmail());
    assertEquals(phoneNumber, clientDoctorWithoutId.getPhoneNumber());
    assertEquals(username, clientDoctorWithoutId.getUsername());
    assertEquals(password, clientDoctorWithoutId.getPassword());
  }
  @Test void getUsername()
  {
    assertEquals("docUser", clientDoctor.getUsername());
  }

  @Test void getPassword()
  {
    assertEquals("pass123", clientDoctor.getPassword());
  }

  @Test void setPassword()
  {
    String newPassword = "newPass123";
    clientDoctor.setPassword(newPassword);
    assertEquals(newPassword, clientDoctor.getPassword());
  }

  @Test void setUsername()
  {
    String newUsername = "newDocUser";
    clientDoctor.setUsername(newUsername);
    assertEquals(newUsername, clientDoctor.getUsername());
  }

  @Test void getDoctorID()
  {
    assertEquals(doctorID, clientDoctor.getDoctorID());
  }

  @Test void getName()
  {
    String expectedName = firstName + " " + lastName;
    assertEquals(expectedName, clientDoctor.getName());
  }

  @Test void setDoctorID()
  {
    int newDoctorID = 2;
    clientDoctor.setDoctorID(newDoctorID);
    assertEquals(newDoctorID, clientDoctor.getDoctorID());
  }

  @Test void setName()
  {
    String newName = "Jane Smith";
    clientDoctor.setName(newName);
    assertEquals("Jane", clientDoctor.getFirstName());
    assertEquals("Smith", clientDoctor.getLastName());
  }

  @Test void getEmail()
  {
    assertEquals(email, clientDoctor.getEmail());
  }

  @Test void getFirstName()
  {
    assertEquals(firstName, clientDoctor.getFirstName());
  }

  @Test void getLastName()
  {
    assertEquals(lastName, clientDoctor.getLastName());
  }

  @Test void getPhoneNumber()
  {
    assertEquals(phoneNumber, clientDoctor.getPhoneNumber());
  }

  @Test void setLastName()
  {
    String newLastName = "Smith";
    clientDoctor.setLastName(newLastName);
    assertEquals(newLastName, clientDoctor.getLastName());
  }

  @Test void setFirstName()
  {
    String newFirstName = "Jane";
    clientDoctor.setFirstName(newFirstName);
    assertEquals(newFirstName, clientDoctor.getFirstName());
  }

  @Test void setEmail()
  {
    String newEmail = "basbasd@gmail.com";
    clientDoctor.setEmail(newEmail);
    assertEquals(newEmail, clientDoctor.getEmail());
  }

  @Test void setPhoneNumber()
  {
    String newPhoneNumber = "0987654321";
    clientDoctor.setPhoneNumber(newPhoneNumber);
    assertEquals(newPhoneNumber, clientDoctor.getPhoneNumber());
  }

  @Test void testToString()
  {
    String expectedString = "Name: '" + clientDoctor.getName() + '\'';
    assertEquals(expectedString, clientDoctor.toString());
  }

  @Test
  void testEquals()
  {
    ClientDoctor anotherDoctor = new ClientDoctor(doctorID, firstName, lastName, email, phoneNumber, username, password);
    assertEquals(clientDoctor, anotherDoctor);

    anotherDoctor.setFirstName("Jane");
    assertNotEquals(clientDoctor, anotherDoctor);

    assertNotEquals(null, clientDoctor);
    assertNotEquals("not a doctor", clientDoctor);
  }

  @Test
  void testEqualsWithDifferentId()
  {
    ClientDoctor anotherDoctor = new ClientDoctor(doctorID, firstName, lastName, email, phoneNumber, username, password);
    anotherDoctor.setDoctorID(2);
    assertNotEquals(clientDoctor, anotherDoctor);
  }

  @Test
  void testEqualsWithNull()
  {
    assertNotEquals(clientDoctor, null);
  }

  @Test
  void testEqualsWithDifferentClass()
  {
    assertNotEquals(clientDoctor, new Object());
  }

  @Test
  void testEqualsWithDifferentFirstNames()
  {
    ClientDoctor anotherDoctor = new ClientDoctor(doctorID, "Jane", lastName, email, phoneNumber, username, password);
    assertNotEquals(clientDoctor, anotherDoctor);
  }
  @Test
  void testEqualsWithDifferentLastNames()
  {
    ClientDoctor anotherDoctor = new ClientDoctor(doctorID, firstName, "Smith", email, phoneNumber, username, password);
    assertNotEquals(clientDoctor, anotherDoctor);
  }


}