package bookings;

import enums.PlaneBaggage;
import transport.Transport;

import java.math.BigDecimal;

public class FlightBooking extends TransportBooking {
    private static final String PRICE_FOR_HAND_BAG = "10.5";
    private static final String PRICE_FOR_CHECKED_BAG = "20.5";
    private PlaneBaggage baggage;

    public FlightBooking(Transport flight, int seatNumber, PlaneBaggage baggage, boolean isForAdult) {
        super(flight, seatNumber, isForAdult);
        this.baggage = baggage;
    }

    public FlightBooking(Transport flight, int seatNumber) {
        super(flight, seatNumber, true);
        this.baggage = PlaneBaggage.CHECKED;
    }

    protected BigDecimal getPriceForLuggage() {
        switch (baggage) {
            case HAND: {
                return new BigDecimal(PRICE_FOR_HAND_BAG);
            }
            case CHECKED: {
                return new BigDecimal(PRICE_FOR_CHECKED_BAG);
            }
            default:
                return BigDecimal.ZERO;
        }
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
