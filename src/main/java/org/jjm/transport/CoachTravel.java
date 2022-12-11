package org.jjm.transport;

import org.jjm.enums.City;
import org.jjm.enums.CoachSeatType;
import org.jjm.enums.TransportType;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.interfaces.IFindPlacementByType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CoachTravel extends Transport implements IFindPlacementByType<CoachSeatType> {

    public CoachTravel() {
    }

    public CoachTravel(LocalDateTime dateDeparture, LocalDateTime dateArrival, City cityFrom, City cityTo,
                       List<Seat> seats) {
        super(dateDeparture, dateArrival, cityFrom, cityTo, TransportType.BUS, seats);
    }

    @Override
    public <CoachSeatType> CoachSeat findByType(CoachSeatType t) throws NoPlacementAvailableException {
        List<Seat> seatsOfGivenType = getSeats().stream()
                .filter(p -> !p.isBooked() && ((CoachSeat) p).getType().equals(t))
                .collect(Collectors.toList());
        if (seatsOfGivenType.size() == 0) {
            throw new NoPlacementAvailableException(
                    String.format("There is no free seat of type %s in the bus. All seats are booked.", t));
        } else
            return (CoachSeat) seatsOfGivenType.get(0);
    }

    @Override
    public String toString() {
        return String.format("Coach trip: %s", super.toString());
    }
}
