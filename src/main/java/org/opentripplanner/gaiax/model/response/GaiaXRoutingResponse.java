package org.opentripplanner.gaiax.model.response;

import java.util.ArrayList;
import java.util.List;

public class GaiaXRoutingResponse {

  private GaiaXFeatureType type;
  private List<GaiaXRoutingFeature> features;

  public GaiaXRoutingResponse() {
    this.type = GaiaXFeatureType.FEATURE_COLLECTION;
    this.features = new ArrayList<>();
  }

  public GaiaXRoutingResponse(GaiaXRoutingFeature feature) {
    this();
    this.features.add(feature);
  }

  public void addFeature(GaiaXRoutingFeature feature) {
    this.features.add(feature);
  }

  public GaiaXRoutingFeature getFeature(int index) {
    if (this.features.size() <= index) this.features.add(new GaiaXRoutingFeature());
    return this.features.get(index);
  }

  public GaiaXFeatureType getType() {
    return type;
  }

  public void setType(GaiaXFeatureType type) {
    this.type = type;
  }

  public List<GaiaXRoutingFeature> getFeatures() {
    return features;
  }

  public void setFeatures(List<GaiaXRoutingFeature> features) {
    this.features = features;
  }
}
