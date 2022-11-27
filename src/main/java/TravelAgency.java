import destination.Destination;
import destination.Place;
import destination.activitiy.Activity;
import destination.activitiy.AtHotelActivity;
import destination.activitiy.OutOfHotelActivity;
import enums.*;
import hotel.Hotel;
import hotel.Room;
import transport.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TravelAgency {
    private List<Destination> destinations;

    public TravelAgency() {
        addDestination(initMalaga());
    }

    private Destination initMalaga() {
//       * create a place
        Place malaga = new Place(Country.ES, City.MALAGA);

//       * build a hotel
        Room doubleRoom = new Room(101, RoomType.DOUBLE, new BigDecimal(200));
        Room singleRoom1 = new Room(102, RoomType.SINGLE, new BigDecimal(100));
        Room singleRoom2 = new Room(103, RoomType.SINGLE, new BigDecimal(100));
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(doubleRoom);
        rooms.add(singleRoom1);
        rooms.add(singleRoom2);
        Hotel hotelAtMalaga = new Hotel("Holiday Inn", 4, " Churriana, 29004 Malaga", rooms);

//        * create a list of possible ways of transport from Warsaw to Malaga
//        by plane
        List<Transport> transports = new ArrayList<>();
        PlaneSeat planeSeat1 = new PlaneSeat(1, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(100));
        PlaneSeat planeSeat2 = new PlaneSeat(2, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(100));
        PlaneSeat planeSeat3 = new PlaneSeat(3, PlaneSeatType.FIRST_CLASS, new BigDecimal(200));
        PlaneSeat planeSeat4 = new PlaneSeat(4, PlaneSeatType.FIRST_CLASS, new BigDecimal(200));
        ArrayList<Seat> planeSeats = new ArrayList<>();
        planeSeats.add(planeSeat1);
        planeSeats.add(planeSeat2);
        planeSeats.add(planeSeat3);
        planeSeats.add(planeSeat4);
        LocalDateTime flightDepartureDateWM = LocalDateTime.of(2022, 5, 5, 9, 30);
        LocalDateTime flightArrivalDateWM = LocalDateTime.of(2022, 5, 5, 12, 45);
        Flight flightWM = new Flight(flightDepartureDateWM, flightArrivalDateWM, City.WARSAW, City.MALAGA, planeSeats);
        transports.add(flightWM);
//        by bus
        Seat seat1 = new Seat(1, new BigDecimal(3));
        Seat seat2 = new Seat(2, new BigDecimal(3));
        ArrayList<Seat> busSeats = new ArrayList<>();
        busSeats.add(seat1);
        busSeats.add(seat2);
        LocalDateTime busDepartureDateWM = LocalDateTime.of(2022, 5, 5, 9, 30);
        LocalDateTime busArrivalDateWM = LocalDateTime.of(2022, 5, 5, 12, 45);
        CoachTravel coachTravelWM = new CoachTravel(busDepartureDateWM, busArrivalDateWM,
                City.WARSAW, City.MALAGA, busSeats);
        transports.add(coachTravelWM);

//        * create a list of possible ways of transport BACK from Warsaw to Malaga
//        by plane
        LocalDateTime flightDepartureDateMW = LocalDateTime.of(2022, 5, 15, 9, 30);
        LocalDateTime flightArrivalDateMW = LocalDateTime.of(2022, 5, 15, 12, 45);
        Flight flightMW = new Flight(flightDepartureDateMW, flightArrivalDateMW, City.MALAGA, City.WARSAW, planeSeats);
        transports.add(flightMW);
//        by bus
        LocalDateTime busDepartureDateMW = LocalDateTime.of(2022, 5, 15, 9, 30);
        LocalDateTime busArrivalDateMW = LocalDateTime.of(2022, 5, 15, 12, 45);
        CoachTravel coachTravelMW = new CoachTravel(busDepartureDateMW, busArrivalDateMW,
                City.MALAGA, City.WARSAW, busSeats);
        transports.add(coachTravelMW);

//       * create a list of available activities
        ArrayList<Activity> activities = new ArrayList<>();
        AtHotelActivity atHotelActivity1 = new AtHotelActivity(
                LocalDateTime.of(2022, 5, 5, 9, 30),
                "Football Match",
                new BigDecimal(10),
                ActivityType.SAND_FOOTBALL
        );
        AtHotelActivity atHotelActivity2 = new AtHotelActivity(
                LocalDateTime.of(2022, 5, 6, 9, 30),
                "Banana Boat Ride",
                new BigDecimal(5),
                ActivityType.BANANA_BOAT
        );
        AtHotelActivity atHotelActivity3 = new AtHotelActivity(
                LocalDateTime.of(2022, 5, 7, 9, 30),
                "Beach Volleyball",
                new BigDecimal(6),
                ActivityType.BEACH_VOLLEYBALL
        );
        activities.add(atHotelActivity1);
        activities.add(atHotelActivity2);
        activities.add(atHotelActivity3);
        OutOfHotelActivity outOfHotelActivity = new OutOfHotelActivity(
                LocalDateTime.of(2022, 5, 5, 12, 45),
                "Caminito Del Rey Tour",
                new BigDecimal(20),
                "Caminito Del Rey Main Street",
                true,
                3.5,
                Language.ENGLISH);
        activities.add(outOfHotelActivity);

        return new Destination(malaga, hotelAtMalaga, transports, activities);
    }

    public void addDestination(Destination destination) {
        if (destinations == null){
            destinations = new ArrayList<>();
        }
        destinations.add(destination);
    }

    public void printDestinations() {
        System.out.println("TRAVEL AGENCY - Available Destinations:");
        destinations.forEach(Destination::printDestinationSummary);
        System.out.println();
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    @Override
    public String toString() {
        return String.format("Travel Agency Destinations: %s", destinations);
    }
}
