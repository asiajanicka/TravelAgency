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

        City goFromCity = City.WARSAW;
        City goToCity = City.MALAGA;
        City goBackFromCity = goToCity;
        City goBackToCity = goFromCity;

        Participant participantJohn = new Participant(new Person("1", "John", "Smith", 46));
        Participant participantSue = new Participant(new Person("2", "Sue", "Smith", 45));
        Participant participantKate = new Participant(new Person("3", "Kate", "Smith", 17));
        Participant participantTom = new Participant(new Person("4", "Tom", "Smith", 15));

        TransportType transportTypeJohnSue = TransportType.PLANE;
        TransportType transportTypeKateTom = TransportType.BUS;

        try {
            int roomNumberJohnSue = 101;
            malagaEs.getHotel().find(roomNumberJohnSue).book();
            logger.debug(String.format("Room %d at %s booked", roomNumberJohnSue, malagaEs.getHotel().getName()));
            HotelBooking hotelBookingJohn = new HotelBooking(startDate, endDate, malagaEs.getHotel(), true,
                    (Room) malagaEs.getHotel().find(roomNumberJohnSue), BoardType.ALL_INCLUSIVE);
            participantJohn.addHotelBooking(hotelBookingJohn);
            logger.debug(String.format("Customized trip - new hotel booking added for [%s]: room %d at %s",
                    participantJohn.getPerson(), roomNumberJohnSue, malagaEs.getHotel()));
            HotelBooking hotelBookingSue = new HotelBooking(startDate, endDate, malagaEs.getHotel(), true,
                    (Room) malagaEs.getHotel().find(roomNumberJohnSue), BoardType.BB);
            participantSue.addHotelBooking(hotelBookingSue);
            logger.debug(String.format("Customized trip - new hotel booking added for [%s]: room %d at %s",
                    participantSue.getPerson(), roomNumberJohnSue, malagaEs.getHotel()));
        } catch (NoPlacementException e) {
            logger.error("Search room by number failed. No hotel booking could be done", e);
        }

        try {
            int roomNumberKate = 102;
            malagaEs.getHotel().find(roomNumberKate).book();
            logger.debug(String.format("Room %d at %s booked", roomNumberKate, malagaEs.getHotel().getName()));
            HotelBooking hotelBookingKate = new HotelBooking(startDate, endDate, malagaEs.getHotel(), false,
                    (Room) malagaEs.getHotel().find(roomNumberKate), BoardType.FB);
            participantKate.addHotelBooking(hotelBookingKate);
            logger.debug(String.format("Customized trip - new hotel booking added for %s: room %d at %s",
                    participantKate.getPerson(), roomNumberKate, malagaEs.getHotel()));
        } catch (NoPlacementException e) {
            logger.error("Search room by number failed. No hotel booking could be done.", e);
        }

        try {
            int roomNumberTom = 103;
            malagaEs.getHotel().find(roomNumberTom).book();
            logger.debug(String.format("Room %d at %s booked", roomNumberTom, malagaEs.getHotel().getName()));
            HotelBooking hotelBookingTom = new HotelBooking(startDate, endDate, malagaEs.getHotel(), false,
                    (Room) malagaEs.getHotel().find(roomNumberTom), BoardType.FB);
            participantTom.addHotelBooking(hotelBookingTom);
            logger.debug(String.format("Customized trip - new hotel booking added for %s: room %d at %s",
                    participantTom.getPerson(), roomNumberTom, malagaEs.getHotel()));
        } catch (NoPlacementException e) {
            logger.error("Search room by number failed. No hotel booking could be done.", e);
        }

        try {
            Transport flightToMalaga = malagaEs.findTransport(goFromCity, goToCity, transportTypeJohnSue);
            try {
                IBook seatToMalagaForJohn = flightToMalaga.findFirstAvailable();
                seatToMalagaForJohn.book();
                logger.debug(String.format("Seat %d on way from %s to %s booked",
                        ((Seat) seatToMalagaForJohn).getNumber(), goFromCity, goToCity));
                FlightBooking flightBookingWMJohn = new FlightBooking(flightToMalaga, (PlaneSeat) seatToMalagaForJohn,
                        PlaneBaggage.HAND, true);
                participantJohn.addTransportBooking(flightBookingWMJohn);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        participantJohn.getPerson(), transportTypeJohnSue, flightToMalaga));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. No transport booking for %s could be done.",
                        goFromCity, goToCity, transportTypeJohnSue, participantJohn.getPerson()), e);
            }

            try {
                IBook seatToMalagaForSue = flightToMalaga.findFirstAvailable();
                seatToMalagaForSue.book();
                logger.debug(String.format("Seat %d on way from %s to %s booked",
                        ((Seat) seatToMalagaForSue).getNumber(), goFromCity, goToCity));
                FlightBooking flightBookingWMSue = new FlightBooking(flightToMalaga, (PlaneSeat) seatToMalagaForSue,
                        PlaneBaggage.CHECKED, true);
                participantSue.addTransportBooking(flightBookingWMSue);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        participantSue.getPerson(), transportTypeJohnSue, flightToMalaga));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. No transport booking for %s could be done.",
                        goFromCity, goToCity, transportTypeJohnSue, participantSue.getPerson()), e);
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
                        participantJohn.getPerson(), transportTypeJohnSue, flightToWarsaw));
                participantJohn.addTransportBooking(flightBookingMWJohn);
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. No transport booking for %s could be done.",
                        goBackFromCity, goBackToCity, transportTypeJohnSue, participantJohn.getPerson()), e);
            }

            try {
                IBook seatToWarsawForSue = flightToWarsaw.findFirstAvailable();
                seatToWarsawForSue.book();
                logger.debug(String.format("Seat %d on way from %s to %s booked",
                        ((Seat) seatToWarsawForSue).getNumber(), goBackFromCity, goBackToCity));
                FlightBooking flightBookingMWSue = new FlightBooking(flightToWarsaw, (PlaneSeat) seatToWarsawForSue,
                        PlaneBaggage.CHECKED, true);
                participantSue.addTransportBooking(flightBookingMWSue);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        participantSue.getPerson(), transportTypeJohnSue, flightToWarsaw));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. No transport booking for %s could be done.",
                        goBackFromCity, goBackToCity, transportTypeJohnSue, participantSue.getPerson()), e);
            }
        } catch (NoTransportException e) {
            logger.error(String.format("Search transport by city and type failed. No transport %s from %s to %s is available.",
                    transportTypeJohnSue, goBackFromCity, goBackToCity), e);
        }

        try {
            Transport coachTravelToMalaga = malagaEs.findTransport(goFromCity, goToCity, transportTypeKateTom);
            try {
                IBook seatToMalagaKate = coachTravelToMalaga.findFirstAvailable();
                seatToMalagaKate.book();
                CoachTravelBooking coachBookingWMKate = new CoachTravelBooking(coachTravelToMalaga,
                        (Seat) seatToMalagaKate, false, 3, 2);
                participantKate.addTransportBooking(coachBookingWMKate);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        participantKate.getPerson(), transportTypeKateTom, coachTravelToMalaga));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. No transport booking for %s could be done.",
                        goFromCity, goToCity, transportTypeKateTom, participantKate.getPerson()), e);
            }

            try {
                IBook seatToMalagaTom = coachTravelToMalaga.findFirstAvailable();
                seatToMalagaTom.book();
                CoachTravelBooking coachBookingWMTom = new CoachTravelBooking(coachTravelToMalaga,
                        (Seat) seatToMalagaTom, false, 1, 3);
                participantTom.addTransportBooking(coachBookingWMTom);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        participantTom.getPerson(), transportTypeKateTom, coachTravelToMalaga));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. No transport booking for %s could be done.",
                        goFromCity, goToCity, transportTypeKateTom, participantTom.getPerson()), e);
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
                participantKate.addTransportBooking(coachBookingMWKate);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        participantKate.getPerson(), transportTypeKateTom, coachTravelToWarsaw));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. No transport booking for %s could be done.",
                        goBackFromCity, goBackToCity, transportTypeKateTom, participantKate.getPerson()), e);
            }

            try {
                IBook seatToWarsawTom = coachTravelToWarsaw.findFirstAvailable();
                seatToWarsawTom.book();
                CoachTravelBooking coachBookingMWTom = new CoachTravelBooking(coachTravelToWarsaw,
                        (Seat) seatToWarsawTom, false, 1, 3);
                participantTom.addTransportBooking(coachBookingMWTom);
                logger.debug(String.format("Customized trip - new transport booking added for %s: %s %s",
                        participantTom.getPerson(), transportTypeKateTom, coachTravelToWarsaw));
            } catch (NoPlacementAvailableException e) {
                logger.error(String.format("No seat free on %s from %s to %s. No transport booking for %s could be done.",
                        goBackFromCity, goBackToCity, transportTypeKateTom, participantTom.getPerson()), e);
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
            participantJohn.addActivity(malagaEs.findActivity(activityNameJohnTom));
        } catch (NoActivityException e) {
            logger.error(String.format("Activity %s not available at %s. %s can participate in this activity",
                    activityNameJohnTom, malagaEs, participantJohn.getPerson()), e);
        }

        try {
            participantSue.addActivity(malagaEs.findActivity("Banana Boat Ride"));
        } catch (NoActivityException e) {
            logger.error(String.format("Activity %s not available at %s. %s can participate in this activity",
                    activity1Sue, malagaEs, participantSue.getPerson()), e);
        }

        try {
            participantSue.addActivity(malagaEs.findActivity("Caminito Del Rey Tour"));
        } catch (NoActivityException e) {
            logger.error(String.format("Activity %s not available at %s. %s can participate in this activity",
                    activity2Sue, malagaEs, participantSue.getPerson()), e);
        }

        try {
            participantKate.addActivity(malagaEs.findActivity("Beach Volleyball"));
        } catch (NoActivityException e) {
            logger.error(String.format("Activity %s not available at %s. %s can participate in this activity",
                    activityKate, malagaEs, participantKate.getPerson()), e);
        }

        try {
            participantTom.addActivity(malagaEs.findActivity("Football Match"));
        } catch (NoActivityException e) {
            logger.error(String.format("Activity %s not available at %s. %s can participate in this activity",
                    activityNameJohnTom, malagaEs, participantTom.getPerson()), e);
        }

        myTrip.addParticipant(participantJohn);
        logger.debug(String.format("Customized trip - participant added: %s ", participantJohn.getPerson()));
        myTrip.addParticipant(participantSue);
        logger.debug(String.format("Customized trip - participant added: %s ", participantSue.getPerson()));
        myTrip.addParticipant(participantKate);
        logger.debug(String.format("Customized trip - participant added: %s ", participantKate.getPerson()));
        myTrip.addParticipant(participantTom);
        logger.debug(String.format("Customized trip - participant added: %s ", participantTom.getPerson()));

        myTrip.printSummary();
    }
}
