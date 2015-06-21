package net.mollywhite.mbta.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.mollywhite.mbta.client.CreatedAtFormatter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Tweet {
  private final OffsetDateTime createdAt;
  private final String idStr;
  private final String text;
  private final Optional<String> inReplyToStatusIdStr;
  private final Optional<String> inReplyToUserIdStr;
  private final Optional<String> inReplyToScreenName;
  private final User user;
  private final Optional<List<Float>> coordinates;
  private final Optional<Place> place;
  private final Optional<Tweet> retweetedStatus;
  private final int retweetCount;
  private final int favoriteCount;
  private final Entities entities;

  @JsonCreator
  public Tweet(@JsonProperty("created_at") String createdAt,
               @JsonProperty("id_str") String idStr,
               @JsonProperty("text") String text,
               @JsonProperty("in_reply_to_status_id_str") String inReplyToStatusIdStr,
               @JsonProperty("in_reply_to_user_id_str") String inReplyToUserIdStr,
               @JsonProperty("in_reply_to_screen_name") String inReplyToScreenName,
               @JsonProperty("user") User user,
               @JsonProperty("coordinates") List<Float> coordinates,
               @JsonProperty("place") Place place,
               @JsonProperty("retweeted_status") Tweet retweetedStatus,
               @JsonProperty("retweet_count") int retweetCount,
               @JsonProperty("favorite_count") int favoriteCount,
               @JsonProperty("entities") Entities entities) {
    this.createdAt = CreatedAtFormatter.get(createdAt);
    this.idStr = idStr;
    this.text = text;
    this.inReplyToStatusIdStr = Optional.ofNullable(inReplyToStatusIdStr);
    this.inReplyToUserIdStr = Optional.ofNullable(inReplyToUserIdStr);
    this.inReplyToScreenName = Optional.ofNullable(inReplyToScreenName);
    this.user = user;
    this.coordinates = Optional.ofNullable(coordinates);
    this.place = Optional.ofNullable(place);
    this.retweetedStatus = Optional.ofNullable(retweetedStatus);
    this.retweetCount = retweetCount;
    this.favoriteCount = favoriteCount;
    this.entities = entities;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public String getIdStr() {
    return idStr;
  }

  public String getText() {
    return text;
  }

  public Optional<String> getInReplyToStatusIdStr() {
    return inReplyToStatusIdStr;
  }

  public Optional<String> getInReplyToUserIdStr() {
    return inReplyToUserIdStr;
  }

  public Optional<String> getInReplyToScreenName() {
    return inReplyToScreenName;
  }

  public User getUser() {
    return user;
  }

  public Optional<List<Float>> getCoordinates() {
    return coordinates;
  }

  public Optional<Place> getPlace() {
    return place;
  }

  public Optional<Tweet> getRetweetedStatus() {
    return retweetedStatus;
  }

  public int getRetweetCount() {
    return retweetCount;
  }

  public int getFavoriteCount() {
    return favoriteCount;
  }

  public Entities getEntities() {
    return entities;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tweet tweet = (Tweet) o;
    return Objects.equals(getRetweetCount(), tweet.getRetweetCount()) &&
        Objects.equals(getFavoriteCount(), tweet.getFavoriteCount()) &&
        Objects.equals(getCreatedAt(), tweet.getCreatedAt()) &&
        Objects.equals(getIdStr(), tweet.getIdStr()) &&
        Objects.equals(getText(), tweet.getText()) &&
        Objects.equals(getInReplyToStatusIdStr(), tweet.getInReplyToStatusIdStr()) &&
        Objects.equals(getInReplyToUserIdStr(), tweet.getInReplyToUserIdStr()) &&
        Objects.equals(getInReplyToScreenName(), tweet.getInReplyToScreenName()) &&
        Objects.equals(getUser(), tweet.getUser()) &&
        Objects.equals(getCoordinates(), tweet.getCoordinates()) &&
        Objects.equals(getPlace(), tweet.getPlace()) &&
        Objects.equals(getRetweetedStatus(), tweet.getRetweetedStatus()) &&
        Objects.equals(getEntities(), tweet.getEntities());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCreatedAt(), getIdStr(), getText(), getInReplyToStatusIdStr(), getInReplyToUserIdStr(), getInReplyToScreenName(), getUser(), getCoordinates(), getPlace(), getRetweetedStatus(), getRetweetCount(), getFavoriteCount(), getEntities());
  }
}
