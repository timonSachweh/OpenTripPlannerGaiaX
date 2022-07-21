package org.opentripplanner.gaiax.model.response;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class GaiaXRoutingProperties {

  private ZonedDateTime startTime;
  private ZonedDateTime endTime;
  private long duration; // in seconds

  public GaiaXRoutingProperties() {
    this.startTime = null;
    this.endTime = null;
    this.duration = -1;
  }

  public GaiaXRoutingProperties(ZonedDateTime startTime, ZonedDateTime endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.duration = this.startTime.until(this.endTime, ChronoUnit.SECONDS);
  }

  public ZonedDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(ZonedDateTime startTime) {
    this.startTime = startTime;
  }

  public ZonedDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(ZonedDateTime endTime) {
    this.endTime = endTime;
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }
}
