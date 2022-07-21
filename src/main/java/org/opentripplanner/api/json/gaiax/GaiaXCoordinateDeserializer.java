package org.opentripplanner.api.json.gaiax;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.opentripplanner.gaiax.model.response.GaiaXRoutingCoordinate;

public class GaiaXCoordinateDeserializer extends JsonDeserializer<GaiaXRoutingCoordinate> {

  @Override
  public GaiaXRoutingCoordinate deserialize(
    JsonParser jsonParser,
    DeserializationContext deserializationContext
  ) throws IOException, JacksonException {
    //TODO: Has to be implemented, if needed
    return null;
  }
}
