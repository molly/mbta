package net.mollywhite.mbta.client;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CreatedAtFormatter {
  public static OffsetDateTime get(String createdAt) throws DateTimeParseException {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy");
    return OffsetDateTime.parse(createdAt, format);
  }
}
