package server.model.bookAppointment;

/**
 * Appointment class represents an appointment in the healthcare system.
 * It contains details such as date, time, patient ID, doctor, and mode of appointment.
 */
public class NewDateTime
{
  private int day;
  private int month;
  private int year;
  private int hour;
  private int minute;

  // Constructor

  /**
   * Creates a new instance of NewDateTime with the specified date and time.
   * @param day day of the month (1-31)
   * @param month month of the year (1-12)
   * @param year year (e.g., 2023)
   * @param hour hour of the day (0-23)
   * @param minute minute of the hour (0-59)
   */
  public NewDateTime(int day, int month, int year, int hour, int minute)
  {
    this.day = day;
    this.month = month;
    this.year = year;
    this.hour = hour;
    this.minute = minute;
  }

  // Getters and setters for each field

  /**
   * Gets the day of the month.
   * @return the day of the month (1-31)
   */
  public int getDay()
  {
    return day;
  }

  /**
   * Sets the day of the month.
   * @param day the day of the month (1-31)
   */
  public void setDay(int day)
  {
    this.day = day;
  }

  /**
   * Gets the month of the year.
   * @return the month of the year (1-12)
   */
  public int getMonth()
  {
    return month;
  }

  /**
   * Sets the month of the year.
   * @param month the month of the year (1-12)
   */
  public void setMonth(int month)
  {
    this.month = month;
  }

  /**
   * Gets the year.
   * @return the year (e.g., 2023)
   */
  public int getYear()
  {
    return year;
  }

  /**
   * Sets the year.
   * @param year the year (e.g., 2023)
   */
  public void setYear(int year)
  {
    this.year = year;
  }

  /**
   * Gets the hour of the day.
   * @return the hour of the day (0-23)
   */
  public int getHour()
  {
    return hour;
  }

  /**
   * Sets the hour of the day.
   * @param hour the hour of the day (0-23)
   */
  public void setHour(int hour)
  {
    this.hour = hour;
  }

  /**
   * Gets the minute of the hour.
   * @return the minute of the hour (0-59)
   */
  public int getMinute()
  {
    return minute;
  }

  /**
   * Sets the minute of the hour.
   * @param minute the minute of the hour (0-59)
   */
  public void setMinute(int minute)
  {
    this.minute = minute;
  }

  /**
   * Returns a string representation of the date in the format "day/month/year".
   * @return a string representing the date
   */
  @Override public String toString()
  {
    return day + "/" + month + "/" + year;
  }

  /**
   * Returns a string representation of the date and time in the format "Date=day-month-year, Time=hour:minute".
   * @return a string representing the date and time
   */
  public String dateAndTime()
  {
    return "Date=" + day + "-" + month + "-" + year + ", Time=" + hour + ":"
        + minute;
  }
}
