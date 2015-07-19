package net.mollywhite.mbta.client;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import net.mollywhite.mbta.MbtaApplication;
import net.mollywhite.mbta.MbtaConfiguration;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class TwitterClientTest {
  TwitterClient twitterClient;
  @ClassRule
  public static final DropwizardAppRule<MbtaConfiguration> RULE= new DropwizardAppRule<MbtaConfiguration>(MbtaApplication.class, ResourceHelpers.resourceFilePath("test-config.yml"));

  @Before
  public void setUp() throws IOException {
    MbtaConfiguration config = RULE.getConfiguration();
    twitterClient = new TwitterClient(config.getTwitterConsumerKey(), config.getTwitterConsumerSecret(), config.getTwitterToken(), config.getTwitterSecret());
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
