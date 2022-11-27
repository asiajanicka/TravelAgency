package transport;

import enums.City;
import enums.TransportType;
import utils.DateFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Transport {
    private LocalDateTime dateDeparture;
    private LocalDateTime dateArrival;
    private City cityFrom;
    private City cityTo;
    private TransportType type;
    private List<Seat> seats;

    public Transport() {
    }

    public Transport(LocalDateTime dateDeparture, LocalDateTime dateArrival, City cityFrom, City cityTo,
                     TransportType type, List<Seat> seats) {
        this.dateDeparture = dateDeparture;
        this.dateArrival = dateArrival;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.type = type;
        this.seats = seats;
    }

    public Seat findSeat(int num) {
        return seats.stream().filter(p->p.getNumber() == num).collect(Collectors.toList()).get(0);
    };

    public LocalDateTime getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(LocalDateTime dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public LocalDateTime getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(LocalDateTime dateArrival) {
        this.dateArrival = dateArrival;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        this.cityTo = cityTo;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return String.format("from %s(%s) to %s(%s)",
                cityFrom, DateFormat.format(dateDeparture),
                cityTo, DateFormat.format(dateArrival));
    }
}
