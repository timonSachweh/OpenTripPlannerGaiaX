package org.opentripplanner.api.json.gaiax;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.opentripplanner.gaiax.model.response.GaiaXRoutingCoordinate;

public class GaiaXCoordinateSerializer extends JsonSerializer<GaiaXRoutingCoordinate> {

  @Override
  public void serialize(
    GaiaXRoutingCoordinate gaiaXRoutingCoordinate,
    JsonGenerator jsonGenerator,
    SerializerProvider serializerProvider
  ) throws IOException {
    double[] coordinates = new double[2];
    coordinates[0] = gaiaXRoutingCoordinate.getLongitude();
    coordinates[1] = gaiaXRoutingCoordinate.getLatitude();
    jsonGenerator.writeArray(coordinates, 0, coordinates.length);
  }
}
