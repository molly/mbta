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
import net.mollywhite.mbta.api.Tweet;
import net.mollywhite.mbta.client.CreatedAtFormatter;
import net.mollywhite.mbta.client.MbtaClient;
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
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class AllResourceTest {
  private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  private AllResource resource;
  private MbtaClient mbtaClient;
  private Tweet retweet;
  private Tweet ashmontTweet;
  private TweetDetails retweetDetails;
  private TweetDetails ashmontTweetDetails;
  private TweetDAO tweetDAO;
  private Connection connection;

  @ClassRule
  public static final DropwizardAppRule<MbtaConfiguration> RULE= new DropwizardAppRule<MbtaConfiguration>(MbtaApplication.class, ResourceHelpers.resourceFilePath("test-config.yml"));

  @Before
  public void setUp() throws Exception {
    MbtaConfiguration config = RULE.getConfiguration();
    DataSourceFactory dsf = config.getDataSourceFactory();
    connection = DriverManager.getConnection(dsf.getUrl(), dsf.getUser(), dsf.getPassword());
    mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    final DBIFactory factory = new DBIFactory();
    DBI dbi = factory.build(RULE.getEnvironment(), dsf, "postgresql");
    tweetDAO = dbi.onDemand(TweetDAO.class);
    mbtaClient = new MbtaClient(mapper, config.getMbtaApiKey());
    retweet = mapper.readValue(fixture("fixtures/RetweetFixture.json"), Tweet.class);
    ashmontTweet = mapper.readValue(fixture("fixtures/AshmontFixture.json"), Tweet.class);
    retweetDetails = new TweetDetails(mbtaClient).from(retweet);
    ashmontTweetDetails = new TweetDetails(mbtaClient).from(ashmontTweet);
    resource = new AllResource(tweetDAO);
    tweetDAO.truncate();
  }

  @After
  public void tearDown() throws Exception {
    connection.close();
  }

  @Test
  public void testGetAllTweets() throws Exception {
    // Retweet fixture is on the orange line
    insertTweetDetails(retweetDetails);
    insertTweetDetails(ashmontTweetDetails);
    List<TweetDetails> response = resource.get();
    assertThat(response).hasSize(2);
  }

  @Test
  public void testGetTweetsByHour() throws Exception {
    String time61minutesAgo = CreatedAtFormatter.getString(OffsetDateTime.now().minusMinutes(61));
    String time30minutesAgo = CreatedAtFormatter.getString(OffsetDateTime.now().minusMinutes(30));
    Tweet tweet61minutesAgo = mapper.readValue(fixture("fixtures/RetweetFixture.json"), Tweet.class);
    tweet61minutesAgo.setCreatedAt(time61minutesAgo);
    TweetDetails tweetDetails61minutesAgo = new TweetDetails(mbtaClient).from(tweet61minutesAgo);
    Tweet tweet30minutesAgo = mapper.readValue(fixture("fixtures/RetweetFixture.json"), Tweet.class);
    tweet30minutesAgo.setCreatedAt(time30minutesAgo);
    TweetDetails tweetDetails30minutesAgo = new TweetDetails(mbtaClient).from(tweet30minutesAgo);

    insertTweetDetails(tweetDetails61minutesAgo);
    insertTweetDetails(tweetDetails30minutesAgo);
    Response response = resource.getTweetsByHour(1);
    assertThat(response.getStatus()).isEqualTo(200);
    assertThat((List<TweetDetails>) response.getEntity()).hasSize(1);
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
