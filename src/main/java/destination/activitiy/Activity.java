package destination.activitiy;

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

    @Override
    public String toString() {
        return String.format("%s on %s", name, DateFormat.format(date));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Activity a = (Activity) o;
        boolean dateEquals = (this.date == null && a.date == null) || (this.date != null && this.date.equals(a.date));
        boolean nameEquals = (this.name == null && a.name == null) || (this.name != null && this.name.equals(a.name));
        boolean priceEquals = (this.price == null && a.price == null)
                || (this.price != null && this.price.equals(a.price));
        return dateEquals && nameEquals && priceEquals;
    }

    @Override
    public int hashCode() {
        int nameHash = 0;
        int dateHash = 0;
        int priceHash = 0;
        if (this.name != null) {
            nameHash = name.hashCode();
        }
        if (this.date != null) {
            dateHash = date.hashCode();
        }
        if (this.price != null) {
            priceHash = price.hashCode();
        }
        return 5 * nameHash + 7 * dateHash + 11 * priceHash;
    }
}
