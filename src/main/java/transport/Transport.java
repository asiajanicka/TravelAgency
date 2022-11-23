package transport;

import enums.City;
import utils.DateFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Transport {
    private LocalDateTime dateDeparture;
    private LocalDateTime dateArrival;
    private Seat seat;
    private City cityFrom;
    private City cityTo;
    private boolean isForAdult;

    public Transport() {
    }

    public Transport(LocalDateTime dateDeparture, LocalDateTime dateArrival, Seat seat, City cityFrom, City cityTo,
                     boolean isForAdult) {
        if (dateDeparture == null) {
            throw new IllegalArgumentException("Departure date can't be null");
        }
        if (dateArrival == null) {
            throw new IllegalArgumentException("Arrival date can't be null");
        }
        if (dateArrival.isBefore(dateDeparture)) {
            throw new IllegalArgumentException("Arrival date can't be before departure date");
        }
        if (seat == null) {
            throw new IllegalArgumentException("Seat can't be null");
        }
        if (cityFrom == null) {
            throw new IllegalArgumentException("Departure city can't be null");
        }
        if (cityTo == null) {
            throw new IllegalArgumentException("Arrival city can't be null");
        }
        this.dateDeparture = dateDeparture;
        this.dateArrival = dateArrival;
        this.seat = seat;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.isForAdult = isForAdult;
    }

    public BigDecimal calculatePrice() {
        return isForAdult() ? seat.getPrice() : seat.getPrice().divide(new BigDecimal(2));
    }

    public LocalDateTime getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(LocalDateTime dateDeparture) {
        if (dateDeparture == null) {
            throw new IllegalArgumentException("Departure date can't be null");
        }
        if (dateArrival.isBefore(dateDeparture)) {
            throw new IllegalArgumentException("Departure date can't be after arrival date");
        }
        this.dateDeparture = dateDeparture;
    }

    public LocalDateTime getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(LocalDateTime dateArrival) {
        if (dateArrival == null) {
            throw new IllegalArgumentException("Arrival date can't be null");
        }
        if (dateArrival.isBefore(dateDeparture)) {
            throw new IllegalArgumentException("Arrival date can't be before departure date");
        }
        this.dateArrival = dateArrival;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        if (seat == null) {
            throw new IllegalArgumentException("Seat can't be null");
        }
        this.seat = seat;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        if (cityFrom == null) {
            throw new IllegalArgumentException("Departure city can't be null");
        }
        this.cityFrom = cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        if (cityTo == null) {
            throw new IllegalArgumentException("Arrival city can't be null");
        }
        this.cityTo = cityTo;
    }

    public boolean isForAdult() {
        return isForAdult;
    }

    public void setForAdult(boolean forAdult) {
        isForAdult = forAdult;
    }

    public String toString() {
        return String.format("From %s(%s) to %s(%s) Is for adult? %b %s Total price: %.2f",
                cityFrom, DateFormat.format(dateDeparture),
                cityTo, DateFormat.format(dateArrival),
                isForAdult(),
                seat,
                calculatePrice());
    }
}
