package net.mollywhite.mbta.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.mollywhite.mbta.client.CreatedAtFormatter;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Tweet {
  private String createdAt;
  private final String idStr;
  private final String text;
  private final String inReplyToStatusIdStr;
  private final String inReplyToUserIdStr;
  private final String inReplyToScreenName;
  private final User user;
  private final Coordinates coordinates;
  private final Place place;
  private final Tweet retweetedStatus;
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
               @JsonProperty("coordinates") Coordinates coordinates,
               @JsonProperty("place") Place place,
               @JsonProperty("retweeted_status") Tweet retweetedStatus,
               @JsonProperty("retweet_count") int retweetCount,
               @JsonProperty("favorite_count") int favoriteCount,
               @JsonProperty("entities") Entities entities) {
    this.createdAt = createdAt;
    this.idStr = idStr;
    this.text = text;
    this.inReplyToStatusIdStr = inReplyToStatusIdStr;
    this.inReplyToUserIdStr = inReplyToUserIdStr;
    this.inReplyToScreenName = inReplyToScreenName;
    this.user = user;
    this.coordinates = coordinates;
    this.place = place;
    this.retweetedStatus = retweetedStatus;
    this.retweetCount = retweetCount;
    this.favoriteCount = favoriteCount;
    this.entities = entities;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  @JsonIgnore
  public OffsetDateTime getCreatedAtDateTime() {
    return CreatedAtFormatter.getTime(createdAt);
  }

  public String getIdStr() {
    return idStr;
  }

  public String getText() {
    return text;
  }

  public String getInReplyToStatusIdStr() {
    return inReplyToStatusIdStr;
  }

  public String getInReplyToUserIdStr() {
    return inReplyToUserIdStr;
  }

  public String getInReplyToScreenName() {
    return inReplyToScreenName;
  }

  public User getUser() {
    return user;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public Place getPlace() {
    return place;
  }

  public Tweet getRetweetedStatus() {
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
