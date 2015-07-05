package net.mollywhite.mbta.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Direction;
import net.mollywhite.mbta.api.Line;
import net.mollywhite.mbta.api.Station;
import net.mollywhite.mbta.api.Tweet;
import net.mollywhite.mbta.api.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetDetails {
  private MbtaClient mbtaClient;
  private Tweet tweet;
  private String lowercaseTweetText;
  private Set<Line> lines;
  private Set<Branch> branches;
  private Set<Station> stations;
  private Set<String> vehicles;
  private Direction direction;
  private Boolean image;
  private Boolean retweet;
  private Boolean official;
  private String category;

  private static Logger logger = LoggerFactory.getLogger(TweetDetails.class);

  @Inject
  public TweetDetails(MbtaClient client) {
    this.mbtaClient = client;
    this.lines = new HashSet<Line>();
    this.branches = new HashSet<Branch>();
    this.stations = new HashSet<Station>();
    this.vehicles = new HashSet<String>();
    this.direction = null;
    this.image =  false;
    this.retweet = false;
    this.official = false;
    this.category = null;
  }

  public TweetDetails(Tweet tweet, Set<Line> lines, Set<Branch> branches, Set<Station> stations, Set<String> vehicles, Direction direction, Boolean image, Boolean retweet, Boolean official, String category) {
    this.tweet = tweet;
    this.lowercaseTweetText = tweet.getText().toLowerCase();
    this.lines = lines;
    this.branches = branches;
    this.stations = stations;
    this.vehicles = vehicles;
    this.direction = direction;
    this.image = image;
    this.retweet = retweet;
    this.official = official;
    this.category = category;
  }

  public TweetDetails from(Tweet tweet) {
    this.tweet = tweet;
    this.lowercaseTweetText = tweet.getText().toLowerCase();
    getLinesFromTweet();
    getBranchesFromTweet();
    getStationsFromTweet();
    getVehiclesFromTweet();
    getDirectionFromTweet();
    getHasImageFromTweet();
    getIsRetweetFromTweet();
    getIsOfficialFromTweet();
    return this;
  }

  private void getLinesFromTweet() {
    if (Pattern.compile("([^\\w]|\\A)red(line|[^\\w]|\\z)").matcher(lowercaseTweetText).find()) {
      this.lines.add(Line.RED);
    }
    if (Pattern.compile("([^\\w]|\\A)orange(line|[^\\w]|\\z)").matcher(lowercaseTweetText).find()) {
      this.lines.add(Line.ORANGE);
    }
    if (Pattern.compile("([^\\w]|\\A)blue(line|[^\\w]|\\z)").matcher(lowercaseTweetText).find()) {
      this.lines.add(Line.BLUE);
    }
    if (Pattern.compile("([^\\w]|\\A)green(line|[^\\w]|\\z)(?! st)").matcher(lowercaseTweetText).find()) {
      this.lines.add(Line.GREEN);
    }
  }

  private void getBranchesFromTweet() {
    if (this.lowercaseTweetText.contains("([^\\w]|\\A)b([^\\w]|\\z)")) {
      this.branches.add(Branch.B);
      this.lines.add(Line.GREEN);
    }
    if (this.lowercaseTweetText.contains("([^\\w]|\\A)c([^\\w]|\\z)")) {
      this.branches.add(Branch.C);
      this.lines.add(Line.GREEN);
    }
    if (this.lowercaseTweetText.contains("([^\\w]|\\A)d([^\\w]|\\z)")) {
      this.branches.add(Branch.D);
      this.lines.add(Line.GREEN);
    }
    if (this.lowercaseTweetText.contains("([^\\w]|\\A)e([^\\w]|\\z)")) {
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
    // TODO: This could be a lot more accurate
    for (Station station : Station.values()) {
      if (station.inTweet(this.lowercaseTweetText)) {
        Set<Line> stationLines = station.getLines();
        Set<Branch> stationBranches = station.getBranches();

        stations.add(station);
        if (Collections.disjoint(lines, stationLines)) {
          logger.info("Mismatch between recorded lines and station lines. Recorded: {}. New: {}. Tweet: {}.", lines.toString(), stationLines.toString(), this.tweet.getText());
        }
        lines.addAll(stationLines);
        if (Collections.disjoint(branches, stationBranches)) {
          logger.info("Mismatch between recorded branches and station branches. Recorded: {}. New: {}. Tweet: {}.", lines.toString(), stationBranches.toString(), this.tweet.getText());
        }
        branches.addAll(station.getBranches());
      }
    }
  }

  @Inject
  private void getVehiclesFromTweet() {
    Matcher m = Pattern.compile("\\d{3,5}").matcher(this.lowercaseTweetText);
    if (m.find()) {
      m.reset();
      List<Vehicle> vehicles = this.mbtaClient.getAllVehicles();
      while (m.find()) {
        vehicles.stream().filter(v -> m.group().equals(v.getId())).forEach(v -> {
          this.vehicles.add(v.getId());
          this.lines.add(v.getLine());
          if (v.getBranch() != null) {
            this.branches.add(v.getBranch());
          }
        });
      }
    }
  }

  private void getDirectionFromTweet() {
    if (this.lowercaseTweetText.contains("inbound") && this.lowercaseTweetText.contains("outbound")) {
      return;
    } else if (this.lowercaseTweetText.contains("inbound")) {
      this.direction = Direction.INBOUND;
    } else if (this.lowercaseTweetText.contains("outbound")) {
      this.direction = Direction.OUTBOUND;
    }
  }

  private void getHasImageFromTweet() {
    this.image = tweet.getEntities().getMedia() != null && !tweet.getEntities().getMedia().isEmpty();
  }

  private void getIsRetweetFromTweet() {
    this.retweet = tweet.getRetweetedStatus() != null;
  }

  private void getIsOfficialFromTweet() {
    List<String> officialAccounts = Lists.newArrayList("MBTA", "MBTA_CR");
    this.official = officialAccounts.contains(tweet.getUser().getScreenName());
  }

  public Tweet getTweet() {
    return tweet;
  }

  @JsonIgnore
  public String getLowercaseTweetText() {
    return lowercaseTweetText;
  }

  public Timestamp getCreatedAtAsTimestamp() {
    return Timestamp.from(tweet.getCreatedAtDateTime().toInstant());
  }

  public Set<Line> getLines() {
    return lines;
  }

  @JsonIgnore
  public Set<String> getLinesAsStrings() {
    Set<String> lineStrings = new HashSet<String>();
    lines.forEach(line -> lineStrings.add(line.name()));
    return lineStrings;
  }

  public Set<Branch> getBranches() {
    return branches;
  }

  @JsonIgnore
  public Set<String> getBranchesAsStrings() {
    Set<String> branchStrings = new HashSet<String>();
    branches.forEach(branch -> branchStrings.add(branch.name()));
    return branchStrings;
  }

  public Set<Station> getStations() {
    return stations;
  }

  @JsonIgnore
  public Set<String> getStationsAsStrings() {
    Set<String> stationStrings = new HashSet<String>();
    stations.forEach(station -> stationStrings.add(station.name()));
    return stationStrings;
  }

  public Set<String> getVehicles() {
    return vehicles;
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
