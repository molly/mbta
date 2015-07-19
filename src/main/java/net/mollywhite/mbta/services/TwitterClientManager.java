package net.mollywhite.mbta.services;

import io.dropwizard.lifecycle.Managed;
import net.mollywhite.mbta.client.TweetConsumer;

public class TwitterClientManager implements Managed {
  private final TweetConsumer tweetConsumer;
  Thread tweetConsumerThread;

  public TwitterClientManager (TweetConsumer tweetConsumer) {
    this.tweetConsumer = tweetConsumer;
    tweetConsumerThread = new Thread(this.tweetConsumer);
  }

  public void start() throws Exception {
    this.tweetConsumerThread.start();
  }

  public void stop() throws Exception {
    this.tweetConsumerThread.interrupt();
  }
}
