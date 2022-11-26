package transport;

import java.math.BigDecimal;
import java.util.Objects;

public class Seat {

    private int number;
    private BigDecimal price;
    private boolean isBooked;

    public Seat() {
    }

    public Seat(int number, BigDecimal price) {
        this.number = number;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String toString() {
        return String.format("Seat: %d", number);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Seat s = (Seat) o;
        return number == s.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
