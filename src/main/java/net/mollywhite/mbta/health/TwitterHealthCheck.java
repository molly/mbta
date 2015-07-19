package net.mollywhite.mbta.health;

import com.codahale.metrics.health.HealthCheck;
import net.mollywhite.mbta.client.TwitterClient;

import javax.inject.Inject;

public class TwitterHealthCheck extends HealthCheck {
  private final TwitterClient client;

  @Inject
  public TwitterHealthCheck(TwitterClient client) {
    this.client = client;
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
