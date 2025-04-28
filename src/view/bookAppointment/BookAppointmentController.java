package view.bookAppointment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BookAppointmentController
{
  @FXML Button addNew;

  @FXML public void addNewAppointment()
  {
    System.out.println("Appointment added");
  }
}
