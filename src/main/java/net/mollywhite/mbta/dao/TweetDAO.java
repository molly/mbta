package net.mollywhite.mbta.dao;

import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Direction;
import net.mollywhite.mbta.api.Line;
import net.mollywhite.mbta.api.Tweet;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.time.OffsetDateTime;

public class TweetDAO {
   @SqlUpdate("INSERT INTO tweets (tweet, time, line, branch, station, direction, image, retweet, official, category)" +
       "VALUES(:tweet, :time, :line, :branch, :station, :direction, :image, :retweet, :official, :category)")
   void insert(@Bind("tweet") Tweet tweet,
               @Bind("time") OffsetDateTime time,
               @Bind("line") Line line,
               @Bind("branch") Branch branch,
               @Bind("station") String station,
               @Bind("direction") Direction direction,
               @Bind("image") Boolean hasImage,
               @Bind("retweet") Boolean isRetweet,
               @Bind("official") Boolean isOfficial,
               @Bind("category") String category) {

   }
}
