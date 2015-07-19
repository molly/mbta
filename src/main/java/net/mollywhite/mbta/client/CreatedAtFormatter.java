package net.mollywhite.mbta.client;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CreatedAtFormatter {
  public static OffsetDateTime getTime(String createdAt) throws DateTimeParseException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy");
    return OffsetDateTime.parse(createdAt, formatter);
  }

  public static String getString(OffsetDateTime createdAt) throws DateTimeParseException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy");
    return createdAt.format(formatter);
  }
}
