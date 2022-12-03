package destination;

import destination.activitiy.Activity;
import enums.City;
import enums.TransportType;
import exceptions.NoActivityException;
import exceptions.NoTransportException;
import hotel.Hotel;
import interfaces.IDescribe;
import transport.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Destination implements IDescribe {
    private Place place;
    private Hotel hotel;
    private List<Transport> transports;
    private List<Activity> activities;

    public Destination() {
    }

    public Destination(Place place, Hotel hotel, List<Transport> transports) {
        this.place = place;
        this.transports = transports;
        this.hotel = hotel;
        this.activities = new ArrayList<>();
    }

    public Destination(Place place, Hotel hotel, List<Transport> transports, List<Activity> activities) {
        this(place, hotel, transports);
        this.activities = activities;
    }

    public Activity findActivity(String name) throws NoActivityException {
        return activities.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(()-> new NoActivityException("There is no such activity available at hotel/city"));
    }

    public void addActivity(Activity activity) {
        if (activities == null) {
            activities = new ArrayList<>();
        }
        activities.add(activity);
    }

    public void addTransport(Transport transport) {
        if (transports == null) {
            transports = new ArrayList<>();
        }
        transports.add(transport);
    }

    @Override
    public void printSummary() {
        System.out.format("--> %s\n", this);
        if (transports.size() > 0) {
            System.out.format(" * Available TRANSPORTS to %s and BACK:\n", place.getCity());
            transports.forEach(System.out::println);
        }
        if (activities.size() > 0) {
            System.out.format(" * Available ACTIVITIES at %s:\n", place.getCity());
            for (Activity activity : activities) {
                System.out.println(activity);
            }
        }
    }

    public Transport findTransport(City cityFrom, City cityTo, TransportType type) throws NoTransportException {
        return transports.stream().filter(p -> p.getType().equals(type)
                && p.getCityFrom().equals(cityFrom)
                && p.getCityTo().equals(cityTo)).
                findFirst()
                .orElseThrow(()-> new NoTransportException(String.format("There is no %s transport available",
                        type.toString().toLowerCase())));
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

    @Override
    public String toString() {
        return String.format("%s %s %s", place.getCity(), place.getCountry(), hotel);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Destination d = (Destination) o;
        boolean placeEquals = (this.place == null && d.place == null)
                || ((this.place != null && this.place == d.place));
        boolean transportsEquals = (this.transports == null && d.transports == null)
                || ((this.transports != null && this.transports.equals(d.transports)));
        boolean hotelEquals = (this.hotel == null && d.hotel == null)
                || (this.hotel != null && this.hotel.equals(d.hotel));
        boolean activitiesEquals = (this.activities == null && d.activities == null)
                || (this.activities != null && this.activities.equals(d.activities));
        return placeEquals && transportsEquals && hotelEquals && activitiesEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, transports, hotel, activities);
    }
}
