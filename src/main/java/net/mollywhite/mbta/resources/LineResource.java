package net.mollywhite.mbta.resources;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/line")
@Produces(MediaType.APPLICATION_JSON)
public class LineResource {
  List<String> tweets;
  final Logger logger = LoggerFactory.getLogger(LineResource.class);

  @GET
  @Timed
  public List<String> LineResource() {
    tweets = new ArrayList<String>();
    tweets.add("hi");
    return tweets;
  }
}
