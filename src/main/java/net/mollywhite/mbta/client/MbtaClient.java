package net.mollywhite.mbta.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MbtaClient {
  private final Client client;
  private final String baseURL;
  private final String apiKey;
  private final ObjectMapper mapper;
  private final List<String> routeIds;
  final Logger logger = LoggerFactory.getLogger(TweetConsumer.class);

  @Inject
  public MbtaClient(ObjectMapper mapper) throws IOException {
    this.mapper = mapper;
    this.client = ClientBuilder.newClient();
    this.baseURL = "http://realtime.mbta.com/developer/api/v2/";
    Properties props = new Properties();
    InputStream is = this.getClass().getClassLoader().getResourceAsStream("./secrets.properties");
    props.load(is);
    this.apiKey = props.getProperty("mbtaApiKey");
    this.routeIds = Lists.newArrayList("Green-B", "Green-C", "Green-D", "Green-E", "Mattapan", "Blue", "Orange", "Red");
  }

  public List<Integer> getAllVehicles() {
    List<Integer> vehicles = new ArrayList();
    for (String route : this.routeIds) {
      vehicles.addAll(this.getVehiclesForRoute(route));
    }
    return vehicles;
  }

  private List<Integer> getVehiclesForRoute(String route) {
    WebTarget target = client.target(baseURL)
        .path("vehiclesbyroute")
        .queryParam("api_key", this.apiKey)
        .queryParam("route", route)
        .queryParam("format", "json");
    JsonNode resp = get(target);
    if (resp != null) {
      List<JsonNode> vehicleNodes = resp.findValues("vehicle_id");
      if (vehicleNodes != null) {
        return Arrays.asList(vehicleNodes.stream().map(n -> n.asInt()).toArray(Integer[]::new));
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
      logger.error("Request to MBTA API returned ", response.getStatus());
      return null;
    }
  }
}
