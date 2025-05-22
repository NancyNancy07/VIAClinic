package client.view.managePatient.addVaccination;

import client.view.managePatient.ManagePatientViewHandler;
import client.viewModel.patients.AddVaccinationViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import server.model.bookAppointment.NewDateTime;
import server.model.patientJournal.Vaccination;

import java.time.LocalDate;

public class AddVaccinationController
{
  @FXML private Label patientNameLabel;
  @FXML private TextField vaccineField;
  @FXML private TextField recommendedField;
  @FXML private TextField commentField;
  @FXML private DatePicker dateTakenPicker;
  @FXML private DatePicker nextDoseDatePicker;
  @FXML private TableView<Vaccination> vaccinationTable;

  @FXML private TableColumn<Vaccination, String> vaccineColumn;
  @FXML private TableColumn<Vaccination, String> dateTakenColumn;
  @FXML private TableColumn<Vaccination, String> recommendedColumn;
  @FXML private TableColumn<Vaccination, String> nextDoseDateColumn;
  @FXML private TableColumn<Vaccination, String> commentColumn;

  private AddVaccinationViewModel viewModel;

  public void init(AddVaccinationViewModel viewModel) {
    this.viewModel = viewModel;
    patientNameLabel.setText(viewModel.getPatientName());

    // Link table columns
    vaccineColumn.setCellValueFactory(new PropertyValueFactory<>("vaccinationName"));
    dateTakenColumn.setCellValueFactory(cellData -> {
      return new SimpleStringProperty(formatDate(cellData.getValue().getDateTaken()));
    });
    recommendedColumn.setCellValueFactory(cellData -> {
      boolean value = cellData.getValue().isRecommended();
      return new SimpleStringProperty(value ? "Yes" : "No");
    });
    nextDoseDateColumn.setCellValueFactory(cellData -> {
      return new SimpleStringProperty(formatDate(cellData.getValue().getDateTaken()));
    });

    commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

    vaccinationTable.setItems(viewModel.getVaccinations());
  }

  @FXML
  private void back()
  {
    ManagePatientViewHandler.showView(ManagePatientViewHandler.ViewType.FRONT);
  }

  @FXML
  private void onAddVaccination() {
    String input = recommendedField.getText().trim().toLowerCase();
    boolean recommended = input.equals("yes") || input.equals("true");

    System.out.println("Recommended parsed: " + recommended);

    viewModel.addVaccination(
        vaccineField.getText(),
        toNewDateTime(dateTakenPicker.getValue()),
        recommended,
        commentField.getText(),
        toNewDateTime(nextDoseDatePicker.getValue()),
        viewModel.getDoctorId(),
        viewModel.getPatientId()
    );

    // Clear fields after adding
    vaccineField.clear();
    recommendedField.clear();
    commentField.clear();
    dateTakenPicker.setValue(null);
    nextDoseDatePicker.setValue(null);
  }

//  private String formatDate(NewDateTime date)
//  {
//    return date.getDay() + "/" + date.getMonth() + "/" + date.getYear();
//  }

  private NewDateTime toNewDateTime(LocalDate localDate)
  {
    return new NewDateTime(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear(), 0, 0);
  }

  private String formatDate(NewDateTime date) {
    return String.format("%02d-%02d-%04d %02d:%02d",
        date.getDay(), date.getMonth(), date.getYear(),
        date.getHour(), date.getMinute());
  }
}
