package net.mollywhite.mbta.api;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public enum Station {
  ALEWIFE ("Alewife", Pattern.compile("alewife st"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  DAVIS ("Davis", Pattern.compile("davis"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  PORTER ("Porter", Pattern.compile("porter"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  HARVARD ("Harvard", Pattern.compile("harvard (?!ave)"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  CENTRAL ("Central", Pattern.compile("central (?!ave)"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  KENDALLMIT ("Kendall/MIT", Pattern.compile("kendall|mit"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  CHARLESMGH ("Charles/MGH", Pattern.compile("charles|mgh"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  PARKST ("Park Street", Pattern.compile("park "), Lists.newArrayList(Line.RED, Line.GREEN), Lists.newArrayList(Branch.B, Branch.C, Branch.D, Branch.E, Branch.ASHMONT, Branch.BRAINTREE)),
  DOWNTOWNCROSSING ("Downtown Crossing", Pattern.compile("downtown"), Lists.newArrayList(Line.RED, Line.ORANGE), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  SOUTHSTATION ("South Station", Pattern.compile("south st"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  BROADWAY ("Broadway", Pattern.compile("broadway"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  ANDREW ("Andrew", Pattern.compile("andrew"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  JFKUMASS ("JFK/UMass", Pattern.compile("jfk|umass"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  SAVINHILL ("Savin Hill", Pattern.compile("savin"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT)),
  FIELDSCORNER ("Fields Corner", Pattern.compile("fields"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT)),
  SHAWMUT ("Shawmut", Pattern.compile("shawmut"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT)),
  ASHMONT ("Ashmont", Pattern.compile("ashmont st"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT)),
  CEDARGROVE ("Cedar Grove", Pattern.compile("cedar"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  BUTLER ("Butler", Pattern.compile("butler"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  MILTON ("Milton", Pattern.compile("milton"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  CENTRALAVE ("Central Avenue", Pattern.compile("central ave"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  VALLEYRD ("Valley Road", Pattern.compile("valley"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  CAPENST ("Capen Street", Pattern.compile("capen"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  MATTAPAN ("Mattapan", Pattern.compile("mattapan st"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  NORTHQUINCY ("North Quincy", Pattern.compile("north quincy"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.BRAINTREE)),
  WOLLASTON ("Wollaston", Pattern.compile("wollaston"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.BRAINTREE)),
  QUINCYCENTER ("Quincy Center", Pattern.compile("quincy c"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.BRAINTREE)),
  QUINCYADAMS ("Quincy Adams", Pattern.compile("quincy adams"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.BRAINTREE)),
  BRAINTREE ("Braintree", Pattern.compile("braintree st"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.BRAINTREE)),
  OAKGROVE ("Oak Grove", Pattern.compile("oak grove st"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  MALDENCENTER ("Malden Center", Pattern.compile("malden"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  WELLINGTON ("Wellington", Pattern.compile("wellington"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  ASSEMBLY ("Assembly", Pattern.compile("assembly"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  SULLIVANSQ ("Sullivan Square", Pattern.compile("sullivan"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  COMMUNITYCOLLEGE ("Community College", Pattern.compile("comm(unity)? college"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  NORTHSTATION ("North Station", Pattern.compile("north st"), Lists.newArrayList(Line.ORANGE, Line.GREEN), Lists.newArrayList(Branch.C, Branch.E)),
  HAYMARKET ("Haymarket", Pattern.compile("haymarket"), Lists.newArrayList(Line.ORANGE, Line.GREEN), Lists.newArrayList(Branch.C, Branch.E)),
  STATE ("State", Pattern.compile("state"), Lists.newArrayList(Line.ORANGE, Line.BLUE), Collections.<Branch>emptyList()),
  CHINATOWN ("Chinatown", Pattern.compile("chinatown"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  TUFTSMEDICALCENTER ("Tufts Medical Center", Pattern.compile("tufts"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  BACKBAY ("Back Bay", Pattern.compile("back bay"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  MASSAVE ("Massachusetts Avenue", Pattern.compile("mass(achusetts)? ave"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  RUGGLES ("Ruggles", Pattern.compile("ruggles"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  ROXBURYCROSSING ("Roxbury Crossing", Pattern.compile("roxbury cr"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  JACKSONSQ ("Jackson Square", Pattern.compile("jackson"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  STONYBROOK ("Stony Brook", Pattern.compile("stony"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  GREENST ("Green Street", Pattern.compile("green st"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  FORESTHILLS ("Forest Hills", Pattern.compile("forest hills st"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),

  private final String name;
  private final Pattern searchTerm;
  private final List<Line> lines;
  private final List<Branch> branches;

  Station(String name, Pattern searchTerm, List<Line> lines, List<Branch> branches) {
    this.name = name;
    this.searchTerm = searchTerm;
    this.lines = lines;
    this.branches = branches;
  }

  public String getName() {
    return name;
  }

  public Pattern getSearchTerm() {
    return searchTerm;
  }

  public List<Line> getLines() {
    return lines;
  }

  public List<Branch> getBranches() {
    return branches;
  }
}
