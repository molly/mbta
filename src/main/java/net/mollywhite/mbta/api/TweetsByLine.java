package net.mollywhite.mbta.api;

import net.mollywhite.mbta.client.TweetDetails;

import java.util.ArrayList;
import java.util.List;

public class TweetsByLine {
  List<TweetDetails> red;
  List<TweetDetails> green;
  List<TweetDetails> blue;
  List<TweetDetails> orange;
  List<TweetDetails> other;

  public TweetsByLine(List<TweetDetails> tweets) {
    this.red = new ArrayList<>();
    this.green = new ArrayList<>();
    this.blue = new ArrayList<>();
    this.orange = new ArrayList<>();
    this.other = new ArrayList<>();

    for (TweetDetails t : tweets) {
      if (t.getLines().isEmpty()) {
        other.add(t);
      } else {
        for (Line line : t.getLines()) {
          switch (line) {
            case RED:
              red.add(t);
              break;
            case GREEN:
              green.add(t);
              break;
            case BLUE:
              blue.add(t);
              break;
            case ORANGE:
              orange.add(t);
              break;
          }
        }
      }
    }
  }

  public List<TweetDetails> getOther() {
    return other;
  }

  public List<TweetDetails> getRed() {
    return red;
  }

  public List<TweetDetails> getGreen() {
    return green;
  }

  public List<TweetDetails> getBlue() {
    return blue;
  }

  public List<TweetDetails> getOrange() {
    return orange;
  }
}
