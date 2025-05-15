package shared;

import server.model.patientJournal.Diagnosis;

public class RequestObject
{
  private String type;
  private String username;
  private String password;
  private String userType;
  private int id;
  private Diagnosis diagnosis;

  public String getType()
  {
    return type;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public String getUserType()
  {
    return userType;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public int getId()
  {
    return id;
  }

  public void setUsername(String email)
  {
    this.username = email;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public void setUserType(String userType)
  {
    this.userType = userType;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public void setDiagnosis(Diagnosis diagnosis) {
    this.diagnosis = diagnosis;
  }

  public Diagnosis getDiagnosis() {
    return diagnosis;
  }
}
