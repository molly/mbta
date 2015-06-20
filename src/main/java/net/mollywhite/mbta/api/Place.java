package net.mollywhite.mbta.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Place {
  private final String fullName;
  private final String name;

  @JsonCreator
  public Place(@JsonProperty("full_name") String fullName,
               @JsonProperty("name") String name) {
    this.fullName = fullName;
    this.name = name;
  }

  public String getFullName() {
    return fullName;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Place place = (Place) o;
    return Objects.equals(getFullName(), place.getFullName()) &&
        Objects.equals(getName(), place.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFullName(), getName());
  }
}
