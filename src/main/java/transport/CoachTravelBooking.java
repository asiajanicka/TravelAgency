package transport;

import java.math.BigDecimal;

public class CoachTravelBooking extends TransportBooking{
    private Seat seat;
    private int numberOfSmallSuitcases;
    private int numberOfLargeSuitcases;

    public CoachTravelBooking(){
    }

    public CoachTravelBooking(Seat seat, boolean isForAdult) {
        super(seat, isForAdult);
        this.seat = seat;
    }

    public CoachTravelBooking(Seat seat, boolean isForAdult, int numberOfSmallSuitcases,
                              int numberOfLargeSuitcases) {
        this(seat, isForAdult);
        this.numberOfSmallSuitcases = numberOfSmallSuitcases;
        this.numberOfLargeSuitcases = numberOfLargeSuitcases;
    }

     protected BigDecimal getPriceForLuggage() {
        BigDecimal priceForLargeSuitcase = new BigDecimal(10);
        BigDecimal priceForSmallSuitcase = new BigDecimal(5);
        return priceForLargeSuitcase.multiply(new BigDecimal(numberOfLargeSuitcases))
                .add(priceForSmallSuitcase.multiply(new BigDecimal(numberOfSmallSuitcases)));
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
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

    public String toString(){
        return String.format("%s Small suitcases: %d, Large suitcases: %d Total price: %.2f",
                super.toString(),
                numberOfLargeSuitcases,
                numberOfSmallSuitcases,
                calculatePrice());
    }
}
