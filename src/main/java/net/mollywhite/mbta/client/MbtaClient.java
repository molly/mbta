package net.mollywhite.mbta.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import net.mollywhite.mbta.api.Branch;
import net.mollywhite.mbta.api.Line;
import net.mollywhite.mbta.api.Route;
import net.mollywhite.mbta.api.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MbtaClient {
  private final Client client;
  private final String baseURL;
  private final String apiKey;
  private final ObjectMapper mapper;
  private final List<Route> routeIds;
  final Logger logger = LoggerFactory.getLogger(TweetConsumer.class);

  public MbtaClient(ObjectMapper mapper, String mbtaApiKey) throws IOException {
    this.mapper = mapper;
    this.client = ClientBuilder.newClient();
    this.baseURL = "http://realtime.mbta.com/developer/api/v2/";
    this.apiKey = mbtaApiKey;
    this.routeIds = Lists.newArrayList(
        new Route("Green-B", Line.GREEN, Branch.B),
        new Route("Green-C", Line.GREEN, Branch.C),
        new Route("Green-D", Line.GREEN, Branch.D),
        new Route("Green-E", Line.GREEN, Branch.E),
        new Route("Mattapan", Line.RED, Branch.MATTAPAN),
        new Route("Blue", Line.BLUE, null),
        new Route("Orange", Line.ORANGE, null),
        new Route("Red", Line.RED, null));
  }

  public List<Vehicle> getAllVehicles() {
    List<Vehicle> vehicles = new ArrayList();
    for (Route route : this.routeIds) {
      vehicles.addAll(this.getVehiclesForRoute(route));
    }
    return vehicles;
  }

  private List<Vehicle> getVehiclesForRoute(Route route) {
    WebTarget target = client.target(baseURL)
        .path("vehiclesbyroute")
        .queryParam("api_key", this.apiKey)
        .queryParam("route", route.getId())
        .queryParam("format", "json");
    JsonNode resp = get(target);

    if (resp != null) {
      List<JsonNode> vehicleNodes = resp.findValues("vehicle_id");
      if (vehicleNodes != null) {
        return Arrays.asList(vehicleNodes.stream().map(n -> new Vehicle(n.asText(), route.getLine(), route.getBranch())).toArray(Vehicle[]::new));
      }
    }
    return new ArrayList<>();
  }

  private JsonNode get(WebTarget target) {
    Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON_TYPE);
    invocationBuilder.header("User-Agent", "MbtaBot/0.1 (https://github.com/molly/mbta)");
    Response response = invocationBuilder.get();
    if (response.getStatus() == 200) {
      String respStr = response.readEntity(String.class);
      JsonNode respJson = null;
      try {
        respJson = mapper.readTree(respStr);
      } catch (IOException e) {
        logger.error("Error while parsing response from MBTA API.");
        return null;
      }
      return respJson;
    } else {
      logger.error("Request to MBTA API returned " + response.getStatus());
      return null;
    }
  }
}
