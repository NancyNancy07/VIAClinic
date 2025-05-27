package client.model.clientBookAppointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientDoctorListTest
{

  private ClientDoctorList clientDoctorList;
  private ClientDoctor doctor1;

  private int doctorID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String username;
  private String password;

  @BeforeEach void setUp()
  {
    doctorID = 1;
    firstName = "John";
    lastName = "Doe";
    email = "asdasd@gmail.com";
    phoneNumber = "1234567890";
    username = "docUser";
    password = "pass123";
    doctor1 = new ClientDoctor(doctorID, firstName, lastName, email, phoneNumber, username, password);
    clientDoctorList = new ClientDoctorList();


  }

  @Test void addDoctor()
  {
    assertEquals(0, clientDoctorList.getNumberOfDoctors());
    clientDoctorList.addDoctor(doctor1);
    assertEquals(1, clientDoctorList.getNumberOfDoctors());
    assertTrue(clientDoctorList.getAllDoctors().contains(doctor1));
  }

  @Test void findDoctorByID()
  {
    clientDoctorList.addDoctor(doctor1);
    ClientDoctor foundDoctor = clientDoctorList.findDoctorByID(doctorID);
    assertNotNull(foundDoctor);
    assertEquals(doctor1, foundDoctor);
  }

  @Test void removeDoctor()
  {
    clientDoctorList.addDoctor(doctor1);
    assertEquals(1, clientDoctorList.getNumberOfDoctors());
    clientDoctorList.removeDoctor(doctor1);
    assertEquals(0, clientDoctorList.getNumberOfDoctors());
    assertNull(clientDoctorList.findDoctorByID(doctorID));
  }

  @Test void testRemoveDoctorByIndex()
  {
    clientDoctorList.addDoctor(doctor1);
    assertEquals(1, clientDoctorList.getNumberOfDoctors());
    clientDoctorList.removeDoctor(0);
    assertEquals(0, clientDoctorList.getNumberOfDoctors());
    assertNull(clientDoctorList.findDoctorByID(doctorID));
  }

  @Test void getNumberOfDoctors()
  {
    assertEquals(0, clientDoctorList.getNumberOfDoctors());
    clientDoctorList.addDoctor(doctor1);
    assertEquals(1, clientDoctorList.getNumberOfDoctors());
  }

  @Test void getAllDoctors()
  {
    assertTrue(clientDoctorList.getAllDoctors().isEmpty());
    clientDoctorList.addDoctor(doctor1);
    assertFalse(clientDoctorList.getAllDoctors().isEmpty());
    assertTrue(clientDoctorList.getAllDoctors().contains(doctor1));
  }

  @Test void testToString()
  {
    String expectedString = "DoctorList{" + "doctors=[]" +  '}';
    assertEquals(expectedString, clientDoctorList.toString());
    clientDoctorList.addDoctor(doctor1);
    expectedString = "DoctorList{" + "doctors=" + clientDoctorList.getAllDoctors() + '}';
    assertEquals(expectedString, clientDoctorList.toString());
  }

  @Test void testEqualsWithNull()
  {
    assertNotEquals(clientDoctorList, null);
  }

  @Test void testEqualsWithDifferentClass()
  {
    assertNotEquals(clientDoctorList, new Object());
  }

  @Test void testEqualsWithSameInstance()
  {
    ClientDoctorList anotherList = clientDoctorList;
    assertEquals(clientDoctorList, anotherList);
  }

  @Test void testEqualsWithDifferentNumberOfDoctors()
  {
    ClientDoctorList anotherList = new ClientDoctorList();
    anotherList.addDoctor(doctor1);
    assertNotEquals(clientDoctorList, anotherList);
  }

  @Test void testEqualsWithSameDoctors()
  {
    ClientDoctorList anotherList = new ClientDoctorList();
    anotherList.addDoctor(doctor1);
    clientDoctorList.addDoctor(doctor1);
    assertEquals(clientDoctorList, anotherList);
  }


}