package client.model.clientBookAppointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.patientJournal.Address;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientAppointmentListTest
{

  ClientAppointmentList appointmentList;
  private ClientNewDateTime dateTime;
  private ClientDoctor doctor;
  private ClientPatient patient;
  private ClientAppointment appointment;
  @BeforeEach void setUp()
  {
    dateTime = new ClientNewDateTime(12, 5, 2025, 14, 30);
    doctor = new ClientDoctor(1, "Gregory", "House", "house@hospital.com", "12345678", "docUser", "pass123");
    Address address = new Address("CityX", "1234", "Main Street");
    patient = new ClientPatient(2, "Lisa", "Cuddy", "lisa@clinic.com", "87654321", "lisaC", "pwd", "010101-1234", address);
    appointment = new ClientAppointment(1, dateTime, patient.getPatientID(), doctor, "in-person");
    appointmentList = new ClientAppointmentList();
  }

  @Test void addAppointment()
  {
    assertEquals(0, appointmentList.getSize());
    appointmentList.addAppointment(appointment);
    assertEquals(1, appointmentList.getSize());
  }

  @Test void getSize()
  {
    assertEquals(0, appointmentList.getSize());
    appointmentList.addAppointment(appointment);
    assertEquals(1, appointmentList.getSize());
    ClientAppointment anotherAppointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "online");
    appointmentList.addAppointment(anotherAppointment);
    assertEquals(2, appointmentList.getSize());
  }

  @Test void findAppointmentByID()
  {
    assertEquals(0, appointmentList.getSize());
    appointmentList.addAppointment(appointment);
    assertEquals(1, appointmentList.getSize());

    ClientAppointment foundAppointment = appointmentList.findAppointmentByID(appointment.getAppointmentID());
    assertNotNull(foundAppointment);
    assertEquals(appointment, foundAppointment);

    ClientAppointment notFoundAppointment = appointmentList.findAppointmentByID(9999);
    assertNull(notFoundAppointment);
  }

  @Test void removeAppointment()
  {
    assertEquals(0, appointmentList.getSize());
    appointmentList.addAppointment(appointment);
    assertEquals(1, appointmentList.getSize());

    ClientAppointment foundAppointment = appointmentList.findAppointmentByID(appointment.getAppointmentID());
    assertNotNull(foundAppointment);
    assertEquals(appointment, foundAppointment);

    appointmentList.removeAppointment(appointment);
    assertEquals(0, appointmentList.getSize());

    foundAppointment = appointmentList.findAppointmentByID(appointment.getAppointmentID());
    assertNull(foundAppointment);
  }

  @Test void getAllAppointments()
  {
    assertEquals(0, appointmentList.getSize());
    appointmentList.addAppointment(appointment);
    assertEquals(1, appointmentList.getSize());
    ClientAppointment anotherAppointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "online");
    appointmentList.addAppointment(anotherAppointment);
    assertEquals(2, appointmentList.getSize());

    List<ClientAppointment> allAppointments = appointmentList.getAllAppointments();
    assertEquals(2, allAppointments.size());
    assertTrue(allAppointments.contains(appointment));
    assertTrue(allAppointments.contains(anotherAppointment));
  }

  @Test void testToString()
  {
    assertEquals("", appointmentList.toString());
    appointmentList.addAppointment(appointment);
    assertEquals(appointmentList.findAppointmentByID(1).toString(), appointmentList.toString());
  }

  @Test void testEquals()
  {
    ClientAppointmentList anotherList = new ClientAppointmentList();
    assertEquals(appointmentList, anotherList);

    appointmentList.addAppointment(appointment);
    anotherList.addAppointment(appointment);
    assertEquals(appointmentList, anotherList);

    ClientAppointment differentAppointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "online");
    anotherList.addAppointment(differentAppointment);
    assertNotEquals(appointmentList, anotherList);

    anotherList.removeAppointment(differentAppointment);
    assertEquals(appointmentList, anotherList);
  }

  @Test
  void testEqualsWithDifferentSizes()
  {
    ClientAppointmentList anotherList = new ClientAppointmentList();
    appointmentList.addAppointment(appointment);
    anotherList.addAppointment(appointment);
    anotherList.addAppointment(new ClientAppointment(dateTime, patient.getPatientID(), doctor, "online"));

    assertNotEquals(appointmentList, anotherList);
  }

  @Test
  void testEqualsWithNull()
  {
    assertNotEquals(appointmentList, null);
  }

  @Test
  void testEqualsWithDifferentClass()
  {
    assertNotEquals(appointmentList, "Not an appointment list");
  }

  @Test
  void testEqualsWithSameReference()
  {
    ClientAppointmentList sameReference = appointmentList;
    assertEquals(appointmentList, sameReference);
  }
}