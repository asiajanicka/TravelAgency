package destination;

import enums.City;
import enums.Country;
import hotel.Hotel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Destination {
    private Country country;
    private City city;
    private Hotel hotel;
    private List<Activity> activities;

    public Destination() {
    }

    public Destination(Country country, City city, Hotel hotel) {
        if (country == null) {
            throw new IllegalArgumentException("Country can't be null");
        }
        if (city == null) {
            throw new IllegalArgumentException("City can't be null");
        }
        this.country = country;
        this.city = city;
        this.hotel = hotel;
        this.activities = new ArrayList<>();
    }

    public Destination(Country country, City city, Hotel hotel, ArrayList<Activity> activities) {
        this(country, city, hotel);
        if (activities == null) {
            throw new IllegalArgumentException("List of activities can't be null");
        }
        if (activities.size() > 0) {
            throw new IllegalArgumentException("List of activities should have at least one activity");
        }
        this.country = country;
        this.city = city;
        this.hotel = hotel;
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity can't be null");
        }
        activities.add(activity);
    }

    public BigDecimal calculateTotalPriceForActivities() {
        if (activities.size() == 0) return BigDecimal.ZERO;
        return activities.stream().map(a -> a.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void printDestinationSummary() {
        System.out.format("DESTINATION: %s %s\n", country, city);
        if (activities.size() > 0) {
            System.out.format("Activities at %s\n", city);
        }
        activities.stream().forEach(System.out::println);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country can't be null");
        }
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City can't be null");
        }
        this.city = city;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        if (activities == null) {
            throw new IllegalArgumentException("List of activities can't be null");
        }
        if (activities.size() > 0) {
            throw new IllegalArgumentException("List of activities should have at least one activity");
        }
        this.activities = activities;
    }

    public String toString() {
        return String.format("%s %s %s", city, country, hotel);
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Destination d = (Destination) o;
        boolean countryEquals = (this.country == d.country);
        boolean cityEquals = (this.city == d.city);
        boolean hotelEquals = ((this.hotel == null && d.hotel == null) || this.hotel.equals(d.hotel));
        boolean activitiesEquals = this.activities.equals(d.activities);
        return countryEquals && cityEquals && hotelEquals && activitiesEquals;
    }

    public int hashCode() {
        return Objects.hash(country, city, hotel, activities);
    }
}
