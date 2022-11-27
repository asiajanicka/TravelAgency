package transport;

import enums.City;
import enums.TransportType;
import utils.DateFormat;

import java.time.LocalDateTime;
import java.util.List;

public class Flight extends Transport {
    public Flight() {
    }

    public Flight(LocalDateTime dateDeparture, LocalDateTime dateArrival, City cityFrom, City cityTo, List<Seat> seats) {
        super(dateDeparture, dateArrival, cityFrom, cityTo, TransportType.PLANE, seats);
    }

    public LocalDateTime getBoardingTime() {
        return getDateDeparture().minusHours(2);
    }

    @Override
    public String toString() {
        return String.format("Flight: %s Boarding time: %s",
                super.toString(), DateFormat.format(getBoardingTime()));
    }
}

