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
        this.number = number;
        this.type = type;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
