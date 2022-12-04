package bookings;

import enums.PlaneBaggage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import transport.Seat;
import transport.Transport;

import java.math.BigDecimal;

public class FlightBooking extends TransportBooking {
    private static final String PRICE_FOR_HAND_BAG = "10.5";
    private static final String PRICE_FOR_CHECKED_BAG = "20.5";
    private PlaneBaggage baggage;
    private static final Logger logger = LogManager.getLogger(FlightBooking.class);

    public FlightBooking(Transport flight, Seat seat, PlaneBaggage baggage, boolean isForAdult) {
        super(flight, seat, isForAdult);
        this.baggage = baggage;
    }

    public FlightBooking(Transport flight, Seat seat) {
        super(flight, seat, true);
        this.baggage = PlaneBaggage.CHECKED;
    }

    protected final BigDecimal getPriceForLuggage() {
        BigDecimal priceForLuggage;
        switch (baggage) {
            case HAND: {
                priceForLuggage = new BigDecimal(PRICE_FOR_HAND_BAG);
                break;
            }
            case CHECKED: {
                priceForLuggage = new BigDecimal(PRICE_FOR_CHECKED_BAG);
                break;
            }
            default:
                priceForLuggage = BigDecimal.ZERO;
        }
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
                super.toString(),
                baggage,
                calculatePrice());
    }
}
