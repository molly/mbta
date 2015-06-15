package net.mollywhite.mbta.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Tweet {
  private final String createdAt;
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
  private final Instant timestampMs;

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
               @JsonProperty("entities") Entities entities,
               @JsonProperty("timestamp_ms") String timestampMs) {
    this.createdAt = createdAt;
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
    this.timestampMs = Instant.ofEpochMilli(Long.valueOf(timestampMs));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tweet tweet = (Tweet) o;
    return Objects.equals(retweetCount, tweet.retweetCount) &&
        Objects.equals(favoriteCount, tweet.favoriteCount) &&
        Objects.equals(createdAt, tweet.createdAt) &&
        Objects.equals(idStr, tweet.idStr) &&
        Objects.equals(text, tweet.text) &&
        Objects.equals(inReplyToStatusIdStr, tweet.inReplyToStatusIdStr) &&
        Objects.equals(inReplyToUserIdStr, tweet.inReplyToUserIdStr) &&
        Objects.equals(inReplyToScreenName, tweet.inReplyToScreenName) &&
        Objects.equals(user, tweet.user) &&
        Objects.equals(coordinates, tweet.coordinates) &&
        Objects.equals(place, tweet.place) &&
        Objects.equals(retweetedStatus, tweet.retweetedStatus) &&
        Objects.equals(entities, tweet.entities) &&
        Objects.equals(timestampMs, tweet.timestampMs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdAt, idStr, text, inReplyToStatusIdStr, inReplyToUserIdStr, inReplyToScreenName, user, coordinates, place, retweetedStatus, retweetCount, favoriteCount, entities, timestampMs);
  }
}
