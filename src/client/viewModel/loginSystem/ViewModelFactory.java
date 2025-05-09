package client.viewModel.loginSystem;

import server.model.bookAppointment.AppointmentModel;

public class ViewModelFactory
{
  private final LoginViewModel loginViewModel;
//  private final AppointmentViewModel appointmentViewModel;
  private AppointmentModel model;

  public ViewModelFactory()
  {
    this.model = model;
    this.loginViewModel = new LoginViewModel();
//    this.appointmentViewModel = new AppointmentViewModel(model);
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }

//  public AppointmentViewModel getAppointmentViewModel()
  //  {
  //    return appointmentViewModel;
  //  }
}
