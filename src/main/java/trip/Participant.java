package trip;

import bookings.HotelBooking;
import bookings.TransportBooking;
import destination.activitiy.Activity;
import interfaces.IDescribe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participant implements IDescribe {
    private Person person;
    private List<HotelBooking> hotelBookings;
    private List<TransportBooking> transportBookings;
    private List<Activity> activities;

    public Participant() {
    }

    public Participant(Person person) {
        this.person = person;
        this.hotelBookings = new ArrayList<>();
        this.transportBookings = new ArrayList<>();
        this.activities = new ArrayList<>();
    }

    public void addTransportBooking(TransportBooking transport) {
        if (transportBookings == null) {
            transportBookings = new ArrayList<>();
        }
        transportBookings.add(transport);
    }

    public void addHotelBooking(HotelBooking hotelBooking) {
        if (hotelBookings == null) {
            hotelBookings = new ArrayList<>();
        }
        if(hotelBooking.getDateFrom().isBefore(hotelBooking.getDateTo())){
            hotelBookings.add(hotelBooking);
        } else {
            // log info
        }
    }

    public void addActivity(Activity activity) {
        if (activities == null) {
            activities = new ArrayList<>();
        }
        activities.add(activity);
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public void printSummary() {
        System.out.format("%s %s\n", person.getFirstName().toUpperCase(), person.getLastName().toUpperCase());
        printTransportBookings();
        printHotelBookings();
        printActivityBookings();
        System.out.println();
    }

    private void printTransportBookings() {
        System.out.println(" * Transport bookings:");
        transportBookings.forEach(System.out::println);
    }

    private void printHotelBookings() {
        System.out.println(" * Hotel bookings:");
        hotelBookings.forEach(System.out::println);
    }

    private void printActivityBookings() {
        System.out.println(" * Activity bookings:");
        activities.forEach(System.out::println);
    }

    public final BigDecimal calculateTotalBookingCost() {
        BigDecimal hotelBookingsCost = hotelBookings
                .stream()
                .map(HotelBooking::calculatePrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal transportBookingsCost = transportBookings
                .stream()
                .map(TransportBooking::calculatePrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal activitiesBookingsCost = activities
                .stream()
                .map(Activity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return hotelBookingsCost.add(transportBookingsCost).add(activitiesBookingsCost);
  //      logger.debug(total calculated cost + partial costs)
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
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Participant p = (Participant) o;
        boolean personEquals = (this.person == null && p.person == null)
                || (this.person != null && this.person.equals(p.person));
        boolean hotelBookingsEquals = (this.hotelBookings == null && p.hotelBookings == null)
                || (this.hotelBookings != null && this.hotelBookings.equals(p.hotelBookings));
        boolean transportBookingsEquals = (this.transportBookings == null && p.transportBookings == null)
                || (this.transportBookings != null && this.transportBookings.equals(p.transportBookings));
        boolean activitiesEquals = (this.activities == null && p.activities == null)
                || (this.activities != null && this.activities.equals(p.activities));
        return personEquals && hotelBookingsEquals && transportBookingsEquals && activitiesEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, hotelBookings, transportBookings, activities);
    }

}
