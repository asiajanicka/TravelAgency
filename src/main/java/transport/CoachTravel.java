package transport;

import enums.City;
import enums.TransportType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CoachTravel extends Transport {
    private List<Seat> seats;

    public CoachTravel() {
    }

    public CoachTravel(LocalDateTime dateDeparture, LocalDateTime dateArrival, City cityFrom, City cityTo,
                       List<Seat> seats) {
        super(dateDeparture, dateArrival, cityFrom, cityTo, TransportType.BUS);
        this.seats = seats;
    }

    @Override
    public Seat findSeat(int num) {
        return seats.stream().filter(p -> p.getNumber() == num).collect(Collectors.toList()).get(0);
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return String.format("Coach trip: %s", super.toString());
    }
}
