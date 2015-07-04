package net.mollywhite.mbta.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import net.mollywhite.mbta.api.Line;
import net.mollywhite.mbta.client.TweetDetails;
import net.mollywhite.mbta.dao.TweetDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.Arrays;
import java.util.List;

@Path("/line")
@Produces(MediaType.APPLICATION_JSON)
public class LineResource {
  private final TweetDAO tweetDAO;
  final Logger logger = LoggerFactory.getLogger(LineResource.class);

  @Inject
  public LineResource(TweetDAO tweetDAO) {
    this.tweetDAO = tweetDAO;
  }

  @GET
  @Path("/{line}")
  @Timed
  public Response getTweetsByLine(@PathParam("line") String lineStr) {
    try {
      Line line = Line.valueOf(lineStr.toUpperCase());
      List<TweetDetails> tweets = tweetDAO.getTweetsByLine(line);
      return Response.ok(tweets).build();
    } catch (IllegalArgumentException e) {
      return Response.status(Status.NOT_FOUND)
          .entity("Line " + lineStr + " not found. Use one of " + Arrays.toString(Line.values()) + ".")
          .build();
    }
  }
}
