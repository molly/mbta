package net.mollywhite.mbta.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Media {
  private final String displayUrl;
  private final String expandedUrl;
  private final List<Integer> indices;
  private final String mediaUrl;
  private final String mediaUrlHttps;
  private final String type;
  private final String url;

  @JsonCreator
  public Media(@JsonProperty("display_url") String displayUrl,
               @JsonProperty("expanded_url") String expandedUrl,
               @JsonProperty("indices") List<Integer> indices,
               @JsonProperty("media_url") String mediaUrl,
               @JsonProperty("media_url_https") String mediaUrlHttps,
               @JsonProperty("type") String type,
               @JsonProperty("url") String url) {
    this.displayUrl = displayUrl;
    this.expandedUrl = expandedUrl;
    this.indices = indices;
    this.mediaUrl = mediaUrl;
    this.mediaUrlHttps = mediaUrlHttps;
    this.type = type;
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

  public String getMediaUrl() {
    return mediaUrl;
  }

  public String getMediaUrlHttps() {
    return mediaUrlHttps;
  }

  public String getType() {
    return type;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Media media = (Media) o;
    return Objects.equals(getDisplayUrl(), media.getDisplayUrl()) &&
        Objects.equals(getExpandedUrl(), media.getExpandedUrl()) &&
        Objects.equals(getIndices(), media.getIndices()) &&
        Objects.equals(getMediaUrl(), media.getMediaUrl()) &&
        Objects.equals(getMediaUrlHttps(), media.getMediaUrlHttps()) &&
        Objects.equals(getType(), media.getType()) &&
        Objects.equals(getUrl(), media.getUrl());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDisplayUrl(), getExpandedUrl(), getIndices(), getMediaUrl(), getMediaUrlHttps(), getType(), getUrl());
  }
}
