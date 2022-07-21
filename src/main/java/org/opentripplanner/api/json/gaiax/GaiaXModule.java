package org.opentripplanner.api.json.gaiax;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.opentripplanner.gaiax.model.response.GaiaXFeatureType;
import org.opentripplanner.gaiax.model.response.GaiaXRoutingCoordinate;

public class GaiaXModule extends SimpleModule {

  private GaiaXFeatureTypeSerializer typeSerializer;

  public GaiaXModule() {
    super(
      "GaiaXModule",
      new Version(1, 0, 0, (String) null, "de.tudo.gaiax", "gaiax-serialization")
    );
    this.addSerializer(GaiaXFeatureType.class, new GaiaXFeatureTypeSerializer());
    this.addSerializer(GaiaXRoutingCoordinate.class, new GaiaXCoordinateSerializer());

    this.addDeserializer(GaiaXFeatureType.class, new GaiaXFeatureTypeDeserializer());
  }

  public void setupModule(Module.SetupContext context) {
    super.setupModule(context);
  }
}
