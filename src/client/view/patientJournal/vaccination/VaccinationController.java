package client.view.patientJournal.vaccination;

import client.view.patientJournal.PatientJournalViewHandler;
import client.viewModel.patientJournal.PatientDiagnosisViewModel;
import client.viewModel.patientJournal.PatientVaccinationViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.Vaccination;

import javafx.scene.control.TableView;

/**
 * VaccinationController handles the logic for displaying and managing vaccinations
 * in a patient's journal. It initializes the vaccination table and binds it to the
 * view model's data.
 */
public class VaccinationController
{
  @FXML private TableView<Vaccination> vaccinationTable;
  @FXML private Label patientName;
  private PatientVaccinationViewModel viewModel;
  @FXML private TableColumn<Vaccination, String> idColumn;
  @FXML private TableColumn<Vaccination, String> vaccinationColumn;
  @FXML private TableColumn<Vaccination, String> dateTakenColumn;
  @FXML private TableColumn<Vaccination, String> recommendedColumn;
  @FXML private TableColumn<Vaccination, String> nextDoseDateColumn;
  @FXML private TableColumn<Vaccination, String> commentColumn;

  /**
   * Initializes the VaccinationController with the provided view model.
   * Sets up the vaccination table and binds it to the view model's data.
   *
   * @param viewModel the view model containing vaccination data and logic
   */
  public void init(PatientVaccinationViewModel viewModel)
  {
    this.viewModel = viewModel;
    vaccinationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    commentColumn.setMaxWidth(300);
    commentColumn.setPrefWidth(300);
    idColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(String.valueOf(cellData.getValue().getVaccinationId())));
    vaccinationColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getVaccinationName()));
    dateTakenColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(String.valueOf(cellData.getValue().getDateTaken().toString())));
    recommendedColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().isRecommended() ? "Yes" : "No"));
    nextDoseDateColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getNextDoseDate().toString()));
    commentColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getComment()));

    int patientId = viewModel.getPatientId();
    vaccinationTable.setItems(viewModel.getVaccinationList(patientId));
  }

  /**
   * Handles the action when the "Back" button is clicked.
   */
  @FXML
  private void onBackButtonClick() {
    PatientJournalViewHandler.showView(PatientJournalViewHandler.ViewType.FRONT);
  }

  /**
   * Formats the date from NewDateTime to a string in the format "DD/MM/YYYY".
   *
   * @param date the NewDateTime object to format
   * @return the formatted date string
   */
  private String formatDate(NewDateTime date) {
    return date.getDay() + "/" + date.getMonth() + "/" + date.getYear();
  }
}
