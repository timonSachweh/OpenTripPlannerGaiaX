package org.opentripplanner.gaiax.model;

import java.io.Serializable;

public class GaiaXRoutingStop implements Cloneable, Serializable {

  private String latitude;
  private String longitude;
  private String waitingTime;

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

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getWaitingTime() {
    return waitingTime;
  }

  public void setWaitingTime(String waitingTime) {
    this.waitingTime = waitingTime;
  }
}
