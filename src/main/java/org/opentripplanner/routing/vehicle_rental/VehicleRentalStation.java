package org.opentripplanner.routing.vehicle_rental;

import static java.util.Locale.ROOT;

import org.opentripplanner.model.FeedScopedId;
import org.opentripplanner.util.I18NString;

public class VehicleRentalStation implements VehicleRentalPlace {

    public FeedScopedId id;
    public I18NString name;
    public double longitude;
    public double latitude;
    public int vehiclesAvailable = Integer.MAX_VALUE;
    public int spacesAvailable = Integer.MAX_VALUE;
    public boolean allowDropoff = true;
    public boolean isCarStation = false;
    public boolean isKeepingVehicleRentalAtDestinationAllowed = false;

    public boolean realTimeData = true;

    public VehicleRentalStationUris rentalUris;

    @Override
    public FeedScopedId getId() {
        return id;
    }

    @Override
    public String getStationId() {
        return getId().getId();
    }

    @Override
    public String getNetwork() {
        return getId().getFeedId();
    }


    @Override
    public I18NString getName() {
        return name;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public int getVehiclesAvailable() {
        return vehiclesAvailable;
    }

    @Override
    public int getSpacesAvailable() {
        return spacesAvailable;
    }

    @Override
    public boolean isAllowDropoff() {
        return allowDropoff;
    }

    @Override
    public boolean isFloatingBike() {
        return false;
    }

    @Override
    public boolean isCarStation() {
        return isCarStation;
    }

    @Override
    public boolean isKeepingVehicleRentalAtDestinationAllowed() {
        return isKeepingVehicleRentalAtDestinationAllowed;
    }

    /**
     * Whether this station is static (usually coming from OSM data) or a real-time source. If no real-time data, users should take
     * bikesAvailable/spacesAvailable with a pinch of salt, as they are always the total capacity divided by two. Only the total is meaningful.
     */
    @Override
    public boolean isRealTimeData() {
        return realTimeData;
    }

    @Override
    public VehicleRentalStationUris getRentalUris() {
        return rentalUris;
    }

    @Override
    public String toString () {
        return String.format(ROOT, "Vehicle rental station %s at %.6f, %.6f", name, latitude, longitude);
    }
}
