package net.mollywhite.mbta.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class User {
  private final String idStr;
  private final String name;
  private final String screenName;
  private final String profileImageUrl;

  @JsonCreator
  public User(@JsonProperty("id_str") String idStr,
              @JsonProperty("name") String name,
              @JsonProperty("screen_name") String screenName,
              @JsonProperty("profile_image_url") String profileImageUrl) {
    this.idStr = idStr;
    this.name = name;
    this.screenName = screenName;
    this.profileImageUrl = profileImageUrl;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(idStr, user.idStr) &&
        Objects.equals(name, user.name) &&
        Objects.equals(screenName, user.screenName) &&
        Objects.equals(profileImageUrl, user.profileImageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idStr, name, screenName, profileImageUrl);
  }
}
