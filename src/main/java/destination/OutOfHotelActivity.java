package destination;

import enums.Language;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        this.address = address;
        this.isTransportProvided = isTransportProvided;
        this.lengthInHours = lengthInHours;
        this.language = language;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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
        this.lengthInHours = lengthInHours;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String toString() {
        return String.format("%s Address: %s Is transport provided? %b Length: %.1f h Lang: %s",
                super.toString(), address, isTransportProvided, lengthInHours, language);
    }
}
