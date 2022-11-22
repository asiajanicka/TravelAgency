import hotel.HotelBooking;
import transport.Transport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private List<HotelBooking> hotelBookings;
    private List<Transport> transports;

    public Person() {
    }

    public Person(String id, String firstName, String lastName, int age, List<HotelBooking> hotelBookings, List<Transport> transports) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.hotelBookings = hotelBookings;
        this.transports = transports;
    }

    public Person(String id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.hotelBookings = new ArrayList<>();
        this.transports = new ArrayList<>();
    }

    public void addHotelBooking(HotelBooking booking) {
        hotelBookings.add(booking);
    }

    public void addTransport(Transport transport) {
        transports.add(transport);
    }

    public void printBookingsSummary() {
        System.out.println("Bookings for: " + firstName.toUpperCase() + " " + lastName.toUpperCase());
        hotelBookings.stream().forEach(System.out::println);
        transports.stream().forEach(System.out::println);
    }

    public BigDecimal calculateTotalCostForHotelBookings() {
        return hotelBookings.stream().map(p -> p.calculatePrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateTotalCostForTransport() {
        return transports.stream().map(p -> p.calculatePrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<HotelBooking> getHotelBookings() {
        return hotelBookings;
    }

    public void setHotelBookings(List<HotelBooking> hotelBookings) {
        this.hotelBookings = hotelBookings;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    public String getPersonInfo() {
        return firstName + " " + lastName + " age: " + age;
    }
}
