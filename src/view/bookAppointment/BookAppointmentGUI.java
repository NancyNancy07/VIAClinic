package view.bookAppointment;

import javafx.application.Application;
import javafx.stage.Stage;
import model.bookAppointment.*;
import viewModel.bookAppointment.BookAppointmentViewModel;
import viewModel.bookAppointment.SharedData;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookAppointmentGUI extends Application
{
  private BookAppointmentViewModel viewModel;

  @Override public void start(Stage primaryStage) throws Exception
  {
    //  Create the lists
    DoctorList doctorList = new DoctorList();
    PatientList patientList = new PatientList();
    AppointmentList appointmentList = new AppointmentList();

    //  Add sample doctor and patient
    doctorList.addDoctor(new Doctor(1, "Dr. Smith"));
    doctorList.addDoctor(new Doctor(2, "Dr. Adams"));
    doctorList.addDoctor(new Doctor(3, "Dr. Brown"));
    doctorList.addDoctor(new Doctor(4, "Dr. Lee"));

    patientList.addPatient(new Patient(1, "John Doe"));
    patientList.addPatient(new Patient(2, "Jane Doe"));
    patientList.addPatient(new Patient(3, "Bob Smith"));
    patientList.addPatient(new Patient(4, "Alice White"));

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

    viewModel = new BookAppointmentViewModel(appointmentService);
    SharedData sharedData = new SharedData();
    BookAppointmentViewHandler viewHandler = new BookAppointmentViewHandler(
        primaryStage, viewModel, sharedData);
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args)
  {
    launch();
  }
}
