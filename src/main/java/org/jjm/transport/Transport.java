package org.jjm.transport;

import org.jjm.destination.enums.City;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.exceptions.NoPlacementException;
import org.jjm.exceptions.PlacementAlreadyBooked;
import org.jjm.transport.enums.TransportType;
import org.jjm.utils.DateFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Transport<T extends Enum> {
    private LocalDateTime dateDeparture;
    private LocalDateTime dateArrival;
    private City cityFrom;
    private City cityTo;
    private TransportType type;
    private List<Seat<T>> seats;

    public Transport() {
        seats = new ArrayList<>();
    }

    public Transport(LocalDateTime dateDeparture, LocalDateTime dateArrival, City cityFrom, City cityTo,
                     TransportType type, List<Seat<T>> seats) {
        this.dateDeparture = dateDeparture;
        this.dateArrival = dateArrival;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.type = type;
        this.seats = seats;
    }

    public Seat<T> getSeat(int seatNumber) throws NoPlacementException {
        return seats.stream()
                .filter(p -> p.getNumber() == seatNumber)
                .findFirst()
                .orElseThrow(() -> new NoPlacementException(String.format("There is no seat with number %d in the %s.",
                        seatNumber, type.toString().toLowerCase())));
    }

    public Seat<T> bookSeat(int seatNumber) throws NoPlacementException, PlacementAlreadyBooked {
        if (getSeat(seatNumber).book())
            throw new PlacementAlreadyBooked(String.format("Seat %d is already booked. Sorry.", seatNumber));
        else return getSeat(seatNumber);
    }

    public Seat<T> getFirstAvailableSeat() throws NoPlacementAvailableException {
        return seats.stream()
                .filter(p -> !p.isBooked())
                .findFirst()
                .orElseThrow(() -> new NoPlacementAvailableException(String.format("There is no free seat in %s. " +
                        "All seats are booked.", type.toString().toLowerCase())));
    }

    public abstract Seat<T> getSeatByType(T seatType) throws NoPlacementAvailableException;

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

    public List<Seat<T>> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat<T>> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return String.format("from %s(%s) to %s(%s)",
                cityFrom, DateFormat.format(dateDeparture),
                cityTo, DateFormat.format(dateArrival));
    }
}
