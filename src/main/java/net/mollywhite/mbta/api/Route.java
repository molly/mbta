package net.mollywhite.mbta.api;

public class Route {
  private final String id;
  private final Line line;
  private final Branch branch;

  public Route(String id, Line line, Branch branch) {
    this.id = id;
    this.line = line;
    this.branch = branch;
  }

  public String getId() {
    return id;
  }

  public Line getLine() {
    return line;
  }

  public Branch getBranch() {
    return branch;
  }
}
