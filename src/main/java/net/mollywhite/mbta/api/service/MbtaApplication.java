package net.mollywhite.mbta.api.service;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class MbtaApplication extends Application<MbtaConfiguration> {
  public static void main(String[] args) throws Exception {
    new MbtaApplication().run(args);
  }

  @Override
  public void run(MbtaConfiguration mbtaConfiguration, Environment environment) throws Exception {

  }
}
