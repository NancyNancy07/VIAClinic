package client.viewModel.bookAppointment;

import client.model.ClientAppointmentModel;
import server.model.bookAppointment.AppointmentModel;

public class BookAppointmentViewModelFactory
{
  private final BookAppointmentSharedData sharedData;
  private final BookAppointmentFrontViewModel frontViewModel;
  private final SelectDoctorViewModel doctorViewModel;
  private final ConfirmationViewModel confirmationViewModel;
  private final SelectDateTimeViewModel dateTimeViewModel;
  private final SelectModeViewModel modeViewModel;

  public BookAppointmentViewModelFactory()
  {
    this.sharedData = BookAppointmentSharedData.getInstance();
    this.doctorViewModel = new SelectDoctorViewModel();
    ClientAppointmentModel model = new ClientAppointmentModel();
    this.frontViewModel = new BookAppointmentFrontViewModel(model);

    this.confirmationViewModel = new ConfirmationViewModel(frontViewModel, doctorViewModel);
    this.dateTimeViewModel = new SelectDateTimeViewModel();
    this.modeViewModel = new SelectModeViewModel();
  }

  public BookAppointmentFrontViewModel getFrontViewModel()
  {
    return frontViewModel;
  }

  public SelectDoctorViewModel getDoctorViewModel()
  {
    return doctorViewModel;
  }

  public ConfirmationViewModel getConfirmationViewModel()
  {
    return confirmationViewModel;
  }

  public SelectDateTimeViewModel getDateTimeViewModel()
  {
    return dateTimeViewModel;
  }

  public SelectModeViewModel getModeViewModel()
  {
    return modeViewModel;
  }
}
