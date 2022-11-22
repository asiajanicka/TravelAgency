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
        this.dateDeparture = dateDeparture;
    }

    public LocalDateTime getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(LocalDateTime dateArrival) {
        this.dateArrival = dateArrival;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
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
                seat.toString(),
                calculatePrice());
    }
}
