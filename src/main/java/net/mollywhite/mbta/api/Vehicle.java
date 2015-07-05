package net.mollywhite.mbta.api;

public class Vehicle {
  private final String id;
  private final Line line;
  private final Branch branch;

  public Vehicle(String id, Line line, Branch branch) {
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
