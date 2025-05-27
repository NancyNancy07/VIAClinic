package client.model.clientBookAppointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientNewDateTimeTest
{
  private int day;
  private int month;
  private int year;
  private int hour;
  private int minute;
  private ClientNewDateTime dateTime;

  @BeforeEach void setUp()
  {
    day = 12;
    month = 5;
    year = 2025;
    hour = 14;
    minute = 30;
    dateTime = new ClientNewDateTime(day, month, year, hour, minute);
  }

  @Test void getDay()
  {
    assertEquals(day, dateTime.getDay());
  }

  @Test void setDay()
  {
    int newDay = 15;
    dateTime.setDay(newDay);
    assertEquals(newDay, dateTime.getDay());
  }

  @Test void getMonth()
  {
    assertEquals(month, dateTime.getMonth());
  }

  @Test void setMonth()
  {
    int newMonth = 6;
    dateTime.setMonth(newMonth);
    assertEquals(newMonth, dateTime.getMonth());
  }

  @Test void getYear()
  {
    assertEquals(year, dateTime.getYear());
  }

  @Test void setYear()
  {
    int newYear = 2026;
    dateTime.setYear(newYear);
    assertEquals(newYear, dateTime.getYear());
  }

  @Test void getHour()
  {
    assertEquals(hour, dateTime.getHour());
  }

  @Test void setHour()
  {
    int newHour = 16;
    dateTime.setHour(newHour);
    assertEquals(newHour, dateTime.getHour());
  }

  @Test void getMinute()
  {
    assertEquals(minute, dateTime.getMinute());
  }

  @Test void setMinute()
  {
    int newMinute = 45;
    dateTime.setMinute(newMinute);
    assertEquals(newMinute, dateTime.getMinute());
  }

  @Test void getDate()
  {
    String expectedDate =  day + "/" + month + "/" + year;
    assertEquals(expectedDate, dateTime.getDate());
  }

  @Test void getTime()
  {
    String expectedTime = hour + ":" + minute;
    assertEquals(expectedTime, dateTime.getTime());
  }

  @Test void testToString()
  {
    String expectedString = day + "/" + month + "/" + year;
    assertEquals(expectedString, dateTime.toString());
  }

  @Test void dateAndTime()
  {
    String expectedDateTime = "Date=" + day + "-" + month + "-" + year + ", Time=" + hour + ":"
        + minute;;
    assertEquals(expectedDateTime, dateTime.dateAndTime());
  }
}