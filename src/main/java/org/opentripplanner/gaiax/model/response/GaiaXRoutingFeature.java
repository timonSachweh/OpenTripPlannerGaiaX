package org.opentripplanner.gaiax.model.response;

import org.opentripplanner.gaiax.model.GaiaXRoutingGeometry;

public class GaiaXRoutingFeature {

  private GaiaXFeatureType type;
  private GaiaXRoutingGeometry geometry;
  private GaiaXRoutingProperties properties;

  public GaiaXRoutingFeature() {
    this.type = GaiaXFeatureType.FEATURE;
    this.geometry = null;
    this.properties = null;
  }

  public GaiaXRoutingFeature(GaiaXRoutingGeometry geometry) {
    this();
    this.geometry = geometry;
  }

  public GaiaXRoutingFeature(GaiaXRoutingProperties properties) {
    this();
    this.properties = properties;
  }

  public GaiaXRoutingFeature(GaiaXRoutingGeometry geometry, GaiaXRoutingProperties properties) {
    this();
    this.geometry = geometry;
    this.properties = properties;
  }

  public GaiaXFeatureType getType() {
    return type;
  }

  public void setType(GaiaXFeatureType type) {
    this.type = type;
  }

  public GaiaXRoutingGeometry getGeometry() {
    return geometry;
  }

  public void setGeometry(GaiaXRoutingGeometry geometry) {
    this.geometry = geometry;
  }

  public GaiaXRoutingProperties getProperties() {
    return properties;
  }

  public void setProperties(GaiaXRoutingProperties properties) {
    this.properties = properties;
  }
}
