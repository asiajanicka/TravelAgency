package destination.activitiy;

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
                              double lengthInHours) {
        super(date, name, price);
        this.address = address;
        this.isTransportProvided = false;
        this.lengthInHours = lengthInHours;
        this.language = Language.ENGLISH;
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

    @Override
    public String toString() {
        return String.format("%s Address: %s Is transport provided? %b Length: %.1f h Lang: %s Cost: %.2f",
                super.toString(), address, isTransportProvided, lengthInHours, language, getPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        OutOfHotelActivity a = (OutOfHotelActivity) o;
        boolean addressEquals = (this.address == null && a.address == null) || this.address.equals(a.address);
        boolean isTransportProvidedEquals = this.isTransportProvided == a.isTransportProvided;
        boolean lengthInHoursEquals = Double.compare(this.lengthInHours, a.lengthInHours) == 0;
        boolean languageEquals = (this.language == null && a.language == null) || (this.language == a.language);
        return addressEquals && isTransportProvidedEquals && lengthInHoursEquals && languageEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, isTransportProvided, lengthInHours, language);
    }
}
