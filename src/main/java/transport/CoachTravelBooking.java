package transport;

import java.math.BigDecimal;

public class CoachTravelBooking extends TransportBooking {
    private static final String PRICE_FOR_SMALL_SUITCASE = "5";
    private static final String PRICE_FOR_LARGE_SUITCASE = "8";
    private int numberOfSmallSuitcases;
    private int numberOfLargeSuitcases;

    public CoachTravelBooking() {
    }

    public CoachTravelBooking(Transport coachTravel, int seatNumber, boolean isForAdult) {
        super(coachTravel, seatNumber, isForAdult);
    }

    public CoachTravelBooking(Transport coachTravel, int seatNumber, boolean isForAdult, int numberOfSmallSuitcases,
                              int numberOfLargeSuitcases) {
        this(coachTravel, seatNumber, isForAdult);
        this.numberOfSmallSuitcases = numberOfSmallSuitcases;
        this.numberOfLargeSuitcases = numberOfLargeSuitcases;
    }

    protected BigDecimal getPriceForLuggage() {
        BigDecimal priceForLargeSuitcase = new BigDecimal(PRICE_FOR_LARGE_SUITCASE);
        BigDecimal priceForSmallSuitcase = new BigDecimal(PRICE_FOR_SMALL_SUITCASE);
        return priceForLargeSuitcase.multiply(new BigDecimal(numberOfLargeSuitcases))
                .add(priceForSmallSuitcase.multiply(new BigDecimal(numberOfSmallSuitcases)));
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
