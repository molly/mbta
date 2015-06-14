package net.mollywhite.mbta.client;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class TweetConsumer implements Runnable {
  private TwitterClient twitterClient;
  final Logger logger = LoggerFactory.getLogger(TweetConsumer.class);

  @Inject
  TweetConsumer(TwitterClient twitterClient) {
    this.twitterClient = twitterClient;
  }

  public void run() {
    BlockingQueue<String> messageQueue = twitterClient.getMessageQueue();
    while (!twitterClient.isDone()) {
      String tweet = null;
      try {
        tweet = messageQueue.take();
      } catch (InterruptedException e) {
        logger.info("Shutting down Tweet consumer.");
      }
      System.out.println(tweet);
    }
  }
}
