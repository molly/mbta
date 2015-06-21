package net.mollywhite.mbta.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class TweetTest {
  private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Test
  public void deserializesFromJSON() throws Exception {
    List<UserMention> users = new ArrayList<UserMention>();
    users.add(new UserMention("150334831", Lists.newArrayList(0, 5), "MBTA", "MBTA"));
    users.add(new UserMention("2535372901", Lists.newArrayList(6, 14), "MBTA Commuter Rail", "MBTA_CR"));
    users.add(new UserMention("95431448", Lists.newArrayList(15, 27), "The Boston Globe", "BostonGlobe"));
    users.add(new UserMention("16558796", Lists.newArrayList(28, 34), "7News Boston", "7News"));
    Tweet expected = new Tweet(
        "Sun Jun 14 22:34:47 +0000 2015",
        "610213825237884929",
        "@MBTA @MBTA_CR @BostonGlobe @7News in this case silence is not Golden can someone share with the people on this train when they will be off?",
        "610210856945606656",
        "150334831",
        "MBTA",
        new User(
            "243519639",
            "John Casey",
            "johncaseywick",
            "http://pbs.twimg.com/profile_images/2789497755/aee93b828a8f95c8d5b0eb14e82947ad_normal.jpeg",
            "https://pbs.twimg.com/profile_images/2789497755/aee93b828a8f95c8d5b0eb14e82947ad_normal.jpeg"
        ),
        null,
        null,
        null,
        0,
        0,
        new Entities(
            Collections.<Hashtag> emptyList(),
            null,
            Collections.<URL> emptyList(),
            users
        ),
        "1434321287818"
    );

    Tweet actual = mapper.readValue(fixture("fixtures/TweetWithReplyToFixture.json"), Tweet.class);
    assertEquals(expected, actual);
  }
}
