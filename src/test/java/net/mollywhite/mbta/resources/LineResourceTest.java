package net.mollywhite.mbta.resources;

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
import net.mollywhite.mbta.dao.TweetDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class LineResourceTest {
  private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  private LineResource resource;
  private Tweet tweet;
  private TweetDetails tweetDetails;
  private TweetDAO tweetDAO;
  private Connection connection;

  @ClassRule
  public static final DropwizardAppRule<MbtaConfiguration> RULE= new DropwizardAppRule<MbtaConfiguration>(MbtaApplication.class, ResourceHelpers.resourceFilePath("test-config.yml"));

  @Before
  public void setUp() throws Exception {
    DataSourceFactory dsf = RULE.getConfiguration().getDataSourceFactory();
    connection = DriverManager.getConnection(dsf.getUrl(), dsf.getUser(), dsf.getPassword());
    mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    final DBIFactory factory = new DBIFactory();
    DBI dbi = factory.build(RULE.getEnvironment(), dsf, "postgresql");
    tweetDAO = dbi.onDemand(TweetDAO.class);
    tweet = mapper.readValue(fixture("fixtures/RetweetFixture.json"), Tweet.class);
    tweetDetails = new TweetDetails(tweet);
    resource = new LineResource(tweetDAO);
    tweetDAO.truncate();
  }

  @After
  public void tearDown() throws Exception {
    connection.close();
  }

  @Test
  public void testGetTweetsByLine() throws Exception {
    // Retweet fixture is on the orange line
    insertTweetDetails(tweetDetails);
    Response orangeLineTweets = resource.getTweetsByLine("orange");
    List<TweetDetails> response = (List<TweetDetails>) orangeLineTweets.getEntity();
    assertThat(orangeLineTweets.getStatus()).isEqualTo(200);
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getLines()).containsOnly(Line.ORANGE);
  }

  @Test
  public void testGetTweetsByLineEmpty() throws Exception {
    Response redLineTweets = resource.getTweetsByLine("red");
    List<TweetDetails> response = (List<TweetDetails>) redLineTweets.getEntity();
    assertThat(redLineTweets.getStatus()).isEqualTo(200);
    assertThat(response).hasSize(0);
  }

  @Test
  public void testGetTweetsByLineNonexistent() throws Exception {
    Response lineTweets = resource.getTweetsByLine("foo");
    assertThat(lineTweets.getStatus()).isEqualTo(404);
  }

  private void insertTweetDetails(TweetDetails tweetDetails) throws JsonProcessingException, SQLException {
    Tweet tweet = tweetDetails.getTweet();
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
