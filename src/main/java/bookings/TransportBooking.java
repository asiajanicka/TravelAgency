package bookings;

import interfaces.ICost;
import transport.Seat;
import transport.Transport;

import java.math.BigDecimal;

public abstract class TransportBooking implements ICost {
    private Transport transport;
    private int seatNumber;
    private boolean isForAdult;

    TransportBooking() {
    }

    public TransportBooking(Transport transport, int seatNumber, boolean isForAdult) {
        this.seatNumber = seatNumber;
        this.transport = transport;
        this.isForAdult = isForAdult;
    }

    public BigDecimal getPriceForSeat() {
        Seat seat = transport.findSeat(seatNumber);
        return isForAdult ? seat.getPrice() : seat.getPrice().divide(new BigDecimal(2));
    }

    protected abstract BigDecimal getPriceForLuggage();

    @Override
    public final BigDecimal calculatePrice() {
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

    public boolean isForAdult() {
        return isForAdult;
    }

    public void setForAdult(boolean forAdult) {
        isForAdult = forAdult;
    }

    @Override
    public String toString() {
        return String.format("%s Seat %d Is for adult? %b", transport.toString(), seatNumber, isForAdult);
    }
}
