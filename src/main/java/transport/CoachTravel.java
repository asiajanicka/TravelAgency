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
        this.numberOfSmallSuitcases = numberOfSmallSuitcases;
    }

    public int getNumberOfLargeSuitcases() {
        return numberOfLargeSuitcases;
    }

    public void setNumberOfLargeSuitcases(int numberOfLargeSuitcases) {
        this.numberOfLargeSuitcases = numberOfLargeSuitcases;
    }

    public String toString() {
        return String.format("Coach travel: %s Small suitcases: %d, Large suitcases: %d",
                super.toString(), numberOfSmallSuitcases, numberOfLargeSuitcases);
    }
}
