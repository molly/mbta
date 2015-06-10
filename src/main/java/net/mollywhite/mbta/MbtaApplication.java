package net.mollywhite.mbta;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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

  @Override
  public void run(MbtaConfiguration mbtaConfiguration, Environment environment) throws Exception {
  }
}
