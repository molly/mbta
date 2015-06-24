package net.mollywhite.mbta.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Tweet;
import net.mollywhite.mbta.dao.TweetDAO;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

public class TweetConsumer implements Runnable {
  private final TwitterClient twitterClient;
  private final ObjectMapper mapper;
  private final TweetDAO tweetDAO;

  final Logger logger = LoggerFactory.getLogger(TweetConsumer.class);

  @Inject
  TweetConsumer(TwitterClient twitterClient, ObjectMapper mapper, DBI dbi) {
    this.twitterClient = twitterClient;
    this.mapper = mapper;
    this.tweetDAO = dbi.onDemand(TweetDAO.class);
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
        tweetDAO.insert(mapper.writeValueAsString(tweet), Timestamp.from(tweet.getCreatedAt().toInstant()), null, null, null, null, null, false, false, null, null);
      } catch (IOException e) {
        e.printStackTrace();
        logger.error("Couldn't parse tweet: %s", tweetStr);
      }
    }
  }



  private Optional<List<Branch>> getBranches(Tweet tweet) {
    String lowercaseTweetText = tweet.getText().toLowerCase();
    List<Branch> branches = new ArrayList<Branch>();
//    if (lowercaseTweetText.contains("red")) {
    return Optional.empty();
  }
}
