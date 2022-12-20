package org.jjm.bookings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.enums.PlaneBaggage;
import org.jjm.enums.PlaneSeatType;
import org.jjm.transport.Seat;
import org.jjm.transport.Transport;

import java.math.BigDecimal;

public class FlightBooking extends TransportBooking<PlaneSeatType> {
    private PlaneBaggage baggage;
    private static final Logger logger = LogManager.getLogger(FlightBooking.class);

    public FlightBooking(Transport flight, Seat<PlaneSeatType> seat, PlaneBaggage baggage, boolean isForAdult) {
        super(flight, seat, isForAdult);
        this.baggage = baggage;
    }

    public FlightBooking(Transport flight, Seat<PlaneSeatType> seat) {
        super(flight, seat, true);
        this.baggage = PlaneBaggage.CHECKED;
    }

    @Override
    protected final BigDecimal getPriceForLuggage() {
        BigDecimal priceForLuggage;
        if (getBaggage() != null) {
            priceForLuggage = getBaggage().getPrice();
        } else priceForLuggage = BigDecimal.ZERO;
        logger.debug(String.format("FLight booking - calculated price for luggage: %,.2f", priceForLuggage));
        return priceForLuggage;
    }

    public PlaneBaggage getBaggage() {
        return baggage;
    }

    public void setBaggage(PlaneBaggage baggage) {
        this.baggage = baggage;
    }

    @Override
    public String toString() {
        return String.format("%s Baggage: %s Total price: %.2f",
                super.toString(), baggage, calculatePrice());
    }
}
