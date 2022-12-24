package org.jjm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.bookings.CoachTravelBooking;
import org.jjm.bookings.FlightBooking;
import org.jjm.bookings.HotelBooking;
import org.jjm.destination.Destination;
import org.jjm.destination.exceptions.NoActivityException;
import org.jjm.enums.BoardType;
import org.jjm.enums.City;
import org.jjm.enums.PlaneBaggage;
import org.jjm.enums.TransportType;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.exceptions.NoPlacementException;
import org.jjm.hotel.Room;
import org.jjm.transport.Seat;
import org.jjm.transport.Transport;
import org.jjm.transport.exceptions.NoTransportException;
import org.jjm.trip.CustomizedTrip;
import org.jjm.trip.Participant;
import org.jjm.trip.Person;
import org.jjm.trip.TravelAgency;

import java.time.LocalDate;

public class TripBooking {

    public static void main(String[] args) {

        TravelAgency travelAgency = new TravelAgency();
        CustomizedTrip myTrip = new CustomizedTrip();

        Destination malagaEs = travelAgency.getDestinations().get(0);
        myTrip.addDestination(malagaEs);

        LocalDate startDate = LocalDate.of(2022, 5, 5);
        LocalDate endDate = LocalDate.of(2022, 5, 15);
        myTrip.setStartDate(startDate);
        myTrip.setEndDate(endDate);

        City goFromCity = City.WARSAW;
        City goToCity = City.MALAGA;
        City goBackFromCity = goToCity;
        City goBackToCity = goFromCity;

        Participant participantJohn = new Participant(new Person("John", "Smith", 46));
        Participant participantSue = new Participant(new Person("Sue", "Smith", 45));
        Participant participantKate = new Participant(new Person("Kate", "Smith", 17));
        Participant participantTom = new Participant(new Person("Tom", "Smith", 15));

        TransportType transportTypeJohnSue = TransportType.PLANE;
        TransportType transportTypeKateTom = TransportType.BUS;

        Room roomJohnSue = null;
        Room roomKate = null;
        Room roomTom = null;

        try {
            roomJohnSue = malagaEs.getHotel().getRoom(101);
            roomKate = malagaEs.getHotel().getRoom(102);
            roomTom = malagaEs.getHotel().getRoom(103);
        } catch (NoPlacementException e) {
            System.out.println("Sorry, program terminated as room for one or more participants is not available");
            System.exit(-1);
        }

        roomJohnSue.book();
        HotelBooking hotelBookingJohn = new HotelBooking(startDate, endDate, malagaEs.getHotel(), true,
                roomJohnSue, BoardType.ALL_INCLUSIVE);
        participantJohn.addHotelBooking(hotelBookingJohn);
        HotelBooking hotelBookingSue = new HotelBooking(startDate, endDate, malagaEs.getHotel(), true,
                roomJohnSue, BoardType.BB);
        participantSue.addHotelBooking(hotelBookingSue);

        roomKate.book();
        HotelBooking hotelBookingKate = new HotelBooking(startDate, endDate, malagaEs.getHotel(), false,
                roomKate, BoardType.FB);
        participantKate.addHotelBooking(hotelBookingKate);

        roomTom.book();
        HotelBooking hotelBookingTom = new HotelBooking(startDate, endDate, malagaEs.getHotel(), false,
                roomTom, BoardType.FB);
        participantTom.addHotelBooking(hotelBookingTom);

        Transport flightToMalaga = null;
        Transport flightToWarsaw = null;
        Transport coachTravelToMalaga = null;
        Transport coachTravelToWarsaw = null;

        try {
            flightToMalaga = malagaEs.findTransport(goFromCity, goToCity, transportTypeJohnSue);
            flightToWarsaw = malagaEs.findTransport(goBackFromCity, goBackToCity, transportTypeJohnSue);
            coachTravelToMalaga = malagaEs.findTransport(goFromCity, goToCity, transportTypeKateTom);
            coachTravelToWarsaw = malagaEs.findTransport(goBackFromCity, goBackToCity, transportTypeKateTom);
        } catch (NoTransportException e) {
            System.out.println("Sorry, program terminated as transport for trip Warsaw -> Malaga could not be found " +
                    "for one or more participants");
            System.exit(-1);
        }

        Seat seatToMalagaForJohn = null;
        Seat seatToWarsawForJohn = null;
        Seat seatToMalagaForSue = null;
        Seat seatToWarsawForSue = null;
        Seat seatToMalagaKate = null;
        Seat seatToWarsawKate = null;
        Seat seatToMalagaTom = null;
        Seat seatToWarsawTom = null;

        try {
            seatToMalagaForJohn = flightToMalaga.getFirstAvailableSeat();
            seatToWarsawForJohn = flightToWarsaw.getFirstAvailableSeat();
            seatToMalagaForSue = flightToMalaga.getFirstAvailableSeat();
            seatToWarsawForSue = flightToWarsaw.getFirstAvailableSeat();
            seatToMalagaKate = coachTravelToMalaga.getFirstAvailableSeat();
            seatToWarsawKate = coachTravelToWarsaw.getFirstAvailableSeat();
            seatToMalagaTom = coachTravelToMalaga.getFirstAvailableSeat();
            seatToWarsawTom = coachTravelToWarsaw.getFirstAvailableSeat();

        } catch (NoPlacementAvailableException e) {
            System.out.println("Sorry, program terminated as there is no seat available for trip to Warsaw -> Malaga for " +
                    "one or more participants");
            System.exit(-1);
        }

        seatToMalagaForJohn.book();
        FlightBooking flightBookingWMJohn = new FlightBooking(flightToMalaga, seatToMalagaForJohn,
                PlaneBaggage.HAND, true);
        participantJohn.addTransportBooking(flightBookingWMJohn);

        seatToMalagaForSue.book();
        FlightBooking flightBookingWMSue = new FlightBooking(flightToMalaga, seatToMalagaForSue,
                PlaneBaggage.CHECKED, true);
        participantSue.addTransportBooking(flightBookingWMSue);

        seatToWarsawForJohn.book();
        FlightBooking flightBookingMWJohn = new FlightBooking(flightToWarsaw, seatToWarsawForJohn,
                PlaneBaggage.HAND, true);
        participantJohn.addTransportBooking(flightBookingMWJohn);

        seatToWarsawForSue.book();
        FlightBooking flightBookingMWSue = new FlightBooking(flightToWarsaw, seatToWarsawForSue,
                PlaneBaggage.CHECKED, true);
        participantSue.addTransportBooking(flightBookingMWSue);

        seatToMalagaKate.book();
        CoachTravelBooking coachBookingWMKate = new CoachTravelBooking(coachTravelToMalaga,
                seatToMalagaKate, false, 3, 2);
        participantKate.addTransportBooking(coachBookingWMKate);

        seatToMalagaTom.book();
        CoachTravelBooking coachBookingWMTom = new CoachTravelBooking(coachTravelToMalaga,
                seatToMalagaTom, false, 1, 3);
        participantTom.addTransportBooking(coachBookingWMTom);

        seatToWarsawKate.book();
        CoachTravelBooking coachBookingMWKate = new CoachTravelBooking(coachTravelToWarsaw,
                seatToWarsawKate, false, 3, 2);
        participantKate.addTransportBooking(coachBookingMWKate);

        seatToWarsawTom.book();
        CoachTravelBooking coachBookingMWTom = new CoachTravelBooking(coachTravelToWarsaw,
                seatToWarsawTom, false, 1, 3);
        participantTom.addTransportBooking(coachBookingMWTom);

        try {
            participantJohn.addActivity(malagaEs.findActivity("Football Match"));
            participantSue.addActivity(malagaEs.findActivity("Banana Boat Ride"));
            participantSue.addActivity(malagaEs.findActivity("Caminito Del Rey Tour"));
            participantKate.addActivity(malagaEs.findActivity("Beach Volleyball"));
            participantTom.addActivity(malagaEs.findActivity("Football Match"));
        } catch (NoActivityException e) {
            System.out.println("Please pay attention, that one or more activities couldn't be assigned to participants." +
                    "For more info, check logs of the program");
        }

        myTrip.addParticipant(participantJohn);
        myTrip.addParticipant(participantSue);
        myTrip.addParticipant(participantKate);
        myTrip.addParticipant(participantTom);

        myTrip.printSummary();
    }
}
