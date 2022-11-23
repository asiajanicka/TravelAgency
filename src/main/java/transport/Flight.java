package transport;

import enums.City;
import enums.PlaneBaggage;
import utils.DateFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Flight extends Transport {
    private PlaneBaggage baggageType;

    public Flight() {
    }

    public Flight(LocalDateTime dateDeparture, LocalDateTime dateArrival, PlaneSeat seat, City cityFrom, City cityTo,
                  boolean isForAdult, PlaneBaggage baggageType) {
        super(dateDeparture, dateArrival, seat, cityFrom, cityTo, isForAdult);
        if (baggageType == null) {
            throw new IllegalArgumentException("Baggage type can't be null");
        }
        this.baggageType = baggageType;
    }

    public BigDecimal calculatePrice() {
        return super.calculatePrice().add(calculatePriceForLuggage());
    }

    private BigDecimal calculatePriceForLuggage() {
        switch (baggageType) {
            case HAND: {
                return new BigDecimal(10);
            }
            case CHECKED: {
                return new BigDecimal(20);
            }
            default:
                throw new IllegalArgumentException("Incorrect baggage type.");
        }
    }

    public LocalDateTime getBoardingTime() {
        return getDateDeparture().minusHours(2);
    }

    public PlaneBaggage getBaggageType() {
        return baggageType;
    }

    public void setBaggageType(PlaneBaggage baggageType) {
        if (baggageType == null) {
            throw new IllegalArgumentException("Baggage type can't be null");
        }
        this.baggageType = baggageType;
    }

    public String toString() {
        return String.format("Flight: %s Boarding time: %s Baggage: %s",
                super.toString(), DateFormat.format(getBoardingTime()), baggageType);
    }
}
