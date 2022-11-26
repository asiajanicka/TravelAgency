package destination;

import destination.activitiy.Activity;
import enums.City;
import enums.TransportType;
import hotel.Hotel;
import transport.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Destination {
    private Place place;
    private Hotel hotel;
    private List<Transport> transports;
    private List<Activity> activities;

    public Destination() {
    }

    public Destination(Place place, Hotel hotel, ArrayList<Transport> transports) {
        this.place = place;
        this.transports = transports;
        this.hotel = hotel;
        this.activities = new ArrayList<>();
    }

    public Destination(Place place, Hotel hotel, ArrayList<Transport> transports, ArrayList<Activity> activities) {
        this(place, hotel, transports);
        this.activities = activities;
    }

    public Activity findActivity(String name) {
        return activities.stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList()).get(0);
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void addTransport(Transport transport) {
        transports.add(transport);
    }

    public void printDestinationSummary() {
        System.out.format("DESTINATION: %s\n", this);
        if (transports.size() > 0) {
            System.out.format("Available TRANSPORTS on way to %s and back\n", place.getCity());
            transports.stream().forEach(System.out::println);
        }
        if (activities.size() > 0) {
            System.out.format("Available ACTIVITIES at %s\n", place.getCity());
            activities.stream().forEach(System.out::println);
        }
    }

    public Transport findTransport(City cityFrom, City cityTo, TransportType type) {
        return transports.stream().filter(p -> p.getType().equals(type)
                && p.getCityFrom().equals(cityFrom)
                && p.getCityTo().equals(cityTo)).collect(Collectors.toList()).get(0);
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
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

    public String toString() {
        return String.format("%s %s %s", place.getCity(), place.getCountry(), hotel);
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Destination d = (Destination) o;
        boolean placeEquals = (this.place == null && d.place == null) || (this.place == d.place);
        boolean transportsEquals = (this.transports == null && d.transports == null)
                || (this.transports.equals(d.transports));
        boolean hotelEquals = (this.hotel == null && d.hotel == null) || this.hotel.equals(d.hotel);
        boolean activitiesEquals = this.activities.equals(d.activities);
        return placeEquals && transportsEquals && hotelEquals && activitiesEquals;
    }

    public int hashCode() {
        return Objects.hash(place, transports, hotel, activities);
    }
}
