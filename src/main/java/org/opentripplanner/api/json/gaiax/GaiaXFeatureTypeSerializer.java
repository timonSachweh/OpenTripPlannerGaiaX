package org.opentripplanner.api.json.gaiax;

import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import org.opentripplanner.gaiax.model.response.GaiaXFeatureType;

public class GaiaXFeatureTypeSerializer extends JsonSerializer<GaiaXFeatureType> {

  @Override
  public void serialize(
    GaiaXFeatureType gaiaXFeatureType,
    com.fasterxml.jackson.core.JsonGenerator jsonGenerator,
    com.fasterxml.jackson.databind.SerializerProvider serializerProvider
  ) throws IOException {
    jsonGenerator.writeString(gaiaXFeatureType.toString());
  }
}
