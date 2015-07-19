package net.mollywhite.mbta.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mollywhite.mbta.api.TweetsByLine;
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
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

@Path("/all")
@Produces(MediaType.APPLICATION_JSON)
public class AllResource {
  private final TweetDAO tweetDAO;
  final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  final Logger logger = LoggerFactory.getLogger(AllResource.class);

  public AllResource(TweetDAO tweetDAO) {
    this.tweetDAO = tweetDAO;
  }

  @GET
  @Timed
  public List<TweetDetails> get() {
    return this.tweetDAO.getAllTweets();
  }

  @GET
  @Path("/hours/{hours}")
  @Timed
  public Response getTweetsByHour(@PathParam("hours") long hours) {
    if (hours > 48) {
      return Response.status(Status.BAD_REQUEST)
          .entity("Getting tweets older than 48 hours not supported")
          .build();
    } else {
      Timestamp offset = Timestamp.from(OffsetDateTime.now().minusHours(hours).toInstant());
      List<TweetDetails> tweets = tweetDAO.getTweetsByHour(offset);
      return Response.ok(tweets).build();
    }
  }

  @GET
  @Path("/hours/{hours}/line")
  public Response getTweetsByHourByLine(@PathParam("hours") long hours) throws JsonProcessingException {
    Response resp = getTweetsByHour(hours);
    if (resp.getStatus() != 200) {
      return resp;
    } else {
      List<TweetDetails> tweets = (List<TweetDetails>) resp.getEntity();
      TweetsByLine tweetsByLine = new TweetsByLine(tweets);
      return resp.ok(mapper.writeValueAsString(tweetsByLine)).build();
    }
  }
}
