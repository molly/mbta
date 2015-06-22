package net.mollywhite.mbta.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import net.mollywhite.mbta.MbtaConfiguration;
import org.skife.jdbi.v2.DBI;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class TwitterClientModule extends AbstractModule {
  @Override
  protected void configure() {
  }

  @Provides
  @Singleton
  TwitterClient provideTwitterClient() throws IOException {
    TwitterClient twitterClient = new TwitterClient();
    return twitterClient;
  }

  @Provides
  BlockingQueue<String> provideMessageQueue(TwitterClient twitterClient) {
    return twitterClient.getMessageQueue();
  }

  @Provides
  ObjectMapper provideObjectMapper() {
    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper;
  }

  @Inject
  @Provides
  DBI providesDBI(MbtaConfiguration configuration, Environment environment) {
    final DBIFactory factory = new DBIFactory();
    return factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
  }
}
