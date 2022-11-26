import destination.activitiy.Activity;
import hotel.HotelBooking;
import transport.TransportBooking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participant {
    private Person person;
    private List<HotelBooking> hotelBookings;
    private List<TransportBooking> transportBookings;
    private List<Activity> activities;

    private Participant() {
    }

    public Participant(Person person) {
        this.person = person;
        this.hotelBookings = new ArrayList<>();
        this.transportBookings = new ArrayList<>();
        this.activities = new ArrayList<>();
    }

    public void addTransportBooking(TransportBooking transport){
        transportBookings.add(transport);
    }

    public void addHotelBooking(HotelBooking hotel){
        hotelBookings.add(hotel);
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public Person getPerson() {
        return person;
    }

    public void printBookings(){
        System.out.format("%s %s\n", person.getFirstName().toUpperCase(), person.getLastName().toUpperCase());
        printTransportBookings();
        printHotelBookings();
        printActivityBookings();
        System.out.println();
    }

    public void printTransportBookings(){
        System.out.println("Transport bookings:");
        transportBookings.stream().forEach(System.out::println);
    }

    public void printHotelBookings(){
        System.out.println("Hotel bookings:");
        hotelBookings.stream().forEach(System.out::println);
    }

    public void printActivityBookings(){
        System.out.println("Activity bookings:");
        activities.stream().forEach(System.out::println);
    }

    public BigDecimal calculateTotalBookingCost(){
        BigDecimal hotelBookingsCost = hotelBookings.stream().map(p->p.calculatePrice()).reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal transportBookingsCost = transportBookings.stream().map(p->p.calculatePrice()).reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal activitiesBookingsCost = activities.stream().map(p->p.getPrice()).reduce(BigDecimal.ZERO,BigDecimal::add);
        return hotelBookingsCost.add(transportBookingsCost).add(activitiesBookingsCost);
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<HotelBooking> getHotelBookings() {
        return hotelBookings;
    }

    public void setHotelBookings(List<HotelBooking> hotelBookings) {
        this.hotelBookings = hotelBookings;
    }

    public List<TransportBooking> getTransportBookings() {
        return transportBookings;
    }

    public void setTransportBookings(List<TransportBooking> transportBookings) {
        this.transportBookings = transportBookings;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;
        Participant that = (Participant) o;
        return Objects.equals(person, that.person) && Objects.equals(hotelBookings, that.hotelBookings) && Objects.equals(transportBookings, that.transportBookings) && Objects.equals(activities, that.activities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, hotelBookings, transportBookings, activities);
    }
}
