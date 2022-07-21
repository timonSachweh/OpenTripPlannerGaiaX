package org.opentripplanner.api.json.gaiax;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.opentripplanner.gaiax.model.response.GaiaXFeatureType;

public class GaiaXFeatureTypeDeserializer extends JsonDeserializer<GaiaXFeatureType> {

  @Override
  public GaiaXFeatureType deserialize(
    com.fasterxml.jackson.core.JsonParser jsonParser,
    com.fasterxml.jackson.databind.DeserializationContext deserializationContext
  ) throws IOException {
    String text = jsonParser.getText();
    if (text == null) return null;
    if (text.equals(GaiaXFeatureType.FEATURE.toString())) return GaiaXFeatureType.FEATURE;
    if (
      text.equals(GaiaXFeatureType.FEATURE_COLLECTION.toString())
    ) return GaiaXFeatureType.FEATURE_COLLECTION;
    if (text.equals(GaiaXFeatureType.LINE_STRING.toString())) return GaiaXFeatureType.LINE_STRING;
    if (text.equals(GaiaXFeatureType.POINT.toString())) return GaiaXFeatureType.POINT;
    if (text.equals(GaiaXFeatureType.POLYGON.toString())) return GaiaXFeatureType.POLYGON;

    return null;
  }
}
