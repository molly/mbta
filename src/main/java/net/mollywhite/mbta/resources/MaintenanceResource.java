package net.mollywhite.mbta.resources;

import com.codahale.metrics.annotation.Timed;
import net.mollywhite.mbta.dao.TweetDAO;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Path("/maintenance")
@Produces(MediaType.APPLICATION_JSON)
public class MaintenanceResource {
  private final TweetDAO tweetDAO;

  public MaintenanceResource(TweetDAO tweetDAO) {
    this.tweetDAO = tweetDAO;
  }

  @DELETE
  @Timed
  public Response deleteOldTweets() {
    Timestamp offset = Timestamp.from(OffsetDateTime.now().minusDays(1).toInstant());
    int modified = tweetDAO.deleteOldTweets(offset);
    return Response.ok(String.valueOf(modified) + " tweets deleted from database.").build();
  }
}
