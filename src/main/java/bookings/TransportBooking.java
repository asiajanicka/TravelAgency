package bookings;

import interfaces.ICost;
import transport.Seat;
import transport.Transport;

import java.math.BigDecimal;

public abstract class TransportBooking implements ICost {
    private Transport transport;
    private Seat seat;
    private boolean isForAdult;

    TransportBooking() {
    }

    public TransportBooking(Transport transport, Seat seat, boolean isForAdult) {
        this.seat = seat;
        this.transport = transport;
        this.isForAdult = isForAdult;
    }

    public BigDecimal getPriceForSeat() {
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

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public boolean isForAdult() {
        return isForAdult;
    }

    public void setForAdult(boolean forAdult) {
        isForAdult = forAdult;
    }

    @Override
    public String toString() {
        return String.format("%s Seat %d Is for adult? %b", transport.toString(), seat.getNumber(), isForAdult);
    }
}
