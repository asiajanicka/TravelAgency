package trip;

import bookings.HotelBooking;
import bookings.TransportBooking;
import destination.activitiy.Activity;
import interfaces.IDescribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DateFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participant implements IDescribe {
    private Person person;
    private List<HotelBooking> hotelBookings;
    private List<TransportBooking> transportBookings;
    private List<Activity> activities;
    private static final Logger logger = LogManager.getLogger(Participant.class);

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
        if(hotelBooking.calculatePrice() != null) {
            if (hotelBookings == null) {
                hotelBookings = new ArrayList<>();
            }
            hotelBookings.add(hotelBooking);
        } else {
            logger.error(String.format("Participant %s - can't add hotel booking as price set to null. " +
                    "Possible wrong dates [from %s to %s]", person, DateFormat.format(hotelBooking.getDateFrom()),
                    DateFormat.format(hotelBooking.getDateTo())));
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
        BigDecimal total = hotelBookingsCost.add(transportBookingsCost).add(activitiesBookingsCost);
        logger.debug(String.format("Participant %s - calculated total booking cost [%,.2f] as sum of hotel bookings " +
                        "[%,.2f] & transport bookings [%,.2f] & activities [%,.2f]",
                this.person, total, hotelBookingsCost, transportBookingsCost, activitiesBookingsCost));
        return total;
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
    public String toString() {
        return person.toString();
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