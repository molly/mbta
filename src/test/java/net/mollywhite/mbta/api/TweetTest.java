package net.mollywhite.mbta.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class TweetTest {
  private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Test
  public void deserializesFromJSON() throws Exception {
    Tweet expected = new Tweet(
        "Sun Jun 14 22:34:47 +0000 2015",
        "610213825237884929",
        "@MBTA @MBTA_CR @BostonGlobe @7News in this case silence is not Golden can someone share with the people on this train when they will be off?",
        "610210856945606656",
        "150334831",
        "MBTA",
        new User(),
        null,
        null,
        null,
        0,
        0,
        new Entities(),
        "1434321287818"
    );
    Tweet actual = mapper.readValue(fixture("fixtures/TweetWithReplyToFixture.json"), Tweet.class);
    assertEquals(expected, actual);
  }
}
