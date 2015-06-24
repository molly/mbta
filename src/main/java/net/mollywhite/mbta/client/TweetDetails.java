package net.mollywhite.mbta.client;

import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Direction;
import net.mollywhite.mbta.api.Line;
import net.mollywhite.mbta.api.Station;
import net.mollywhite.mbta.api.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Set;
import java.util.regex.Pattern;

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

  private static Logger logger = LoggerFactory.getLogger(TweetDetails.class);

  public TweetDetails(Tweet tweet) {
    this.tweet = tweet;
    this.lowercaseTweetText = tweet.getText().toLowerCase();
  }

  public TweetDetails get() {
    getLinesFromTweet();
    getBranchesFromTweet();
    getStationsFromTweet();
    return this;
  }

  private void getLinesFromTweet() {
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

  private void getBranchesFromTweet() {
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

  private void getStationsFromTweet() {
    for (Station station : Station.values()) {
      if (station.getSearchTerm().matcher(this.lowercaseTweetText).matches()) {
        Set<Line> stationLines = station.getLines();
        Set<Branch> stationBranches = station.getBranches();

        stations.add(station.name());
        if (!Collections.disjoint(lines, stationLines)) {
          logger.info("Mismatch between recorded lines and station lines. Recorded: {}. Station: {}. Tweet: {}.", lines.toString(), stationLines.toString(), this.tweet);
        }
        lines.addAll(stationLines);
        if (!Collections.disjoint(branches, stationBranches)) {
          logger.info("Mismatch between recorded lines and station lines. Recorded: {}. Station: {}. Tweet: {}.", lines.toString(), stationBranches.toString(), this.tweet);
        }
        branches.addAll(station.getBranches());
      }
    }
  }

  public Tweet getTweet() {
    return tweet;
  }

  public String getLowercaseTweetText() {
    return lowercaseTweetText;
  }

  public Set<Line> getLines() {
    return lines;
  }

  public Set<Branch> getBranches() {
    return branches;
  }

  public Set<String> getStations() {
    return stations;
  }

  public String getVehicle() {
    return vehicle;
  }

  public Direction getDirection() {
    return direction;
  }

  public Boolean getImage() {
    return image;
  }

  public Boolean getRetweet() {
    return retweet;
  }

  public Boolean getOfficial() {
    return official;
  }

  public String getCategory() {
    return category;
  }
}
