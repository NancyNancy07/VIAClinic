package client.model.clientBookAppointment;

import static org.junit.jupiter.api.Assertions.*;
import client.model.clientBookAppointment.ClientAppointment;
import client.model.clientBookAppointment.ClientDoctor;
import client.model.clientBookAppointment.ClientNewDateTime;
import client.model.clientBookAppointment.ClientPatient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Address;

import static org.junit.jupiter.api.Assertions.*;

public class ClientAppointmentTest {

  private ClientNewDateTime dateTime;
  private ClientDoctor doctor;
  private ClientPatient patient;

  @BeforeEach
  void setUp() {
    dateTime = new ClientNewDateTime(12, 5, 2025, 14, 30);
    doctor = new ClientDoctor(1, "Gregory", "House", "house@hospital.com", "12345678", "docUser", "pass123");
    Address address = new Address("CityX", "1234", "Main Street");
    patient = new ClientPatient(2, "Lisa", "Cuddy", "lisa@clinic.com", "87654321", "lisaC", "pwd", "010101-1234", address);
  }

  @Test
  void testBookAppointment() {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertNotNull(appointment);
    assertEquals(dateTime, appointment.getDateTime());
    assertEquals(patient.getPatientID(), appointment.getPatientID());
    assertEquals(doctor, appointment.getDoctor());
    assertEquals("in-person", appointment.getMode());
  }

  @Test void getPatient()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient, doctor, "in-person");
    assertEquals(patient, appointment.getPatient());
  }

  @Test void setPatient()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient, doctor, "in-person");
    assertEquals(patient, appointment.getPatient());
    ClientPatient patient2 = new ClientPatient(3, "Turbo", "Titan", "asdasd@gmail.com", "22232223", "turbo123", "titan123", "454545-6892", new Address("CityY", "5678", "Second Street"));
    appointment.setPatient(patient2);
    assertEquals(patient2, appointment.getPatient());
  }

  @Test void getDate()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertEquals(dateTime.getDate(), appointment.getDate());
  }

  @Test void getTime()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertEquals(dateTime.getTime(), appointment.getTime());
  }

  @Test void getAppointmentID()
  {
    ClientAppointment appointment = new ClientAppointment(1, dateTime, patient, doctor, "in-person");
    assertEquals(1, appointment.getAppointmentID());
  }

  @Test void getDoctor()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertEquals(doctor, appointment.getDoctor());
  }

  @Test void getPatientID()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertEquals(patient.getPatientID(), appointment.getPatientID());
  }

  @Test void setDateTime()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertEquals(dateTime, appointment.getDateTime());
    ClientNewDateTime newDateTime = new ClientNewDateTime(13, 5, 2025, 15, 0);
    appointment.setDateTime(newDateTime);
    assertEquals(newDateTime, appointment.getDateTime());
  }

  @Test void setAppointmentID()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertEquals(0, appointment.getAppointmentID());
    appointment.setAppointmentID(1);
    assertEquals(1, appointment.getAppointmentID());
  }

  @Test void setDoctorID()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertEquals(doctor, appointment.getDoctor());
    ClientDoctor doctor1 = new ClientDoctor(3, "Bangarang", "House", "house@hospital.com", "12345678", "docUser", "pass123");
    appointment.setDoctorID(doctor1);
    assertEquals(doctor1, appointment.getDoctor());
  }

  @Test void setMode()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertEquals("in-person", appointment.getMode());
    appointment.setMode("voice");
    assertEquals("voice", appointment.getMode());
  }

  @Test void setPatientID()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertEquals(patient.getPatientID(), appointment.getPatientID());
    int newPatientID = 3;
    appointment.setPatientID(newPatientID);
    assertEquals(newPatientID, appointment.getPatientID());
  }

  @Test void getMode()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertEquals("in-person", appointment.getMode());
  }

  @Test void getDateTime()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertEquals(dateTime, appointment.getDateTime());
  }

  @Test void testToString()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    String expectedString = "Appointment ID: " + appointment.getAppointmentID() + ", " + dateTime + ", Doctor= "
        + doctor.getName() + ", Mode='" + appointment.getMode() + "'\n";
    assertEquals(expectedString, appointment.toString());
  }

  @Test void testEquals()
  {
    ClientAppointment appointment1 = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    ClientAppointment appointment2 = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertEquals(appointment1, appointment2);
    appointment2.setMode("voice");
    assertNotEquals(appointment1, appointment2);
  }

  @Test
  void testEquals_nullObject_returnsFalse()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertNotEquals(null, appointment);
  }

  @Test
  void testEquals_differentClass_returnsFalse()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertNotEquals("not an appointment", appointment);
  }

  @Test
  void testEquals_differentDateTime_returnsFalse()
  {
    ClientNewDateTime differentTime = new ClientNewDateTime(13, 5, 2025, 10, 45);
    ClientAppointment a1 = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    ClientAppointment a2 = new ClientAppointment(differentTime, patient.getPatientID(), doctor, "in-person");
    assertNotEquals(a1, a2);
  }

  @Test
  void testEquals_differentAppointmentID_returnsFalse()
  {
    ClientAppointment a1 = new ClientAppointment(1, dateTime, patient.getPatientID(), doctor, "in-person");
    ClientAppointment a2 = new ClientAppointment(2, dateTime, patient.getPatientID(), doctor, "in-person");
    assertNotEquals(a1, a2);
  }

  @Test
  void testEquals_differentDoctor_returnsFalse()
  {
    ClientDoctor otherDoctor = new ClientDoctor(2, "Other", "Doctor", "email", "000", "user", "pass");
    ClientAppointment a1 = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    ClientAppointment a2 = new ClientAppointment(dateTime, patient.getPatientID(), otherDoctor, "in-person");
    assertNotEquals(a1, a2);
  }

  @Test
  void testEquals_differentPatientID_returnsFalse()
  {
    ClientAppointment a1 = new ClientAppointment(dateTime, 1, doctor, "in-person");
    ClientAppointment a2 = new ClientAppointment(dateTime, 2, doctor, "in-person");
    assertNotEquals(a1, a2);
  }

  @Test
  void testEquals_differentMode_returnsFalse()
  {
    ClientAppointment a1 = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    ClientAppointment a2 = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "video");
    assertNotEquals(a1, a2);
  }

  @Test
  void testEquals_nullAppointment_returnsFalse()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertFalse(appointment.equals(null));

  }
  @Test
  void testEquals_differentClassObject_returnsFalse()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");


    Object obj = new Object();
    assertFalse(appointment.equals(obj));
  }

  @Test
  void testEquals_sameObject_returnsTrue()
  {
    ClientAppointment appointment = new ClientAppointment(dateTime, patient.getPatientID(), doctor, "in-person");
    assertTrue(appointment.equals(appointment));
  }
}
