package org.opentripplanner.api.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.opentripplanner.gaiax.model.response.GaiaXFeatureType;

public class GaiaXModule extends SimpleModule {

  private GaiaXFeatureTypeSerializer typeSerializer;

  public GaiaXModule() {
    super(
      "GaiaXModule",
      new Version(1, 0, 0, (String) null, "de.tudo.gaiax", "gaiax-serialization")
    );
    this.typeSerializer = new GaiaXFeatureTypeSerializer();
    this.addSerializer(GaiaXFeatureType.class, this.typeSerializer);

    this.addDeserializer(GaiaXFeatureType.class, new GaiaXFeatureTypeDeserializer());
  }

  public void setupModule(Module.SetupContext context) {
    super.setupModule(context);
  }
}
