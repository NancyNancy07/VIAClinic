package client.view.loginSystem;

import javafx.application.Application;
import javafx.stage.Stage;
import server.model.bookAppointment.*;
import client.viewModel.loginSystem.AppointmentViewModel;
import client.viewModel.bookAppointment.BookAppointmentViewModel;
import client.viewModel.loginSystem.ViewModelFactory;

import java.time.LocalDate;
import java.time.LocalTime;

public class LoginSystemGUI extends Application
{
  private BookAppointmentViewModel viewModel;
  private AppointmentViewModel appointmentViewModel;

  @Override public void start(Stage primaryStage) throws Exception
  {
    //  Create the lists
    DoctorList doctorList = new DoctorList();
    PatientList patientList = new PatientList();
    AppointmentList appointmentList = new AppointmentList();

    //  Add sample doctor and patient
    doctorList.addDoctor(new Doctor(1, "Dr. Smith", "asd123", "123456789"));
    doctorList.addDoctor(new Doctor(2, "Dr. Adams", "asd123", "123456789"));
    doctorList.addDoctor(new Doctor(3, "Dr. Brown", "asd123", "123456789"));
    doctorList.addDoctor(new Doctor(4, "Dr. Lee", "asd123", "123456789"));

    patientList.addPatient(new Patient(1, "John Doe", "asq123", "123"));
    patientList.addPatient(new Patient(2, "Jane Doe", "asw123", "987654"));
    patientList.addPatient(new Patient(3, "Bob Smith", "ase123", "9876543"));
    patientList.addPatient(
        new Patient(4, "Alice White", "asd123", "123456789"));

    //  Create AppointmentService
    AppointmentService appointmentService = new AppointmentService(doctorList,
        patientList, appointmentList);

    //  Book a few appointments

    LocalTime t = LocalTime.now();
    LocalDate d = LocalDate.now();

    DateTime dateTime = new DateTime(d, t);

    Appointment a1 = appointmentService.bookAppointment(1, 1, dateTime,
        "In-person");
    Appointment a2 = appointmentService.bookAppointment(2, 2, dateTime,
        "Online");
    Appointment a3 = appointmentService.bookAppointment(3, 3, dateTime,
        "In-person");
    Appointment a4 = appointmentService.bookAppointment(4, 4, dateTime,
        "Online");

    ViewModelFactory factory = new ViewModelFactory(appointmentService);
    LoginSystemViewHandler viewHandler = new LoginSystemViewHandler(
        primaryStage, factory);
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args)
  {
    launch();
  }
}
