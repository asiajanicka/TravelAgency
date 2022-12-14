package org.jjm.transport;

import org.jjm.enums.City;
import org.jjm.enums.PlaneSeatType;
import org.jjm.enums.TransportType;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.interfaces.IFindByType;
import org.jjm.utils.DateFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Flight extends Transport implements IFindByType<PlaneSeatType, PlaneSeat> {
    public Flight() {
    }

    public Flight(LocalDateTime dateDeparture, LocalDateTime dateArrival, City cityFrom, City cityTo, List<Seat> seats) {
        super(dateDeparture, dateArrival, cityFrom, cityTo, TransportType.PLANE, seats);
    }

    public LocalDateTime getBoardingTime() {
        return getDateDeparture().minusHours(2);
    }

    @Override
    public PlaneSeat findByType(PlaneSeatType planeSeatType) throws NoPlacementAvailableException {
        List<Seat> seatsOfGivenType = getSeats().stream()
                .filter(p -> !p.isBooked() && ((PlaneSeat) p).getType().equals(planeSeatType))
                .collect(Collectors.toList());
        if (seatsOfGivenType.size() == 0) {
            throw new NoPlacementAvailableException(
                    String.format("There is no free seat of type %s on the plane. All seats are booked.",
                            planeSeatType.toString()));
        } else
            return (PlaneSeat) seatsOfGivenType.get(0);
    }

    @Override
    public String toString() {
        return String.format("Flight: %s Boarding time: %s",
                super.toString(), DateFormat.format(getBoardingTime()));
    }

}

