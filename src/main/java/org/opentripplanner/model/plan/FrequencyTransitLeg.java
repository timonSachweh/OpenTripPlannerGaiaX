package org.opentripplanner.model.plan;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.opentripplanner.model.TripPattern;
import org.opentripplanner.model.transfer.ConstrainedTransfer;
import org.opentripplanner.routing.trippattern.TripTimes;
import org.opentripplanner.transit.model.site.StopLocation;

/**
 * One leg of a trip -- that is, a temporally continuous piece of the journey that takes place on a
 * particular vehicle, which does run using a frequency-based schedule, rather than a timetable-
 * based schedule.
 */
public class FrequencyTransitLeg extends ScheduledTransitLeg {

  private final int frequencyHeadwayInSeconds;

  public FrequencyTransitLeg(
    TripTimes tripTimes,
    TripPattern tripPattern,
    int boardStopIndexInPattern,
    int alightStopIndexInPattern,
    ZonedDateTime startTime,
    ZonedDateTime endTime,
    LocalDate serviceDate,
    ZoneId zoneId,
    ConstrainedTransfer transferFromPreviousLeg,
    ConstrainedTransfer transferToNextLeg,
    int generalizedCost,
    int frequencyHeadwayInSeconds,
    @Nullable Float accessibilityScore
  ) {
    super(
      tripTimes,
      tripPattern,
      boardStopIndexInPattern,
      alightStopIndexInPattern,
      startTime,
      endTime,
      serviceDate,
      zoneId,
      transferFromPreviousLeg,
      transferToNextLeg,
      generalizedCost,
      accessibilityScore
    );
    this.frequencyHeadwayInSeconds = frequencyHeadwayInSeconds;
  }

  @Override
  public Boolean getNonExactFrequency() {
    return frequencyHeadwayInSeconds != 0;
  }

  @Override
  public Integer getHeadway() {
    return frequencyHeadwayInSeconds;
  }

  @Override
  public boolean isPartiallySameTransitLeg(Leg other) {
    var same = super.isPartiallySameTransitLeg(other);
    // frequency-based trips have all the same trip id, so we have to check that the start times
    // are not equal
    if (other instanceof FrequencyTransitLeg frequencyTransitLeg) {
      var start = getTripTimes().getDepartureTime(0);
      var otherStart = frequencyTransitLeg.getTripTimes().getDepartureTime(0);
      return same && (start == otherStart);
    } else {
      return same;
    }
  }

  @Override
  public List<StopArrival> getIntermediateStops() {
    List<StopArrival> visits = new ArrayList<>();

    for (int i = boardStopPosInPattern + 1; i < alightStopPosInPattern; i++) {
      StopLocation stop = tripPattern.getStop(i);

      int arrivalTime = tripTimes.getArrivalTime(i);
      int departureTime = tripTimes.getDepartureTime(i) + frequencyHeadwayInSeconds;

      StopArrival visit = new StopArrival(
        Place.forStop(stop),
        serviceDate.toZonedDateTime(zoneId, arrivalTime),
        serviceDate.toZonedDateTime(zoneId, departureTime),
        i,
        tripTimes.getOriginalGtfsStopSequence(i)
      );
      visits.add(visit);
    }
    return visits;
  }

  @Override
  public ScheduledTransitLeg withAccessibilityScore(Float score) {
    return new FrequencyTransitLeg(
      tripTimes,
      tripPattern,
      boardStopPosInPattern,
      alightStopPosInPattern,
      getStartTime(),
      getEndTime(),
      serviceDate.toLocalDate(),
      zoneId,
      getTransferFromPrevLeg(),
      getTransferToNextLeg(),
      getGeneralizedCost(),
      frequencyHeadwayInSeconds,
      score
    );
  }
}
