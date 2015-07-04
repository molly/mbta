package net.mollywhite.mbta.dao;

import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Direction;
import net.mollywhite.mbta.api.Line;
import net.mollywhite.mbta.client.TweetDetails;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.List;

public interface TweetDAO {
  @SqlUpdate("INSERT INTO tweets (tweet, time, line, branch, station, vehicle, direction, image, retweet, official, category)" +
      " VALUES (:tweet, :time, :line, :branch, :station, :vehicle, :direction, :image, :retweet, :official, :category)")
  void insert(@Bind("tweet") String tweet, @Bind("time") Timestamp time, @Bind("line") Array lines,
              @Bind("branch") Array branches, @Bind("station") Array stations,
              @Bind("vehicle") Array vehicles, @Bind("direction") Direction direction, @Bind("image") Boolean hasImage,
              @Bind("retweet") Boolean isRetweet, @Bind("official") Boolean isOfficial,
              @Bind("category") String category);

  @SqlUpdate("TRUNCATE tweets")
  void truncate();

  @SqlQuery("SELECT COUNT(*) FROM tweets")
  int count();

  @SqlQuery("SELECT * FROM tweets WHERE :line = ANY (line)")
  @Mapper(TweetDetailsMapper.class)
  List<TweetDetails> getTweetsByLine(@Bind("line") Line line);

  @SqlQuery("SELECT * FROM tweets WHERE :branch = ANY (branch)")
  @Mapper(TweetDetailsMapper.class)
  List<TweetDetails> getTweetsByBranch(@Bind("branch") Branch branch);

  void close();
}
