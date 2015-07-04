package net.mollywhite.mbta.dao;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Direction;
import net.mollywhite.mbta.api.Line;
import net.mollywhite.mbta.api.Station;
import net.mollywhite.mbta.api.Tweet;
import net.mollywhite.mbta.client.TweetConsumer;
import net.mollywhite.mbta.client.TweetDetails;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;

public class TweetDetailsMapper implements ResultSetMapper<TweetDetails> {
  final Logger logger = LoggerFactory.getLogger(TweetConsumer.class);

  @Override
  public TweetDetails map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    String tweetStr = rs.getString("tweet");
    Set<Line> lines = Sets.newHashSet(
        Arrays.stream((String[]) rs.getArray("line").getArray()).map(l -> Line.valueOf(l)).toArray(Line[]::new));
    Set<Branch> branches = Sets.newHashSet(
        Arrays.stream((String[]) rs.getArray("branch").getArray()).map(b -> Branch.valueOf(b)).toArray(Branch[]::new));
    Set<Station> stations = Sets.newHashSet(
        Arrays.stream((String[]) rs.getArray("station").getArray()).map(s -> Station.valueOf(s)).toArray(Station[]::new));
    Set<String> vehicles = Sets.newHashSet((String []) rs.getArray("vehicle").getArray());
    String directionStr = rs.getString("direction");
    Direction direction = null;
    if (directionStr != null) {
      direction = Direction.valueOf(directionStr);
    }

    try {
      Tweet tweet = mapper.readValue(tweetStr, Tweet.class);
      TweetDetails tweetDetails = new TweetDetails(
          tweet,
          lines,
          branches,
          stations,
          vehicles,
          direction,
          rs.getBoolean("image"),
          rs.getBoolean("retweet"),
          rs.getBoolean("official"),
          rs.getString("category")
      );
      return tweetDetails;
    } catch (IOException e) {
      logger.error("Poorly formatted tweet: ", tweetStr);
      return null;
    }
  }
}
