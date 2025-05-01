package client.view.bookAppointment.front;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import server.model.bookAppointment.Appointment;
import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.BookAppointmentViewModel;
import client.viewModel.bookAppointment.SharedData;

public class FrontViewController
{
  private BookAppointmentViewModel viewModel;
  @FXML private TableView<Appointment> appointmentTable;
  @FXML private TableColumn<Appointment, String> appointment;
  @FXML private AnchorPane pane;
  private SharedData sharedData;
  @FXML private Label doctorName;
  @FXML private Label mode;
  @FXML private Label date;

  public void init(BookAppointmentViewModel viewModel, SharedData sharedData)
  {
    this.viewModel = viewModel;
    ObservableList<Appointment> observableAppointments = FXCollections.observableArrayList(
        viewModel.getAppointmentList());

    appointment.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));

    appointmentTable.setItems(observableAppointments);

    appointmentTable.setOnMouseClicked(event -> manageAppointment());
  }

  public void manageAppointment()
  {
    pane.setVisible(true);

    Appointment selected = appointmentTable.getSelectionModel()
        .getSelectedItem();

    doctorName.setText(selected.getDoctorName());

    mode.setText(selected.getMode());
    date.setText(selected.getDate().toString());
  }

  public void cancelAppointment()
  {
    Appointment selected = appointmentTable.getSelectionModel()
        .getSelectedItem();
    viewModel.getAppointmentList().remove(selected);
    back();
  }

  public void addNewAppointment()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.DOCTOR);
  }

  public void back()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }
}
