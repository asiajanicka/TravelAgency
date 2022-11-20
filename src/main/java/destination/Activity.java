package destination;

import enums.ActivityType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Activity {
    private LocalDate date;
    private String name;
    private ActivityType type;
    private BigDecimal price;

    Activity(){
    }

    public Activity(LocalDate date, String name, ActivityType type, BigDecimal price) {
        this.date = date;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String toString(){
        return name;
    }
}
