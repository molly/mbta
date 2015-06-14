package net.mollywhite.mbta;

import com.google.inject.Inject;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.mollywhite.mbta.dao.TweetDAO;
import net.mollywhite.mbta.resources.TweetResource;
import org.skife.jdbi.v2.DBI;

public class MbtaApplication extends Application<MbtaConfiguration> {

  private GuiceBundle<MbtaConfiguration> guiceBundle;

  public static void main(String[] args) throws Exception {
    new MbtaApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap bootstrap) {
    guiceBundle = GuiceBundle.<MbtaConfiguration>newBuilder()
        .addModule(new MbtaModule())
        .enableAutoConfig(getClass().getPackage().getName())
        .setConfigClass(MbtaConfiguration.class)
        .build();

    bootstrap.addBundle(guiceBundle);
  }

  @Override
  public String getName() {
    return "mbta";
  }

  @Inject
  @Override
  public void run(MbtaConfiguration mbtaConfiguration, Environment environment) throws Exception {
    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(environment, mbtaConfiguration.getDataSourceFactory(), "postgresql");
    final TweetDAO tweetDAO = jdbi.onDemand(TweetDAO.class);
    environment.jersey().register(new TweetResource(tweetDAO));
  }
}
