package org.opentripplanner.gaiax.model.response;

import org.locationtech.jts.geom.Coordinate;

public class GaiaXRoutingCoordinate {

  private double latitude;
  private double longitude;

  public GaiaXRoutingCoordinate(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public GaiaXRoutingCoordinate(Coordinate coordinate) {
    this.latitude = coordinate.y;
    this.longitude = coordinate.x;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
}
