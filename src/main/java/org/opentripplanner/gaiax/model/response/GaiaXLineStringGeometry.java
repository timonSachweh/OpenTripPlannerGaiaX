package org.opentripplanner.gaiax.model.response;

import java.util.ArrayList;
import java.util.List;
import org.opentripplanner.gaiax.model.GaiaXRoutingGeometry;

public class GaiaXLineStringGeometry extends GaiaXRoutingGeometry {

  private List<GaiaXRoutingCoordinate> coordinates;

  public GaiaXLineStringGeometry() {
    super(GaiaXFeatureType.LINE_STRING);
    this.coordinates = new ArrayList<>();
  }

  public GaiaXLineStringGeometry(List<GaiaXRoutingCoordinate> coordinates) {
    this();
    this.coordinates = coordinates;
  }

  public void addCoordinate(GaiaXRoutingCoordinate coordinate) {
    this.coordinates.add(coordinate);
  }

  public GaiaXRoutingCoordinate getCoordinate(int index) {
    return this.coordinates.get(index);
  }

  public List<GaiaXRoutingCoordinate> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<GaiaXRoutingCoordinate> coordinates) {
    this.coordinates = coordinates;
  }
}
