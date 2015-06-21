package net.mollywhite.mbta.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import net.mollywhite.mbta.api.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class TweetConsumer implements Runnable {
  private final TwitterClient twitterClient;
  private final ObjectMapper mapper;

  final Logger logger = LoggerFactory.getLogger(TweetConsumer.class);

  @Inject
  TweetConsumer(TwitterClient twitterClient, ObjectMapper mapper) {
    this.twitterClient = twitterClient;
    this.mapper = mapper;
  }

  public void run() {
    BlockingQueue<String> messageQueue = twitterClient.getMessageQueue();

    while (!twitterClient.isDone()) {
      String tweetStr = null;
      try {
        tweetStr = messageQueue.take();
      } catch (InterruptedException e) {
        logger.info("Shutting down Tweet consumer.");
      }

      try {
        Tweet tweet = mapper.readValue(tweetStr, Tweet.class);
      } catch (IOException e) {
        e.printStackTrace();
        logger.error("Couldn't parse tweet: %s", tweetStr);
      }
    }
  }
}
