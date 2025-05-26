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

/**
 * SelectDoctorViewController handles the selection of a doctor for an appointment.
 * It initializes the doctor table and manages the navigation between views.
 */
public class SelectDoctorViewController
{
  private SelectDoctorViewModel viewModel;

  @FXML private TableView<ClientDoctor> doctorTable;
  @FXML private TableColumn<ClientDoctor, String> doctor;

  /**
   * Initializes the controller with the provided view model.
   *
   * @param viewModel the view model containing the list of doctors
   */
  public void init(SelectDoctorViewModel viewModel)
  {
    this.viewModel = viewModel;

    ObservableList<ClientDoctor> observableDoctor = FXCollections.observableArrayList(
        viewModel.getDoctorList());

    doctor.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));

    doctorTable.setItems(observableDoctor);
  }

  /**
   * Handles the action when the next button is clicked.
   * It checks if a doctor is selected and navigates to the next view.
   */
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

  /**
   * Handles the action when the back button is clicked.
   * It navigates back to the front view of the appointment booking process.
   */
  public void goBack()
  {
    BookAppointmentViewHandler.showView(BookAppointmentViewHandler.ViewType.FRONT);
  }
}
