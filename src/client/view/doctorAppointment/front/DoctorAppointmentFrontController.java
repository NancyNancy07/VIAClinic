package client.view.doctorAppointment.front;

import client.model.clientBookAppointment.ClientAppointment;
import client.viewModel.bookAppointment.BookAppointmentFrontViewModel;
import client.viewModel.doctorAppointment.DoctorAppointmentViewModel;
import client.viewModel.loginSystem.LoginSharedData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DoctorAppointmentFrontController
{
  private DoctorAppointmentViewModel viewModel;
  @FXML private TableView<ClientAppointment> appointmentTable;
  @FXML private TableColumn<ClientAppointment, String> appointment;
  @FXML private Label doctorName;

  public void init(DoctorAppointmentViewModel viewModel)
  {
    this.viewModel = viewModel;
    ObservableList<ClientAppointment> observableAppointments = FXCollections.observableArrayList(
        viewModel.getDoctorAppointmentList().getAllAppointments());

    appointment.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));

    appointmentTable.setItems(observableAppointments);
    doctorName.setText(viewModel.getUsername());
  }

}
