package net.mollywhite.mbta.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import net.mollywhite.mbta.api.Tweet;
import net.mollywhite.mbta.dao.TweetDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class TweetConsumer implements Runnable {
  private final TwitterClient twitterClient;
  private final ObjectMapper mapper;
  private final TweetDAO tweetDAO;
  private final Connection connection;
  private final MbtaClient mbtaClient;

  final Logger logger = LoggerFactory.getLogger(TweetConsumer.class);

  public TweetConsumer(TwitterClient twitterClient, ObjectMapper mapper, TweetDAO tweetDAO, Connection connection, MbtaClient mbtaClient) {
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
        try {
          Tweet tweet = mapper.readValue(tweetStr, Tweet.class);
          if (shouldInsertTweet(tweet)) {
            insertTweet(tweet);
          }
        } catch (IOException | SQLException e)  {
          e.printStackTrace();
          logger.error("Couldn't parse tweet: {}", tweetStr);
        }
      } catch (InterruptedException e) {
        logger.info("Shutting down Tweet consumer.");
      }
    }
  }

  boolean shouldInsertTweet(Tweet tweet) {
    String tweetText = tweet.getText().toLowerCase();
    Timestamp offset = Timestamp.from(OffsetDateTime.now().minusHours(2).toInstant());
    List<TweetDetails> recentStoredTweets = tweetDAO.getTweetsByHour(offset);
    for (TweetDetails stored : recentStoredTweets) {
      String storedText = stored.getLowercaseTweetText();
      double longestSubstring = this.longestSubstring(tweetText, storedText);
      double shortestStringLength = tweetText.length() < storedText.length() ? tweetText.length() : storedText.length();
      if ((longestSubstring / shortestStringLength) > 0.5) {
        return false;
      }
    }
    return true;
  }

  int longestSubstring(String first, String second) {
    /* Taken from https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Longest_common_substring#Java, CC-BY-SA 3.0 */
    if (first == null || second == null || first.length() == 0 || second.length() == 0) {
      return 0;
    }

    int maxLen = 0;
    int fl = first.length();
    int sl = second.length();
    int[][] table = new int[fl+1][sl+1];

    for(int s=0; s <= sl; s++)
      table[0][s] = 0;
    for(int f=0; f <= fl; f++)
      table[f][0] = 0;




    for (int i = 1; i <= fl; i++) {
      for (int j = 1; j <= sl; j++) {
        if (first.charAt(i-1) == second.charAt(j-1)) {
          if (i == 1 || j == 1) {
            table[i][j] = 1;
          }
          else {
            table[i][j] = table[i - 1][j - 1] + 1;
          }
          if (table[i][j] > maxLen) {
            maxLen = table[i][j];
          }
        }
      }
    }
    return maxLen;
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

    logger.info("Inserted: " + tweet.getText() + "\n" + tweet.getCreatedAt() + "Lines: " +
        tweetDetails.getLines().toString() + "\tBranches: " + tweetDetails.getBranches().toString() +
        "\tStations: " + tweetDetails.getStations().toString() + "\tVehicles: " +
        tweetDetails.getVehicles().toString() + "\tDirection: " +
        (tweetDetails.getDirection() != null ? tweetDetails.getDirection().name() : "") +
        "\nImage: " + tweetDetails.getImage().toString() + "\tRetweet: " + tweetDetails.getRetweet().toString() +
        "\tOfficial: " + tweetDetails.getOfficial().toString() + "\tCategory: " + tweetDetails.getCategory());
  }

  public int count() {
    return tweetDAO.count();
  }
}
