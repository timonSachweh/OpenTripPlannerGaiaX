package org.opentripplanner.gaiax.model;

import org.opentripplanner.gaiax.model.response.GaiaXFeatureType;

public abstract class GaiaXRoutingGeometry {

  private GaiaXFeatureType type;

  public GaiaXRoutingGeometry() {
    this.type = null;
  }

  public GaiaXRoutingGeometry(GaiaXFeatureType type) {
    this.type = type;
  }

  public GaiaXFeatureType getType() {
    return type;
  }

  public void setType(GaiaXFeatureType type) {
    this.type = type;
  }
}
