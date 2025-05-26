package client.view.bookAppointment.selectdoctor;

import client.model.clientBookAppointment.ClientDoctor;
import client.viewModel.bookAppointment.BookAppointmentSharedData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.SelectDoctorViewModel;

public class SelectDoctorViewController
{
  private SelectDoctorViewModel viewModel;

  @FXML private TableView<ClientDoctor> doctorTable;
  @FXML private TableColumn<ClientDoctor, String> doctor;

  public void init(SelectDoctorViewModel viewModel)
  {
    this.viewModel = viewModel;

    ObservableList<ClientDoctor> observableDoctor = FXCollections.observableArrayList(
        viewModel.getDoctorList());

    doctor.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));

    doctorTable.setItems(observableDoctor);
  }

  public void nextView()
  {
    ClientDoctor selectedDoctor = doctorTable.getSelectionModel().getSelectedItem();

    if (selectedDoctor != null)
    {
      BookAppointmentSharedData.getInstance().setSelectedDoctorId(selectedDoctor.getDoctorID());
      viewModel.setSelectedDoctor(selectedDoctor.getDoctorID());
      BookAppointmentViewHandler.showView(BookAppointmentViewHandler.ViewType.MODE);
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
    BookAppointmentViewHandler.showView(BookAppointmentViewHandler.ViewType.FRONT);
  }
}
