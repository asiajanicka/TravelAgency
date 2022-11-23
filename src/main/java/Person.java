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

    public Person(String id, String firstName, String lastName, int age) {
        if (id == null) {
            throw new IllegalArgumentException("Person id can't be null");
        }
        if (firstName == null) {
            throw new IllegalArgumentException("First name can't be null");
        }
        if (lastName == null) {
            throw new IllegalArgumentException("Last name can't be null");
        }
        if (age < 1 || age > 100) {
            throw new IllegalArgumentException("Age should be between 1 and 100 inclusive");
        }
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.hotelBookings = new ArrayList<>();
        this.transports = new ArrayList<>();
    }

    public Person(String id, String firstName, String lastName, int age, List<HotelBooking> hotelBookings, List<Transport> transports) {
        this(id, firstName, lastName, age);
        if (hotelBookings == null) {
            throw new IllegalArgumentException("List of hotel booking can't be null");
        }
        if (hotelBookings.size() > 0) {
            throw new IllegalArgumentException("List of hotel booking should have at least one booking");
        }
        if (transports == null) {
            throw new IllegalArgumentException("List of transport booking can't be null");
        }
        if (transports.size() > 0) {
            throw new IllegalArgumentException("List of transport booking should have at least one booking");
        }
        this.hotelBookings = hotelBookings;
        this.transports = transports;
    }

    public void addHotelBooking(HotelBooking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Hotel booking can't be null");
        }
        hotelBookings.add(booking);
    }

    public void addTransport(Transport transport) {
        if (transport == null) {
            throw new IllegalArgumentException("Transport booking can't be null");
        }
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
        if (id == null) {
            throw new IllegalArgumentException("Person id can't be null");
        }
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("First name can't be null");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("Last name can't be null");
        }
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 1 || age > 100) {
            throw new IllegalArgumentException("Age should be between 1 and 100 inclusive");
        }
        this.age = age;
    }

    public List<HotelBooking> getHotelBookings() {
        return hotelBookings;
    }

    public void setHotelBookings(List<HotelBooking> hotelBookings) {
        if (hotelBookings == null) {
            throw new IllegalArgumentException("List of hotel booking can't be null");
        }
        if (hotelBookings.size() > 0) {
            throw new IllegalArgumentException("List of hotel booking should have at least one booking");
        }
        this.hotelBookings = hotelBookings;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        if (transports == null) {
            throw new IllegalArgumentException("List of transport booking can't be null");
        }
        if (transports.size() > 0) {
            throw new IllegalArgumentException("List of transport booking should have at least one booking");
        }
        this.transports = transports;
    }

    public String getPersonInfo() {
        return firstName + " " + lastName + " age: " + age;
    }
}
