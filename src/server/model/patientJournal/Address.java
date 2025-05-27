package server.model.patientJournal;

/**
 * Address class represents an address in the healthcare system.
 * It contains details such as city, post code, street, and address ID.
 */
public class Address
{
  private int addressId;
  private String city;
  private String postCode;
  private String street;

  /**
   * Default constructor for Address.
   * Initializes the address with default values.
   */
  public Address() {}

  /**
   * Constructor for the Address class.
   * Initializes a new instance of Address with the specified parameters.
   *
   * @param city     The city of the address.
   * @param postCode The postal code of the address.
   * @param street   The street of the address.
   */
  public Address(String city, String postCode, String street) {
    this.city = city;
    this.postCode = postCode;
    this.street = street;
  }

  /**
   * Constructor for the Address class with an address ID.
   * Initializes a new instance of Address with the specified parameters.
   *
   * @param addressId The unique identifier for the address.
   * @param city      The city of the address.
   * @param postCode  The postal code of the address.
   * @param street    The street of the address.
   */
  public Address(int addressId, String city, String postCode, String street) {
    this.addressId = addressId;
    this.city = city;
    this.postCode = postCode;
    this.street = street;
  }

  /**
   * Gets the address ID
   * @return the unique identifier for the address
   */
  public int getAddressId() { return addressId; }
  /**
   * Gets the city of the address.
   * @return the city as a String
   */
  public String getCity() { return city; }
  /**
   * Gets the postal code of the address.
   * @return the postal code as a String
   */
  public String getPostCode() { return postCode; }
  /**
   * Gets the street of the address.
   * @return the street as a String
   */
  public String getStreet() { return street; }
}
