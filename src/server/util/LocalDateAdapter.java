package server.util;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateAdapter is a Gson adapter for serializing and deserializing LocalDate objects.
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate>
{
  private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

  /**
   * Serializes a LocalDate object to a JSON element.
   * @param date the LocalDate object to serialize
   * @param typeOfSrc the type of the source object
   * @param context the context for serialization
   * @return a JsonElement representing the LocalDate
   */
  @Override
  public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context)
  {
    return new JsonPrimitive(date.format(formatter));
  }

  /**
   * Deserializes a JSON element to a LocalDate object.
   * @param json the JSON element to deserialize
   * @param typeOfT the type of the target object
   * @param context the context for deserialization
   * @return a LocalDate object parsed from the JSON element
   * @throws JsonParseException if the JSON element cannot be parsed as a LocalDate
   */
  @Override
  public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException
  {
    return LocalDate.parse(json.getAsString(), formatter);
  }
}
