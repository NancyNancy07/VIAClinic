package view.bookAppointment.front;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.bookAppointment.Appointment;
import view.bookAppointment.BookAppointmentViewHandler;
import viewModel.bookAppointment.BookAppointmentViewModel;

public class FrontViewController
{
  private BookAppointmentViewModel viewModel;
  @FXML private TableView<Appointment> appointmentTable;
  @FXML private TableColumn<Appointment, String> appointment;

  public void init(BookAppointmentViewModel viewModel)
  {
    this.viewModel = viewModel;
    ObservableList<Appointment> observableAppointments = FXCollections.observableArrayList(
        viewModel.getAppointmentList());

    appointment.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().toString()));

    appointmentTable.setItems(observableAppointments);

  }

  public void addNewAppointment()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.DOCTOR);
  }
}
