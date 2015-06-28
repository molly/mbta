package net.mollywhite.mbta.dao;

import net.mollywhite.mbta.api.Direction;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.sql.Array;
import java.sql.Timestamp;

public interface TweetDAO {
  @SqlUpdate("INSERT INTO tweets (tweet, time, line, branch, station, vehicles, direction, image, retweet, official, category)" +
      " VALUES (:tweet, :time, :line, :branch, :station, :vehicles, :direction, :image, :retweet, :official, :category)")
  void insert(@Bind("tweet") String tweet, @Bind("time") Timestamp time, @Bind("line") Array lines,
              @Bind("branch") Array branches, @Bind("station") Array stations,
              @Bind("vehicles") Array vehicles, @Bind("direction") Direction direction, @Bind("image") Boolean hasImage,
              @Bind("retweet") Boolean isRetweet, @Bind("official") Boolean isOfficial,
              @Bind("category") String category);

  @SqlQuery("SELECT COUNT(*) FROM tweets")
  int count();

  @SqlUpdate("TRUNCATE tweets")
  void truncate();

  void close();
}
