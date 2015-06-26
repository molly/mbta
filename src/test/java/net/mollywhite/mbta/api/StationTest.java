package net.mollywhite.mbta.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class StationTest {
  @Test
  public void testGetLines() throws Exception {
    assertThat(Station.BACKBAY.getLines()).containsOnly(Line.ORANGE);
    assertThat(Station.PARKST.getLines()).containsOnly(Line.RED, Line.GREEN);
  }

  @Test
  public void testGetUnconnectedLines() throws Exception {
    assertThat(Station.BACKBAY.getUnconnectedLines()).containsOnly(Line.RED, Line.GREEN, Line.BLUE);
    assertThat(Station.PARKST.getUnconnectedLines()).containsOnly(Line.BLUE, Line.ORANGE);
  }

  @Test
  public void testGetBranches() throws Exception {
    assertThat(Station.AIRPORT.getBranches()).isEmpty();
    assertThat(Station.CLEVELANDCIRCLE.getBranches()).containsOnly(Branch.C);
    assertThat(Station.PORTER.getBranches()).containsOnly(Branch.BRAINTREE, Branch.ASHMONT);
    assertThat(Station.NORTHSTATION.getBranches()).containsOnly(Branch.C, Branch.E);
  }

  @Test
  public void testGetUnconnectedBranches() throws Exception {
    assertThat(Station.AIRPORT.getUnconnectedBranches()).containsOnly(Branch.B, Branch.C, Branch.D, Branch.E, Branch.ASHMONT, Branch.BRAINTREE, Branch.MATTAPAN);
    assertThat(Station.CLEVELANDCIRCLE.getUnconnectedBranches()).containsOnly(Branch.B, Branch.D, Branch.E, Branch.ASHMONT, Branch.BRAINTREE, Branch.MATTAPAN);
    assertThat(Station.PORTER.getUnconnectedBranches()).containsOnly(Branch.B, Branch.C, Branch.D, Branch.E, Branch.MATTAPAN);
    assertThat(Station.NORTHSTATION.getUnconnectedBranches()).containsOnly(Branch.B, Branch.D, Branch.ASHMONT, Branch.BRAINTREE, Branch.MATTAPAN);
  }

  @Test
  public void testInTweet() throws Exception {
    assertThat(Station.ALEWIFE.inTweet("train to alewife is late")).isTrue();
    assertThat(Station.PORTER.inTweet("davisson")).isFalse();
    assertThat(Station.KENDALLMIT.inTweet("something mit")).isTrue();
    assertThat(Station.KENDALLMIT.inTweet("something admit something")).isFalse();
    assertThat(Station.KENDALLMIT.inTweet("kendall something")).isTrue();
    assertThat(Station.SOUTHSTATION.inTweet("something south street something")).isFalse();
    assertThat(Station.SOUTHSTATION.inTweet("something south station something")).isTrue();
    assertThat(Station.NORTHQUINCY.inTweet("no. quincy")).isTrue();
    assertThat(Station.MASSAVE.inTweet("mass. ave")).isTrue();
    assertThat(Station.GOVTCTR.inTweet("govt ctr")).isTrue();
    assertThat(Station.BUEAST.inTweet("train to bu east is early")).isTrue();
    assertThat(Station.SOUTHST.inTweet("train to south station is early")).isFalse();
    assertThat(Station.SOUTHST.inTweet("train to south st is early")).isTrue();
    assertThat(Station.SOUTHSTATION.inTweet("train to south st is early")).isTrue();
  }
}
