package destination;

import enums.City;
import enums.Country;
import hotel.Hotel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class Destination {
    private Country country;
    private City city;
    private Hotel hotel;
    private List<Activity> activities;

    public Destination(){
    }

    public Destination(Country country, City city, Hotel hotel) {
        this.country = country;
        this.city = city;
        this.hotel = hotel;
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public BigDecimal calculateTotalPriceForActivities(){
        return activities.stream().map(a->a.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void printDestinationSummary(){
        System.out.format("DESTINATION: %s %s\n", country, city);
        if(activities.size()>0){
            System.out.format("Activities at %s\n", city);
        }
        activities.stream().forEach(System.out::println);
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
        this.activities = activities;
    }
}
