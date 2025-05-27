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

/**
 * FrontViewController is responsible for managing the front view of the book appointment feature.
 * It initializes the appointment table and handles user interactions such as viewing and canceling appointments.
 */
public class FrontViewController
{
  private BookAppointmentFrontViewModel viewModel;
  @FXML private TableView<ClientAppointment> appointmentTable;
  @FXML private TableColumn<ClientAppointment, String> idCol;
  @FXML private TableColumn<ClientAppointment, String> dateCol;
  @FXML private TableColumn<ClientAppointment, String> timeCol;
  @FXML private TableColumn<ClientAppointment, String> doctorCol;
  @FXML private TableColumn<ClientAppointment, String> modeCol;
  @FXML private AnchorPane pane;
  @FXML private Label mode;
  @FXML private Label date;

  /**
   * Initializes the FrontViewController with the provided view model.
   * @param viewModel the view model containing appointment data
   */
  public void init(BookAppointmentFrontViewModel viewModel)
  {
    this.viewModel = viewModel;
    ObservableList<ClientAppointment> observableAppointments = FXCollections.observableArrayList(
        viewModel.getAppointmentList().getAllAppointments());

    idCol.setCellValueFactory(
        cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAppointmentID())));

    dateCol.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

    timeCol.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getTime()));

    doctorCol.setCellValueFactory(
        cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDoctor())));

    modeCol.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getMode()));

    appointmentTable.setItems(observableAppointments);
    appointmentTable.setOnMouseClicked(event -> manageAppointment());
  }

  /**
   * Manages the appointment by displaying its details in the pane.
   * This method is called when an appointment is selected from the table.
   */
  public void manageAppointment()
  {
    pane.setVisible(true);

    ClientAppointment selected = appointmentTable.getSelectionModel()
        .getSelectedItem();
    //    doctorName.setText(selected.getDoctorName());

    mode.setText(selected.getMode());
    date.setText(selected.getDate());
  }

  /**
   * Cancels the selected appointment and removes it from the appointment list.
   * This method is called when the user chooses to cancel an appointment.
   */
  public void cancelAppointment()
  {
    ClientAppointment selected = appointmentTable.getSelectionModel().getSelectedItem();

    if (selected == null)
      return; // Optional: show a message to select an appointment

    boolean cancelled = viewModel.cancelAppointment(selected.getAppointmentId());

    if (cancelled)
    {
      viewModel.getAppointmentList().removeAppointment(selected);
      // Optional: show confirmation message to user
    }
    else
    {
      // Optional: show error alert to user
    }

    back();
  }

  /**
   * Adds a new appointment by showing the book appointment view.
   * This method is called when the user chooses to add a new appointment.
   */
  public void addNewAppointment()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.DOCTOR);
  }

  /**
   * Navigates back to the front view of the book appointment feature.
   * This method is called when the user chooses to go back.
   */
  public void back()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }
}
