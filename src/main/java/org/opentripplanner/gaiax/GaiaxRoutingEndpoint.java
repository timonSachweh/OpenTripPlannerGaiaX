package org.opentripplanner.gaiax;

import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.glassfish.grizzly.http.server.Request;
import org.opentripplanner.gaiax.model.GaiaXStartEndRoutingRequest;
import org.opentripplanner.gaiax.model.response.GaiaXRoutingResponse;
import org.opentripplanner.standalone.server.OTPServer;

@Path("gaiax/routing")
@PermitAll
public class GaiaxRoutingEndpoint {

  @Context
  private OTPServer otpServer;

  @DefaultValue("true")
  @QueryParam("routing")
  private boolean routing;

  @DefaultValue("true")
  @QueryParam("eta")
  private boolean eta;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public GaiaXRoutingResponse routing(
    GaiaXStartEndRoutingRequest request,
    @Context UriInfo uriInfo,
    @Context Request grizzlyRequest
  ) {
    GaiaXRoutingService routingService = new GaiaXRoutingService(this.otpServer);
    return routingService.getRouteForFullRequest(request, routing, eta);
  }
}
