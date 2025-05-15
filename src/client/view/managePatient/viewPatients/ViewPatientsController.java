package client.view.managePatient.viewPatients;

import client.view.managePatient.ManagePatientViewHandler;
import client.viewModel.patientsJournal.PatientsViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import server.model.bookAppointment.Patient;

public class ViewPatientsController
{
  @FXML TableView<Patient> patientsTable;
  @FXML TableColumn<Patient, String> patient;
  @FXML AnchorPane pane;
  @FXML Label patientName;

  private PatientsViewModel viewModel;

  public void init(PatientsViewModel viewModel)
  {
    this.viewModel = viewModel;
    ObservableList<Patient> patients = FXCollections.observableArrayList(
        viewModel.getPatientList());

    patient.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().toString()));

    patientsTable.setItems(patients);

    patientsTable.setOnMouseClicked(event -> managePatientData());

  }

  @FXML private void managePatientData()
  {
    pane.setVisible(true);

    Patient selected = patientsTable.getSelectionModel().getSelectedItem();
    patientName.setText(selected.getName());
    viewModel.setPatientName(selected.getName());
    viewModel.setPatientId(selected);
  }

  public void setAddDiagnosisView()
  {
    addDiagnosisView();
  }

  @FXML private void addDiagnosisView()
  {
    ManagePatientViewHandler.showView(
        ManagePatientViewHandler.ViewType.DIAGNOSIS);
  }

  @FXML private void back()
  {
    ManagePatientViewHandler.showView(ManagePatientViewHandler.ViewType.FRONT);
  }
}
