package transport;

import java.math.BigDecimal;

public class Seat {

    private int number;
    private BigDecimal price;

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

    public String toString() {
        return String.format("Seat: %d", number);
    }
}
