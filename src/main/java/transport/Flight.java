package transport;

import enums.City;
import enums.TransportType;
import utils.DateFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Flight extends Transport {

    private List<PlaneSeat> seats;

   public Flight(){
   }

    public Flight(LocalDateTime dateDeparture, LocalDateTime dateArrival, City cityFrom, City cityTo, List<PlaneSeat> seats) {
        super(dateDeparture, dateArrival, cityFrom, cityTo, TransportType.PLANE);
        this.seats = seats;
    }

    public LocalDateTime getBoardingTime() {
        return getDateDeparture().minusHours(2);
    }

    @Override
    public Seat findSeat(int num) {
       return seats.stream().filter(p->p.getNumber() == num).collect(Collectors.toList()).get(0);
    }

    public List<PlaneSeat> getSeats() {
        return seats;
    }

    public void setSeats(List<PlaneSeat> seats) {
        this.seats = seats;
    }

    public String toString() {
        return String.format("Flight: %s Boarding time: %s",
                super.toString(), DateFormat.format(getBoardingTime()));
    }
}

