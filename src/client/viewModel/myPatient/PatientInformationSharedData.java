package client.viewModel.myPatient;

import server.model.patientJournal.Address;

public class PatientInformationSharedData
{
  private static PatientInformationSharedData instance;
  private String patientName;
  private String email;
  private String phoneNumber;
  private String cpr;
  private Address address;


  private PatientInformationSharedData()
  {
  }

  public static synchronized PatientInformationSharedData getInstance()
  {
    if (instance == null)
    {
      instance = new PatientInformationSharedData();
    }
    return instance;
  }

  public Address getAddress()
  {
    return address;
  }

  public void setAddress(Address address)
  {
    this.address = address;
  }

  public String getCpr()
  {
    return cpr;
  }

  public void setCpr(String cpr)
  {
    this.cpr = cpr;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPatientName()
  {
    return patientName;
  }

  public void setPatientName(String patientName)
  {
    this.patientName = patientName;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }
}