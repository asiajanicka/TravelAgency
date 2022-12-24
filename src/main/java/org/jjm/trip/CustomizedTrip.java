package org.jjm.trip;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.destination.Destination;
import org.jjm.interfaces.IDescribe;
import org.jjm.utils.DateFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomizedTrip implements IDescribe {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Participant> participants;
    private List<Destination> destinations;
    private static final Logger logger = LogManager.getLogger(CustomizedTrip.class);

    public CustomizedTrip() {
        participants = new ArrayList<>();
        destinations = new ArrayList<>();
        logger.info("Trip - Customized trip - created: empty");
    }

    public CustomizedTrip(List<Destination> destinations, List<Participant> participants,
                          LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.destinations = destinations;
        this.participants = participants;
    }

    public void addDestination(Destination destination) {
        destinations.add(destination);
        logger.info(String.format("Trip - New destination added to the trip: %s", destination));
    }

    public void addParticipant(Participant person) {
        participants.add(person);
        logger.info(String.format("Trip - New participant added to the trip: %s ", person));
    }

    @Override
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
        BigDecimal totalForAll = participants.stream()
                .map(d -> d.calculateTotalBookingCost())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        logger.debug(String.format("Trip - Calculated total cost of trip: %,.2f", totalForAll));
        return totalForAll;
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
        logger.info(String.format("Trip - starts at: %s", DateFormat.format(startDate)));
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        logger.info(String.format("Trip - ends at: %s", DateFormat.format(endDate)));
    }
}
