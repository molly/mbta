package net.mollywhite.mbta.resources;

import com.codahale.metrics.annotation.Timed;
import net.mollywhite.mbta.client.TweetDetails;
import net.mollywhite.mbta.dao.TweetDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/all")
@Produces(MediaType.APPLICATION_JSON)
public class AllResource {
  private final TweetDAO tweetDAO;
  final Logger logger = LoggerFactory.getLogger(AllResource.class);

  public AllResource(TweetDAO tweetDAO) {
    this.tweetDAO = tweetDAO;
  }

  @GET
  @Timed
  public List<TweetDetails> get() {
    return this.tweetDAO.getAllTweets();
  }
}
