package net.mollywhite.mbta.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Inject;
import net.mollywhite.mbta.api.Tweet;
import net.mollywhite.mbta.dao.TweetDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class TweetConsumer implements Runnable {
  private final TwitterClient twitterClient;
  private final ObjectMapper mapper;
  private final TweetDAO tweetDAO;
  private final Connection connection;
  private final MbtaClient mbtaClient;

  final Logger logger = LoggerFactory.getLogger(TweetConsumer.class);

  @Inject
  TweetConsumer(TwitterClient twitterClient, ObjectMapper mapper, TweetDAO tweetDAO, Connection connection, MbtaClient mbtaClient) {
    this.twitterClient = twitterClient;
    this.mapper = mapper;
    this.tweetDAO = tweetDAO;
    this.connection = connection;
    this.mbtaClient = mbtaClient;
  }

  public void run() {
    BlockingQueue<String> messageQueue = twitterClient.getMessageQueue();

    while (!twitterClient.isDone()) {
      String tweetStr = null;
      try {
        tweetStr = messageQueue.take();
        System.out.println(tweetStr);
        try {
          Tweet tweet = mapper.readValue(tweetStr, Tweet.class);
          insertTweet(tweet);
        } catch (IOException | SQLException e)  {
          e.printStackTrace();
          logger.error("Couldn't parse tweet: {}", tweetStr);
        }
      } catch (InterruptedException e) {
        logger.info("Shutting down Tweet consumer.");
      }
    }
  }

  @VisibleForTesting
  void insertTweet(Tweet tweet) throws JsonProcessingException, SQLException {
    TweetDetails tweetDetails = new TweetDetails(this.mbtaClient).from(tweet);
    Set<String> lines = tweetDetails.getLinesAsStrings();
    Set<String> branches = tweetDetails.getBranchesAsStrings();
    Set<String> stations = tweetDetails.getStationsAsStrings();
    Set<String> vehicles = tweetDetails.getVehicles();
    String[] lineArray = lines.toArray(new String[lines.size()]);
    String[] branchArray = branches.toArray(new String[branches.size()]);
    String[] stationArray = stations.toArray(new String[stations.size()]);
    String[] vehicleArray = vehicles.toArray(new String[vehicles.size()]);

    tweetDAO.insert(mapper.writeValueAsString(tweet),
        Timestamp.from(tweet.getCreatedAtDateTime().toInstant()),
        connection.createArrayOf("varchar", lineArray),
        connection.createArrayOf("varchar", branchArray),
        connection.createArrayOf("varchar", stationArray),
        connection.createArrayOf("varchar", vehicleArray),
        tweetDetails.getDirection(),
        tweetDetails.getImage(),
        tweetDetails.getRetweet(),
        tweetDetails.getOfficial(),
        tweetDetails.getCategory());

    logger.info("Inserted: {}\n{}\nLines: {}\tBranches:{}\tStations: {}\tVehicles: {}\tDirection: {}\nImage: {}\t" +
            "Retweet: {}\tOfficial: {}\tCategory: {}", tweet.getText(), tweet.getCreatedAt().toString(),
        tweetDetails.getLines().toString(), tweetDetails.getBranches().toString(), tweetDetails.getStations().toString(),
        tweetDetails.getVehicles().toString(), tweetDetails.getDirection() != null ? tweetDetails.getDirection().name() : null,
        tweetDetails.getImage().toString(), tweetDetails.getRetweet().toString(), tweetDetails.getOfficial().toString(),
        tweetDetails.getCategory());
  }

  public int count() {
    return tweetDAO.count();
  }
}
