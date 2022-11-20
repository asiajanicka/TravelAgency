import destination.Destination;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomizedTrip {
    private List<Destination> destinations;
    private List<Person> participants;
    private LocalDate startDate;
    private LocalDate endDate;

    public CustomizedTrip(){
    }

    public CustomizedTrip( LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.destinations = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public CustomizedTrip(List<Destination> destinations, List<Person> participants, LocalDate startDate, LocalDate endDate) {
        this.destinations = destinations;
        this.participants = participants;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addDestination(Destination destination){
        destinations.add(destination);
    }

    public void addParticipant(Person person){
        participants.add(person);
    }

    public void printSummary(){
        System.out.println("TRIP INFO\n---------------------------");
        System.out.format("FROM %tB %te, %tY TO %tB %te, %tY\n",
                startDate, startDate, startDate,
                endDate, endDate, endDate);
        System.out.format("TOTAL COST: € %,.2f\n",calculateTotalPrice());
        System.out.println("\nPARTICIPANTS\n---------------------------");
        participants.stream().forEach(person -> System.out.println(person.getPersonInfo()));
        System.out.println("\nDESTINATIONS\n---------------------------");
        destinations.stream().forEach(Destination::printDestinationSummary);
        System.out.println("\nBOOKINGS\n---------------------------");
        participants.stream().forEach(Person::printBookingsSummary);

    }

    public BigDecimal calculateTotalPrice(){
        BigDecimal priceForActivities = destinations.stream()
                .map(d->d.calculateTotalPriceForActivities())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPrice = priceForActivities.add(
                participants.stream().map(
                        p -> p.calculateTotalCostForHotelBookings().add(p.calculateTotalCostForFlights())
                ).reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        return totalPrice;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Person> participants) {
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
