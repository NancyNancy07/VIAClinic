package server.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalTime;

/**
 * LocalTimeAdapter is a custom Gson TypeAdapter for serializing and deserializing
 * LocalTime objects to and from JSON.
 * It writes LocalTime as a string in the format "HH:mm:ss".
 */
public class LocalTimeAdapter extends TypeAdapter<LocalTime>
{
  /**
   * Writes a LocalTime to JSON.
   * @param out the JsonWriter to write to
   * @param value the LocalTime object to write
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void write(JsonWriter out, LocalTime value) throws IOException
  {
    out.value(value.toString()); // writes as "HH:mm:ss"
  }

  /**
   * Reads a LocalTime from JSON.
   * Expects the JSON to contain a string in the format "HH:mm:ss".
   *
   * @param in the JsonReader to read from
   * @return the LocalTime object parsed from the JSON string
   * @throws IOException if an I/O error occurs
   */
  @Override
  public LocalTime read(JsonReader in) throws IOException
  {
    return LocalTime.parse(in.nextString());
  }
}
