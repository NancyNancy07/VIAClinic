package view.bookAppointment.modeofconsultation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import view.bookAppointment.BookAppointmentViewHandler;
import viewModel.bookAppointment.BookAppointmentViewModel;
import viewModel.bookAppointment.SharedData;

public class ModeViewController
{
  private BookAppointmentViewModel viewModel;
  private SharedData sharedData;
  @FXML Label doctorName;
  @FXML Button videoCall;
  @FXML Button voiceCall;
  @FXML Button inPerson;

  public void init(BookAppointmentViewModel viewModel, SharedData sharedData)
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
    sharedData.setConsultationMode(videoCall.getText());
    nextView();
  }

  public void handleInPerson()
  {
    sharedData.setConsultationMode(inPerson.getText());
    nextView();
  }

  public void handleVoiceCall()
  {
    sharedData.setConsultationMode(voiceCall.getText());
    nextView();
  }

  public void nextView()
  {
    if (sharedData.getConsultationMode() != null)
    {
      BookAppointmentViewHandler.showView(
          BookAppointmentViewHandler.ViewType.TIME);
    }

  }
}
