package shared;

public class RequestObject
{
  private String type;
  private String email;
  private String password;

  // Getters and setters
  public String getType() { return type; }
  public String getUsername() { return email; }
  public String getPassword() { return password; }

  public void setType(String type) { this.type = type; }
  public void setEmail(String email) { this.email = email; }
  public void setPassword(String password) { this.password = password; }
}
