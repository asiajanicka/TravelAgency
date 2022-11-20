package flight;

import enums.City;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Flight {
    private LocalDate dateDeparture;
    private LocalDate dateArrival;
    private Seat seat;
    private City cityFrom;
    private City cityTo;
    private boolean isForAdult;

    Flight(){
    }

    public Flight(LocalDate dateDeparture, LocalDate dateArrival, Seat seat, City cityFrom, City cityTo, boolean isForAdult) {
        this.dateDeparture = dateDeparture;
        this.dateArrival = dateArrival;
        this.seat = seat;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.isForAdult = isForAdult;
    }

    public BigDecimal calculatePrice(){
        return isForAdult() ? seat.getPrice() : seat.getPrice().divide(new BigDecimal(2));
    }

    public LocalDate getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(LocalDate dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public LocalDate getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(LocalDate dateArrival) {
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

    public String toString(){
        return String.format("Flight: from %s(%tB %te, %tY) to %s(%tB %te, %tY) Is for adult? %b seat number: %d %s Total price: %.2f",
                cityFrom, dateDeparture, dateDeparture, dateDeparture,
                cityTo, dateArrival, dateArrival, dateArrival,
                isForAdult(),
                seat.getNumber(),
                seat.getType(),
                calculatePrice());
    }
}
