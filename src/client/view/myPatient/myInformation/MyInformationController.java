package client.view.myPatient.myInformation;

import client.viewModel.myPatient.PatientInformationSharedData;
import client.viewModel.myPatient.PatientInformationViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Address;

public class MyInformationController
{

  @FXML private Label name;
  @FXML private Label email;
  @FXML private Label phone;
  @FXML private Label cpr;
  @FXML private Label street;
  @FXML private Label postCode;
  @FXML private Label city;

  private PatientInformationViewModel viewModel;

  public void init(PatientInformationViewModel viewModel) {
    this.viewModel = viewModel;

    PatientInformationSharedData patient = viewModel.getSharedData();
    name.setText(PatientInformationSharedData.getInstance().getPatientName());
    email.setText(PatientInformationSharedData.getInstance().getEmail());
    phone.setText(PatientInformationSharedData.getInstance().getPhoneNumber());
    cpr.setText(PatientInformationSharedData.getInstance().getCpr());



    Address address = PatientInformationSharedData.getInstance().getAddress();
    if (address != null) {
      street.setText(address.getStreet());
      postCode.setText(address.getPostCode());
      city.setText(address.getCity());
    }
  }
}