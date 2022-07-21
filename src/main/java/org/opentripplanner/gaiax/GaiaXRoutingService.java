package org.opentripplanner.gaiax;

import java.util.Arrays;
import java.util.List;
import org.opentripplanner.api.parameter.QualifiedModeSet;
import org.opentripplanner.gaiax.model.GaiaXRoutingStop;
import org.opentripplanner.gaiax.model.GaiaXStartEndRoutingRequest;
import org.opentripplanner.gaiax.model.response.GaiaXLineStringGeometry;
import org.opentripplanner.gaiax.model.response.GaiaXRoutingCoordinate;
import org.opentripplanner.gaiax.model.response.GaiaXRoutingResponse;
import org.opentripplanner.model.GenericLocation;
import org.opentripplanner.model.plan.Itinerary;
import org.opentripplanner.model.plan.Leg;
import org.opentripplanner.routing.RoutingService;
import org.opentripplanner.routing.api.request.RoutingRequest;
import org.opentripplanner.routing.api.response.RoutingResponse;
import org.opentripplanner.standalone.server.OTPServer;
import org.opentripplanner.standalone.server.Router;

public class GaiaXRoutingService {

  protected OTPServer otpServer;

  public GaiaXRoutingService(OTPServer otpServer) {
    this.otpServer = otpServer;
  }

  public GaiaXRoutingResponse getRouteForFullRequest(
    GaiaXStartEndRoutingRequest request,
    boolean routing,
    boolean eta
  ) {
    System.out.println(otpServer.getRouter().graph.getTimeZone().toZoneId().toString());

    RoutingRequest routingRequest = this.buildRoutingRequest(request);

    Router router = otpServer.getRouter();
    RoutingService routingService = new RoutingService(router.graph);

    RoutingResponse res = routingService.route(routingRequest, router);

    if (
      res == null ||
      res.getTripPlan() == null ||
      res.getTripPlan().itineraries == null ||
      res.getTripPlan().itineraries.isEmpty()
    ) return null;

    Itinerary itinerary = res.getTripPlan().itineraries.get(0);

    GaiaXRoutingResponse response = new GaiaXRoutingResponse();
    if (routing) response = this.addRoutingResponseToGeoJson(response, itinerary);
    if (eta) response = this.addEtaMetadataToGeoJson(response, itinerary);
    return response;
  }

  private RoutingRequest buildRoutingRequest(GaiaXStartEndRoutingRequest request) {
    RoutingRequest routingRequest = otpServer.getRouter().copyDefaultRoutingRequest();
    GaiaXRoutingStop start = request.getTripLocations().get(0);
    GaiaXRoutingStop end = request.getTripLocations().get(1);

    routingRequest.from = new GenericLocation(start.getLatitude(), start.getLongitude());
    routingRequest.to = new GenericLocation(end.getLatitude(), end.getLongitude());
    QualifiedModeSet mode = new QualifiedModeSet("CAR");
    routingRequest.modes = mode.getRequestModes();

    return routingRequest;
  }

  private GaiaXRoutingResponse addRoutingResponseToGeoJson(
    GaiaXRoutingResponse geoJson,
    Itinerary itinerary
  ) {
    for (int i = 0; i < itinerary.getLegs().size(); i++) {
      Leg leg = itinerary.getLegs().get(i);
      List<GaiaXRoutingCoordinate> coordinateList = Arrays
        .stream(leg.getLegGeometry().getCoordinates())
        .map(GaiaXRoutingCoordinate::new)
        .toList();

      geoJson.getFeature(i).setGeometry(new GaiaXLineStringGeometry(coordinateList));
    }

    return geoJson;
  }

  private GaiaXRoutingResponse addEtaMetadataToGeoJson(
    GaiaXRoutingResponse geoJson,
    Itinerary itinerary
  ) {
    System.out.println(itinerary.startTime().toString());
    System.out.println(itinerary.endTime().toString());
    return geoJson;
  }
}
