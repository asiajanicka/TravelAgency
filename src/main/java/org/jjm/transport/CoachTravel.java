package org.jjm.transport;

import org.jjm.destination.enums.City;
import org.jjm.transport.enums.CoachSeatType;
import org.jjm.transport.enums.TransportType;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;

public class CoachTravel extends Transport<CoachSeatType> {

    public CoachTravel() {
    }

    public CoachTravel(LocalDateTime dateDeparture, LocalDateTime dateArrival, City cityFrom, City cityTo,
                       List<Seat<CoachSeatType>> seats) {
        super(dateDeparture, dateArrival, cityFrom, cityTo, TransportType.BUS, seats);
    }

    @Override
    public Seat<CoachSeatType> getSeatByType(CoachSeatType seatType) throws NoPlacementAvailableException {
        return Utils.findSeatByType(getSeats(), seatType);
    }

    @Override
    public String toString() {
        return String.format("Coach trip: %s", super.toString());
    }
}
