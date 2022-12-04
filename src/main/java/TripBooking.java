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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import transport.PlaneSeat;
import transport.Seat;
import transport.Transport;
import trip.CustomizedTrip;
import trip.Participant;
import trip.Person;
import trip.TravelAgency;
import utils.DateFormat;

import java.time.LocalDate;

public class TripBooking {
    private static final Logger logger = LogManager.getLogger(TripBooking.class);

    public static void main(String[] args) {

        TravelAgency travelAgency = new TravelAgency();
        logger.info("Travel agency initialized with possible destinations");

        CustomizedTrip myTrip = new CustomizedTrip();
        logger.info("Customized trip - created: empty");

        Destination malagaEs = travelAgency.getDestinations().get(0);
        myTrip.addDestination(malagaEs);
        logger.debug(String.format("Customized trip - new destination added: %s", malagaEs));

        LocalDate startDate = LocalDate.of(2022, 5, 5);
        LocalDate endDate = LocalDate.of(2022, 5, 15);
        myTrip.setStartDate(startDate);
        myTrip.setEndDate(endDate);
        logger.debug(String.format("Customized trip - date set: from %s to %s",
                DateFormat.format(startDate), DateFormat.format(endDate)));

        Person john = new Person("1", "John", "Smith", 46);
        logger.debug(String.format("Customized trip - participant added: %s ", john));
        Person sue = new Person("2", "Sue", "Smith", 45);
        myTrip.addParticipant(new Participant(sue));
        logger.debug(String.format("Customized trip - participant added: %s ", sue));
        Person kate = new Person("3", "Kate", "Smith", 17);
        myTrip.addParticipant(new Participant(kate));
        logger.debug(String.format("Customized trip - participant added: %s ", kate));
        Person tom = new Person("4", "Tom", "Smith", 15);
        myTrip.addParticipant(new Participant(tom));
        logger.debug(String.format("Customized trip - participant added: %s ", tom));

        try {
            int roomNumberJohnSue = 101;
            malagaEs.getHotel().find(roomNumberJohnSue).book();
            logger.debug(String.format("Room %d at %s booked", roomNumberJohnSue, malagaEs.getHotel().getName()));
            HotelBooking hotelBookingJohn = new HotelBooking(startDate, endDate, malagaEs.getHotel(), true,
                    (Room) malagaEs.getHotel().find(roomNumberJohnSue), BoardType.ALL_INCLUSIVE);
            myTrip.getParticipant(john).addHotelBooking(hotelBookingJohn);
            logger.debug(String.format("Customized trip - new hotel booking added for %s: room %d at %s",
                    john, roomNumberJohnSue, malagaEs.getHotel()));
            HotelBooking hotelBookingSue = new HotelBooking(startDate, endDate, malagaEs.getHotel(), true,
                    (Room) malagaEs.getHotel().find(roomNumberJohnSue), BoardType.BB);
            myTrip.getParticipant(sue).addHotelBooking(hotelBookingSue);
            logger.debug(String.format("Customized trip - new hotel booking added for %s: room %d at %s",
                    sue, roomNumberJohnSue, malagaEs.getHotel()));
        } catch (NoPlacementException e) {
            logger.error("Search room by number failed. No hotel booking could be done", e);
        }

        try {
            int roomNumberKate = 102;
            malagaEs.getHotel().find(roomNumberKate).book();
            logger.debug(String.format("Room %d at %s booked", roomNumberKate, malagaEs.getHotel().getName()));
            HotelBooking hotelBookingKate = new HotelBooking(startDate, endDate, malagaEs.getHotel(), false,
                    (Room) malagaEs.getHotel().find(roomNumberKate), BoardType.FB);
            myTrip.getParticipant(kate).addHotelBooking(hotelBookingKate);
            logger.debug(String.format("Customized trip - new hotel booking added for %s: room %d at %s",
                    kate, roomNumberKate, malagaEs.getHotel()));
        } catch (NoPlacementException e) {
            logger.error("Search room by number failed. No hotel booking could be done.", e);
        }

        try {
            int roomNumberTom = 103;
            malagaEs.getHotel().find(roomNumberTom).book();
            logger.debug(String.format("Room %d at %s booked", roomNumberTom, malagaEs.getHotel().getName()));
            HotelBooking hotelBookingTom = new HotelBooking(startDate, endDate, malagaEs.getHotel(), false,
                    (Room) malagaEs.getHotel().find(roomNumberTom), BoardType.FB);
            myTrip.getParticipant(tom).addHotelBooking(hotelBookingTom);
            logger.debug(String.format("Customized trip - new hotel booking added for %s: room %d at %s",
                    tom, roomNumberTom, malagaEs.getHotel()));
        } catch (NoPlacementException e) {
            logger.error("Search room by number failed. No hotel booking could be done.", e);
        }

        City goFromCity = City.WARSAW;
        City goToCity = City.MALAGA;
        City goBackFromCity = goToCity;
        City goBackToCity = goFromCity;
        TransportType transportTypeJohnSue = TransportType.PLANE;

        try {
            Transport flightToMalaga = malagaEs.findTransport(goFromCity, goToCity, transportTypeJohnSue);
            try {
                IBook seatToMalagaForJohn = flightToMalaga.findFirstAvailable();
                seatToMalagaForJohn.book();
                logger.debug(String.format("Seat %d on way from %s to %s booked",
                        ((Seat) seatToMalagaForJohn).getNumber(), goFromCity, goToCity));
                FlightBooking flightBookingWMJohn = new FlightBooking(flightToMalaga, (PlaneSeat) seatToMalagaForJohn,
                        PlaneBaggage.HAND, true);
                myTrip.getParticipant(john).addTransportBooking(flightBookingWMJohn);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        john, transportTypeJohnSue, flightToMalaga));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. " +
                        "No transport booking for %s could be done.", goFromCity, goToCity, transportTypeJohnSue, john), e);
            }

            try {
                IBook seatToMalagaForSue = flightToMalaga.findFirstAvailable();
                seatToMalagaForSue.book();
                logger.debug(String.format("Seat %d on way from %s to %s booked",
                        ((Seat) seatToMalagaForSue).getNumber(), goFromCity, goToCity));
                FlightBooking flightBookingWMSue = new FlightBooking(flightToMalaga, (PlaneSeat) seatToMalagaForSue,
                        PlaneBaggage.CHECKED, true);
                myTrip.getParticipant(sue).addTransportBooking(flightBookingWMSue);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        sue, transportTypeJohnSue, flightToMalaga));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. " +
                        "No transport booking for %s could be done.", goFromCity, goToCity, transportTypeJohnSue, sue), e);
            }
        } catch (NoTransportException e) {
            logger.error(String.format("Search transport by city and type failed. No transport %s from %s to %s is available.",
                    transportTypeJohnSue, goFromCity, goToCity), e);
        }

        try {
            Transport flightToWarsaw = malagaEs.findTransport(goBackFromCity, goBackToCity, transportTypeJohnSue);

            try {
                IBook seatToWarsawForJohn = flightToWarsaw.findFirstAvailable();
                seatToWarsawForJohn.book();
                logger.debug(String.format("Seat %d on way from %s to %s booked",
                        ((Seat) seatToWarsawForJohn).getNumber(), goBackFromCity, goBackToCity));
                FlightBooking flightBookingMWJohn = new FlightBooking(flightToWarsaw, (PlaneSeat) seatToWarsawForJohn,
                        PlaneBaggage.HAND, true);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        john, transportTypeJohnSue, flightToWarsaw));
                myTrip.getParticipant(john).addTransportBooking(flightBookingMWJohn);
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. " +
                        "No transport booking for %s could be done.", goBackFromCity, goBackToCity,
                        transportTypeJohnSue, john), e);
            }
            try {
                IBook seatToWarsawForSue = flightToWarsaw.findFirstAvailable();
                seatToWarsawForSue.book();
                logger.debug(String.format("Seat %d on way from %s to %s booked",
                        ((Seat) seatToWarsawForSue).getNumber(), goBackFromCity, goBackToCity));
                FlightBooking flightBookingMWSue = new FlightBooking(flightToWarsaw, (PlaneSeat) seatToWarsawForSue,
                        PlaneBaggage.CHECKED, true);
                myTrip.getParticipant(sue).addTransportBooking(flightBookingMWSue);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        sue, transportTypeJohnSue, flightToWarsaw));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. " +
                                "No transport booking for %s could be done.", goBackFromCity, goBackToCity,
                        transportTypeJohnSue, sue), e);
            }
        } catch (NoTransportException e) {
            logger.error(String.format("Search transport by city and type failed. No transport %s from %s to %s is available.",
                    transportTypeJohnSue, goBackFromCity, goBackToCity), e);
        }

        TransportType transportTypeKateTom = TransportType.BUS;
        try {
            Transport coachTravelToMalaga = malagaEs.findTransport(goFromCity, goToCity, transportTypeKateTom);
            try {
                IBook seatToMalagaKate = coachTravelToMalaga.findFirstAvailable();
                seatToMalagaKate.book();
                CoachTravelBooking coachBookingWMKate = new CoachTravelBooking(coachTravelToMalaga,
                        (Seat) seatToMalagaKate, false, 3, 2);
                myTrip.getParticipant(kate).addTransportBooking(coachBookingWMKate);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        kate, transportTypeKateTom, coachTravelToMalaga));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. " +
                                "No transport booking for %s could be done.", goFromCity, goToCity,
                        transportTypeKateTom, kate), e);
            }
            try {
                IBook seatToMalagaTom = coachTravelToMalaga.findFirstAvailable();
                seatToMalagaTom.book();
                CoachTravelBooking coachBookingWMTom = new CoachTravelBooking(coachTravelToMalaga,
                        (Seat) seatToMalagaTom, false, 1, 3);
                myTrip.getParticipant(tom).addTransportBooking(coachBookingWMTom);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        tom, transportTypeKateTom, coachTravelToMalaga));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. " +
                                "No transport booking for %s could be done.", goFromCity, goToCity,
                        transportTypeKateTom, tom), e);
            }
        } catch (NoTransportException e) {
            logger.error(String.format("Search transport by city and type failed. No transport %s from %s to %s is available.",
                    transportTypeKateTom, goFromCity, goToCity), e);
        }

        try {
            Transport coachTravelToWarsaw = malagaEs.findTransport(goBackFromCity, goBackToCity, transportTypeKateTom);
            try {
                IBook seatToWarsawKate = coachTravelToWarsaw.findFirstAvailable();
                seatToWarsawKate.book();
                CoachTravelBooking coachBookingMWKate = new CoachTravelBooking(coachTravelToWarsaw,
                        (Seat) seatToWarsawKate, false, 3, 2);
                myTrip.getParticipant(kate).addTransportBooking(coachBookingMWKate);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        kate, transportTypeKateTom, coachTravelToWarsaw));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. " +
                                "No transport booking for %s could be done.", goBackFromCity, goBackToCity,
                        transportTypeKateTom, kate), e);
            }
            try {
                IBook seatToWarsawTom = coachTravelToWarsaw.findFirstAvailable();
                seatToWarsawTom.book();
                CoachTravelBooking coachBookingMWTom = new CoachTravelBooking(coachTravelToWarsaw,
                        (Seat) seatToWarsawTom, false, 1, 3);
                myTrip.getParticipant(tom).addTransportBooking(coachBookingMWTom);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        tom, transportTypeKateTom, coachTravelToWarsaw));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. No transport booking for %s could be done.",
                        goBackFromCity, goBackToCity, transportTypeKateTom, tom), e);
            }
        } catch (NoTransportException e) {
            logger.error(String.format("Search transport by city and type failed. No transport %s from %s to %s is " +
                            "available.", transportTypeKateTom, goBackFromCity, goBackToCity), e);
        }

        String activityNameJohnTom = "Football Match";
        String activity1Sue = "Banana Boat Ride";
        String activity2Sue = "Caminito Del Rey Tour";
        String activityKate = "Beach Volleyball";
        try {
            myTrip.getParticipant(john).addActivity(malagaEs.findActivity(activityNameJohnTom));
        } catch (NoActivityException e) {
            logger.error(String.format("Activity %s not available at %s. %s can participate in this activity",
                    activityNameJohnTom, malagaEs, john), e);
        }
        try {
            myTrip.getParticipant(sue).addActivity(malagaEs.findActivity("Banana Boat Ride"));
        } catch (NoActivityException e) {
            logger.error(String.format("Activity %s not available at %s. %s can participate in this activity",
                    activity1Sue, malagaEs, sue), e);
            throw new RuntimeException(e);
        }
        try {
            myTrip.getParticipant(sue).addActivity(malagaEs.findActivity("Caminito Del Rey Tour"));
        } catch (NoActivityException e) {
            logger.error(String.format("Activity %s not available at %s. %s can participate in this activity",
                    activity2Sue, malagaEs, sue), e);
            throw new RuntimeException(e);
        }
        try {
            myTrip.getParticipant(kate).addActivity(malagaEs.findActivity("Beach Volleyball"));
        } catch (NoActivityException e) {
            logger.error(String.format("Activity %s not available at %s. %s can participate in this activity",
                    activityKate, malagaEs, kate), e);
            throw new RuntimeException(e);
        }
        try {
            myTrip.getParticipant(tom).addActivity(malagaEs.findActivity("Football Match"));
        } catch (NoActivityException e) {
            logger.error(String.format("Activity %s not available at %s. %s can participate in this activity",
                    activityNameJohnTom, malagaEs, tom), e);
        }
    }
}
