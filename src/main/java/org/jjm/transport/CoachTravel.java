package org.jjm.transport;

import org.jjm.enums.City;
import org.jjm.enums.TransportType;

import java.time.LocalDateTime;
import java.util.List;

public class CoachTravel extends Transport {

    public CoachTravel() {
    }

    public CoachTravel(LocalDateTime dateDeparture, LocalDateTime dateArrival, City cityFrom, City cityTo,
                       List<Seat> seats) {
        super(dateDeparture, dateArrival, cityFrom, cityTo, TransportType.BUS, seats);
    }

    @Override
    public String toString() {
        return String.format("Coach trip: %s", super.toString());
    }
}
