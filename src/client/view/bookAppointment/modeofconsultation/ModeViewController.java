package client.view.bookAppointment.modeofconsultation;

import client.viewModel.bookAppointment.BookAppointmentSharedData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.SelectModeViewModel;

/**
 * Controller for the mode of consultation selection view in the book appointment process.
 * This controller handles user interactions for selecting a consultation mode (video call, voice call, or in-person).
 */
public class ModeViewController
{
  private SelectModeViewModel viewModel;

  @FXML private Label doctorName;
  @FXML private Button videoCall;
  @FXML private Button voiceCall;
  @FXML private Button inPerson;

  /**
   * Initializes the controller with the provided view model.
   *
   * @param viewModel the view model containing the data and logic for this view
   */
  public void init(SelectModeViewModel viewModel)
  {
    this.viewModel = viewModel;
    doctorName.setText(viewModel.getSelectedDoctorName());
  }

  /**
   * Handles the video call button click event.
   * Sets the consultation mode to video call.
   */
  public void handleVideoCall()
  {
    viewModel.setMode(videoCall.getText());
  }

  /**
   * Handles the in-person button click event.
   * Sets the consultation mode to in-person.
   */
  public void handleInPerson()
  {
    viewModel.setMode(inPerson.getText());
  }

  /**
   * Handles the voice call button click event.
   * Sets the consultation mode to voice call.
   */
  public void handleVoiceCall()
  {
    viewModel.setMode(voiceCall.getText());
  }

  /**
   * Proceeds to the next view in the booking process.
   * If a consultation mode is selected, it navigates to the time selection view.
   * If no mode is selected, it shows a warning alert.
   */
  public void nextView()
  {
    if (viewModel.getConsultationMode() != null)
    {
      BookAppointmentSharedData.getInstance()
          .setConsultationMode(viewModel.getConsultationMode());
      BookAppointmentViewHandler.showView(
          BookAppointmentViewHandler.ViewType.TIME);
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
