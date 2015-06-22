package net.mollywhite.mbta.client;

import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Direction;
import net.mollywhite.mbta.api.Line;
import net.mollywhite.mbta.api.Tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TweetDetails {
  private final Tweet tweet;
  private final String lowercaseTweetText;
  private Set<Line> lines;
  private Set<Branch> branches;
  private Set<String> stations;
  private String vehicle;
  private Direction direction;
  private Boolean image;
  private Boolean retweet;
  private Boolean official;
  private String category;

  public TweetDetails(Tweet tweet) {
    this.tweet = tweet;
    this.lowercaseTweetText = tweet.getText().toLowerCase();
  }

  public TweetDetails get() {
    getLines();
    getBranches();
    return this;
  }

  private void getLines() {
    // TODO: This is janky, fix.
    if (this.lowercaseTweetText.contains("red")) {
      this.lines.add(Line.RED);
    }
    if (this.lowercaseTweetText.contains("orange")) {
      this.lines.add(Line.ORANGE);
    }
    if (this.lowercaseTweetText.contains("blue")) {
      this.lines.add(Line.BLUE);
    }
    if (this.lowercaseTweetText.contains("green")) {
      this.lines.add(Line.GREEN);
    }
  }

  private void getBranches() {
    // TODO: This is janky, fix.
    List<Branch> branches = new ArrayList<Branch>();
    if (this.lowercaseTweetText.contains(" b ")) {
      this.branches.add(Branch.B);
      this.lines.add(Line.GREEN);
    }
    if (this.lowercaseTweetText.contains(" c ")) {
      this.branches.add(Branch.C);
      this.lines.add(Line.GREEN);
    }
    if (this.lowercaseTweetText.contains(" d ")) {
      this.branches.add(Branch.D);
      this.lines.add(Line.GREEN);
    }
    if (this.lowercaseTweetText.contains(" e ")) {
      this.branches.add(Branch.E);
      this.lines.add(Line.GREEN);
    }
    if (this.lowercaseTweetText.contains("mattapan")) {
      this.branches.add(Branch.MATTAPAN);
      this.lines.add(Line.RED);
    }
  }


}
