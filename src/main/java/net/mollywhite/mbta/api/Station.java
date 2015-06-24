package net.mollywhite.mbta.api;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public enum Station {
  ALEWIFE ("Alewife", Pattern.compile("alewife"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  DAVIS ("Davis", Pattern.compile("davis[^\\w]"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  PORTER ("Porter", Pattern.compile("porter[^\\w]"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  HARVARD ("Harvard", Pattern.compile("harvard (?!ave)"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  CENTRAL ("Central", Pattern.compile("central (?!ave)"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  KENDALLMIT ("Kendall/MIT", Pattern.compile("(kendall|[^\\w]mit[^\\w])"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  CHARLESMGH ("Charles/MGH", Pattern.compile("(charles|[^\\w]mgh[^\\w])"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  PARKST ("Park Street", Pattern.compile("[^\\w]park[^\\w]"), Lists.newArrayList(Line.RED, Line.GREEN), Lists.newArrayList(Branch.B, Branch.C, Branch.D, Branch.E, Branch.ASHMONT, Branch.BRAINTREE)),
  DOWNTOWNCROSSING ("Downtown Crossing", Pattern.compile("downtown"), Lists.newArrayList(Line.RED, Line.ORANGE), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  SOUTHSTATION ("South Station", Pattern.compile("south st(?!reet)"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  BROADWAY ("Broadway", Pattern.compile("broadway"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  ANDREW ("Andrew", Pattern.compile("[^\\w]andrew[^\\w]"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  JFKUMASS ("JFK/UMass", Pattern.compile("(jfk|umass)"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT, Branch.BRAINTREE)),
  SAVINHILL ("Savin Hill", Pattern.compile("savin"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT)),
  FIELDSCORNER ("Fields Corner", Pattern.compile("[^\\w]fields[^\\w]"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT)),
  SHAWMUT ("Shawmut", Pattern.compile("shawmut"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT)),
  ASHMONT ("Ashmont", Pattern.compile("ashmont"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.ASHMONT)),
  CEDARGROVE ("Cedar Grove", Pattern.compile("[^\\w]cedar[^\\w]"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  BUTLER ("Butler", Pattern.compile("[^\\w]butler[^\\w]"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  MILTON ("Milton", Pattern.compile("[^\\w]milton[^\\w]"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  CENTRALAVE ("Central Avenue", Pattern.compile("central ave"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  VALLEYRD ("Valley Road", Pattern.compile("valley"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  CAPENST ("Capen Street", Pattern.compile(" capen[^\\w]"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  MATTAPAN ("Mattapan", Pattern.compile("mattapan"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.MATTAPAN)),
  NORTHQUINCY ("North Quincy", Pattern.compile("n(orth|o\\.?|\\.)? quincy"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.BRAINTREE)),
  WOLLASTON ("Wollaston", Pattern.compile("wollaston"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.BRAINTREE)),
  QUINCYCENTER ("Quincy Center", Pattern.compile("quincy c"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.BRAINTREE)),
  QUINCYADAMS ("Quincy Adams", Pattern.compile("quincy adams"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.BRAINTREE)),
  BRAINTREE ("Braintree", Pattern.compile("braintree"), Lists.newArrayList(Line.RED), Lists.newArrayList(Branch.BRAINTREE)),
  OAKGROVE ("Oak Grove", Pattern.compile("oak grove"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  MALDENCENTER ("Malden Center", Pattern.compile("malden"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  WELLINGTON ("Wellington", Pattern.compile("wellington"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  ASSEMBLY ("Assembly", Pattern.compile("assembly"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  SULLIVANSQ ("Sullivan Square", Pattern.compile("sullivan"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  COMMUNITYCOLLEGE ("Community College", Pattern.compile("comm(unity)?\\.? college"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  NORTHSTATION ("North Station", Pattern.compile("n(orth|o\\.?|\\.)? st"), Lists.newArrayList(Line.ORANGE, Line.GREEN), Lists.newArrayList(Branch.C, Branch.E)),
  HAYMARKET ("Haymarket", Pattern.compile("haymarket"), Lists.newArrayList(Line.ORANGE, Line.GREEN), Lists.newArrayList(Branch.C, Branch.E)),
  STATE ("State", Pattern.compile("[^\\w]state[^\\w]"), Lists.newArrayList(Line.ORANGE, Line.BLUE), Collections.<Branch>emptyList()),
  CHINATOWN ("Chinatown", Pattern.compile("chinatown"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  TUFTSMEDICALCENTER ("Tufts Medical Center", Pattern.compile("tufts"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  BACKBAY ("Back Bay", Pattern.compile("back bay"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  MASSAVE ("Massachusetts Avenue", Pattern.compile("mass(achusetts)?\\.? ave"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  RUGGLES ("Ruggles", Pattern.compile("ruggles"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  ROXBURYCROSSING ("Roxbury Crossing", Pattern.compile("roxbury cr"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  JACKSONSQ ("Jackson Square", Pattern.compile("[^\\w]jackson"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  STONYBROOK ("Stony Brook", Pattern.compile("[^\\w]stony"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  GREENST ("Green Street", Pattern.compile("green st"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  FORESTHILLS ("Forest Hills", Pattern.compile("forest hills"), Lists.newArrayList(Line.ORANGE), Collections.<Branch>emptyList()),
  WONDERLAND ("Wonderland", Pattern.compile("wonderland"), Lists.newArrayList(Line.BLUE), Collections.<Branch>emptyList()),
  REVEREBEACH ("Revere Beach", Pattern.compile("revere"), Lists.newArrayList(Line.BLUE), Collections.<Branch>emptyList()),
  BEACHMONT ("Beachmont", Pattern.compile("beachmont"), Lists.newArrayList(Line.BLUE), Collections.<Branch>emptyList()),
  SUFFOLKDOWNS ("Suffolk Downs", Pattern.compile("suffolk downs"), Lists.newArrayList(Line.BLUE), Collections.<Branch>emptyList()),
  ORIENTHEIGHTS ("Orient Heights", Pattern.compile("orient"), Lists.newArrayList(Line.BLUE), Collections.<Branch>emptyList()),
  WOODISLAND ("Wood Island", Pattern.compile("wood island"), Lists.newArrayList(Line.BLUE), Collections.<Branch>emptyList()),
  AIRPORT ("Airport", Pattern.compile("airport st"), Lists.newArrayList(Line.BLUE), Collections.<Branch>emptyList()),
  MAVERICK ("Maverick", Pattern.compile("maverick"), Lists.newArrayList(Line.BLUE), Collections.<Branch>emptyList()),
  AQUARIUM ("Aquarium", Pattern.compile("aquarium"), Lists.newArrayList(Line.BLUE), Collections.<Branch>emptyList()),
  GOVTCTR ("Government Center", Pattern.compile("gov(ernment|t\\.?) (center|ctr)"), Lists.newArrayList(Line.BLUE), Collections.<Branch>emptyList()),
  BOWDOIN ("Bowdoin", Pattern.compile("bowdoin"), Lists.newArrayList(Line.BLUE), Collections.<Branch>emptyList()),
  LECHMERE ("Lechmere", Pattern.compile("lechmere"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  SCIENCEPARKWESTEND ("Science Park/West End", Pattern.compile("science park"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  BOYLSTON ("Boylston", Pattern.compile("boylston"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B, Branch.C, Branch.D, Branch.E)),
  ARLINGTON ("Arlington", Pattern.compile("arlington"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B, Branch.C, Branch.D, Branch.E)),
  COPLEY ("Copley", Pattern.compile("copley"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B, Branch.C, Branch.D, Branch.E)),
  PRUDENTIAL ("Prudential", Pattern.compile("pru(dential)?[^\\w]"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  SYMPHONY ("Symphony", Pattern.compile("symphony"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  NORTHEASTERN ("Northeastern", Pattern.compile("northeastern"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  MUSEUMOFFINEARTS ("Museum of Fine Arts", Pattern.compile("museum|mofa"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  LONGWOODMEDICALAREA ("Longwood Medical Area", Pattern.compile("longwood"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  BRIGHAMCIRCLE ("Brigham Circle", Pattern.compile("brigham"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  FENWOODRD ("Fenwood Road", Pattern.compile("fenwood"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  MISSIONPARK ("Mission Park", Pattern.compile("mission park"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  RIVERWAY ("Riverway", Pattern.compile("riverway"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  BACKOFTHEHILL ("Back of the Hill", Pattern.compile("back of the hill"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  HEATH ("Heath", Pattern.compile("heath"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.E)),
  HYNESCONVENTIONCTR ("Hynes Convention Center", Pattern.compile("hynes"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B, Branch.C, Branch.D)),
  KENMORE ("Kenmore", Pattern.compile("kenmore"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B, Branch.C, Branch.D)),
  FENWAY ("Fenway", Pattern.compile("fenway"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  LONGWOOD ("Longwood", Pattern.compile("longwood"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  BROOKLINEVILLAGE ("Brookline Village", Pattern.compile("brookline village"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  BROOKLINEHILLS ("Brookline Hills", Pattern.compile("brookline hills"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  BEACONSFIELD ("Beaconsfield", Pattern.compile("beaconsfield"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  RESERVOIR ("Reservoir", Pattern.compile("reservoir"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  CHESTNUTHILL ("Chestnut Hill", Pattern.compile("chestnut"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  NEWTONCENTRE ("Newton Centre", Pattern.compile("newton c"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  NEWTONHIGHLANDS ("Newton Highlands", Pattern.compile("newton high"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  ELIOT ("Eliot", Pattern.compile("[^\\w]eliot"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  WABAN ("Waban", Pattern.compile("waban"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  WOODLAND ("Woodland", Pattern.compile("woodland"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  RIVERSIDE ("Riverside", Pattern.compile("riverside"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.D)),
  STMARYSST ("St. Marys Street", Pattern.compile("[^\\w]s(t\\.?|aint)[^\\w]mary"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  HAWESST ("Hawes Street", Pattern.compile("[^\\w]hawes[^\\w]"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  KENTST ("Kent Street", Pattern.compile("[^\\w]kent[^\\w]"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  STPAULSTC ("St. Paul Street", Pattern.compile("[^\\w]s(t\\.?|aint)[^\\w]paul"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  COOLIDGECORNER ("Coolidge Corner", Pattern.compile("coolidge"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  SUMMITAVE ("Summit Avenue", Pattern.compile("[^\\w]summit"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  BRANDONHALL ("Brandon Hall", Pattern.compile("brandon hall"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  FAIRBANKSST ("Fairbanks", Pattern.compile("fairbank"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  WASHINGTONSQ ("Washington Square", Pattern.compile("washington sq"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  TAPPANST ("Tappan Street", Pattern.compile("tappan"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  DEANRD ("Dean Road", Pattern.compile("[^\\w]dean[^\\w]"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  ENGLEWOODAVE ("Englewood Avenue", Pattern.compile("eng(le|el)wood"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  CLEVELANDCIRCLE ("Cleveland Circle", Pattern.compile("cleveland"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.C)),
  BLANDFORDST ("Blandford Street", Pattern.compile("blandford"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  BUEAST ("Boston University East", Pattern.compile("[^\\w](bu|b\\.u\\.|boston univ(ersity)?)[^\\w] e"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  BUCENTRAL ("Boston University Central", Pattern.compile("[^\\w](bu|b\\.u\\.|boston univ(ersity)?)[^\\w] cent"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  BUWEST ("Boston University West", Pattern.compile("[^\\w](bu|b\\.u\\.|boston univ(ersity)?)[^\\w] w"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  STPAULSTB ("St. Paul Street", Pattern.compile("[^\\w]s(t\\.?|aint)[^\\w]paul"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  PLEASANTST ("Pleasant Street", Pattern.compile("pleasant"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  BABCOCKST ("Babcock Street", Pattern.compile("babcock"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  PACKARDSCORNER ("Packards Corner", Pattern.compile("packards"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  HARVARDAVE ("Harvard Avenue", Pattern.compile("harvard av"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  GRIGGSST ("Griggs Street/Long Avenue", Pattern.compile("griggs"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  ALLSTONST ("Allston Street", Pattern.compile("allston st"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  WARRENST ("Warren Street", Pattern.compile("[^\\w]warren[^\\w]"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  WASHINGTONST ("Washington Street", Pattern.compile("washington st"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  SUTHERLANDRD ("Sutherland Road", Pattern.compile("sutherland"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  CHISWICKRD ("Chiswick Road", Pattern.compile("chiswick"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  CHESTNUTHILLAVE ("Chestnut Hill Avenue", Pattern.compile("chestnut"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  SOUTHST ("South Street", Pattern.compile("[^\\w]south st"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B)),
  BOSTONCOLLEGE ("Boston College", Pattern.compile("([^\\w]bc[^\\w]|boston college)"), Lists.newArrayList(Line.GREEN), Lists.newArrayList(Branch.B));

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
