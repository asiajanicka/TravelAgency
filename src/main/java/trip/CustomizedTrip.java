package trip;

import destination.Destination;
import interfaces.IDescribe;
import utils.DateFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomizedTrip implements IDescribe {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Participant> participants;
    private List<Destination> destinations;

    public CustomizedTrip() {
    }

    public CustomizedTrip(List<Destination> destinations, List<Participant> participants,
                          LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.destinations = destinations;
        this.participants = participants;
    }

    public void addDestination(Destination destination) {
        if (destinations == null) {
            destinations = new ArrayList<>();
        }
        destinations.add(destination);
    }

    public void addParticipant(Participant person) {
        if (participants == null) {
            participants = new ArrayList<>();
        }
        participants.add(person);
    }

    public Participant getParticipant(Person person) {
        return participants
                .stream()
                .filter(p -> p.getPerson().equals(person))
                .collect(Collectors.toList())
                .get(0);
    }

    public void printSummary() {
        System.out.println("TRIP INFO\n---------------------------");
        System.out.format("FROM %s TO %s\n",
                DateFormat.format(startDate), DateFormat.format(endDate));
        System.out.format("TOTAL COST: € %,.2f\n", calculateTotalPrice());
        System.out.println("\nPARTICIPANTS\n---------------------------");
        participants.stream().map(p -> p.getPerson()).forEach(System.out::println);
        System.out.println("\nDESTINATIONS\n---------------------------");
        destinations.stream().forEach(System.out::println);
        System.out.println("\nBOOKINGS\n---------------------------");
        participants.stream().forEach(Participant::printSummary);
    }

    public BigDecimal calculateTotalPrice() {
        return participants.stream()
                .map(d -> d.calculateTotalBookingCost())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
