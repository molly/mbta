package net.mollywhite.mbta.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import net.mollywhite.mbta.api.Branch;
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

@Path("/branch")
@Produces(MediaType.APPLICATION_JSON)
public class BranchResource {
  private final TweetDAO tweetDAO;
  final Logger logger = LoggerFactory.getLogger(BranchResource.class);

  @Inject
  public BranchResource(TweetDAO tweetDAO) {
    this.tweetDAO = tweetDAO;
  }

  @GET
  @Path("/{branch}")
  @Timed
  public Response getTweetsByBranch(@PathParam("branch") String branchStr) {
    try {
      Branch branch = Branch.valueOf(branchStr.toUpperCase());
      List<TweetDetails> tweets = tweetDAO.getTweetsByBranch(branch);
      return Response.ok(tweets).build();
    } catch (IllegalArgumentException e) {
      return Response.status(Status.NOT_FOUND)
          .entity("Line " + branchStr + " not found. Use one of " + Arrays.toString(Branch.values()) + ".")
          .build();
    }
  }
}
