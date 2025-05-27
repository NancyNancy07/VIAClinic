package client.view.managePatient.addLabResult;
import client.viewModel.managePatients.AddLabResultViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.LabResult;

/**
 * AddLabResultController handles the front-end logic for adding lab results for a patient.
 * It allows doctors to input lab test details and view existing lab results.
 */
public class AddLabResultController
{

  @FXML private Label patientNameLabel;

  @FXML private TableView<LabResult> labResultTable;
  @FXML private TableColumn<LabResult, String> testNameColumn;
  @FXML private TableColumn<LabResult, String> sampleTypeColumn;
  @FXML private TableColumn<LabResult, String> dateCollectedColumn;
  @FXML private TableColumn<LabResult, String> commentColumn;

  @FXML private TextField testNameField;
  @FXML private TextField sampleTypeField;
  @FXML private TextField commentField;
  @FXML private DatePicker dateCollectedPicker;
  private AddLabResultViewModel viewModel;

  /**
   * Initializes the controller with the provided view model.
   *
   * @param viewModel the view model containing the lab results and patient information
   */
  public void init(AddLabResultViewModel viewModel)
  {
    this.viewModel = viewModel;
    patientNameLabel.setText(viewModel.getPatientName());
    labResultTable.setItems(viewModel.getLabResults());
    testNameColumn.setCellValueFactory(new PropertyValueFactory<>("testName"));
    sampleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("sampleType"));
    dateCollectedColumn.setCellValueFactory(cellData -> {
      NewDateTime date = cellData.getValue().getDateCollected();
      return new SimpleStringProperty(date != null ? date.toString() : "N/A");
    });
    commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
  }

  /**
   * Handles the action of adding a new lab result.
   * It retrieves the input data from the fields, validates it, and calls the view model to add the lab result.
   */
  @FXML private void onAddLabResult()
  {
    try
    {
      String testName = testNameField.getText();
      String sampleType = sampleTypeField.getText();
      NewDateTime dateCollected = toNewDateTime(dateCollectedPicker.getValue());
      String comment = commentField.getText();

      viewModel.addLabResult(testName, sampleType, dateCollected, comment,
          viewModel.getDoctorId(), viewModel.getPatientId());
      System.out.println("Doctor id: "+viewModel.getDoctorId());
      System.out.println("Patient id: "+viewModel.getPatientId());
      clearForm();
    }
    catch (Exception e)
    {
      System.out.println("Error: " + e.getMessage());
      showAlert(
          "Invalid input. Please make sure all fields are filled in correctly.");
    }
  }


  /**
   * Converts a LocalDate to a NewDateTime object with time set to midnight.
   *
   * @param date the LocalDate to convert
   * @return a NewDateTime object representing the date at midnight
   */
  private NewDateTime toNewDateTime(java.time.LocalDate date)
  {
    return new NewDateTime(date.getDayOfMonth(), date.getMonthValue(),
        date.getYear(), 0, 0);
  }

  /**
   * Handles the action of going back to the previous view.
   * It navigates back to the patient journal view.
   */

  private void clearForm()
  {
    testNameField.clear();
    sampleTypeField.clear();
    commentField.clear();
    dateCollectedPicker.setValue(null);

  }

  /**
   * Displays an alert dialog with the specified message.
   *
   * @param msg the message to display in the alert dialog
   */
  private void showAlert(String msg)
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.showAndWait();
  }
}




