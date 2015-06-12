package net.mollywhite.mbta;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class MbtaConfiguration extends Configuration {
  @NotEmpty
  @JsonProperty
  private String template;

  @NotEmpty
  @JsonProperty
  private String defaultName = "Stranger";

  public String getTemplate() {
    return template;
  }

  public String getDefaultName() {
    return defaultName;
  }
}
