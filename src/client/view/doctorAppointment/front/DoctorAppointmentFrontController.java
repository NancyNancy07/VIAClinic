package client.view.doctorAppointment.front;

import client.model.clientBookAppointment.ClientAppointment;
import client.model.clientBookAppointment.ClientNewDateTime;
import client.viewModel.doctorAppointment.DoctorAppointmentViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import server.model.bookAppointment.Patient;

import java.time.LocalDate;

/**
 * DoctorAppointmentFrontController handles the front-end logic for managing doctor appointments.
 * It allows doctors to view, reschedule, and confirm appointments with patients.
 */
public class DoctorAppointmentFrontController
{
  private DoctorAppointmentViewModel viewModel;
  @FXML private TableView<ClientAppointment> appointmentTable;
  @FXML private Label doctorName;
  @FXML private Label doctorN;
  @FXML private Label patientName;
  @FXML private TextField mode;
  @FXML private DatePicker date;
  @FXML private AnchorPane pane;
  @FXML private ComboBox<String> time;
  @FXML private Button rescheduleButton;
  @FXML private Button confirmButton;

  @FXML private TableColumn<ClientAppointment, String> idCol;
  @FXML private TableColumn<ClientAppointment, String> dateCol;
  @FXML private TableColumn<ClientAppointment, String> timeCol;
  @FXML private TableColumn<ClientAppointment, String> patientCol;
  @FXML private TableColumn<ClientAppointment, String> modeCol;

  /**
   * Initializes the DoctorAppointmentFrontController with the provided view model.
   *
   * @param viewModel the view model containing appointment data and logic
   */
  public void init(DoctorAppointmentViewModel viewModel)
  {
    this.viewModel = viewModel;
    ObservableList<ClientAppointment> observableAppointments = FXCollections.observableArrayList(
        viewModel.getDoctorAppointmentList().getAllAppointments());

    idCol.setCellValueFactory(cellData -> new SimpleStringProperty(
        String.valueOf(cellData.getValue().getAppointmentID())));

    dateCol.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

    timeCol.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getTime()));

    patientCol.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getPatient().getUsername()));

    modeCol.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getMode()));

    appointmentTable.setItems(observableAppointments);
    doctorName.setText(viewModel.getUsername());

    appointmentTable.setOnMouseClicked(event -> manageAppointment());
  }

  /**
   * Manages the selected appointment by displaying its details in the pane.
   * This method is called when an appointment is selected from the table.
   */
  public void manageAppointment()
  {
    pane.setVisible(true);
    confirmButton.setVisible(false);
    doctorN.setText(viewModel.getUsername());
    ClientAppointment selected = appointmentTable.getSelectionModel()
        .getSelectedItem();
    int patientId = selected.getPatientID();
    patientName.setText(selected.getPatient().getName());
    mode.setText(selected.getMode());

    String[] parts = selected.getDate().split("/");
    int day = Integer.parseInt(parts[0]);
    int month = Integer.parseInt(parts[1]);
    int year = Integer.parseInt(parts[2]);
    date.setValue(LocalDate.of(year, month, day));
    time.setValue(selected.getTime());
  }

  /**
   * Reschedules the selected appointment by enabling the input fields for mode, date, and time.
   * This method is called when the reschedule button is clicked.
   */
  public void rescheduleAppointment()
  {
    mode.setDisable(false);
    mode.setEditable(true);
    time.setDisable(false);
    date.setDisable(false);
    rescheduleButton.setVisible(false);
    confirmButton.setVisible(true);
  }

  /**
   * Sends a reschedule request for the selected appointment with the new mode, date, and time.
   * This method is called when the confirm button is clicked.
   */
  public void sendRescheduleRequest()
  {
    ClientAppointment selected = appointmentTable.getSelectionModel()
        .getSelectedItem();
    if (selected == null)
      return;

    String newMode = mode.getText();
    LocalDate newDate = date.getValue();
    String selectedTime = time.getValue();

    if (newDate == null || newMode == null || newMode.isEmpty()
        || selectedTime == null || selectedTime.isEmpty())
      return;

    String[] timeParts = selectedTime.split(":");
    int hour = Integer.parseInt(timeParts[0]);
    int minute = Integer.parseInt(timeParts[1]);

    ClientNewDateTime newDateTime = new ClientNewDateTime(
        newDate.getDayOfMonth(), newDate.getMonthValue(), newDate.getYear(),
        hour, minute);
    viewModel.rescheduleAppointment(selected, newMode, newDateTime);

    appointmentTable.refresh();
    confirmButton.setVisible(false);
    rescheduleButton.setVisible(true);
    mode.setDisable(true);
    time.setDisable(true);
    date.setDisable(true);
    pane.setVisible(false);
  }

  /**
   * Confirms the selected appointment by marking it as confirmed in the system.
   * This method is called when the confirm button is clicked.
   */
  public void back()
  {
    pane.setVisible(false);
  }
}
