package shared;

public class ResponseObject
{
  private String status;
  private String message;

  public ResponseObject(String status, String message)
  {
    this.status = status;
    this.message = message;
  }

  public String getStatus() { return status; }
  public String getMessage() { return message; }
}
