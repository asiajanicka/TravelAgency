package destination;

import utils.DateFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Activity {
    private LocalDateTime date;
    private String name;
    private BigDecimal price;

    Activity() {
    }

    public Activity(LocalDateTime date, String name, BigDecimal price) {
        this.date = date;
        this.name = name;
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String toString() {
        return String.format("%s on %s", name, DateFormat.format(date));
    }
}
