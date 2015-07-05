package net.mollywhite.mbta.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Line;
import net.mollywhite.mbta.api.Station;
import net.mollywhite.mbta.api.Tweet;
import net.mollywhite.mbta.api.Vehicle;
import org.junit.Before;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TweetDetailsTest {
  private ObjectMapper mapper;
  private MbtaClient mbtaClient;

  @Before
  public void setUp() throws Exception {
    this.mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    this.mbtaClient = mock(MbtaClient.class);
    when(mbtaClient.getAllVehicles()).thenReturn(Lists.newArrayList(new Vehicle("12345", Line.GREEN, Branch.B)));
  }

  @Test
  public void testGetReplyTo() throws Exception {
    final Tweet tweet = mapper.readValue(fixture("fixtures/TweetWithReplyToFixture.json"), Tweet.class);
    final TweetDetails tweetDetails = new TweetDetails(mbtaClient).from(tweet);
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
    final TweetDetails tweetDetails = new TweetDetails(mbtaClient).from(tweet);
    assertThat(tweetDetails.getLines()).containsExactly(Line.ORANGE);
    assertThat(tweetDetails.getBranches()).isEmpty();
    assertThat(tweetDetails.getStations()).containsOnly(Station.WELLINGTON, Station.OAKGROVE);
    assertThat(tweetDetails.getVehicles()).isEmpty();
    assertThat(tweetDetails.getDirection()).isNull();
    assertThat(tweetDetails.getImage()).isFalse();
    assertThat(tweetDetails.getRetweet()).isTrue();
    assertThat(tweetDetails.getOfficial()).isFalse();
  }

  @Test
  public void testGetVehiclesFromTweet() throws Exception {
    final Tweet tweet = mapper.readValue(fixture("fixtures/TweetWithVehicleFixture.json"), Tweet.class);
    final TweetDetails tweetDetails = new TweetDetails(mbtaClient).from(tweet);
    assertThat(tweetDetails.getVehicles()).containsOnly("12345");
    assertThat(tweetDetails.getLines()).containsOnly(Line.GREEN);
    assertThat(tweetDetails.getBranches()).containsOnly(Branch.B);
  }
}
