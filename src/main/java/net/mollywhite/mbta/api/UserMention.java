package net.mollywhite.mbta.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class UserMention {
  private final String idStr;
  private final List<Integer> indices;
  private final String name;
  private final String screenName;

  @JsonCreator
  public UserMention(@JsonProperty("id_str") String idStr,
                     @JsonProperty("indices") List<Integer> indices,
                     @JsonProperty("name") String name,
                     @JsonProperty("screen_name") String screenName) {
    this.idStr = idStr;
    this.indices = indices;
    this.name = name;
    this.screenName = screenName;
  }

  public String getIdStr() {
    return idStr;
  }

  public List<Integer> getIndices() {
    return indices;
  }

  public String getName() {
    return name;
  }

  public String getScreenName() {
    return screenName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserMention that = (UserMention) o;
    return Objects.equals(getIdStr(), that.getIdStr()) &&
        Objects.equals(getIndices(), that.getIndices()) &&
        Objects.equals(getName(), that.getName()) &&
        Objects.equals(getScreenName(), that.getScreenName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIdStr(), getIndices(), getName(), getScreenName());
  }
}
