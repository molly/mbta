package net.mollywhite.mbta.client;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class TwitterClientTest {
  TwitterClient twitterClient;

  @Before
  public void setUp() throws IOException {
    twitterClient = new TwitterClient();
  }

  @Test
  public void testGetMessageQueue() throws Exception {
    BlockingQueue<String> messageQueue = twitterClient.getMessageQueue();
    assertNotNull(twitterClient.getMessageQueue());
  }

  @Test
  public void testGetMessageQueueCapacity() throws Exception {
    assertEquals(twitterClient.getMessageQueueCapacity(), 100000);
  }

  @Test
  public void testIsDone() throws Exception {
    assertFalse(twitterClient.isDone());
  }
}
