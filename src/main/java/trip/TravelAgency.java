package trip;

import destination.Destination;
import destination.Place;
import destination.activitiy.Activity;
import destination.activitiy.AtHotelActivity;
import destination.activitiy.OutOfHotelActivity;
import enums.*;
import hotel.Hotel;
import hotel.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import transport.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TravelAgency {
    private List<Destination> destinations;
    private static final Logger logger = LogManager.getLogger(TravelAgency.class);

    public TravelAgency() {
        addDestination(initMalaga());
        logger.debug("Travel Agency - initialized with destination: Malaga ES");
    }

    private Destination initMalaga() {
        Place malaga = new Place(Country.ES, City.MALAGA);

        Room doubleRoom = new Room(101, RoomType.DOUBLE, new BigDecimal(200));
        Room singleRoom1 = new Room(102, RoomType.SINGLE, new BigDecimal(100));
        Room singleRoom2 = new Room(103, RoomType.SINGLE, new BigDecimal(100));
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(doubleRoom);
        rooms.add(singleRoom1);
        rooms.add(singleRoom2);
        Hotel hotelAtMalaga = new Hotel("Holiday Inn", 4, " Churriana, 29004 Malaga", rooms);

        List<Transport> transports = new ArrayList<>();
        PlaneSeat planeSeatWM1 = new PlaneSeat(1, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(100));
        PlaneSeat planeSeatWM2 = new PlaneSeat(2, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(100));
        PlaneSeat planeSeatWM3 = new PlaneSeat(3, PlaneSeatType.FIRST_CLASS, new BigDecimal(200));
        PlaneSeat planeSeatWM4 = new PlaneSeat(4, PlaneSeatType.FIRST_CLASS, new BigDecimal(200));
        ArrayList<Seat> planeSeats = new ArrayList<>();
        planeSeats.add(planeSeatWM1);
        planeSeats.add(planeSeatWM2);
        planeSeats.add(planeSeatWM3);
        planeSeats.add(planeSeatWM4);
        LocalDateTime flightDepartureDateWM = LocalDateTime.of(2022, 5, 5, 9, 30);
        LocalDateTime flightArrivalDateWM = LocalDateTime.of(2022, 5, 5, 12, 45);
        Flight flightWM = new Flight(flightDepartureDateWM, flightArrivalDateWM, City.WARSAW, City.MALAGA,
                planeSeats);
        transports.add(flightWM);

        Seat seatWM1 = new Seat(1, new BigDecimal(3));
        Seat seatWM2 = new Seat(2, new BigDecimal(3));
        Seat seatWM3 = new Seat(3, new BigDecimal(3));
        Seat seatWM4 = new Seat(4, new BigDecimal(3));
        List<Seat> busSeats = new ArrayList<>();
        busSeats.add(seatWM1);
        busSeats.add(seatWM2);
        busSeats.add(seatWM3);
        busSeats.add(seatWM4);
        LocalDateTime busDepartureDateWM = LocalDateTime.of(2022, 5, 5, 9, 30);
        LocalDateTime busArrivalDateWM = LocalDateTime.of(2022, 5, 5, 12, 45);
        CoachTravel coachTravelWM = new CoachTravel(busDepartureDateWM, busArrivalDateWM,
                City.WARSAW, City.MALAGA, busSeats);
        transports.add(coachTravelWM);

        PlaneSeat planeSeatMW1 = new PlaneSeat(1, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(100));
        PlaneSeat planeSeatMW2 = new PlaneSeat(2, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(100));
        PlaneSeat planeSeatMW3 = new PlaneSeat(3, PlaneSeatType.FIRST_CLASS, new BigDecimal(200));
        PlaneSeat planeSeatMW4 = new PlaneSeat(4, PlaneSeatType.FIRST_CLASS, new BigDecimal(200));
        List<Seat> planeSeatsBack = new ArrayList<>();
        planeSeatsBack.add(planeSeatMW1);
        planeSeatsBack.add(planeSeatMW2);
        planeSeatsBack.add(planeSeatMW3);
        planeSeatsBack.add(planeSeatMW4);
        LocalDateTime flightDepartureDateMW = LocalDateTime.of(2022, 5, 15, 9, 30);
        LocalDateTime flightArrivalDateMW = LocalDateTime.of(2022, 5, 15, 12, 45);
        Flight flightMW = new Flight(flightDepartureDateMW, flightArrivalDateMW, City.MALAGA, City.WARSAW, planeSeatsBack);
        transports.add(flightMW);

        Seat seatMW1 = new Seat(1, new BigDecimal(3));
        Seat seatMW2 = new Seat(2, new BigDecimal(3));
        Seat seatMW3 = new Seat(3, new BigDecimal(3));
        Seat seatMW4 = new Seat(4, new BigDecimal(3));
        List<Seat> busSeatsBack = new ArrayList<>();
        busSeatsBack.add(seatMW1);
        busSeatsBack.add(seatMW2);
        busSeatsBack.add(seatMW3);
        busSeatsBack.add(seatMW4);
        LocalDateTime busDepartureDateMW = LocalDateTime.of(2022, 5, 15, 9, 30);
        LocalDateTime busArrivalDateMW = LocalDateTime.of(2022, 5, 15, 12, 45);
        CoachTravel coachTravelMW = new CoachTravel(busDepartureDateMW, busArrivalDateMW, City.MALAGA, City.WARSAW,
                busSeatsBack);
        transports.add(coachTravelMW);

        ArrayList<Activity> activities = new ArrayList<>();
        AtHotelActivity atHotelActivity1 = new AtHotelActivity(
                LocalDateTime.of(2022, 5, 5, 9, 30), "Football Match",
                new BigDecimal(10), ActivityType.SAND_FOOTBALL
        );
        AtHotelActivity atHotelActivity2 = new AtHotelActivity(
                LocalDateTime.of(2022, 5, 6, 9, 30), "Banana Boat Ride",
                new BigDecimal(5), ActivityType.BANANA_BOAT
        );
        AtHotelActivity atHotelActivity3 = new AtHotelActivity(
                LocalDateTime.of(2022, 5, 7, 9, 30), "Beach Volleyball",
                new BigDecimal(6), ActivityType.BEACH_VOLLEYBALL
        );
        activities.add(atHotelActivity1);
        activities.add(atHotelActivity2);
        activities.add(atHotelActivity3);
        OutOfHotelActivity outOfHotelActivity = new OutOfHotelActivity(
                LocalDateTime.of(2022, 5, 5, 12, 45), "Caminito Del Rey Tour",
                new BigDecimal(20), "Caminito Del Rey Main Street", true, 3.5,
                Language.ENGLISH);
        activities.add(outOfHotelActivity);

        return new Destination(malaga, hotelAtMalaga, transports, activities);
    }

    public void addDestination(Destination destination) {
        if (destinations == null) {
            destinations = new ArrayList<>();
        }
        destinations.add(destination);
    }

    public void printDestinations() {
        System.out.println("TRAVEL AGENCY - Available Destinations:");
        destinations.forEach(Destination::printSummary);
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