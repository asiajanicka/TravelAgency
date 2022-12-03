import bookings.CoachTravelBooking;
import bookings.FlightBooking;
import bookings.HotelBooking;
import destination.Destination;
import enums.BoardType;
import enums.City;
import enums.PlaneBaggage;
import enums.TransportType;
import exceptions.NoActivityException;
import exceptions.NoPlacementAvailableException;
import exceptions.NoPlacementException;
import exceptions.NoTransportException;
import hotel.Room;
import interfaces.IBook;
import transport.PlaneSeat;
import transport.Seat;
import transport.Transport;
import trip.CustomizedTrip;
import trip.Participant;
import trip.Person;
import trip.TravelAgency;

import java.time.LocalDate;

public class TripBooking {
    public static void main(String[] args) {
/*      Trip from May 5th 2022 to May 15th 2022
        Destination Spain, Malaga Hotel Holiday Inn
        Family of 4 :
        - John Smith age 46 (double room with Sue, board All inclusive)
        - Sue Smith age 45  (double room with John, board Bed & Bracket)
        - Kate Smith age 17 (single room, full board)
        - Tom Smith age 15 (single room, full board)
        Trip Warsaw -> Malaga
        John and Sue travel by plane on May 5th 2022
        Kate and Tom travel by bus on May 5th 2022
        Trip back Malaga -> Warsaw
        John and Sue travel by plane on May 15th 2022
        Kate and Tom travel by bus on May 15th 2022
        Family wants to participate in following activities:
        John: Football Match
        Sue: Banana Boat Ride & Caminito Del Rey Tour
        Kate: Beach Volleyball
        Tom: Football Match
*/

//        initialize travel agency with possible destinations (currently only one destination - Malaga)
        TravelAgency travelAgency = new TravelAgency();
        travelAgency.printDestinations();

//        start to create a customized trip
        CustomizedTrip myTrip = new CustomizedTrip();

//        choose destination available at the travel agency
        Destination malagaEs = travelAgency.getDestinations().get(0);
        myTrip.addDestination(malagaEs);

//        set start and end dates of the trip
        LocalDate startDate = LocalDate.of(2022, 5, 5);
        LocalDate endDate = LocalDate.of(2022, 5, 15);
        myTrip.setStartDate(startDate);
        myTrip.setEndDate(endDate);

//        create participants of the trip
        Person john = new Person("1", "John", "Smith", 46);
        Person sue = new Person("2", "Sue", "Smith", 45);
        Person kate = new Person("3", "Kate", "Smith", 17);
        Person tom = new Person("4", "Tom", "Smith", 15);

        myTrip.addParticipant(new Participant(john));
        myTrip.addParticipant(new Participant(sue));
        myTrip.addParticipant(new Participant(kate));
        myTrip.addParticipant(new Participant(tom));

        try {
            malagaEs.getHotel().find(101).book();
            HotelBooking hotelBookingJohn = new HotelBooking(startDate, endDate, malagaEs.getHotel(), true,
                    (Room) malagaEs.getHotel().find(101), BoardType.ALL_INCLUSIVE);
            myTrip.getParticipant(john).addHotelBooking(hotelBookingJohn);
            HotelBooking hotelBookingSue = new HotelBooking(startDate, endDate, malagaEs.getHotel(), true,
                    (Room) malagaEs.getHotel().find(101), BoardType.BB);
            myTrip.getParticipant(sue).addHotelBooking(hotelBookingSue);
        } catch (NoPlacementException e) {
            throw new RuntimeException(e);
        }

        try {
            malagaEs.getHotel().find(102).book(); // book single room for Kate
            HotelBooking hotelBookingKate = new HotelBooking(startDate, endDate, malagaEs.getHotel(), false,
                    (Room) malagaEs.getHotel().find(102), BoardType.FB);
            myTrip.getParticipant(kate).addHotelBooking(hotelBookingKate);
        } catch (NoPlacementException e) {
            throw new RuntimeException(e);
        }

        try {
            malagaEs.getHotel().find(103).book(); // book single room for Tom
            HotelBooking hotelBookingTom = new HotelBooking(startDate, endDate, malagaEs.getHotel(), false,
                    (Room) malagaEs.getHotel().find(103), BoardType.FB);
            myTrip.getParticipant(tom).addHotelBooking(hotelBookingTom);
        } catch (NoPlacementException e) {
            throw new RuntimeException(e);
        }

//        create transport bookings for each participant using findFirstAvailable method
//        * flight to Malaga booking
        try {
            Transport flightToMalaga = malagaEs.findTransport(City.WARSAW, City.MALAGA, TransportType.PLANE);
            try {
                IBook seatToMalagaForJohn = flightToMalaga.findFirstAvailable();
                seatToMalagaForJohn.book();
                FlightBooking flightBookingWMJohn = new FlightBooking(flightToMalaga, (PlaneSeat) seatToMalagaForJohn,
                        PlaneBaggage.HAND, true);
                myTrip.getParticipant(john).addTransportBooking(flightBookingWMJohn);
            } catch (NoPlacementAvailableException e) {
            }

            try {
                IBook seatToMalagaForSue = flightToMalaga.findFirstAvailable();
                seatToMalagaForSue.book();
                FlightBooking flightBookingWMSue = new FlightBooking(flightToMalaga, (PlaneSeat) seatToMalagaForSue,
                        PlaneBaggage.CHECKED, true);
                myTrip.getParticipant(sue).addTransportBooking(flightBookingWMSue);

            } catch (NoPlacementAvailableException e) {
            }

//        * flight back to Warsaw booking
            Transport flightToWarsaw = malagaEs.findTransport(City.MALAGA, City.WARSAW, TransportType.PLANE);

            try {
                IBook seatToWarsawForJohn = flightToWarsaw.findFirstAvailable();
                seatToWarsawForJohn.book();
                FlightBooking flightBookingMWJohn = new FlightBooking(flightToWarsaw, (PlaneSeat) seatToWarsawForJohn,
                        PlaneBaggage.HAND, true);
                myTrip.getParticipant(john).addTransportBooking(flightBookingMWJohn);
            } catch (NoPlacementAvailableException e) {
            }
            try {
                IBook seatToWarsawForSue = flightToWarsaw.findFirstAvailable();
                seatToWarsawForSue.book();
                FlightBooking flightBookingMWSue = new FlightBooking(flightToWarsaw, (PlaneSeat) seatToWarsawForSue,
                        PlaneBaggage.CHECKED, true);
                myTrip.getParticipant(sue).addTransportBooking(flightBookingMWSue);

            } catch (NoPlacementAvailableException e) {
            }
        } catch (NoTransportException e) {
            throw new RuntimeException(e);
        }

//        * coach travel to Malaga booking
        try {
            Transport coachTravelToMalaga = malagaEs.findTransport(City.WARSAW, City.MALAGA, TransportType.BUS);
            try {
                IBook seatToMalagaKate = coachTravelToMalaga.findFirstAvailable();
                seatToMalagaKate.book();
                CoachTravelBooking coachBookingWMKate = new CoachTravelBooking(coachTravelToMalaga,
                        (Seat) seatToMalagaKate, false, 3, 2);
                myTrip.getParticipant(kate).addTransportBooking(coachBookingWMKate);
            } catch (NoPlacementAvailableException e) {
            }
            try {
                IBook seatToMalagaTom = coachTravelToMalaga.findFirstAvailable();
                seatToMalagaTom.book();
                CoachTravelBooking coachBookingWMTom = new CoachTravelBooking(coachTravelToMalaga,
                        (Seat) seatToMalagaTom, false, 1, 3);
                myTrip.getParticipant(tom).addTransportBooking(coachBookingWMTom);

            } catch (NoPlacementAvailableException e) {
            }
        } catch (NoTransportException e) {

        }

//        * coach travel back to Warsaw booking
        try {
            Transport coachTravelToWarsaw = malagaEs.findTransport(City.MALAGA, City.WARSAW, TransportType.BUS);
            try {
                IBook seatToWarsawKate = coachTravelToWarsaw.findFirstAvailable();
                seatToWarsawKate.book();
                CoachTravelBooking coachBookingMWKate = new CoachTravelBooking(coachTravelToWarsaw,
                        (Seat) seatToWarsawKate, false, 3, 2);
                myTrip.getParticipant(kate).addTransportBooking(coachBookingMWKate);
            } catch (NoPlacementAvailableException e) {
            }
            try {
                IBook seatToWarsawTom = coachTravelToWarsaw.findFirstAvailable();
                seatToWarsawTom.book();
                CoachTravelBooking coachBookingMWTom = new CoachTravelBooking(coachTravelToWarsaw,
                        (Seat) seatToWarsawTom, false, 1, 3);
                myTrip.getParticipant(tom).addTransportBooking(coachBookingMWTom);
            } catch (NoPlacementAvailableException e) {
            }
        } catch (NoTransportException e) {
            throw new RuntimeException(e);
        }


//        add activities for participants
        try {
            myTrip.getParticipant(john).addActivity(malagaEs.findActivity("Football Match"));
        } catch (NoActivityException e) {
            throw new RuntimeException(e);
        }
        try {
            myTrip.getParticipant(sue).addActivity(malagaEs.findActivity("Banana Boat Ride"));
        } catch (NoActivityException e) {
            throw new RuntimeException(e);
        }
        try {
            myTrip.getParticipant(sue).addActivity(malagaEs.findActivity("Caminito Del Rey Tour"));
        } catch (NoActivityException e) {
            throw new RuntimeException(e);
        }
        try {
            myTrip.getParticipant(kate).addActivity(malagaEs.findActivity("Beach Volleyball"));
        } catch (NoActivityException e) {
            throw new RuntimeException(e);
        }
        try {
            myTrip.getParticipant(tom).addActivity(malagaEs.findActivity("Football Match"));
        } catch (NoActivityException e) {
            throw new RuntimeException(e);
        }

        myTrip.printSummary();
    }
}
