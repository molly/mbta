package net.mollywhite.mbta.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class User {
  private final String idStr;
  private final String name;
  private final String screenName;
  private final String profileImageUrl;
  private final String profileImageUrlHttps;

  @JsonCreator
  public User(@JsonProperty("id_str") String idStr,
              @JsonProperty("name") String name,
              @JsonProperty("screen_name") String screenName,
              @JsonProperty("profile_image_url") String profileImageUrl,
              @JsonProperty("profile_image_url_https") String profileImageUrlHttps) {
    this.idStr = idStr;
    this.name = name;
    this.screenName = screenName;
    this.profileImageUrl = profileImageUrl;
    this.profileImageUrlHttps = profileImageUrlHttps;
  }

  public String getIdStr() {
    return idStr;
  }

  public String getName() {
    return name;
  }

  public String getScreenName() {
    return screenName;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public String getProfileImageUrlHttps() {
    return profileImageUrlHttps;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(getIdStr(), user.getIdStr()) &&
        Objects.equals(getName(), user.getName()) &&
        Objects.equals(getScreenName(), user.getScreenName()) &&
        Objects.equals(getProfileImageUrl(), user.getProfileImageUrl()) &&
        Objects.equals(getProfileImageUrlHttps(), user.getProfileImageUrlHttps());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIdStr(), getName(), getScreenName(), getProfileImageUrl(), getProfileImageUrlHttps());
  }
}
