package destination;

import enums.City;
import enums.Country;

import java.util.Objects;

public class Place {
    private Country country;
    private City city;

    public Place() {
    }

    public Place(Country country, City city) {
        this.country = country;
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("Place: %s in %s", city, country);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Place p = (Place) o;
        boolean countryEquals = this.country == p.country;
        boolean cityEquals = this.city == p.city;
        return countryEquals && cityEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city);
    }
}
