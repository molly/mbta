package net.mollywhite.mbta.dao;

import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Direction;
import net.mollywhite.mbta.api.Line;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.sql.Timestamp;
import java.util.Set;

public interface TweetDAO {
  @SqlUpdate("INSERT INTO tweets (tweet, time, line, branch, station, vehicle, direction, image, retweet, official, category)" +
      " VALUES (:tweet, :time, :line, :branch, :station, :vehicle, :direction, :image, :retweet, :official, :category)")
  void insert(@Bind("tweet") String tweet, @Bind("time") Timestamp time, @Bind("line") Set<Line> lines,
              @Bind("branch") Set<Branch> branches, @Bind("station") Set<String> stations,
              @Bind("vehicle") String vehicle, @Bind("direction") Direction direction, @Bind("image") Boolean hasImage,
              @Bind("retweet") Boolean isRetweet, @Bind("official") Boolean isOfficial,
              @Bind("category") String category);

  void close();
}

