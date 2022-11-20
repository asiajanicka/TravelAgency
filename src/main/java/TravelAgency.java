import destination.Activity;
import enums.*;
import destination.Destination;
import flight.Flight;
import flight.Seat;
import hotel.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TravelAgency {
    public static void main(String[] args) {

//        Trip from May 5, 2022 to May 15, 2022
//        Destination Spain, Malaga Hotel Holiday Inn
//        Family of 4 :
//        - John Smith age 26 (double room with Sue, board All inclusive)
//        - Sue Smith age 25  (double room with John, board Bed & Bracket)
//        - Kate Smith age 17 (single room, full board)
//        - Tom Smith age 15 (single room, full board)
//        Flight from Warsaw to Malaga on May 5, 2022
//        - John and Sue first class seats
//        - Tom and Kate economy class seats
//        Flight back on May 15, 2022
//        - John and Sue first class seats
//        - Tom and Kate economy class seats
//        Family wants to participate in following activities: sand football, diving, beach volleyball


        LocalDate dateFrom = LocalDate.of(2022, 5, 5);
        LocalDate dateTo = LocalDate.of(2022,5, 15);

        CustomizedTrip trip = new CustomizedTrip(dateFrom, dateTo);

        Person john = new Person("1","John", "Smith", 26);
        Person sue = new Person("2","Sue", "Smith", 25);
        Person kate = new Person("3","Kate", "Smith", 17);
        Person tom = new Person("4","Tom", "Smith", 15);

        trip.addParticipant(john);
        trip.addParticipant(sue);
        trip.addParticipant(kate);
        trip.addParticipant(tom);

       // HOTEL
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

        // FLIGHT FROM WARSAW TO MALAGA
        LocalDate departureDateWM =  LocalDate.of(2022, 5, 5);
        LocalDate arrivalDateWM =  LocalDate.of(2022, 5, 5);

        Seat seatFC1WM = new Seat(1, PlaneSeatType.FIRST_CLASS, new BigDecimal(1000));
        Flight flightWMForJohn = new Flight(departureDateWM, arrivalDateWM, seatFC1WM, City.WARSAW, City.MALAGA, true);
        john.addFlight(flightWMForJohn);

        Seat seatFC2WM = new Seat(2, PlaneSeatType.FIRST_CLASS, new BigDecimal(1000));
        Flight flightWMForSue = new Flight(departureDateWM, arrivalDateWM, seatFC2WM, City.WARSAW, City.MALAGA, true);
        sue.addFlight(flightWMForSue);

        Seat seatEC1WM = new Seat(3, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(500));
        Flight flightWMForKate = new Flight(departureDateWM, arrivalDateWM, seatEC1WM, City.WARSAW, City.MALAGA, false);
        kate.addFlight(flightWMForKate);

        Seat seatEC2WM = new Seat(4, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(500));
        Flight flightWMForTom = new Flight(departureDateWM, arrivalDateWM, seatEC2WM, City.WARSAW, City.MALAGA, false);
        tom.addFlight(flightWMForTom);

        // FLIGHT BACK FROM MALAGA TO WARSAW
        LocalDate departureDateMW =  LocalDate.of(2022, 5, 15);
        LocalDate arrivalDateMW =  LocalDate.of(2022, 5, 15);

        Seat seatFC1MW = new Seat(1, PlaneSeatType.FIRST_CLASS, new BigDecimal(1000));
        Flight flightMWForJohn = new Flight(departureDateMW, arrivalDateWM, seatFC1MW, City.MALAGA, City.WARSAW, true);
        john.addFlight(flightMWForJohn);

        Seat seatFC2MW = new Seat(2, PlaneSeatType.FIRST_CLASS, new BigDecimal(1000));
        Flight flightMWForSue = new Flight(departureDateMW, arrivalDateWM, seatFC2MW, City.MALAGA, City.WARSAW, true);
        sue.addFlight(flightMWForSue);

        Seat seatEC1MW = new Seat(3, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(500));
        Flight flightMWForKate = new Flight(departureDateMW, arrivalDateWM, seatEC1MW, City.MALAGA, City.WARSAW, false);
        kate.addFlight(flightMWForKate);

        Seat seatEC2MW = new Seat(4, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(500));
        Flight flightMWForTom = new Flight(departureDateMW, arrivalDateWM, seatEC2MW, City.MALAGA, City.WARSAW, false);
        tom.addFlight(flightMWForTom);

        // add activities at the destination for whole family
        Activity footballMatch = new Activity(LocalDate.of(2022, 5, 6),
                "Football Match", ActivityType.SAND_FOOTBALL, new BigDecimal(10));
        Activity diving = new Activity(LocalDate.of(2022, 5, 7),
                "Diving in the ocean", ActivityType.DIVING, new BigDecimal(6));
        Activity volleyballMatch = new Activity(LocalDate.of(2022, 5, 7),
                "Volleyball on the beach", ActivityType.BEACH_VOLLEYBALL, new BigDecimal(8));

        destination.addActivity(footballMatch);
        destination.addActivity(diving);
        destination.addActivity(volleyballMatch);

        trip.printSummary();
    }
}
