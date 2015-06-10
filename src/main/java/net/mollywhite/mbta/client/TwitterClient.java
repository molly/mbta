package net.mollywhite.mbta.client;

import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TwitterClient {

  public TwitterClient() {
  }

  public static void main(String[] args) {
    TwitterClient twitterClient = new TwitterClient();
    twitterClient.run();
  }

  public void run() {
    BlockingQueue<String> messageQueue = new LinkedBlockingQueue<String>(100000);
    BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);

    Hosts hosts = new HttpHosts(Constants.STREAM_HOST);
    StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();

    List<String> terms = new ArrayList<String>(Arrays.asList("mbta"));
    endpoint.trackTerms(terms);

    Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
  }
}
