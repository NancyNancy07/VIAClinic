package client.viewModel.loginSystem;

import server.model.bookAppointment.AppointmentModel;

/**
 * ViewModelFactory is responsible for creating and providing instances of view models
 * used in the login system. It currently provides a LoginViewModel instance.
 */
public class ViewModelFactory
{
  private final LoginViewModel loginViewModel;
//  private final AppointmentViewModel appointmentViewModel;
  private AppointmentModel model;

  /**
   * Constructor for ViewModelFactory.
   */
  public ViewModelFactory()
  {
    this.model = model;
    this.loginViewModel = new LoginViewModel();
//    this.appointmentViewModel = new AppointmentViewModel(model);
  }

  /**
   * Gets the LoginViewModel instance.
   *
   * @return The LoginViewModel instance.
   */
  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }

//  public AppointmentViewModel getAppointmentViewModel()
  //  {
  //    return appointmentViewModel;
  //  }
}
