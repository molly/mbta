package net.mollywhite.mbta.api;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public enum Station {
  ALEWIFE ("Alewife", Pattern.compile("alewife"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT, Branch.BRAINTREE)),
  DAVIS ("Davis", Pattern.compile("davis([^\\w]|\\z)"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT, Branch.BRAINTREE)),
  PORTER ("Porter", Pattern.compile("porter([^\\w]|\\z)"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT, Branch.BRAINTREE)),
  HARVARD ("Harvard", Pattern.compile("harvard(?! ?ave)"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT, Branch.BRAINTREE)),
  CENTRAL ("Central", Pattern.compile("central(?! ?ave)"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT, Branch.BRAINTREE)),
  KENDALLMIT ("Kendall/MIT", Pattern.compile("(kendal|([^\\w]|\\A)mit([^\\w]|\\z))"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT, Branch.BRAINTREE)),
  CHARLESMGH ("Charles/MGH", Pattern.compile("(charles|([^\\w]|\\A)mgh([^\\w]|\\z))"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT, Branch.BRAINTREE)),
  PARKST ("Park Street", Pattern.compile("([^\\w]|\\A)park([^\\w]|\\z)"), Sets.newHashSet(Line.RED, Line.GREEN), Sets.newHashSet(Branch.B, Branch.C, Branch.D, Branch.E, Branch.ASHMONT, Branch.BRAINTREE)),
  DOWNTOWNCROSSING ("Downtown Crossing", Pattern.compile("downtown"), Sets.newHashSet(Line.RED, Line.ORANGE), Sets.newHashSet(Branch.ASHMONT, Branch.BRAINTREE)),
  SOUTHSTATION ("South Station", Pattern.compile("south ?st(?!reet)"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT, Branch.BRAINTREE)),
  BROADWAY ("Broadway", Pattern.compile("broadway"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT, Branch.BRAINTREE)),
  ANDREW ("Andrew", Pattern.compile("([^\\w]|\\A)andrew([^\\w]|\\z)"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT, Branch.BRAINTREE)),
  JFKUMASS ("JFK/UMass", Pattern.compile("(jfk|umass)"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT, Branch.BRAINTREE)),
  SAVINHILL ("Savin Hill", Pattern.compile("savin"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT)),
  FIELDSCORNER ("Fields Corner", Pattern.compile("([^\\w]|\\A)fields([^\\w]|\\z)"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT)),
  SHAWMUT ("Shawmut", Pattern.compile("shawmut"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT)),
  ASHMONT ("Ashmont", Pattern.compile("ashmont"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.ASHMONT)),
  CEDARGROVE ("Cedar Grove", Pattern.compile("([^\\w]|\\A)cedar([^\\w]|\\z)"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.MATTAPAN)),
  BUTLER ("Butler", Pattern.compile("([^\\w]|\\A)butler([^\\w]|\\z)"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.MATTAPAN)),
  MILTON ("Milton", Pattern.compile("([^\\w]|\\A)milton([^\\w]|\\z)"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.MATTAPAN)),
  CENTRALAVE ("Central Avenue", Pattern.compile("central ave"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.MATTAPAN)),
  VALLEYRD ("Valley Road", Pattern.compile("valley"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.MATTAPAN)),
  CAPENST ("Capen Street", Pattern.compile(" capen([^\\w]|\\z)"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.MATTAPAN)),
  MATTAPAN ("Mattapan", Pattern.compile("mattapan"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.MATTAPAN)),
  NORTHQUINCY ("North Quincy", Pattern.compile("n(orth|o\\.?|\\.)? ?quincy"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.BRAINTREE)),
  WOLLASTON ("Wollaston", Pattern.compile("wollaston"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.BRAINTREE)),
  QUINCYCENTER ("Quincy Center", Pattern.compile("quincy ?c"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.BRAINTREE)),
  QUINCYADAMS ("Quincy Adams", Pattern.compile("quincy ?adams"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.BRAINTREE)),
  BRAINTREE ("Braintree", Pattern.compile("braintree"), Sets.newHashSet(Line.RED), Sets.newHashSet(Branch.BRAINTREE)),
  OAKGROVE ("Oak Grove", Pattern.compile("oak ?grove"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  MALDENCENTER ("Malden Center", Pattern.compile("malden"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  WELLINGTON ("Wellington", Pattern.compile("wellington"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  ASSEMBLY ("Assembly", Pattern.compile("assembly"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  SULLIVANSQ ("Sullivan Square", Pattern.compile("sullivan"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  COMMUNITYCOLLEGE ("Community College", Pattern.compile("comm(unity|\\.)? ?college"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  NORTHSTATION ("North Station", Pattern.compile("([^\\w]|\\A)n(orth|o\\.?|\\.)? ?st"), Sets.newHashSet(Line.ORANGE, Line.GREEN), Sets.newHashSet(Branch.C, Branch.E)),
  HAYMARKET ("Haymarket", Pattern.compile("haymarket"), Sets.newHashSet(Line.ORANGE, Line.GREEN), Sets.newHashSet(Branch.C, Branch.E)),
  STATE ("State", Pattern.compile("([^\\w]|\\A)state([^\\w]|\\z)"), Sets.newHashSet(Line.ORANGE, Line.BLUE), Collections.<Branch>emptySet()),
  CHINATOWN ("Chinatown", Pattern.compile("chinatown"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  TUFTSMEDICALCENTER ("Tufts Medical Center", Pattern.compile("tufts"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  BACKBAY ("Back Bay", Pattern.compile("back bay"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  MASSAVE ("Massachusetts Avenue", Pattern.compile("mass(achusetts|\\.)? ?ave"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  RUGGLES ("Ruggles", Pattern.compile("ruggles"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  ROXBURYCROSSING ("Roxbury Crossing", Pattern.compile("roxbury ?cr"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  JACKSONSQ ("Jackson Square", Pattern.compile("([^\\w]|\\A)jackson"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  STONYBROOK ("Stony Brook", Pattern.compile("([^\\w]|\\A)stony"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  GREENST ("Green Street", Pattern.compile("green ?st"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  FORESTHILLS ("Forest Hills", Pattern.compile("forest ?hills"), Sets.newHashSet(Line.ORANGE), Collections.<Branch>emptySet()),
  WONDERLAND ("Wonderland", Pattern.compile("wonderland"), Sets.newHashSet(Line.BLUE), Collections.<Branch>emptySet()),
  REVEREBEACH ("Revere Beach", Pattern.compile("revere"), Sets.newHashSet(Line.BLUE), Collections.<Branch>emptySet()),
  BEACHMONT ("Beachmont", Pattern.compile("beachmont"), Sets.newHashSet(Line.BLUE), Collections.<Branch>emptySet()),
  SUFFOLKDOWNS ("Suffolk Downs", Pattern.compile("suffolk ?downs"), Sets.newHashSet(Line.BLUE), Collections.<Branch>emptySet()),
  ORIENTHEIGHTS ("Orient Heights", Pattern.compile("orient"), Sets.newHashSet(Line.BLUE), Collections.<Branch>emptySet()),
  WOODISLAND ("Wood Island", Pattern.compile("wood ?island"), Sets.newHashSet(Line.BLUE), Collections.<Branch>emptySet()),
  AIRPORT ("Airport", Pattern.compile("airport"), Sets.newHashSet(Line.BLUE), Collections.<Branch>emptySet()),
  MAVERICK ("Maverick", Pattern.compile("maverick"), Sets.newHashSet(Line.BLUE), Collections.<Branch>emptySet()),
  AQUARIUM ("Aquarium", Pattern.compile("aquarium"), Sets.newHashSet(Line.BLUE), Collections.<Branch>emptySet()),
  GOVTCTR ("Government Center", Pattern.compile("gov(ernment|t\\.?) ?(center|ctr)"), Sets.newHashSet(Line.BLUE), Collections.<Branch>emptySet()),
  BOWDOIN ("Bowdoin", Pattern.compile("bowdoin"), Sets.newHashSet(Line.BLUE), Collections.<Branch>emptySet()),
  LECHMERE ("Lechmere", Pattern.compile("lechmere"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  SCIENCEPARKWESTEND ("Science Park/West End", Pattern.compile("science park"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  BOYLSTON ("Boylston", Pattern.compile("boylston"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B, Branch.C, Branch.D, Branch.E)),
  ARLINGTON ("Arlington", Pattern.compile("arlington"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B, Branch.C, Branch.D, Branch.E)),
  COPLEY ("Copley", Pattern.compile("copley"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B, Branch.C, Branch.D, Branch.E)),
  PRUDENTIAL ("Prudential", Pattern.compile("pru(dential)?([^\\w]|\\z)"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  SYMPHONY ("Symphony", Pattern.compile("symphony"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  NORTHEASTERN ("Northeastern", Pattern.compile("northeastern"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  MUSEUMOFFINEARTS ("Museum of Fine Arts", Pattern.compile("(museum|([^\\w]|\\A)mofa([^\\w]|\\z))"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  LONGWOODMEDICALAREA ("Longwood Medical Area", Pattern.compile("longwood"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  BRIGHAMCIRCLE ("Brigham Circle", Pattern.compile("brigham"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  FENWOODRD ("Fenwood Road", Pattern.compile("fenwood"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  MISSIONPARK ("Mission Park", Pattern.compile("mission ?park"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  RIVERWAY ("Riverway", Pattern.compile("riverway"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  BACKOFTHEHILL ("Back of the Hill", Pattern.compile("back ?of ?the ?hill"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  HEATH ("Heath", Pattern.compile("heath"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.E)),
  HYNESCONVENTIONCTR ("Hynes Convention Center", Pattern.compile("hynes"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B, Branch.C, Branch.D)),
  KENMORE ("Kenmore", Pattern.compile("kenmore"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B, Branch.C, Branch.D)),
  FENWAY ("Fenway", Pattern.compile("fenway"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  LONGWOOD ("Longwood", Pattern.compile("longwood"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  BROOKLINEVILLAGE ("Brookline Village", Pattern.compile("brookline ?village"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  BROOKLINEHILLS ("Brookline Hills", Pattern.compile("brookline ?hills"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  BEACONSFIELD ("Beaconsfield", Pattern.compile("beaconsfield"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  RESERVOIR ("Reservoir", Pattern.compile("reservoir"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  CHESTNUTHILL ("Chestnut Hill", Pattern.compile("chestnut"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  NEWTONCENTRE ("Newton Centre", Pattern.compile("newton ?c"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  NEWTONHIGHLANDS ("Newton Highlands", Pattern.compile("newton ?high"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  ELIOT ("Eliot", Pattern.compile("([^\\w]|\\A)eliot"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  WABAN ("Waban", Pattern.compile("waban"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  WOODLAND ("Woodland", Pattern.compile("woodland"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  RIVERSIDE ("Riverside", Pattern.compile("riverside"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.D)),
  STMARYSST ("St. Marys Street", Pattern.compile("([^\\w]|\\A)s(t\\.?|aint)[^\\w]?mary"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  HAWESST ("Hawes Street", Pattern.compile("([^\\w]|\\A)hawes([^\\w]|\\z)"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  KENTST ("Kent Street", Pattern.compile("([^\\w]|\\A)kent([^\\w]|\\z)"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  STPAULSTC ("St. Paul Street", Pattern.compile("([^\\w]|\\A)s(t\\.?|aint)[^\\w]?paul"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  COOLIDGECORNER ("Coolidge Corner", Pattern.compile("coolidge"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  SUMMITAVE ("Summit Avenue", Pattern.compile("([^\\w]|\\A)summit"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  BRANDONHALL ("Brandon Hall", Pattern.compile("brandon ?hall"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  FAIRBANKSST ("Fairbanks", Pattern.compile("fairbank"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  WASHINGTONSQ ("Washington Square", Pattern.compile("washington ?sq"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  TAPPANST ("Tappan Street", Pattern.compile("tappan"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  DEANRD ("Dean Road", Pattern.compile("([^\\w]|\\A)dean([^\\w]|\\z)"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  ENGLEWOODAVE ("Englewood Avenue", Pattern.compile("eng(le|el)wood"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  CLEVELANDCIRCLE ("Cleveland Circle", Pattern.compile("cleveland"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.C)),
  BLANDFORDST ("Blandford Street", Pattern.compile("blandford"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  BUEAST ("Boston University East", Pattern.compile("([^\\w]|\\A)(bu|b\\.u\\.|boston univ(ersity)?)[^\\w]e"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  BUCENTRAL ("Boston University Central", Pattern.compile("([^\\w]|\\A)(bu|b\\.u\\.|boston univ(ersity)?)[^\\w]cent"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  BUWEST ("Boston University West", Pattern.compile("([^\\w]|\\A)(bu|b\\.u\\.|boston univ(ersity)?)[^\\w]w"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  STPAULSTB ("St. Paul Street", Pattern.compile("([^\\w]|\\A)s(t\\.?|aint)[^\\w]?paul"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  PLEASANTST ("Pleasant Street", Pattern.compile("pleasant"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  BABCOCKST ("Babcock Street", Pattern.compile("babcock"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  PACKARDSCORNER ("Packards Corner", Pattern.compile("packards"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  HARVARDAVE ("Harvard Avenue", Pattern.compile("harvard ?av"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  GRIGGSST ("Griggs Street/Long Avenue", Pattern.compile("griggs"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  ALLSTONST ("Allston Street", Pattern.compile("allston ?st"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  WARRENST ("Warren Street", Pattern.compile("([^\\w]|\\A)warren([^\\w]|\\z)"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  WASHINGTONST ("Washington Street", Pattern.compile("washington ?st"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  SUTHERLANDRD ("Sutherland Road", Pattern.compile("sutherland"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  CHISWICKRD ("Chiswick Road", Pattern.compile("chiswick"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  CHESTNUTHILLAVE ("Chestnut Hill Avenue", Pattern.compile("chestnut"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  SOUTHST ("South Street", Pattern.compile("([^\\w]|\\A)south ?st(?!ation)"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B)),
  BOSTONCOLLEGE ("Boston College", Pattern.compile("(([^\\w]|\\A)bc([^\\w]|\\z)|boston ?college)"), Sets.newHashSet(Line.GREEN), Sets.newHashSet(Branch.B));

  private final String name;
  private final Pattern searchTerm;
  private final Set<Line> lines;
  private final Set<Branch> branches;

  Station(String name, Pattern searchTerm, Set<Line> lines, Set<Branch> branches) {
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

  public boolean inTweet(String tweet) {
    return getSearchTerm().matcher(tweet).find();
  }

  public Set<Line> getLines() {
    return lines;
  }

  public Set<Line> getUnconnectedLines() {
    Set<Line> lines = new HashSet<Line>(Arrays.asList(Line.values()));
    lines.removeAll(this.getLines());
    return lines;
  }

  public Set<Branch> getBranches() {
    return branches;
  }

  public Set<Branch> getUnconnectedBranches() {
    Set<Branch> branches = new HashSet<Branch>(Arrays.asList(Branch.values()));
    branches.removeAll(this.getBranches());
    return branches;
  }
}
