package bookings;

import transport.Seat;
import transport.Transport;

import java.math.BigDecimal;

public abstract class TransportBooking extends Booking {
    private Transport transport;
    private int seatNumber;

    TransportBooking() {
    }

    public TransportBooking(Transport transport, int seatNumber, boolean isForAdult) {
        super(isForAdult);
        this.seatNumber = seatNumber;
        this.transport = transport;
    }

    public BigDecimal getPriceForSeat() {
        Seat seat = transport.findSeat(seatNumber);
        return isForAdult() ? seat.getPrice() : seat.getPrice().divide(new BigDecimal(2));
    }

    protected abstract BigDecimal getPriceForLuggage();

    public BigDecimal calculatePrice() {
        return getPriceForSeat().add(getPriceForLuggage());
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return String.format("%s Seat %d Is for adult? %b", transport.toString(), seatNumber, isForAdult());
    }
}
