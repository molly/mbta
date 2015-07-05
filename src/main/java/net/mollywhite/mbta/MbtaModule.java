package net.mollywhite.mbta;

import com.google.inject.AbstractModule;
import net.mollywhite.mbta.client.ClientModule;

public class MbtaModule extends AbstractModule {
  @Override
  protected void configure() {
    install(new ClientModule());
  }
}
