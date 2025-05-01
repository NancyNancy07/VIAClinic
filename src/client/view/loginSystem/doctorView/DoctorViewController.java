package client.view.loginSystem.doctorView;

import client.viewModel.loginSystem.AppointmentViewModel;
import client.viewModel.loginSystem.LoginDataStore;
import client.viewModel.loginSystem.LoginViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import server.model.bookAppointment.Appointment;

public class DoctorViewController
{
  @FXML private Label doctorName;
  @FXML private TableView<Appointment> appointmentList;
  @FXML private TableColumn<Appointment, String> appointment;
  private LoginViewModel loginViewModel;
  private AppointmentViewModel appointmentViewModel;

  public DoctorViewController()
  {
  }

  public void init(AppointmentViewModel appointmentViewModel)
  {
    this.appointmentViewModel = appointmentViewModel;
    ObservableList<Appointment> observableDoctor = FXCollections.observableArrayList(
        appointmentViewModel.getAppointmentList());
    this.appointment.setCellValueFactory((cellData) -> {
      return new SimpleStringProperty(
          ((Appointment) cellData.getValue()).toString());
    });
    this.appointmentList.setItems(observableDoctor);

    doctorName.setText(LoginDataStore.getInstance().getDoctorEmail());
  }
}
