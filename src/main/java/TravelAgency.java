import destination.AtHotelActivity;
import destination.Destination;
import destination.OutOfHotelActivity;
import enums.*;
import hotel.Hotel;
import hotel.HotelBooking;
import hotel.Room;
import transport.CoachTravel;
import transport.Flight;
import transport.PlaneSeat;
import transport.Seat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TravelAgency {
    public static void main(String[] args) {

/*      Trip from May 5, 2022 to May 15, 2022
        Destination Spain, Malaga Hotel Holiday Inn
        Family of 4 :
        - John Smith age 26 (double room with Sue, board All inclusive)
        - Sue Smith age 25  (double room with John, board Bed & Bracket)
        - Kate Smith age 17 (single room, full board)
        - Tom Smith age 15 (single room, full board)
        Trip Warsaw -> Malaga
        John and Tom travel by plane
        Flight from Warsaw to Malaga on May 5, 2022
        - John first class seats
        - Tom economy class seats
        Sue and Kate travel by coach
        Coach travel from Warsaw to Malaga on May 5, 2022
        Trip back Malaga -> Warsaw
        Flight from Malaga to Warsaw on May 15, 2022
        - John first class seats
        - Tom economy class seats
        Sue and Kate travel by coach
        Coach travel from Malaga to Warsaw on May 15, 2022
        Family wants to participate in following activities:
        at the hotel: football on the beach & diving in the ocean
        out of hotel: Caminito Del Rey Tour
 */
//        Trip start and end dates
        LocalDate dateFrom = LocalDate.of(2022, 5, 5);
        LocalDate dateTo = LocalDate.of(2022, 5, 15);
        CustomizedTrip trip = new CustomizedTrip(dateFrom, dateTo);

//        Trip participants - Smith family
        Person john = new Person("1", "John", "Smith", 26);
        Person sue = new Person("2", "Sue", "Smith", 25);
        Person kate = new Person("3", "Kate", "Smith", 17);
        Person tom = new Person("4", "Tom", "Smith", 15);
        trip.addParticipant(john);
        trip.addParticipant(sue);
        trip.addParticipant(kate);
        trip.addParticipant(tom);

//       Hotel & Destination
        List<Room> rooms = new ArrayList<>();
        Hotel hotel = new Hotel("Holiday Inn", 4, " Churriana, 29004 Malaga", rooms);
        Destination destination = new Destination(Country.ES, City.MALAGA, hotel);
        trip.addDestination(destination);

        Room doubleRoom = new Room(101, RoomType.DOUBLE, new BigDecimal(200));
        Room singleRoom1 = new Room(102, RoomType.SINGLE, new BigDecimal(100));
        Room singleRoom2 = new Room(103, RoomType.SINGLE, new BigDecimal(100));
        rooms.add(doubleRoom);
        rooms.add(singleRoom1);
        rooms.add(singleRoom2);

//        Booking of hotel room for each person
        HotelBooking holidayInBookingForJohn = new HotelBooking(dateFrom,
                dateTo,
                true,
                hotel.getRooms().get(0),
                BoardType.ALL_INCLUSIVE);
        john.addHotelBooking(holidayInBookingForJohn);

        HotelBooking holidayInBookingForSue = new HotelBooking(dateFrom,
                dateTo,
                true,
                hotel.getRooms().get(0),
                BoardType.BB);
        sue.addHotelBooking(holidayInBookingForSue);

        HotelBooking holidayInBookingForKate = new HotelBooking(dateFrom,
                dateTo,
                false,
                hotel.getRooms().get(1),
                BoardType.FB);
        kate.addHotelBooking(holidayInBookingForKate);

        HotelBooking holidayInBookingForTom = new HotelBooking(dateFrom,
                dateTo,
                false,
                hotel.getRooms().get(2),
                BoardType.FB);
        tom.addHotelBooking(holidayInBookingForTom);

//        TRIP FROM WARSAW TO MALAGA
        LocalDateTime flightDepartureDateWM = LocalDateTime.of(2022, 5, 5, 12, 30);
        LocalDateTime flightArrivalDateWM = LocalDateTime.of(2022, 5, 5, 9, 45);

        LocalDateTime coachDepartureDateWM = LocalDateTime.of(2022, 5, 5, 9, 37);
        LocalDateTime coachArrivalDateWM = LocalDateTime.of(2022, 5, 5, 19, 40);

//        Flight booking for John
        PlaneSeat seatFC1WM = new PlaneSeat(1, PlaneSeatType.FIRST_CLASS, new BigDecimal(1000));
        Flight flightWMForJohn = new Flight(flightDepartureDateWM, flightArrivalDateWM, seatFC1WM,
                City.WARSAW, City.MALAGA, true, PlaneBaggage.CHECKED);
        john.addTransport(flightWMForJohn);

//        Flight booking for Tom
        PlaneSeat seatEC2WM = new PlaneSeat(2, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(500));
        Flight flightWMForTom = new Flight(flightDepartureDateWM, flightArrivalDateWM, seatEC2WM,
                City.WARSAW, City.MALAGA, false, PlaneBaggage.HAND);
        tom.addTransport(flightWMForTom);

//        Coach trip booking for Sue
        Seat seatWM1 = new Seat(2, new BigDecimal(100));
        CoachTravel coachWMForSue = new CoachTravel(coachDepartureDateWM, coachArrivalDateWM, seatWM1,
                City.WARSAW, City.MALAGA, true, 1, 2);
        sue.addTransport(coachWMForSue);

//       Coach trip booking for Kate
        Seat seatWM2 = new Seat(1, new BigDecimal(100));
        CoachTravel coachWMForKate = new CoachTravel(coachDepartureDateWM, coachArrivalDateWM, seatWM2,
                City.WARSAW, City.MALAGA, true, 4, 3);
        kate.addTransport(coachWMForKate);

//        TRIP BACK FROM MALAGA TO WARSAW
        LocalDateTime flightDepartureDateMW = LocalDateTime.of(2022, 5, 15, 11, 30);
        LocalDateTime flightArrivalDateMW = LocalDateTime.of(2022, 5, 15, 9, 45);

        LocalDateTime coachDepartureDateMW = LocalDateTime.of(2022, 5, 15, 8, 37);
        LocalDateTime coachArrivalDateMW = LocalDateTime.of(2022, 5, 15, 18, 40);

//        Flight back booking for John
        PlaneSeat seatFC1MW = new PlaneSeat(1, PlaneSeatType.FIRST_CLASS, new BigDecimal(1000));
        Flight flightMWForJohn = new Flight(flightDepartureDateMW, flightArrivalDateMW, seatFC1MW,
                City.MALAGA, City.WARSAW, true, PlaneBaggage.CHECKED);
        john.addTransport(flightMWForJohn);

//        Flight back booking for Tom
        PlaneSeat seatEC2MW = new PlaneSeat(4, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(500));
        Flight flightMWForTom = new Flight(flightDepartureDateMW, flightArrivalDateMW, seatEC2MW,
                City.MALAGA, City.WARSAW, false, PlaneBaggage.HAND);
        tom.addTransport(flightMWForTom);

//        Coach trip back booking for Sue
        Seat seatMW1 = new Seat(2, new BigDecimal(100));
        CoachTravel coachMWForSue = new CoachTravel(coachDepartureDateMW, coachArrivalDateMW, seatMW1,
                City.WARSAW, City.MALAGA, true, 1, 2);
        sue.addTransport(coachMWForSue);

//       Coach trip back booking for Kate
        Seat seatMW2 = new Seat(1, new BigDecimal(100));
        CoachTravel coachForMWKate = new CoachTravel(coachDepartureDateMW, coachArrivalDateMW, seatMW2,
                City.WARSAW, City.MALAGA, true, 4, 3);
        kate.addTransport(coachForMWKate);

//        Activities at the destination are unnamed
        AtHotelActivity footballMatch = new AtHotelActivity(LocalDateTime.of(2022, 5, 6, 9, 30),
                "Football on the beach", new BigDecimal(10), ActivityType.SAND_FOOTBALL);
        AtHotelActivity diving = new AtHotelActivity(LocalDateTime.of(2022, 5, 7, 10, 45),
                "Diving in the ocean", new BigDecimal(6), ActivityType.DIVING, true);
        OutOfHotelActivity sightseeing = new OutOfHotelActivity(LocalDateTime.of(2022, 5, 7, 8, 30),
                "Caminito Del Rey Tour", new BigDecimal(8), "Caminito Del Rey", true,
                4.5d, Language.ENGLISH);
        destination.addActivity(footballMatch);
        destination.addActivity(diving);
        destination.addActivity(sightseeing);

        trip.printSummary();
    }
}
