package destination;

import utils.DateFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Activity {
    private LocalDateTime date;
    private String name;
    private BigDecimal price;

    Activity() {
    }

    public Activity(LocalDateTime date, String name, BigDecimal price) {
        if (date == null) {
            throw new IllegalArgumentException("Date of activity can't be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name of activity can't be null");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price of activity can't be null");
        }
        this.date = date;
        this.name = name;
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Date of activity can't be null");
        }
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name of activity can't be null");
        }
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price of activity can't be null");
        }
        this.price = price;
    }

    public String toString() {
        return String.format("%s on %s", name, DateFormat.format(date));
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Activity a = (Activity) o;
        boolean dateEquals = this.date.equals(a.date);
        boolean nameEquals = this.name.equals(a.name);
        boolean priceEquals = this.price.equals(a.price);
        return dateEquals && nameEquals && priceEquals;
    }

    public int hashCode() {
        return Objects.hash(date, name, price);
    }
}
