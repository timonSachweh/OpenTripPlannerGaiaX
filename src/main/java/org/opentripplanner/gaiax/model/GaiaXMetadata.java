package org.opentripplanner.gaiax.model;

import eu.datex2.siri13.schema._1_0._1_0.VehicleTypeEnum;
import java.io.Serializable;

public class GaiaXMetadata implements Cloneable, Serializable {

  private VehicleTypeEnum vehicleType;

  @Override
  public GaiaXMetadata clone() {
    try {
      GaiaXMetadata clone = (GaiaXMetadata) super.clone();
      clone.vehicleType = vehicleType;
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  public VehicleTypeEnum getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(VehicleTypeEnum vehicleType) {
    this.vehicleType = vehicleType;
  }
}
