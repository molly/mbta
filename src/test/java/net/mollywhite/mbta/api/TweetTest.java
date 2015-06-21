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
    Tweet expectedReplyTo = new Tweet(
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

    Tweet expectedRetweet = new Tweet(
        "Sun Jun 21 17:27:47 +0000 2015",
        "612673280051388416",
        "RT @MBTA: #MBTA #OrangeLine evening Wellington - Oak Grove svc suspension starts 6/22 to make the subway more winter resilient: http://t.co\u2026",
        null,
        null,
        null,
        new User(
            "21014931",
            "Maureen Fox",
            "maureenfox",
            "http://pbs.twimg.com/profile_images/1993942809/image_normal.jpg",
            "https://pbs.twimg.com/profile_images/1993942809/image_normal.jpg"
        ),
        null,
        null,
        new Tweet(
            "Sun Jun 21 17:05:04 +0000 2015",
            "612667561788997633",
            "#MBTA #OrangeLine evening Wellington - Oak Grove svc suspension starts 6/22 to make the subway more winter resilient: http://t.co/QcmDEfbY4T",
            null,
            null,
            null,
            new User(
               "150334831",
                "MBTA",
                "MBTA",
                "http://pbs.twimg.com/profile_images/950550372/ProfilePic_normal.jpg",
                "https://pbs.twimg.com/profile_images/950550372/ProfilePic_normal.jpg"
            ),
            null,
            null,
            null,
            4,
            1,
            new Entities(
                Lists.newArrayList(new Hashtag(Lists.newArrayList(0, 5), "MBTA"), new Hashtag(Lists.newArrayList(6, 17), "OrangeLine")),
                null,
                Lists.newArrayList(new URL("bit.ly/1exXdtK", "http://bit.ly/1exXdtK", Lists.newArrayList(118, 140), "http://t.co/QcmDEfbY4T")),
                Collections.<UserMention> emptyList()
            ),
            null
        ),
        0,
        0,
        new Entities(
            Lists.newArrayList(new Hashtag(Lists.newArrayList(10, 15), "MBTA"), new Hashtag(Lists.newArrayList(16, 27), "OrangeLine")),
            null,
            Lists.newArrayList(new URL("bit.ly/1exXdtK", "http://bit.ly/1exXdtK", Lists.newArrayList(139, 140), "http://t.co/QcmDEfbY4T")),
            Lists.newArrayList(new UserMention("150334831", Lists.newArrayList(3, 8), "MBTA", "MBTA"))
        ),
        "1434907667540"
    );

    Tweet actualReplyTo = mapper.readValue(fixture("fixtures/TweetWithReplyToFixture.json"), Tweet.class);
    Tweet actualRetweet = mapper.readValue(fixture("fixtures/RetweetFixture.json"), Tweet.class);
    assertEquals(expectedReplyTo, actualReplyTo);
    assertEquals(expectedRetweet, actualRetweet);
  }
}
