package transport;

import java.math.BigDecimal;

public class Seat {

    private int number;
    private BigDecimal price;

    public Seat() {
    }

    public Seat(int number, BigDecimal price) {
        if (number <= 0) {
            throw new IllegalArgumentException("Seat number must be greater than 0");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price can't be null");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.number = number;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Seat number must be greater than 0");
        }
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price can't be null");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price;
    }

    public String toString() {
        return String.format("Seat: %d", number);
    }
}
