package client.view.bookAppointment.front;

import client.model.clientBookAppointment.ClientAppointment;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.BookAppointmentFrontViewModel;

public class FrontViewController
{
  private BookAppointmentFrontViewModel viewModel;
  @FXML private TableView<ClientAppointment> appointmentTable;
  @FXML private TableColumn<ClientAppointment, String> appointment;
  @FXML private AnchorPane pane;
  @FXML private Label doctorName;
  @FXML private Label mode;
  @FXML private Label date;

  public void init(BookAppointmentFrontViewModel viewModel)
  {
    this.viewModel = viewModel;
    ObservableList<ClientAppointment> observableAppointments = FXCollections.observableArrayList(
        viewModel.getAppointmentList().getAllAppointments());

    appointment.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));

    appointmentTable.setItems(observableAppointments);

    appointmentTable.setOnMouseClicked(event -> manageAppointment());
  }

  public void manageAppointment()
  {
    pane.setVisible(true);

    ClientAppointment selected = appointmentTable.getSelectionModel()
        .getSelectedItem();

    //    doctorName.setText(selected.getDoctorName());

    mode.setText(selected.getMode());
    date.setText(selected.getDate().toString());
  }

  public void cancelAppointment()
  {
    ClientAppointment selected = appointmentTable.getSelectionModel()
        .getSelectedItem();
    viewModel.getAppointmentList().removeAppointment(selected);
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
