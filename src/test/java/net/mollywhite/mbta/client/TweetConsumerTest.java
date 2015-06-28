package net.mollywhite.mbta.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import net.mollywhite.mbta.MbtaApplication;
import net.mollywhite.mbta.MbtaConfiguration;
import net.mollywhite.mbta.api.Tweet;
import net.mollywhite.mbta.dao.TweetDAO;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import java.sql.Connection;
import java.sql.DriverManager;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class TweetConsumerTest {
  private TweetConsumer tweetConsumer;
  private ObjectMapper mapper;
  private TweetDAO tweetDAO;

  @ClassRule
  public static final DropwizardAppRule<MbtaConfiguration> RULE= new DropwizardAppRule<MbtaConfiguration>(MbtaApplication.class, ResourceHelpers.resourceFilePath("test-config.yml"));

  @Before
  public void setUp() throws Exception {
    DataSourceFactory dsf = RULE.getConfiguration().getDataSourceFactory();
    Connection connection = DriverManager.getConnection(dsf.getUrl(), dsf.getUser(), dsf.getPassword());
    TwitterClient twitterClient = new TwitterClient();
    mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    final DBIFactory factory = new DBIFactory();
    DBI dbi = factory.build(RULE.getEnvironment(), dsf, "postgresql");
    tweetConsumer = new TweetConsumer(twitterClient, mapper, dbi, connection);
    tweetDAO = dbi.onDemand(TweetDAO.class);
    tweetDAO.truncate();
  }

  @Test
  public void testInsert() throws Exception {
    assertThat(tweetConsumer.count()).isZero();
    final Tweet tweet = mapper.readValue(fixture("fixtures/TweetWithReplyToFixture.json"), Tweet.class);
    tweetConsumer.insertTweet(tweet);
    assertThat(tweetConsumer.count()).isEqualTo(1);
  }
}
