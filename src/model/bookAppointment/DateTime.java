package model.bookAppointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTime
{
  private LocalDate date;
  private LocalTime time;

  private int day;
  private int month;
  private int year;

  public DateTime(LocalDate date, LocalTime time)
  {
    this.date = date;
    this.time = time;
  }

  public LocalDate getDate()
  {
    return date;
  }

  public LocalTime getTime()
  {
    return time;
  }

  public void setDate(LocalDate date)
  {
    this.date = date;
  }

  public void setTime(LocalTime time)
  {
    this.time = time;
  }

  @Override public String toString()
  {
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    return "Date= " + date + ", Time= " + time.format(timeFormatter);
  }
}
