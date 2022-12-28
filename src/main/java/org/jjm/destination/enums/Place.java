package org.jjm.destination.enums;

public enum Place {
    MALAGA_ES(Country.ES, City.MALAGA),
    WARSAW_PL(Country.PL, City.WARSAW);
    private Country country;
    private City city;

    Place(Country country, City city) {
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
}
