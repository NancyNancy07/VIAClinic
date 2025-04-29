package view.bookAppointment.selectdoctor;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.bookAppointment.Doctor;
import view.bookAppointment.BookAppointmentViewHandler;
import viewModel.bookAppointment.BookAppointmentViewModel;
import viewModel.bookAppointment.SharedData;

public class DoctorViewController
{
  private BookAppointmentViewModel viewModel;
  private SharedData sharedData;
  @FXML private TableView<Doctor> doctorTable;
  @FXML private TableColumn<Doctor, String> doctor;

  public void init(BookAppointmentViewModel viewModel, SharedData sharedData)
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
      sharedData.setSelectedDoctorId(selectedDoctorId);
      BookAppointmentViewHandler.showView(
          BookAppointmentViewHandler.ViewType.MODE);
    }
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.MODE);
  }

  public void goBack()
  {
    BookAppointmentViewHandler.showView(
        BookAppointmentViewHandler.ViewType.FRONT);
  }
}
