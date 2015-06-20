package net.mollywhite.mbta.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Hashtag {
  private final List<Integer> indices;
  private final String text;

  @JsonCreator
  public Hashtag(@JsonProperty("indices") List<Integer> indices,
                 @JsonProperty("text") String text) {
    this.indices = indices;
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public List<Integer> getIndices() {
    return indices;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Hashtag hashtag = (Hashtag) o;
    return Objects.equals(getIndices(), hashtag.getIndices()) &&
        Objects.equals(getText(), hashtag.getText());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIndices(), getText());
  }
}
