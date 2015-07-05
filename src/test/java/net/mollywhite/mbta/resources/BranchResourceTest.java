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
import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Tweet;
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
import java.util.List;
import java.util.Set;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class BranchResourceTest {
  private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  private BranchResource resource;
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
    tweet = mapper.readValue(fixture("fixtures/AshmontFixture.json"), Tweet.class);
    MbtaClient mbtaClient = new MbtaClient(mapper);
    tweetDetails = new TweetDetails(mbtaClient).from(tweet);
    resource = new BranchResource(tweetDAO);
    tweetDAO.truncate();
  }

  @After
  public void tearDown() throws Exception {
    connection.close();
  }

  @Test
  public void testGetTweetsByBranch() throws Exception {
    insertTweetDetails(tweetDetails);
    Response ashmontBranchTweets = resource.getTweetsByBranch("ashmont");
    List<TweetDetails> response = (List<TweetDetails>) ashmontBranchTweets.getEntity();
    assertThat(ashmontBranchTweets.getStatus()).isEqualTo(200);
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getBranches()).containsOnly(Branch.ASHMONT);
  }

  @Test
  public void testGetTweetsByBranchEmpty() throws Exception {
    Response bBranchTweets = resource.getTweetsByBranch("b");
    List<TweetDetails> response = (List<TweetDetails>) bBranchTweets.getEntity();
    assertThat(bBranchTweets.getStatus()).isEqualTo(200);
    assertThat(response).hasSize(0);
  }

  @Test
  public void testGetTweetsByBranchNonexistent() throws Exception {
    Response branchTweets = resource.getTweetsByBranch("foo");
    assertThat(branchTweets.getStatus()).isEqualTo(404);
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
