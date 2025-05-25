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

import java.time.LocalDate;

public class DoctorAppointmentFrontController
{
  private DoctorAppointmentViewModel viewModel;
  @FXML private TableView<ClientAppointment> appointmentTable;
  @FXML private TableColumn<ClientAppointment, String> appointment;
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
        String.valueOf(cellData.getValue().getPatientID())));

    modeCol.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getMode()));

    appointmentTable.setItems(observableAppointments);
    doctorName.setText(viewModel.getUsername());

    appointmentTable.setOnMouseClicked(event -> manageAppointment());
  }

  public void manageAppointment()
  {
    pane.setVisible(true);
    confirmButton.setVisible(false);
    doctorN.setText(viewModel.getUsername());
    ClientAppointment selected = appointmentTable.getSelectionModel()
        .getSelectedItem();
    int patientId = selected.getPatientID();
    System.out.println(selected.getAppointmentID());
    patientName.setText(viewModel.getPatient(patientId).getName());
    mode.setText(selected.getMode());

    String[] parts = selected.getDate().split("/");
    int day = Integer.parseInt(parts[0]);
    int month = Integer.parseInt(parts[1]);
    int year = Integer.parseInt(parts[2]);
    date.setValue(LocalDate.of(year, month, day));
    time.setValue(selected.getTime());
  }

  public void rescheduleAppointment()
  {
    mode.setDisable(false);
    mode.setEditable(true);
    time.setDisable(false);
    date.setDisable(false);
    rescheduleButton.setVisible(false);
    confirmButton.setVisible(true);
  }

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
  }

  public void back()
  {
    pane.setVisible(false);
  }
}
