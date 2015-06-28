package net.mollywhite.mbta.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mollywhite.mbta.api.Line;
import net.mollywhite.mbta.api.Station;
import net.mollywhite.mbta.api.Tweet;
import org.junit.Before;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class TweetDetailsTest {
  private ObjectMapper mapper;

  @Before
  public void setUp() throws Exception {
    mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Test
  public void testGetReplyTo() throws Exception {
    final Tweet tweet = mapper.readValue(fixture("fixtures/TweetWithReplyToFixture.json"), Tweet.class);
    final TweetDetails tweetDetails = new TweetDetails(tweet).get();
    assertThat(tweetDetails.getLines()).isEmpty();
    assertThat(tweetDetails.getBranches()).isEmpty();
    assertThat(tweetDetails.getStations()).isEmpty();
    assertThat(tweetDetails.getVehicles()).isEmpty();
    assertThat(tweetDetails.getDirection()).isNull();
    assertThat(tweetDetails.getImage()).isFalse();
    assertThat(tweetDetails.getRetweet()).isFalse();
    assertThat(tweetDetails.getOfficial()).isFalse();
  }

  @Test
  public void testRetweet() throws Exception {
    final Tweet tweet = mapper.readValue(fixture("fixtures/RetweetFixture.json"), Tweet.class);
    final TweetDetails tweetDetails = new TweetDetails(tweet).get();
    assertThat(tweetDetails.getLines()).containsExactly(Line.ORANGE);
    assertThat(tweetDetails.getBranches()).isEmpty();
    assertThat(tweetDetails.getStations()).containsOnly(Station.WELLINGTON, Station.OAKGROVE);
    assertThat(tweetDetails.getVehicles()).isEmpty();
    assertThat(tweetDetails.getDirection()).isNull();
    assertThat(tweetDetails.getImage()).isFalse();
    assertThat(tweetDetails.getRetweet()).isTrue();
    assertThat(tweetDetails.getOfficial()).isFalse();
  }
}
