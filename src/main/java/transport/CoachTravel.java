package transport;

import enums.City;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CoachTravel extends Transport {
    private int numberOfSmallSuitcases;
    private int numberOfLargeSuitcases;

    public CoachTravel() {
    }

    public CoachTravel(LocalDateTime dateDeparture, LocalDateTime dateArrival, Seat seat, City cityFrom, City cityTo,
                       boolean isForAdult, int numberOfSmallSuitcases, int numberOfLargeSuitcases) {
        super(dateDeparture, dateArrival, seat, cityFrom, cityTo, isForAdult);
        if (numberOfLargeSuitcases < 0 || numberOfLargeSuitcases > 4) {
            throw new IllegalArgumentException("Number of large suitcases should be between 0 and 4 inclusive");
        }
        if (numberOfSmallSuitcases < 0 || numberOfSmallSuitcases > 6) {
            throw new IllegalArgumentException("Number of small suitcases should be between 0 and 6 inclusive");
        }
        this.numberOfSmallSuitcases = numberOfSmallSuitcases;
        this.numberOfLargeSuitcases = numberOfLargeSuitcases;
    }

    public BigDecimal calculatePrice() {
        return super.calculatePrice().add(calculatePriceForLuggage());
    }

    private BigDecimal calculatePriceForLuggage() {
        BigDecimal priceForLargeSuitcase = new BigDecimal(10);
        BigDecimal priceForSmallSuitcase = new BigDecimal(5);
        return priceForLargeSuitcase.multiply(new BigDecimal(numberOfLargeSuitcases))
                .add(priceForSmallSuitcase.multiply(new BigDecimal(numberOfSmallSuitcases)));
    }

    public int getNumberOfSmallSuitcases() {
        return numberOfSmallSuitcases;
    }

    public void setNumberOfSmallSuitcases(int numberOfSmallSuitcases) {
        if (numberOfSmallSuitcases < 0 || numberOfSmallSuitcases > 6) {
            throw new IllegalArgumentException("Number of small suitcases should be between 0 and 6 inclusive");
        }
        this.numberOfSmallSuitcases = numberOfSmallSuitcases;
    }

    public int getNumberOfLargeSuitcases() {
        return numberOfLargeSuitcases;
    }

    public void setNumberOfLargeSuitcases(int numberOfLargeSuitcases) {
        if (numberOfLargeSuitcases < 0 || numberOfLargeSuitcases > 4) {
            throw new IllegalArgumentException("Number of large suitcases should be between 0 and 4 inclusive");
        }
        this.numberOfLargeSuitcases = numberOfLargeSuitcases;
    }

    public String toString() {
        return String.format("Coach travel: %s Small suitcases: %d, Large suitcases: %d",
                super.toString(), numberOfSmallSuitcases, numberOfLargeSuitcases);
    }
}
