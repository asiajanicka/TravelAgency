package org.jjm.bookings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.transport.Seat;
import org.jjm.transport.Transport;

import java.math.BigDecimal;

public class CoachTravelBooking extends TransportBooking {
    private static final String PRICE_FOR_SMALL_SUITCASE = "5";
    private static final String PRICE_FOR_LARGE_SUITCASE = "8";
    private int numberOfSmallSuitcases;
    private int numberOfLargeSuitcases;
    private static final Logger logger = LogManager.getLogger(CoachTravelBooking.class);

    public CoachTravelBooking() {
    }

    public CoachTravelBooking(Transport coachTravel, Seat seat, boolean isForAdult) {
        super(coachTravel, seat, isForAdult);
    }

    public CoachTravelBooking(Transport coachTravel, Seat seat, boolean isForAdult, int numberOfSmallSuitcases,
                              int numberOfLargeSuitcases) {
        this(coachTravel, seat, isForAdult);
        this.numberOfSmallSuitcases = numberOfSmallSuitcases;
        this.numberOfLargeSuitcases = numberOfLargeSuitcases;
    }

    @Override
    protected final BigDecimal getPriceForLuggage() {
        BigDecimal priceForLargeSuitcase = new BigDecimal(PRICE_FOR_LARGE_SUITCASE);
        BigDecimal priceForSmallSuitcase = new BigDecimal(PRICE_FOR_SMALL_SUITCASE);
        BigDecimal priceForLuggage = priceForLargeSuitcase.multiply(new BigDecimal(numberOfLargeSuitcases))
                .add(priceForSmallSuitcase.multiply(new BigDecimal(numberOfSmallSuitcases)));
        logger.debug(String.format("Coach Travel Booking - calculated price for luggage: %,.2f", priceForLuggage));
        return priceForLuggage;
    }

    public int getNumberOfSmallSuitcases() {
        return numberOfSmallSuitcases;
    }

    public void setNumberOfSmallSuitcases(int numberOfSmallSuitcases) {
        this.numberOfSmallSuitcases = numberOfSmallSuitcases;
    }

    public int getNumberOfLargeSuitcases() {
        return numberOfLargeSuitcases;
    }

    public void setNumberOfLargeSuitcases(int numberOfLargeSuitcases) {
        this.numberOfLargeSuitcases = numberOfLargeSuitcases;
    }

    @Override
    public String toString() {
        return String.format("%s Small suitcases: %d, Large suitcases: %d Total price: %.2f",
                super.toString(), numberOfLargeSuitcases, numberOfSmallSuitcases, calculatePrice());
    }
}
