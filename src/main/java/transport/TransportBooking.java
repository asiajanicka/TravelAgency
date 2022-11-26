package transport;

import java.math.BigDecimal;

public abstract class TransportBooking {
    private Seat seat;
    private boolean isForAdult;

    TransportBooking(){};

    public TransportBooking(Seat seat, boolean isForAdult) {
        this.seat = seat;
        this.isForAdult = isForAdult;
    }

    public BigDecimal getPriceForSeat() {
       return isForAdult() ? seat.getPrice() : seat.getPrice().divide(new BigDecimal(2));
    }

    protected abstract BigDecimal getPriceForLuggage();

    public BigDecimal calculatePrice() {
        return getPriceForSeat().add(getPriceForLuggage());
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public boolean isForAdult() {
        return isForAdult;
    }

    public void setForAdult(boolean forAdult) {
        isForAdult = forAdult;
    }

    public String toString(){
        return String.format("Seat %d Is for adult? %b", seat.getNumber(), isForAdult);
    }
}
