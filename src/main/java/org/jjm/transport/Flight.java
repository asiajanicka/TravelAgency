package org.jjm.transport;

import org.jjm.enums.City;
import org.jjm.enums.PlaneSeatType;
import org.jjm.enums.TransportType;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.utils.DateFormat;
import org.jjm.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;

public class Flight extends Transport<PlaneSeatType>{
    public Flight() {
    }

    public Flight(LocalDateTime dateDeparture, LocalDateTime dateArrival, City cityFrom, City cityTo, List<Seat<PlaneSeatType>> seats) {
        super(dateDeparture, dateArrival, cityFrom, cityTo, TransportType.PLANE, seats);
    }

    public LocalDateTime getBoardingTime() {
        return getDateDeparture().minusHours(2);
    }

    @Override
    public Seat<PlaneSeatType> getSeatByType(PlaneSeatType seatType) throws NoPlacementAvailableException {
        return Utils.findSeatByType(getSeats(), seatType);
    }

    @Override
    public String toString() {
        return String.format("Flight: %s Boarding time: %s",
                super.toString(), DateFormat.format(getBoardingTime()));
    }
}

