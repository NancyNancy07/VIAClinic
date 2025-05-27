package client.viewModel.bookAppointment;

import client.model.clientBookAppointment.ClientAppointmentService;

/**
 * BookAppointmentViewModelFactory is responsible for creating and managing the view models
 * used in the book appointment process. It initializes shared data and individual view models
 * for different steps in the appointment booking workflow.
 */
public class BookAppointmentViewModelFactory
{
  private final BookAppointmentSharedData sharedData;
  private final BookAppointmentFrontViewModel frontViewModel;
  private final SelectDoctorViewModel doctorViewModel;
  private final ConfirmationViewModel confirmationViewModel;
  private final SelectDateTimeViewModel dateTimeViewModel;
  private final SelectModeViewModel modeViewModel;

  /**
   * Default constructor for BookAppointmentViewModelFactory.
   * Initializes the shared data and creates instances of the view models
   * required for the appointment booking process.
   */
  public BookAppointmentViewModelFactory()
  {
    this.sharedData = BookAppointmentSharedData.getInstance();
    this.doctorViewModel = new SelectDoctorViewModel();
    ClientAppointmentService model = new ClientAppointmentService();
    this.frontViewModel = new BookAppointmentFrontViewModel(model);

    this.confirmationViewModel = new ConfirmationViewModel(frontViewModel, doctorViewModel);
    this.dateTimeViewModel = new SelectDateTimeViewModel();
    this.modeViewModel = new SelectModeViewModel();
  }

  /**
   * Gets the BookAppointmentFrontViewModel.
   * @return the front view model used for the initial booking interface
   */
  public BookAppointmentFrontViewModel getFrontViewModel()
  {
    return frontViewModel;
  }

  /**
   * Gets the SelectDoctorViewModel.
   * @return the view model used for selecting a doctor
   */
  public SelectDoctorViewModel getDoctorViewModel()
  {
    return doctorViewModel;
  }

  /**
   * Gets the ConfirmationViewModel.
   * @return the view model used for confirming the appointment
   */
  public ConfirmationViewModel getConfirmationViewModel()
  {
    return confirmationViewModel;
  }

  /**
   * Gets the SelectDateTimeViewModel.
   * @return the view model used for selecting the date and time of the appointment
   */
  public SelectDateTimeViewModel getDateTimeViewModel()
  {
    return dateTimeViewModel;
  }

  /**
   * Gets the SelectModeViewModel.
   * @return the view model used for selecting the mode of consultation
   */
  public SelectModeViewModel getModeViewModel()
  {
    return modeViewModel;
  }
}
