package net.mollywhite.mbta.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Coordinates {
  private final List<Float> coordinates;

  public Coordinates(@JsonProperty("coordinates") List<Float> coordinates) {
    this.coordinates = coordinates;
  }

  public List<Float> getCoordinates() {
    return coordinates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coordinates that = (Coordinates) o;
    return Objects.equals(getCoordinates(), that.getCoordinates());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCoordinates());
  }
}
