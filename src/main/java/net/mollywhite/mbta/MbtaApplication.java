package net.mollywhite.mbta;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.mollywhite.mbta.client.MbtaClient;
import net.mollywhite.mbta.client.TweetConsumer;
import net.mollywhite.mbta.client.TwitterClient;
import net.mollywhite.mbta.dao.TweetDAO;
import net.mollywhite.mbta.health.TwitterHealthCheck;
import net.mollywhite.mbta.resources.AllResource;
import net.mollywhite.mbta.resources.BranchResource;
import net.mollywhite.mbta.resources.LineResource;
import net.mollywhite.mbta.resources.StationResource;
import net.mollywhite.mbta.services.TwitterClientManager;
import org.skife.jdbi.v2.DBI;

import java.sql.Connection;
import java.sql.DriverManager;

public class MbtaApplication extends Application<MbtaConfiguration> {
  private DBI dbi;

  public static void main(String[] args) throws Exception {
    new MbtaApplication().run(args);
  }

  @Override
  public String getName() {
    return "mbta";
  }

  @Override
  public void initialize(Bootstrap bootstrap) {
  }

  @Override
  public void run(MbtaConfiguration config, Environment environment) throws Exception {
    final DataSourceFactory dsf = config.getDataSourceFactory();
    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(environment, dsf, "postgresql");
    final TweetDAO tweetDAO = jdbi.onDemand(TweetDAO.class);

    final TwitterClient twitterClient = new TwitterClient(config.getTwitterConsumerKey(), config.getTwitterConsumerSecret(), config.getTwitterToken(), config.getTwitterSecret());
    final TwitterHealthCheck twitterHealthCheck = new TwitterHealthCheck(twitterClient);
    final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    final Connection connection = DriverManager.getConnection(dsf.getUrl(), dsf.getUser(), dsf.getPassword());
    final MbtaClient mbtaClient = new MbtaClient(mapper, config.getMbtaApiKey());
    final TweetConsumer tweetConsumer = new TweetConsumer(twitterClient, mapper, tweetDAO, connection, mbtaClient);
    TwitterClientManager twitterClientManager = new TwitterClientManager(tweetConsumer);

    environment.healthChecks().register("Twitter healthcheck", twitterHealthCheck);
    environment.jersey().register(new AllResource(tweetDAO));
    environment.jersey().register(new BranchResource(tweetDAO));
    environment.jersey().register(new LineResource(tweetDAO));
    environment.jersey().register(new StationResource(tweetDAO));
    environment.lifecycle().manage(twitterClientManager);
  }
}
