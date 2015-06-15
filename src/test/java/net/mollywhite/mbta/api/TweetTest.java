package net.mollywhite.mbta.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;

public class TweetTest {
  private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Test
  public void deserializesFromJSON() throws Exception {
    Tweet tweet = mapper.readValue(fixture("fixtures/TweetWithReplyToFixture.json"), Tweet.class);
  }
}
