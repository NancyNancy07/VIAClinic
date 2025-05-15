package server.model.patientJournal;

public class Address
{
  private int addressId;
  private String city;
  private String postCode;
  private String street;

  public Address() {}

  public Address(String city, String postCode, String street) {
    this.city = city;
    this.postCode = postCode;
    this.street = street;
  }

  public Address(int addressId, String city, String postCode, String street) {
    this.addressId = addressId;
    this.city = city;
    this.postCode = postCode;
    this.street = street;
  }

  public int getAddressId() { return addressId; }
  public String getCity() { return city; }
  public String getPostCode() { return postCode; }
  public String getStreet() { return street; }
}
