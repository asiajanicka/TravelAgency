package org.jjm.bookings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.interfaces.ICost;
import org.jjm.transport.Seat;
import org.jjm.transport.Transport;

import java.math.BigDecimal;

public abstract class TransportBooking<T> implements ICost {
    private Transport<T> transport;
    private Seat<T> seat;
    private boolean isForAdult;
    private static final Logger logger = LogManager.getLogger(TransportBooking.class);

    TransportBooking() {
    }

    public TransportBooking(Transport<T> transport, Seat<T> seat, boolean isForAdult) {
        this.seat = seat;
        this.transport = transport;
        this.isForAdult = isForAdult;
    }

    public BigDecimal getPriceForSeat() {
        BigDecimal priceForSeat = isForAdult ? seat.getPrice() : seat.getPrice().divide(new BigDecimal(2));
        logger.debug(String.format("Transport booking - calculated price for seat: %,.2f", priceForSeat));
        return priceForSeat;
    }

    protected abstract BigDecimal getPriceForLuggage();

    @Override
    public final BigDecimal calculatePrice() {
        BigDecimal price = getPriceForSeat().add(getPriceForLuggage());
        logger.debug(String.format("Transport booking - calculated price: %,.2f", price));
        return price;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport<T> transport) {
        this.transport = transport;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat<T> seat) {
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
        return String.format("%s %s Is for adult? %b", transport.toString(), seat.toString(), isForAdult);
    }
}
