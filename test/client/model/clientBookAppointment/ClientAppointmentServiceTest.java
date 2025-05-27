package client.model.clientBookAppointment;

import client.clientNetwork.PatientAppointmentClient;
import client.clientNetwork.PatientClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.model.clientBookAppointment.*;
import org.mockito.Mockito;
import server.model.patientJournal.Address;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClientAppointmentServiceTest
{

  private ClientAppointmentList appointmentList;
  private ClientDoctorList doctorList;
  private PatientAppointmentClient mockClient;
  private PatientClient patientClient;

  private ClientDoctor doctor;
  private ClientNewDateTime dateTime;

  private ClientAppointmentService service;

  @BeforeEach void setUp()
  {
    this.appointmentList = new ClientAppointmentList();
    this.doctorList = new ClientDoctorList();
    this.mockClient = new PatientAppointmentClient();
    this.patientClient = new PatientClient();

    doctor = new ClientDoctor(1, "John", "Doe", "john@hospital.com", "1234567890", "docuser", "pass123");
    dateTime = new ClientNewDateTime(12, 5, 2025, 14, 30);

    mockClient = mock(PatientAppointmentClient.class);

    service = new ClientAppointmentService() {
      {
        try {
          var field = ClientAppointmentService.class.getDeclaredField("networkClient");
          field.setAccessible(true);
          field.set(this, mockClient);
        } catch (Exception e) {
          fail("Reflection injection failed: " + e.getMessage());
        }
      }
    };
  }

  @Test
  void testBookAppointment_returnsAppointment() {
    ClientAppointment fakeResponse = new ClientAppointment(dateTime, 42, doctor, "in-person");

    when(mockClient.bookAppointment(any(ClientAppointment.class))).thenReturn(fakeResponse);

    ClientAppointment result = service.bookAppointment(dateTime, 42, doctor, "in-person");

    assertNotNull(result);
    assertEquals("in-person", result.getMode());
    assertEquals(42, result.getPatientID());
    assertEquals(doctor, result.getDoctor());

    verify(mockClient).bookAppointment(any(ClientAppointment.class));
  }

  @Test void cancelAppointment()
  {
    boolean result = service.cancelAppointment(1);
    assertFalse(result);
  }

  @Test
  void testModifyAppointment_returnsModifiedAppointment()
  {
    ClientAppointment modified = new ClientAppointment(99, dateTime, 42, doctor, "video");
    when(mockClient.modifyAppointment(anyInt(), anyInt(), any(ClientDoctor.class), any(ClientNewDateTime.class), anyString()))
        .thenReturn(modified);

    ClientAppointment result = service.modifyAppointment(99, 42, doctor, dateTime, "video");

    assertNotNull(result);
    assertEquals(99, result.getAppointmentID());
    assertEquals("video", result.getMode());
    verify(mockClient).modifyAppointment(anyInt(), anyInt(), any(), any(), any());
  }


  @Test
  void testGetAppointmentList_returnsCorrectList()
  {
    ClientAppointmentList mockedList = new ClientAppointmentList();
    mockedList.addAppointment(new ClientAppointment(dateTime, 42, doctor, "in-person"));

    when(mockClient.getAppointmentByPatientId(42)).thenReturn(mockedList);

    ClientAppointmentList result = service.getAppointmentList(42);

    assertNotNull(result);
    assertEquals(1, result.getSize());
    verify(mockClient).getAppointmentByPatientId(42);
  }

  @Test
  void testGetDoctorAppointmentList_returnsUpdatedList()
  {
    ClientAppointment appt1 = new ClientAppointment(dateTime, 42, doctor, "online");
    ClientAppointmentList fetched = new ClientAppointmentList();
    fetched.addAppointment(appt1);

    when(mockClient.getAppointmentByDoctorId(doctor.getDoctorID())).thenReturn(fetched);

    ClientAppointmentList result = service.getDoctorAppointmentList(doctor.getDoctorID());

    assertNotNull(result);
    assertEquals(1, result.getSize());
    assertEquals(appt1, result.getAllAppointments().get(0));
    verify(mockClient).getAppointmentByDoctorId(doctor.getDoctorID());
  }

  @Test
  void testGetDoctorList_returnsUpdatedList()
  {
    ClientDoctorList fetched = new ClientDoctorList();
    fetched.addDoctor(doctor);

    when(mockClient.getDoctorList()).thenReturn(fetched);

    ClientDoctorList result = service.getDoctorList();

    assertNotNull(result);
    assertEquals(1, result.getNumberOfDoctors());
    assertEquals(doctor.getDoctorID(), result.getAllDoctors().get(0).getDoctorID());
    verify(mockClient).getDoctorList();
  }
}