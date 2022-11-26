package transport;

import enums.City;
import enums.TransportType;
import utils.DateFormat;

import java.time.LocalDateTime;

public abstract class Transport {
    private LocalDateTime dateDeparture;
    private LocalDateTime dateArrival;
    private City cityFrom;
    private City cityTo;
    private TransportType type;

    public Transport() {
    }

    public Transport(LocalDateTime dateDeparture, LocalDateTime dateArrival, City cityFrom, City cityTo,
                     TransportType type) {
        this.dateDeparture = dateDeparture;
        this.dateArrival = dateArrival;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.type = type;
    }

    public abstract Seat findSeat(int num);

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

    @Override
    public String toString() {
        return String.format("from %s(%s) to %s(%s)",
                cityFrom, DateFormat.format(dateDeparture),
                cityTo, DateFormat.format(dateArrival));
    }
}
