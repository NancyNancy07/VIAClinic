package client.view.managePatient.viewPatients;

import client.view.managePatient.ManagePatientViewHandler;
import client.viewModel.managePatients.PatientsViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import server.model.bookAppointment.Patient;

/**
 * ViewPatientsController handles the display and management of patients in the application.
 * It initializes the patient list and provides methods to manage patient data.
 */
public class ViewPatientsController
{
  @FXML TableView<Patient> patientsTable;
  @FXML TableColumn<Patient, String> patient;
  @FXML AnchorPane pane;
  @FXML Label patientName;

  private PatientsViewModel viewModel;

  /**
   * Initializes the ViewPatientsController with the provided PatientsViewModel.
   * Sets up the patient table and binds the patient data to the view.
   *
   * @param viewModel the view model containing patient data
   */
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

  /**
   * Handles the action when a patient is selected from the table.
   * It retrieves the selected patient and updates the view with the patient's name.
   */
  @FXML private void managePatientData()
  {
    Patient selected = patientsTable.getSelectionModel().getSelectedItem();
    if (selected == null)
    {
      return;
    }
    pane.setVisible(true);

    patientsTable.getSelectionModel().getSelectedItem();
    patientName.setText(selected.getName());
    viewModel.setPatientName(selected.getName());
    viewModel.setPatientId(selected);
  }

  /**
   * Sets the view to add a diagnosis for the selected patient.
   * This method is called to navigate to the diagnosis view.
   */
  public void setAddDiagnosisView()
  {
    addDiagnosisView();
  }

  /**
   * Sets the view to add a prescription for the selected patient.
   * This method is called to navigate to the prescription view.
   */
  public void setAddPrescriptionView()
  {
    addPrescriptionView();
  }
  /**
   * Sets the view to add a lab result for the selected patient.
   * This method is called to navigate to the lab result view.
   */
  public void setADDLabResultView(){addLabResultView();}

  /**
   * Sets the view to add a referral for the selected patient.
   * This method is called to navigate to the referral view.
   */
  public void setAddReferralView()
  {
    addReferralView();
  }

  /**
   * Sets the view to add a vaccination for the selected patient.
   * This method is called to navigate to the vaccination view.
   */
  public void setAddVaccinationView()
  {
    addVaccinationView();
  }

  /**
   * Sets the view to go back to the front view.
   * This method is called to navigate back to the main patient management view.
   */
  @FXML private void addDiagnosisView()
  {
    ManagePatientViewHandler.showView(
        ManagePatientViewHandler.ViewType.DIAGNOSIS);
  }

  /**
   * Sets the view to add a prescription for the selected patient.
   * This method is called to navigate to the prescription view.
   */
  @FXML private void addPrescriptionView()
  {
    ManagePatientViewHandler.showView(
        ManagePatientViewHandler.ViewType.PRESCRIPTION);
  }
  /**
   * Sets the view to add a lab result for the selected patient.
   * This method is called to navigate to the lab result view.
   */
  @FXML private void addLabResultView()
  {
    ManagePatientViewHandler.showView(
        ManagePatientViewHandler.ViewType.LABRESULT);
  }


  /**
   * Sets the view to add a referral for the selected patient.
   * This method is called to navigate to the referral view.
   */
  @FXML private void addReferralView()
  {
    ManagePatientViewHandler.showView(
        ManagePatientViewHandler.ViewType.REFERRAL);
  }

  /**
   * Sets the view to add a vaccination for the selected patient.
   * This method is called to navigate to the vaccination view.
   */
  @FXML private void addVaccinationView()
  {
    ManagePatientViewHandler.showView(
        ManagePatientViewHandler.ViewType.VACCINATION);
  }

  /**
   * Navigates back to the front view of the patient management feature.
   * This method is called when the user chooses to go back.
   */
  @FXML private void back()
  {
    ManagePatientViewHandler.showView(ManagePatientViewHandler.ViewType.FRONT);
  }
}
