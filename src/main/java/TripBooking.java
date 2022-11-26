import destination.Destination;
import enums.BoardType;
import enums.City;
import enums.PlaneBaggage;
import enums.TransportType;
import hotel.HotelBooking;
import transport.CoachTravelBooking;
import transport.FlightBooking;
import transport.Seat;
import transport.Transport;

import java.time.LocalDate;

public class TripBooking {
    public static void main(String[] args) {
/*      Trip from May 5th 2022 to May 15th 2022
        Destination Spain, Malaga Hotel Holiday Inn
        Family of 4 :
        - John Smith age 26 (double room with Sue, board All inclusive)
        - Sue Smith age 25  (double room with John, board Bed & Bracket)
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
        Tom: "Football Match
*/

//        initialize travel agency with possible destinations
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
        Person john = new Person("1", "John", "Smith", 26);
        Person sue = new Person("2", "Sue", "Smith", 25);
        Person kate = new Person("3", "Kate", "Smith", 17);
        Person tom = new Person("4", "Tom", "Smith", 15);

        myTrip.addParticipant(new Participant(john));
        myTrip.addParticipant(new Participant(sue));
        myTrip.addParticipant(new Participant(kate));
        myTrip.addParticipant(new Participant(tom));

//        create hotel bookings for each participant
        HotelBooking hotelBookingJohn = new HotelBooking(startDate, endDate, true,
                malagaEs.getHotel().findRoom(101), BoardType.ALL_INCLUSIVE);
        myTrip.getParticipant(john).addHotelBooking(hotelBookingJohn);
        HotelBooking hotelBookingSue = new HotelBooking(startDate, endDate, true,
                malagaEs.getHotel().findRoom(101), BoardType.BB);
        myTrip.getParticipant(sue).addHotelBooking(hotelBookingSue);
        HotelBooking hotelBookingKate = new HotelBooking(startDate, endDate, false,
                malagaEs.getHotel().findRoom(102), BoardType.FB);
        myTrip.getParticipant(kate).addHotelBooking(hotelBookingKate);
        HotelBooking hotelBookingTom = new HotelBooking(startDate, endDate, false,
                malagaEs.getHotel().findRoom(103), BoardType.FB);
        myTrip.getParticipant(tom).addHotelBooking(hotelBookingTom);

//        create transport bookings for each participant
        Seat seatToMalagaJohn = malagaEs.findTransport(City.WARSAW, City.MALAGA, TransportType.PLANE).findSeat(1);
        Seat seatToWarsawJohn = malagaEs.findTransport(City.MALAGA, City.WARSAW, TransportType.PLANE).findSeat(1);
        FlightBooking flightBookingWMJohn = new FlightBooking(seatToMalagaJohn, PlaneBaggage.HAND, true);
        FlightBooking flightBookingMWJohn = new FlightBooking(seatToWarsawJohn, PlaneBaggage.HAND, true);
        myTrip.getParticipant(john).addTransportBooking(flightBookingWMJohn);
        myTrip.getParticipant(john).addTransportBooking(flightBookingMWJohn);

        Seat seatToMalagaSue = malagaEs.findTransport(City.WARSAW, City.MALAGA, TransportType.PLANE).findSeat(2);
        Seat seatToWarsawSue = malagaEs.findTransport(City.MALAGA, City.WARSAW, TransportType.PLANE).findSeat(2);
        FlightBooking flightBookingWMSue = new FlightBooking(seatToMalagaSue, PlaneBaggage.CHECKED, true);
        FlightBooking flightBookingMWSue = new FlightBooking(seatToWarsawSue, PlaneBaggage.CHECKED, true);
        myTrip.getParticipant(sue).addTransportBooking(flightBookingWMSue);
        myTrip.getParticipant(sue).addTransportBooking(flightBookingMWSue);

        Seat seatToMalagaKate = malagaEs.findTransport(City.WARSAW, City.MALAGA, TransportType.BUS).findSeat(1);
        Seat seatToWarsawKate = malagaEs.findTransport(City.MALAGA, City.WARSAW, TransportType.BUS).findSeat(1);
        CoachTravelBooking coachBookingWMKate = new CoachTravelBooking(seatToMalagaKate, false, 3, 2);
        CoachTravelBooking coachBookingMWKate = new CoachTravelBooking(seatToWarsawKate, false, 3, 2);
        myTrip.getParticipant(kate).addTransportBooking(coachBookingMWKate);
        myTrip.getParticipant(kate).addTransportBooking(coachBookingWMKate);

        Seat seatToMalagaTom = malagaEs.findTransport(City.WARSAW, City.MALAGA, TransportType.BUS).findSeat(2);
        Seat seatToWarsawTom = malagaEs.findTransport(City.MALAGA, City.WARSAW, TransportType.BUS).findSeat(2);
        CoachTravelBooking coachBookingWMTom = new CoachTravelBooking(seatToMalagaTom, false, 1, 3);
        CoachTravelBooking coachBookingMWTom = new CoachTravelBooking(seatToWarsawTom, false, 1, 3);
        myTrip.getParticipant(tom).addTransportBooking(coachBookingMWTom);
        myTrip.getParticipant(tom).addTransportBooking(coachBookingWMTom);

//        add activities for participants
        myTrip.getParticipant(john).addActivity(malagaEs.findActivity("Football Match"));
        myTrip.getParticipant(sue).addActivity(malagaEs.findActivity("Banana Boat Ride"));
        myTrip.getParticipant(sue).addActivity(malagaEs.findActivity("Caminito Del Rey Tour"));
        myTrip.getParticipant(kate).addActivity(malagaEs.findActivity("Beach Volleyball"));
        myTrip.getParticipant(tom).addActivity(malagaEs.findActivity("Football Match"));

        myTrip.printSummary();

    }
}
