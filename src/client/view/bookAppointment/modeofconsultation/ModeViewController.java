package client.view.bookAppointment.modeofconsultation;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.SelectModeViewModel;

public class ModeViewController
{
  private SelectModeViewModel viewModel;

  @FXML private Label doctorName;
  @FXML private Button videoCall;
  @FXML private Button voiceCall;
  @FXML private Button inPerson;

  public void init(SelectModeViewModel viewModel)
  {
    this.viewModel = viewModel;
    doctorName.setText(viewModel.getSelectedDoctorName());
  }

  public void handleVideoCall()
  {
    viewModel.setMode(videoCall.getText());
  }

  public void handleInPerson()
  {
    viewModel.setMode(inPerson.getText());
  }

  public void handleVoiceCall()
  {
    viewModel.setMode(voiceCall.getText());
  }

  public void nextView()
  {
    if (viewModel.getConsultationMode() != null)
    {
      BookAppointmentViewHandler.showView(BookAppointmentViewHandler.ViewType.TIME);
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("No Mode of Consultation Selected");
      alert.setHeaderText(null);
      alert.setContentText("Please select a mode before continuing.");
      alert.showAndWait();
    }
  }
}
