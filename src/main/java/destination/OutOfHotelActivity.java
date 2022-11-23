package destination;

import enums.Language;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class OutOfHotelActivity extends Activity {
    private String address;
    private boolean isTransportProvided;
    private double lengthInHours;
    private Language language;

    public OutOfHotelActivity() {
    }

    public OutOfHotelActivity(LocalDateTime date, String name, BigDecimal price, String address,
                              boolean isTransportProvided, double lengthInHours, Language language) {
        super(date, name, price);
        if (address == null) {
            throw new IllegalArgumentException("Address of activity can't be null");
        }
        if (lengthInHours <= 0) {
            throw new IllegalArgumentException("Length of the trip must be greater than 0");
        }
        this.address = address;
        this.isTransportProvided = isTransportProvided;
        this.lengthInHours = lengthInHours;
        this.language = language;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Address of activity can't be null");
        }
        this.address = address;
    }

    public boolean isTransportProvided() {
        return isTransportProvided;
    }

    public void setTransportProvided(boolean transportProvided) {
        isTransportProvided = transportProvided;
    }

    public double getLengthInHours() {
        return lengthInHours;
    }

    public void setLengthInHours(double lengthInHours) {
        if (lengthInHours <= 0) {
            throw new IllegalArgumentException("Length of the trip must be greater than 0");
        }
        this.lengthInHours = lengthInHours;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        if (language == null) {
            throw new IllegalArgumentException("Language of activity can't be null");
        }
        this.language = language;
    }

    public String toString() {
        return String.format("%s Address: %s Is transport provided? %b Length: %.1f h Lang: %s",
                super.toString(), address, isTransportProvided, lengthInHours, language);
    }

    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        OutOfHotelActivity a = (OutOfHotelActivity) o;
        boolean addressEquals = this.address.equals(a.address);
        boolean isTransportProvidedEquals = (this.isTransportProvided == a.isTransportProvided);
        boolean lengthInHoursEquals = (Double.compare(this.lengthInHours, a.lengthInHours) == 0);
        boolean languageEquals = (this.language == a.language);
        return addressEquals && isTransportProvidedEquals && lengthInHoursEquals && languageEquals;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), address, isTransportProvided, lengthInHours, language);
    }
}
