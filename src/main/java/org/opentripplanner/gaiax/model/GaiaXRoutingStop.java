package org.opentripplanner.gaiax.model;

import java.io.Serializable;

public class GaiaXRoutingStop implements Cloneable, Serializable {

  private Double latitude;
  private Double longitude;
  private int waitingTime;

  @Override
  public GaiaXRoutingStop clone() {
    try {
      GaiaXRoutingStop clone = (GaiaXRoutingStop) super.clone();
      clone.latitude = latitude;
      clone.longitude = longitude;
      clone.waitingTime = waitingTime;
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public int getWaitingTime() {
    return waitingTime;
  }

  public void setWaitingTime(int waitingTime) {
    this.waitingTime = waitingTime;
  }
}
