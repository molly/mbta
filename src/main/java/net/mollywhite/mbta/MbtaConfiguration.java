package net.mollywhite.mbta;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MbtaConfiguration extends Configuration {
  @Valid
  @NotNull
  @JsonProperty
  private DataSourceFactory database = new DataSourceFactory();

  @NotEmpty
  private String twitterConsumerKey;

  @NotEmpty
  private String twitterConsumerSecret;

  @NotEmpty
  private String twitterSecret;

  @NotEmpty
  private String twitterToken;

  @NotEmpty
  private String mbtaApiKey;

  public DataSourceFactory getDataSourceFactory() {
    return database;
  }

  @JsonProperty
  public String getTwitterConsumerKey() {
    return twitterConsumerKey;
  }

  @JsonProperty
  public void setTwitterConsumerKey(String twitterConsumerKey) {
    this.twitterConsumerKey = twitterConsumerKey;
  }

  @JsonProperty
  public String getTwitterConsumerSecret() {
    return twitterConsumerSecret;
  }

  @JsonProperty
  public void setTwitterConsumerSecret(String twitterConsumerSecret) {
    this.twitterConsumerSecret = twitterConsumerSecret;
  }

  @JsonProperty
  public String getTwitterSecret() {
    return twitterSecret;
  }

  @JsonProperty
  public void setTwitterSecret(String twitterSecret) {
    this.twitterSecret = twitterSecret;
  }

  @JsonProperty
  public String getTwitterToken() {
    return twitterToken;
  }

  @JsonProperty
  public void setTwitterToken(String twitterToken) {
    this.twitterToken = twitterToken;
  }

  @JsonProperty
  public String getMbtaApiKey() {
    return mbtaApiKey;
  }

  @JsonProperty
  public void setMbtaApiKey(String mbtaApiKey) {
    this.mbtaApiKey = mbtaApiKey;
  }
}
