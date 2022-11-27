package bookings;

import java.math.BigDecimal;

public abstract class Booking {
    private boolean isForAdult;

    public Booking() {
    }

    public Booking(boolean isForAdult) {
        this.isForAdult = isForAdult;
    }

    public abstract BigDecimal calculatePrice();

    public boolean isForAdult() {
        return isForAdult;
    }

    public void setForAdult(boolean forAdult) {
        isForAdult = forAdult;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Booking b = (Booking) o;
        return this.isForAdult == b.isForAdult;
    }
}
