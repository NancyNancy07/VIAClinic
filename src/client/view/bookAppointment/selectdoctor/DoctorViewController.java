package client.view.bookAppointment.selectdoctor;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import server.model.bookAppointment.Doctor;
import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.BookAppointmentViewModel;
import client.viewModel.bookAppointment.BookAppointmentSharedData;

public class DoctorViewController
{
  private BookAppointmentViewModel viewModel;
  private BookAppointmentSharedData sharedData;
  @FXML private TableView<Doctor> doctorTable;
  @FXML private TableColumn<Doctor, String> doctor;

  public void init(BookAppointmentViewModel viewModel,
      BookAppointmentSharedData sharedData)
  {
    this.viewModel = viewModel;
    this.sharedData = sharedData;
    ObservableList<Doctor> observableDoctor = FXCollections.observableArrayList(
        viewModel.getDoctorList());

    doctor.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));

    doctorTable.setItems(observableDoctor);
  }

  public void nextView()
  {
    Doctor selectedDoctor = doctorTable.getSelectionModel().getSelectedItem();

    if (selectedDoctor != null)
    {
      int selectedDoctorId = selectedDoctor.getDoctorID();
      viewModel.setSelectedDoctor(selectedDoctorId);
      BookAppointmentViewHandler.showView(
          BookAppointmentViewHandler.ViewType.MODE);
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("No Doctor Selected");
      alert.setHeaderText(null);
      alert.setContentText("Please select a doctor before continuing.");
      alert.showAndWait();
    }
  }

  public void goBack()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }
}
