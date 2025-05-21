package client.view.managePatient.addLabResult;
import client.viewModel.patients.AddLabResultViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.LabResult;


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



  private NewDateTime toNewDateTime(java.time.LocalDate date)
  {
    return new NewDateTime(date.getDayOfMonth(), date.getMonthValue(),
        date.getYear(), 0, 0);
  }

  private void clearForm()
  {
    testNameField.clear();
    sampleTypeField.clear();
    commentField.clear();
    dateCollectedPicker.setValue(null);

  }

  private void showAlert(String msg)
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.showAndWait();
  }
}




