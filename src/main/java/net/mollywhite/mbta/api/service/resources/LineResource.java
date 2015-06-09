package net.mollywhite.mbta.api.service.resources;

import com.codahale.metrics.annotation.Timed;
import net.mollywhite.mbta.api.service.api.Tweet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/line")
@Produces(MediaType.APPLICATION_JSON)
public class LineResource {
  List<Tweet> tweets;

  @GET
  @Timed
  public List<Tweet> LineResource() {
    return tweets;
  }
}
