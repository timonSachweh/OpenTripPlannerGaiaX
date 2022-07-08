package org.opentripplanner.gaiax.model;

import java.io.Serializable;
import java.util.List;

public class GaiaXStartEndRoutingRequest implements Cloneable, Serializable {

  private List<GaiaXRoutingStop> tripLocations;
  private GaiaXMetadata metadata;

  @Override
  public GaiaXStartEndRoutingRequest clone() {
    try {
      GaiaXStartEndRoutingRequest clone = (GaiaXStartEndRoutingRequest) super.clone();
      clone.tripLocations = List.copyOf(tripLocations);
      clone.metadata = metadata.clone();
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  public List<GaiaXRoutingStop> getTripLocations() {
    return tripLocations;
  }

  public void setTripLocations(List<GaiaXRoutingStop> tripLocations) {
    this.tripLocations = tripLocations;
  }

  public GaiaXMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(GaiaXMetadata metadata) {
    this.metadata = metadata;
  }
}
