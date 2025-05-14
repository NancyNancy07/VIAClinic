package client.view.bookAppointment.modeofconsultation;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import client.view.bookAppointment.BookAppointmentViewHandler;
import client.viewModel.bookAppointment.BookAppointmentViewModel;
import client.viewModel.bookAppointment.BookAppointmentSharedData;

public class ModeViewController
{
  private BookAppointmentViewModel viewModel;
  private BookAppointmentSharedData sharedData;
  @FXML Label doctorName;
  @FXML Button videoCall;
  @FXML Button voiceCall;
  @FXML Button inPerson;

  public void init(BookAppointmentViewModel viewModel,
      BookAppointmentSharedData sharedData)
  {
    this.viewModel = viewModel;
    this.sharedData = sharedData;
    String name = "";
    for (int i = 0; i < viewModel.getDoctorList().size(); i++)
    {
      if (viewModel.getDoctorList().get(i).getDoctorID()
          == sharedData.getSelectedDoctorId())
      {
        name = viewModel.getDoctorList().get(i).getName();
      }

    }
    doctorName.setText(name);
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
    if (sharedData.getConsultationMode() != null)
    {
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
