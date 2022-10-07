package org.opentripplanner.gaiax;

import com.azure.core.annotation.Get;
import com.azure.core.annotation.Post;
import java.io.IOException;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.codehaus.jackson.map.ObjectMapper;
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

  @DefaultValue("")
  @QueryParam("routingParams")
  private String request;

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

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public GaiaXRoutingResponse routingParameterized(
    @Context UriInfo uriInfo,
    @Context Request grizzlyRequest
  ) throws IOException {
    if (request.equals("")) {
      return null;
    }
    ObjectMapper mapper = new ObjectMapper();
    GaiaXStartEndRoutingRequest routingRequest = mapper.readValue(
      request,
      GaiaXStartEndRoutingRequest.class
    );
    GaiaXRoutingService routingService = new GaiaXRoutingService(this.otpServer);
    return routingService.getRouteForFullRequest(routingRequest, routing, eta);
  }
}
