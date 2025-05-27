package server.model.patientJournal;
import server.model.bookAppointment.NewDateTime;

/**
 * LabResult class represents a laboratory test result in the healthcare system.
 * It contains details such as test name, sample type, date collected, comments,
 * doctor ID, and patient ID.
 */
public class LabResult
{private int labResultId;
  private String testName;
  private String sampleType;
  private NewDateTime dateCollected;
  private String comment;
  private int doctorId;
  private int patientId;

  /**
   * Default constructor for LabResult.
   * Initializes the fields to default values.
   * @param testName the name of the lab test
   * @param sampleType the type of sample taken
   * @param dateCollected the date the sample was collected
   * @param comment additional notes on the result
   * @param doctorId the ID of the responsible doctor
   * @param patientId the ID of the patient for whom the test was conducted
   */
  public  LabResult(String testName, String sampleType,
      NewDateTime dateCollected, String comment, int doctorId, int patientId)
  {
    this.testName = testName;
    this.sampleType = sampleType;
    this.dateCollected = dateCollected;
    this.comment = comment;
    this.doctorId = doctorId;
    this.patientId = patientId;
  }

  /**
   * Constructor for LabResult with labResultId.
   * Initializes the fields including the unique lab result ID.
   * @param labResultId the unique identifier for the lab result
   * @param testName the name of the lab test
   * @param sampleType the type of sample taken
   * @param dateCollected the date the sample was collected
   * @param comment additional notes on the result
   * @param doctorId the ID of the responsible doctor
   * @param patientId the ID of the patient for whom the test was conducted
   */
  public LabResult( int labResultId,String testName, String sampleType, NewDateTime dateCollected, String comment, int doctorId, int patientId)
  {this.labResultId=labResultId;
    this.testName=testName;
    this.sampleType =sampleType;
    this.dateCollected=dateCollected;
    this.comment=comment;
    this.doctorId=doctorId;
    this.patientId=patientId;
  }
  /**
   * Gets the unique identifier for the lab result.
   * @return the lab result ID
   */
  public int getLabResultId()
  {
    return labResultId;
  }
  /**
   * Returns a string representation of the LabResult object.
   * @return a string containing the lab result ID
   */
  public String toString()
  {
    return "  LabResultID: " + labResultId;
  }
  /**
   * Sets the unique identifier for the lab result.
   * @param labResultId the lab result ID to set
   */
  public void setLabResultId(int labResultId) {
    this.labResultId = labResultId;
  }
  /**
   * Gets the ID of the patient for whom the test was conducted.
   * @return the patient ID
   */
  public int getPatientId()
  {
    return patientId;
  }

  /**
   * Gets the name of the lab test.
   * @return the test name
   */
  public String getTestName()
  {
    return testName;
  }
  /**
   * Gets the type of sample taken for the lab test.
   * @return the sample type
   */
  public String getSampleType()
  {
    return sampleType;
  }
  /**
   * Gets the date when the sample was collected.
   * @return the date collected
   */
  public NewDateTime getDateCollected()
  {
    return dateCollected;
  }
  /**
   * Gets any additional comments regarding the lab result.
   * @return the comment
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * Gets the ID of the doctor responsible for the lab test.
   * @return the doctor ID
   */
  public int getDoctorId()
  {
    return doctorId;
  }

}
