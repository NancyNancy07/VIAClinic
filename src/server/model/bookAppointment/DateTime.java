package server.model.bookAppointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * DateTime class represents a date and time combination.
 * It contains methods to get and set the date and time,
 * as well as to format the date and time as a string.
 */
public class DateTime
{
  private LocalDate date;
  private LocalTime time;

  private int day;
  private int month;
  private int year;

  /**
   * Default constructor for DateTime.
   * Initializes date and time to the current date and time.
   */
  public DateTime(LocalDate date, LocalTime time)
  {
    this.date = date;
    this.time = time;
  }

  /**
   * Get date of the DateTime object.
   * @return the date as a LocalDate object
   */
  public LocalDate getDate()
  {
    return date;
  }

  /**
   * Get time of the DateTime object.
   * @return the time as a LocalTime object
   */
  public LocalTime getTime()
  {
    return time;
  }

  /**
   * Set the date of the DateTime object.
   * @param date the date to set as a LocalDate object
   */
  public void setDate(LocalDate date)
  {
    this.date = date;
  }

  /**
   * Set the time of the DateTime object.
   * @param time the time to set as a LocalTime object
   */
  public void setTime(LocalTime time)
  {
    this.time = time;
  }

  /**
   * String representation of the DateTime object.
   * @return a formatted string containing the date and time
   */
  @Override public String toString()
  {
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    return "Date= " + date + ", Time= " + time.format(timeFormatter);
  }

  /**
   * Checks if two DateTime objects are equal.
   * Two DateTime objects are considered equal if their date and time are the same.
   * @param obj the object to compare with
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    DateTime other = (DateTime) obj;
    return date.equals(other.date) && time.equals(other.time);
  }
}
