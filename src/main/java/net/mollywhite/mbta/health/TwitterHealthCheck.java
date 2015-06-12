package net.mollywhite.mbta.health;

import com.google.inject.Inject;
import com.hubspot.dropwizard.guice.InjectableHealthCheck;
import net.mollywhite.mbta.client.TwitterClient;

public class TwitterHealthCheck extends InjectableHealthCheck {
  private final TwitterClient client;

  @Inject
  public TwitterHealthCheck(TwitterClient client) {
    this.client = client;
  }

  @Override
  public String getName() {
    return "Twitter health check";
  }

  @Override
  protected Result check() throws Exception {
    int capacity = this.client.getMessageQueueCapacity();
    if (capacity > 100) {
      return Result.healthy();
    }
    return Result.unhealthy("Tweet queue is over 90% full: %s entries free", capacity);
  }
}
