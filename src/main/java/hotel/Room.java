package hotel;

import enums.RoomType;

import java.math.BigDecimal;
import java.util.Objects;

public class Room {
    private int number;
    private RoomType type;
    private BigDecimal price;

    public Room() {
    }

    public Room(int number, RoomType type, BigDecimal price) {
        if (number <= 0) {
            throw new IllegalArgumentException("Room number must be greater than 0");
        }
        if (type == null) {
            throw new IllegalArgumentException("Room type can't be null");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price can't be null");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.number = number;
        this.type = type;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Room number must be greater than 0");
        }
        this.number = number;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        if (type == null) {
            throw new IllegalArgumentException("Room type can't be null");
        }
        this.type = type;
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
        return String.format("Room number: %d Room type: %s", number, type);
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Room r = (Room) o;
        boolean numberEquals = (this.number == r.number);
        boolean typeEquals = (this.type == r.type);
        boolean priceEquals = this.price.equals(r.price);
        return numberEquals && typeEquals && priceEquals;
    }

    public int hashCode() {
        return Objects.hash(number, type, price);
    }
}
