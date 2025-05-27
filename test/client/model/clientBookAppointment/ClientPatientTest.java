package client.model.clientBookAppointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.patientJournal.Address;

import static org.junit.jupiter.api.Assertions.*;

class ClientPatientTest
{
  private int patientID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String CPR;
  private Address address;
  private String username;
  private String password;
  private ClientPatient clientPatient;

  @BeforeEach void setUp()
  {
    patientID = 1;
    firstName = "Jane";
    lastName = "Doe";
    email = "asdasd@gmail.com";
    phoneNumber = "1234567890";
    CPR = "123456-7890";
    address = new Address("Horsens", "8700", "Borgergade");
    username = "patientUser";
    password = "pass123";
    clientPatient = new ClientPatient(patientID, firstName, lastName, email, phoneNumber, username, password, CPR, address);

  }


  @Test void constructorWithoutId()
  {
    ClientPatient clientPatientWithoutId = new ClientPatient(firstName, lastName, email, phoneNumber, username, password, CPR, address);
    assertEquals(firstName, clientPatientWithoutId.getFirstName());
    assertEquals(lastName, clientPatientWithoutId.getLastName());
    assertEquals(email, clientPatientWithoutId.getEmail());
    assertEquals(phoneNumber, clientPatientWithoutId.getPhoneNumber());
    assertEquals(username, clientPatientWithoutId.getUsername());
    assertEquals(password, clientPatientWithoutId.getPassword());
  }
  @Test void getPatientID()
  {
    assertEquals(1, clientPatient.getPatientID());
  }

  @Test void getName()
  {
    assertEquals("Jane Doe", firstName + " " + lastName);
  }

  @Test void getFirstName()
  {
    assertEquals("Jane", clientPatient.getFirstName());
  }

  @Test void getLastName()
  {
    assertEquals("Doe", clientPatient.getLastName());
  }

  @Test void getPhoneNumber()
  {
    assertEquals("1234567890", clientPatient.getPhoneNumber());
  }

  @Test void testGetUsername()
  {
    assertEquals("patientUser", clientPatient.getUsername());
  }

  @Test void testGetPassword()
  {
    assertEquals("pass123", clientPatient.getPassword());
  }

  @Test void getEmail()
  {
    assertEquals("asdasd@gmail.com", clientPatient.getEmail());
  }

  @Test void setPatientID()
  {
    int newPatientID = 2;
    clientPatient.setPatientID(newPatientID);
    assertEquals(newPatientID, clientPatient.getPatientID());
  }

  @Test void setName()
  {
    String newName = "Alice Smith";
    clientPatient.setName(newName);
    assertEquals("Alice", clientPatient.getFirstName());
    assertEquals("Smith", clientPatient.getLastName());
  }


  @Test void testToString()
  {
    String expectedString = "PatientID= " + patientID + ", Name= '" + clientPatient.getName() + '\'';
    assertEquals(expectedString, clientPatient.toString());
  }

  @Test void testEqualsWithNull()
  {
    assertFalse(clientPatient.equals(null));
  }

  @Test void testEqualsWithDifferentClass()
  {
    assertFalse(clientPatient.equals("Not a ClientPatient"));
  }

  @Test void testEqualsWithSameObject()
  {
    assertTrue(clientPatient.equals(clientPatient));
  }

  @Test void testEqualsWithSameValues()
  {
    ClientPatient anotherPatient = new ClientPatient(patientID, firstName, lastName, email, phoneNumber, username, password, CPR, address);
    assertTrue(clientPatient.equals(anotherPatient));
  }

  @Test void testEqualsWithDifferentId()
  {
    ClientPatient anotherPatient = new ClientPatient(2, firstName, lastName, email, phoneNumber, username, password, CPR, address);
    assertFalse(clientPatient.equals(anotherPatient));
  }

  @Test void testEqualsWithDifferentName()
  {
    ClientPatient anotherPatient = new ClientPatient(patientID, "Alice", "Smith", email, phoneNumber, username, password, CPR, address);
    assertFalse(clientPatient.equals(anotherPatient));
  }

  @Test void testEqualsWithDifferentEmail()
  {
    ClientPatient anotherPatient = new ClientPatient(patientID, firstName, lastName, "bangarang@gmail.com", phoneNumber, username, password, CPR, address);
    assertFalse(clientPatient.equals(anotherPatient));
  }

  @Test void testEqualsWithDifferentPhoneNumber()
  {
    ClientPatient anotherPatient = new ClientPatient(patientID, firstName, lastName, email, "0987654321", username, password, CPR, address);
    assertFalse(clientPatient.equals(anotherPatient));
  }

  @Test void testEqualsWithDifferentCPR()
  {
    ClientPatient anotherPatient = new ClientPatient(patientID, firstName, lastName, email, phoneNumber, username, password, "098765-4321", address);
    assertFalse(clientPatient.equals(anotherPatient));
  }

  @Test void testEqualsWithDifferentAddress()
  {
    Address newAddress = new Address("Aarhus", "8000", "Main Street");
    ClientPatient anotherPatient = new ClientPatient(patientID, firstName, lastName, email, phoneNumber, username, password, CPR, newAddress);
    assertFalse(clientPatient.equals(anotherPatient));
  }
}