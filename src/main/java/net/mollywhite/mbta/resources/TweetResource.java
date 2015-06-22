package net.mollywhite.mbta.resources;

import net.mollywhite.mbta.dao.TweetDAO;

public class TweetResource {
  private final TweetDAO tweetDAO;

  public TweetResource(TweetDAO tweetDAO) {
    this.tweetDAO = tweetDAO;
  }
}
