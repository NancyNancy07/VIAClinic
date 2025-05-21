package server.model.patientJournal;
import server.model.bookAppointment.NewDateTime;

public class LabResult
{private int labResultId;
  private String testName;
  private String sampleType;
  private NewDateTime dateCollected;
  private String comment;
  private int doctorId;
  private int patientId;
  public  LabResult(String testName, String sampleType,
      NewDateTime dateCollected, String comment, int doctorId, int patientId)
  {

  }
  public LabResult( int labResultId,String testName, String sampleType, NewDateTime dateCollected, String comment, int doctorId, int patientId)
  {this.labResultId=labResultId;
    this.testName=testName;
    this.sampleType =sampleType;
    this.dateCollected=dateCollected;
    this.comment=comment;
    this.doctorId=doctorId;
    this.patientId=patientId;
  }
  public int getLabResultId()
  {
    return labResultId;
  }
  public String toString()
  {
    return "  LabResultID: " + labResultId;
  }
  public void setLabResultId(int labResultId) {
    this.labResultId = labResultId;
  }
  public int getPatientId()
  {
    return patientId;
  }
  public String getTestName()
  {
    return testName;
  }
  public String getSampleType()
  {
    return sampleType;
  }
  public NewDateTime getDateCollected()
  {
    return dateCollected;
  }
  public String getComment()
  {
    return comment;
  }

  public int getDoctorId()
  {
    return doctorId;
  }

}
