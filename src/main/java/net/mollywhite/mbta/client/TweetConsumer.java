package net.mollywhite.mbta.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Inject;
import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Tweet;
import net.mollywhite.mbta.dao.TweetDAO;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class TweetConsumer implements Runnable {
  private final TwitterClient twitterClient;
  private final ObjectMapper mapper;
  private final TweetDAO tweetDAO;
  private final Connection connection;

  final Logger logger = LoggerFactory.getLogger(TweetConsumer.class);

  @Inject
  TweetConsumer(TwitterClient twitterClient, ObjectMapper mapper, DBI dbi, Connection connection) {
    this.twitterClient = twitterClient;
    this.mapper = mapper;
    this.tweetDAO = dbi.onDemand(TweetDAO.class);
    this.connection = connection;
  }

  public void run() {
    BlockingQueue<String> messageQueue = twitterClient.getMessageQueue();

    while (!twitterClient.isDone()) {
      String tweetStr = null;
      try {
        tweetStr = messageQueue.take();
        try {
          Tweet tweet = mapper.readValue(tweetStr, Tweet.class);
          insertTweet(tweet);
        } catch (IOException | SQLException e)  {
          e.printStackTrace();
          logger.error("Couldn't parse tweet: %s", tweetStr);
        }
      } catch (InterruptedException e) {
        logger.info("Shutting down Tweet consumer.");
      }
    }
  }

  @VisibleForTesting
  void insertTweet(Tweet tweet) throws JsonProcessingException, SQLException {
    TweetDetails tweetDetails = new TweetDetails(tweet).get();
    Set<String> lines = tweetDetails.getLinesAsStrings();
    Set<String> branches = tweetDetails.getBranchesAsStrings();
    Set<String> stations = tweetDetails.getStationsAsStrings();
    Set<String> vehicles = tweetDetails.getVehicles();
    String[] lineArray = lines.toArray(new String[lines.size()]);
    String[] branchArray = branches.toArray(new String[branches.size()]);
    String[] stationArray = stations.toArray(new String[stations.size()]);
    String[] vehicleArray = vehicles.toArray(new String[vehicles.size()]);

    tweetDAO.insert(mapper.writeValueAsString(tweet),
        Timestamp.from(tweet.getCreatedAt().toInstant()),
        connection.createArrayOf("varchar", lineArray),
        connection.createArrayOf("varchar", branchArray),
        connection.createArrayOf("varchar", stationArray),
        connection.createArrayOf("varchar", vehicleArray),
        tweetDetails.getDirection(),
        tweetDetails.getImage(),
        tweetDetails.getRetweet(),
        tweetDetails.getOfficial(),
        tweetDetails.getCategory());
  }

  public int count() {
    return tweetDAO.count();
  }

  private Optional<List<Branch>> getBranches(Tweet tweet) {
    String lowercaseTweetText = tweet.getText().toLowerCase();
    List<Branch> branches = new ArrayList<Branch>();
//    if (lowercaseTweetText.contains("red")) {
    return Optional.empty();
  }
}
