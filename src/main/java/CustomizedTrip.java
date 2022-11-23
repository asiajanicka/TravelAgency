import destination.Destination;
import utils.DateFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomizedTrip {
    private List<Destination> destinations;
    private List<Person> participants;
    private LocalDate startDate;
    private LocalDate endDate;

    public CustomizedTrip() {
    }

    public CustomizedTrip(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date can't be null");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("End date can't be null");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Start date can't be before end date");
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.destinations = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public CustomizedTrip(List<Destination> destinations, List<Person> participants,
                          LocalDate startDate, LocalDate endDate) {
        this(startDate, endDate);
        if (destinations == null) {
            throw new IllegalArgumentException("List of destinations can't be null");
        }
        if (destinations.size() > 0) {
            throw new IllegalArgumentException("List of destinations should have at least one destination");
        }
        if (participants == null) {
            throw new IllegalArgumentException("List of participants can't be null");
        }
        if (participants.size() > 0) {
            throw new IllegalArgumentException("List of participants should have at least one participant");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addDestination(Destination destination) {
        if (destination == null) {
            throw new IllegalArgumentException("Destination can't be null");
        }
        destinations.add(destination);
    }

    public void addParticipant(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Participant can't be null");
        }
        participants.add(person);
    }

    public void printSummary() {
        System.out.println("TRIP INFO\n---------------------------");
        System.out.format("FROM %s TO %s\n",
                DateFormat.format(startDate), DateFormat.format(endDate));
        System.out.format("TOTAL COST: € %,.2f\n", calculateTotalPrice());
        System.out.println("\nPARTICIPANTS\n---------------------------");
        participants.stream().forEach(person -> System.out.println(person.getPersonInfo()));
        System.out.println("\nDESTINATIONS\n---------------------------");
        destinations.stream().forEach(Destination::printDestinationSummary);
        System.out.println("\nBOOKINGS\n---------------------------");
        participants.stream().forEach(Person::printBookingsSummary);
    }

    public BigDecimal calculateTotalPrice() {
        BigDecimal priceForActivities = destinations.stream()
                .map(d -> d.calculateTotalPriceForActivities())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPrice = priceForActivities.add(
                participants.stream()
                        .map(p -> p.calculateTotalCostForHotelBookings().add(p.calculateTotalCostForTransport()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
        return totalPrice;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        if (destinations == null) {
            throw new IllegalArgumentException("List of destinations can't be null");
        }
        if (destinations.size() > 0) {
            throw new IllegalArgumentException("List of destinations should have at least one destination");
        }
        this.destinations = destinations;
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Person> participants) {
        if (participants == null) {
            throw new IllegalArgumentException("List of participants can't be null");
        }
        if (participants.size() > 0) {
            throw new IllegalArgumentException("List of participants should have at least one participant");
        }
        this.participants = participants;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date can't be null");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Start date can't be before end date");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate == null) {
            throw new IllegalArgumentException("End date can't be null");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Start date can't be before end date");
        }
        this.endDate = endDate;
    }
}
