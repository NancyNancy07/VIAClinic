package client.view.bookAppointment.selectdoctor;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

  public void init(BookAppointmentViewModel viewModel, BookAppointmentSharedData sharedData)
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
    int selectedDoctorId = doctorTable.getSelectionModel().getSelectedItem()
        .getDoctorID();
    if (selectedDoctorId != -1)
    {
      viewModel.setSelectedDoctor(selectedDoctorId);
      BookAppointmentViewHandler.showView(
          BookAppointmentViewHandler.ViewType.MODE);
    }

  /*  if (selectedDoctorId != -1)
    {
      sharedData.setSelectedDoctorId(selectedDoctorId);
      BookAppointmentViewHandler.showView(
          BookAppointmentViewHandler.ViewType.MODE);
    }*/

  }

  public void goBack()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }
}
