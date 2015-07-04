package net.mollywhite.mbta.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import net.mollywhite.mbta.MbtaApplication;
import net.mollywhite.mbta.MbtaConfiguration;
import net.mollywhite.mbta.api.Line;
import net.mollywhite.mbta.api.Tweet;
import net.mollywhite.mbta.client.TweetDetails;
import net.mollywhite.mbta.client.TwitterClient;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class TweetDAOTest {
  private ObjectMapper mapper;
  private Connection connection;
  private TweetDAO tweetDAO;
  private Tweet tweet;

  @ClassRule
  public static final DropwizardAppRule<MbtaConfiguration> RULE= new DropwizardAppRule<MbtaConfiguration>(MbtaApplication.class, ResourceHelpers.resourceFilePath("test-config.yml"));

  @Before
  public void setUp() throws Exception {
    DataSourceFactory dsf = RULE.getConfiguration().getDataSourceFactory();
    connection = DriverManager.getConnection(dsf.getUrl(), dsf.getUser(), dsf.getPassword());
    TwitterClient twitterClient = new TwitterClient();
    mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    final DBIFactory factory = new DBIFactory();
    DBI dbi = factory.build(RULE.getEnvironment(), dsf, "postgresql");
    tweetDAO = dbi.onDemand(TweetDAO.class);
    tweet = mapper.readValue(fixture("fixtures/TweetWithReplyToFixture.json"), Tweet.class);
    tweetDAO.truncate();
  }

  @Test
  public void testTruncate() throws Exception {
    tweetDAO.truncate();
    assertThat(tweetDAO.count()).isEqualTo(0);
  }

  @Test
  public void testCount() throws Exception {
    assertThat(tweetDAO.count()).isEqualTo(0);
    insertTweet(tweet);
    assertThat(tweetDAO.count()).isEqualTo(1);
  }

  @Test
  public void testGetTweetsByLine() throws Exception {
    insertTweet(tweet); // tweet contains a tweet that is on the orange line
    List<TweetDetails> orangeLineTweets = tweetDAO.getTweetsByLine(Line.ORANGE);
    assertThat(orangeLineTweets);
  }

  private void insertTweet(Tweet tweet) throws JsonProcessingException, SQLException {
    TweetDetails tweetDetails = new TweetDetails(tweet);
    Set<String> lines = tweetDetails.getLinesAsStrings();
    Set<String> branches = tweetDetails.getBranchesAsStrings();
    Set<String> stations = tweetDetails.getStationsAsStrings();
    Set<String> vehicles = tweetDetails.getVehicles();
    String[] lineArray = lines.toArray(new String[lines.size()]);
    String[] branchArray = branches.toArray(new String[branches.size()]);
    String[] stationArray = stations.toArray(new String[stations.size()]);
    String[] vehicleArray = vehicles.toArray(new String[vehicles.size()]);

    tweetDAO.insert(mapper.writeValueAsString(tweet),
        Timestamp.from(tweet.getCreatedAtDateTime().toInstant()),
        connection.createArrayOf("varchar", lineArray),
        connection.createArrayOf("varchar", branchArray),
        connection.createArrayOf("varchar", stationArray),
        connection.createArrayOf("varchar", vehicleArray),
        tweetDetails.getDirection(),
        tweetDetails.getImage(),
        tweetDetails.getRetweet(),
        tweetDetails.getOfficial(),
        tweetDetails.getCategory());
  }
}
