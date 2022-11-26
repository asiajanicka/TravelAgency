package transport;

import enums.PlaneBaggage;

import java.math.BigDecimal;

public class FlightBooking extends TransportBooking{

    private PlaneBaggage baggage;

    public FlightBooking(Seat seat, PlaneBaggage baggage, boolean isForAdult) {
        super(seat, isForAdult);
        this.baggage = baggage;
    }

    protected BigDecimal getPriceForLuggage() {
        switch (baggage) {
            case HAND: {
                return new BigDecimal(10);
            }
            case CHECKED: {
                return new BigDecimal(20);
            }
            default: return BigDecimal.ZERO;
        }
    }

    public String toString(){
        return String.format("%s Baggage: %s Total price: %.2f",
                super.toString(),
                baggage,
                calculatePrice());
    }
}
