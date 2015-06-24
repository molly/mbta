package net.mollywhite.mbta.client;

import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Direction;
import net.mollywhite.mbta.api.Line;
import net.mollywhite.mbta.api.Station;
import net.mollywhite.mbta.api.Tweet;

import java.util.Set;
import java.util.regex.Pattern;

public class TweetDetails {
  private final Tweet tweet;
  private final String lowercaseTweetText;
  private Set<Line> lines;
  private Set<Branch> branches;
  private Set<Station> stations;
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
    if (Pattern.matches("[^\\w]red(line|[^\\w])", lowercaseTweetText)) {
      this.lines.add(Line.RED);
    }
    if (Pattern.matches("[^\\w]orange(line|[^\\w])", lowercaseTweetText)) {
      this.lines.add(Line.ORANGE);
    }
    if (Pattern.matches("[^\\w]blue(line|[^\\w])", lowercaseTweetText)) {
      this.lines.add(Line.BLUE);
    }
    if (Pattern.matches("[^\\w]green(line|[^\\w])(?! st)", lowercaseTweetText)) {
      this.lines.add(Line.GREEN);
    }
  }

  private void getBranches() {
    if (this.lowercaseTweetText.contains("[^\\w]b[^\\w]")) {
      this.branches.add(Branch.B);
      this.lines.add(Line.GREEN);
    }
    if (this.lowercaseTweetText.contains("[^\\w]c[^\\w]")) {
      this.branches.add(Branch.C);
      this.lines.add(Line.GREEN);
    }
    if (this.lowercaseTweetText.contains("[^\\w]d[^\\w]")) {
      this.branches.add(Branch.D);
      this.lines.add(Line.GREEN);
    }
    if (this.lowercaseTweetText.contains("[^\\w]e[^\\w]")) {
      this.branches.add(Branch.E);
      this.lines.add(Line.GREEN);
    }
    if (this.lowercaseTweetText.contains("mattapan")) {
      this.branches.add(Branch.MATTAPAN);
    }
    if (this.lowercaseTweetText.contains("ashmont")) {
      this.branches.add(Branch.ASHMONT);
    }
    if (this.lowercaseTweetText.contains("braintree")) {
      this.branches.add(Branch.BRAINTREE);
    }
  }

  private void getStations() {
    for (Station station : Station.values()) {
      if (station.getSearchTerm().matcher(this.lowercaseTweetText).matches()) {
        stations.add(station);
      }
    }
  }
}
