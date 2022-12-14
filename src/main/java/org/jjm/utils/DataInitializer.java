package org.jjm.utils;

import org.jjm.destination.Destination;
import org.jjm.destination.Place;
import org.jjm.destination.activitiy.Activity;
import org.jjm.destination.activitiy.AtHotelActivity;
import org.jjm.destination.activitiy.OutOfHotelActivity;
import org.jjm.enums.*;
import org.jjm.hotel.Hotel;
import org.jjm.hotel.Room;
import org.jjm.transport.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
    public static Destination initMalaga() {
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
        Seat<PlaneSeatType> planeSeatWM1 = new Seat<>(1, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(100));
        Seat<PlaneSeatType> planeSeatWM2 = new Seat<>(2, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(100));
        Seat<PlaneSeatType> planeSeatWM3 = new Seat<>(3, PlaneSeatType.BUSINESS_CLASS, new BigDecimal(200));
        Seat<PlaneSeatType> planeSeatWM4 = new Seat<>(4, PlaneSeatType.FIRST_CLASS, new BigDecimal(200));
        ArrayList<Seat<PlaneSeatType>> planeSeats = new ArrayList<>();
        planeSeats.add(planeSeatWM1);
        planeSeats.add(planeSeatWM2);
        planeSeats.add(planeSeatWM3);
        planeSeats.add(planeSeatWM4);
        LocalDateTime flightDepartureDateWM = LocalDateTime.of(2022, 5, 5, 9, 30);
        LocalDateTime flightArrivalDateWM = LocalDateTime.of(2022, 5, 5, 12, 45);
        Flight flightWM = new Flight(flightDepartureDateWM, flightArrivalDateWM, City.WARSAW, City.MALAGA,
                planeSeats);
        transports.add(flightWM);

        Seat<CoachSeatType> seatWM1 = new Seat<>(1, CoachSeatType.AISLE, new BigDecimal(3));
        Seat<CoachSeatType> seatWM2 = new Seat<>(2, CoachSeatType.MIDDLE, new BigDecimal(3));
        Seat<CoachSeatType> seatWM3 = new Seat<>(3, CoachSeatType.WINDOW, new BigDecimal(3));
        Seat<CoachSeatType> seatWM4 = new Seat<>(4, CoachSeatType.MIDDLE, new BigDecimal(3));
        List<Seat<CoachSeatType>> busSeats = new ArrayList<>();
        busSeats.add(seatWM1);
        busSeats.add(seatWM2);
        busSeats.add(seatWM3);
        busSeats.add(seatWM4);
        LocalDateTime busDepartureDateWM = LocalDateTime.of(2022, 5, 5, 9, 30);
        LocalDateTime busArrivalDateWM = LocalDateTime.of(2022, 5, 5, 12, 45);
        CoachTravel coachTravelWM = new CoachTravel(busDepartureDateWM, busArrivalDateWM,
                City.WARSAW, City.MALAGA, busSeats);
        transports.add(coachTravelWM);

        Seat<PlaneSeatType> planeSeatMW1 = new Seat<>(1, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(100));
        Seat<PlaneSeatType> planeSeatMW2 = new Seat<>(2, PlaneSeatType.ECONOMY_CLASS, new BigDecimal(100));
        Seat<PlaneSeatType> planeSeatMW3 = new Seat<>(3, PlaneSeatType.FIRST_CLASS, new BigDecimal(200));
        Seat<PlaneSeatType> planeSeatMW4 = new Seat<>(4, PlaneSeatType.FIRST_CLASS, new BigDecimal(200));
        List<Seat<PlaneSeatType>> planeSeatsBack = new ArrayList<>();
        planeSeatsBack.add(planeSeatMW1);
        planeSeatsBack.add(planeSeatMW2);
        planeSeatsBack.add(planeSeatMW3);
        planeSeatsBack.add(planeSeatMW4);
        LocalDateTime flightDepartureDateMW = LocalDateTime.of(2022, 5, 15, 9, 30);
        LocalDateTime flightArrivalDateMW = LocalDateTime.of(2022, 5, 15, 12, 45);
        Flight flightMW = new Flight(flightDepartureDateMW, flightArrivalDateMW, City.MALAGA, City.WARSAW, planeSeatsBack);
        transports.add(flightMW);

        Seat<CoachSeatType> seatMW1 = new Seat<>(1, CoachSeatType.MIDDLE, new BigDecimal(3));
        Seat<CoachSeatType> seatMW2 = new Seat<>(2, CoachSeatType.WINDOW, new BigDecimal(3));
        Seat<CoachSeatType> seatMW3 = new Seat<>(3, CoachSeatType.AISLE,  new BigDecimal(3));
        Seat<CoachSeatType> seatMW4 = new Seat<>(4, CoachSeatType.WINDOW, new BigDecimal(3));
        List<Seat<CoachSeatType>> busSeatsBack = new ArrayList<>();
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
}
