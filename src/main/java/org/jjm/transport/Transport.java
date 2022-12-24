package org.jjm.transport;

import org.jjm.enums.City;
import org.jjm.enums.PlacementType;
import org.jjm.enums.TransportType;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.exceptions.NoPlacementException;
import org.jjm.exceptions.PlacementAlreadyBooked;
import org.jjm.utils.DateFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        seats = new ArrayList<>();
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

    public Seat getSeat(int seatNumber) throws NoPlacementException {
        return seats.stream()
                .filter(p -> p.getNumber() == seatNumber)
                .findFirst()
                .orElseThrow(() -> new NoPlacementException(PlacementType.SEAT, seatNumber));
    }

    public Seat bookSeat(int seatNumber) throws NoPlacementException, PlacementAlreadyBooked {
        if (getSeat(seatNumber).book())
            throw new PlacementAlreadyBooked(type, seatNumber, cityFrom, cityTo);
        else return getSeat(seatNumber);
    }

    public List<Seat> getAvailableSeats() {
        return seats.stream().filter(p -> !p.isBooked()).collect(Collectors.toList());
    }

    public Seat getFirstAvailableSeat() throws NoPlacementAvailableException {
        if (getAvailableSeats().size() == 0) {
            throw new NoPlacementAvailableException(cityFrom, cityTo);
        } else
            return getAvailableSeats().get(0);
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
        return String.format("%s from %s(%s) to %s(%s)", type,
                cityFrom, DateFormat.format(dateDeparture),
                cityTo, DateFormat.format(dateArrival));
    }
}
