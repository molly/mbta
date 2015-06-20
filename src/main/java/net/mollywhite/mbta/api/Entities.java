package net.mollywhite.mbta.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Entities {
  private final List<Hashtag> hashtags;
  private final List<Media> media;
  private final List<URL> urls;
  private final List<UserMention> userMentions;

  @JsonCreator
  public Entities(@JsonProperty("hashtags") List<Hashtag> hashtags,
                  @JsonProperty("media") List<Media> media,
                  @JsonProperty("urls") List<URL> urls,
                  @JsonProperty("user_mentions") List<UserMention> userMentions) {
    this.hashtags = hashtags;
    this.media = media;
    this.urls = urls;
    this.userMentions = userMentions;
  }

  public List<Hashtag> getHashtags() {
    return hashtags;
  }

  public List<Media> getMedia() {
    return media;
  }

  public List<URL> getUrls() {
    return urls;
  }

  public List<UserMention> getUserMentions() {
    return userMentions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Entities entities = (Entities) o;
    return Objects.equals(getHashtags(), entities.getHashtags()) &&
        Objects.equals(getMedia(), entities.getMedia()) &&
        Objects.equals(getUrls(), entities.getUrls()) &&
        Objects.equals(getUserMentions(), entities.getUserMentions());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getHashtags(), getMedia(), getUrls(), getUserMentions());
  }
}
