package net.mollywhite.mbta.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class URL {
  private final String displayUrl;
  private final String expandedUrl;
  private final List<Integer> indices;
  private final String url;

  @JsonCreator
  public URL(@JsonProperty("display_url") String displayUrl,
             @JsonProperty("expanded_url") String expandedUrl,
             @JsonProperty("indices") List<Integer> indices,
             @JsonProperty("url") String url) {
    this.displayUrl = displayUrl;
    this.expandedUrl = expandedUrl;
    this.indices = indices;
    this.url = url;
  }

  public String getDisplayUrl() {
    return displayUrl;
  }

  public String getExpandedUrl() {
    return expandedUrl;
  }

  public List<Integer> getIndices() {
    return indices;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    URL url1 = (URL) o;
    return Objects.equals(getDisplayUrl(), url1.getDisplayUrl()) &&
        Objects.equals(getExpandedUrl(), url1.getExpandedUrl()) &&
        Objects.equals(getIndices(), url1.getIndices()) &&
        Objects.equals(getUrl(), url1.getUrl());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDisplayUrl(), getExpandedUrl(), getIndices(), getUrl());
  }
}
