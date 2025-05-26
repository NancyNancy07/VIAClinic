package client.model.clientBookAppointment;

/**
 * ClientNewDateTime class represents a date and time with day, month, year,
 * hour, and minute fields.
 */
public class ClientNewDateTime
{
  private int day;
  private int month;
  private int year;
  private int hour;
  private int minute;

  // Constructor

  /**
   * Constructor to initialize the date and time.
   * @param day day of the month (1-31)
   * @param month month of the year (1-12)
   * @param year year (e.g., 2023)
   * @param hour hour of the day (0-23)
   * @param minute minute of the hour (0-59)
   */
  public ClientNewDateTime(int day, int month, int year, int hour, int minute)
  {
    this.day = day;
    this.month = month;
    this.year = year;
    this.hour = hour;
    this.minute = minute;
  }

  // Getters and setters for each field

  /**
   * Get the day of the month.
   * @return the day of the month (1-31)
   */
  public int getDay()
  {
    return day;
  }

  /**
   * Set the day of the month.
   * @param day the day of the month (1-31)
   */
  public void setDay(int day)
  {
    this.day = day;
  }

  /**
   * Get the month of the year.
   * @return the month of the year (1-12)
   */
  public int getMonth()
  {
    return month;
  }

  /**
   * Set the month of the year.
   * @param month the month of the year (1-12)
   */
  public void setMonth(int month)
  {
    this.month = month;
  }

  /**
   * Get the year.
   * @return the year (e.g., 2023)
   */
  public int getYear()
  {
    return year;
  }

  /**
   * Set the year.
   * @param year the year (e.g., 2023)
   */
  public void setYear(int year)
  {
    this.year = year;
  }

  /**
   * Get the hour of the day.
   * @return the hour of the day (0-23)
   */
  public int getHour()
  {
    return hour;
  }

  /**
   * Set the hour of the day.
   * @param hour the hour of the day (0-23)
   */
  public void setHour(int hour)
  {
    this.hour = hour;
  }

  /**
   * Get the minute of the hour.
   * @return the minute of the hour (0-59)
   */
  public int getMinute()
  {
    return minute;
  }

  /**
   * Set the minute of the hour.
   * @param minute the minute of the hour (0-59)
   */
  public void setMinute(int minute)
  {
    this.minute = minute;
  }

  /**
   * Get the date in the format "day/month/year".
   * @return the date as a string
   */
  public String getDate()
  {
    return day + "/" + month + "/" + year;
  }

  /**
   * Get the time in the format "hour:minute".
   * @return the time as a string
   */
  public String getTime()
  {
    return hour + ":" + minute;
  }

  /**
   * Returtn a string representation of the date and time in the format
   * @return a string representation of the date in "day/month/year" format
   */
  @Override public String toString()
  {
    return day + "/" + month + "/" + year;
  }

  /**
   * Returns a string representation of the date and time.
   * @return a string in the format "Date=day-month-year, Time=hour:minute"
   */
  public String dateAndTime()
  {
    return "Date=" + day + "-" + month + "-" + year + ", Time=" + hour + ":"
        + minute;
  }

}
