package net.mollywhite.mbta.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import net.mollywhite.mbta.api.Station;
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

@Path("/station")
@Produces(MediaType.APPLICATION_JSON)
public class StationResource {
  private final TweetDAO tweetDAO;
  final Logger logger = LoggerFactory.getLogger(StationResource.class);

  @Inject
  public StationResource(TweetDAO tweetDAO) {
    this.tweetDAO = tweetDAO;
  }

  @GET
  @Path("/{station}")
  @Timed
  public Response getTweetsByStation(@PathParam("station") String stationStr) {
    try {
      Station station = Station.valueOf(stationStr.toUpperCase());
      List<TweetDetails> tweets = tweetDAO.getTweetsByStation(station);
      return Response.ok(tweets).build();
    } catch (IllegalArgumentException e) {
      return Response.status(Status.NOT_FOUND)
          .entity("Line " + stationStr + " not found. Use one of " + Arrays.toString(Station.values()) + ".")
          .build();
    }
  }
}
