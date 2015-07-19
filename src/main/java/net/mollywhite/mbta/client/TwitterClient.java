package net.mollywhite.mbta.client;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TwitterClient {
  private final Client client;
  private BlockingQueue<String> messageQueue;

  public TwitterClient(String twitterConsumerKey, String twitterConsumerSecret, String twitterToken, String twitterSecret) throws IOException {
    this.messageQueue = new LinkedBlockingQueue<String>(100000);
    BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);

    Hosts hosts = new HttpHosts(Constants.STREAM_HOST);
    StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();

    List<String> terms = new ArrayList<String>(Arrays.asList("mbta"));
    endpoint.trackTerms(terms);

    Authentication auth = new OAuth1(twitterConsumerKey, twitterConsumerSecret, twitterToken, twitterSecret);

    ClientBuilder builder = new ClientBuilder()
        .name("mbta-twitter-client")
        .hosts(hosts)
        .authentication(auth)
        .endpoint(endpoint)
        .processor(new StringDelimitedProcessor(messageQueue))
        .eventMessageQueue(eventQueue);

    this.client = builder.build();
    this.client.connect();
  }

  public BlockingQueue<String> getMessageQueue() {
    return this.messageQueue;
  }

  public int getMessageQueueCapacity() {
    return this.messageQueue.remainingCapacity();
  }

  public boolean isDone() {
    return this.client.isDone();
  }
}
