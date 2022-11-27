import destination.Destination;
import enums.BoardType;
import enums.City;
import enums.PlaneBaggage;
import enums.TransportType;
import bookings.HotelBooking;
import bookings.CoachTravelBooking;
import bookings.FlightBooking;
import transport.Transport;

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
        Person john = new Person("1", "John", "Smith", 46);
        Person sue = new Person("2", "Sue", "Smith", 45);
        Person kate = new Person("3", "Kate", "Smith", 17);
        Person tom = new Person("4", "Tom", "Smith", 15);

        myTrip.addParticipant(new Participant(john));
        myTrip.addParticipant(new Participant(sue));
        myTrip.addParticipant(new Participant(kate));
        myTrip.addParticipant(new Participant(tom));

//        create hotel bookings for each participant
        HotelBooking hotelBookingJohn = new HotelBooking(startDate, endDate, malagaEs.getHotel(), true,
                malagaEs.getHotel().findRoom(101), BoardType.ALL_INCLUSIVE);
        myTrip.getParticipant(john).addHotelBooking(hotelBookingJohn);
        HotelBooking hotelBookingSue = new HotelBooking(startDate, endDate, malagaEs.getHotel(), true,
                malagaEs.getHotel().findRoom(101), BoardType.BB);
        myTrip.getParticipant(sue).addHotelBooking(hotelBookingSue);
        HotelBooking hotelBookingKate = new HotelBooking(startDate, endDate, malagaEs.getHotel(), false,
                malagaEs.getHotel().findRoom(102), BoardType.FB);
        myTrip.getParticipant(kate).addHotelBooking(hotelBookingKate);
        HotelBooking hotelBookingTom = new HotelBooking(startDate, endDate, malagaEs.getHotel(), false,
                malagaEs.getHotel().findRoom(103), BoardType.FB);
        myTrip.getParticipant(tom).addHotelBooking(hotelBookingTom);

//        create transport bookings for each participant
//        * flight to Malaga booking
        Transport flightToMalaga = malagaEs.findTransport(City.WARSAW, City.MALAGA, TransportType.PLANE);

        flightToMalaga.findSeat(1).bookSeat(); // book seat 1 for John on given flight to Malaga
        FlightBooking flightBookingWMJohn = new FlightBooking(flightToMalaga, 1,
                PlaneBaggage.HAND, true);
        myTrip.getParticipant(john).addTransportBooking(flightBookingWMJohn);

        flightToMalaga.findSeat(2).bookSeat(); // book seat 2 for Sue on given flight to Malaga
        FlightBooking flightBookingWMSue = new FlightBooking(flightToMalaga, 2,
                PlaneBaggage.CHECKED, true);
        myTrip.getParticipant(sue).addTransportBooking(flightBookingWMSue);

//        * flight back to Warsaw booking
        Transport flightToWarsaw = malagaEs.findTransport(City.MALAGA, City.WARSAW, TransportType.PLANE);

        flightToWarsaw.findSeat(1).bookSeat();
        FlightBooking flightBookingMWJohn = new FlightBooking(flightToWarsaw, 1,
                PlaneBaggage.HAND, true);
        myTrip.getParticipant(john).addTransportBooking(flightBookingMWJohn);

        flightToWarsaw.findSeat(2).bookSeat();
        FlightBooking flightBookingMWSue = new FlightBooking(flightToWarsaw, 2,
                PlaneBaggage.CHECKED, true);
        myTrip.getParticipant(sue).addTransportBooking(flightBookingMWSue);

//        * coach travel to Malaga booking
        Transport coachTravelToMalaga = malagaEs.findTransport(City.WARSAW, City.MALAGA, TransportType.BUS);

        coachTravelToMalaga.findSeat(1).bookSeat();
        CoachTravelBooking coachBookingWMKate = new CoachTravelBooking(coachTravelToMalaga, 1,
                false, 3, 2);
        myTrip.getParticipant(kate).addTransportBooking(coachBookingWMKate);

        coachTravelToMalaga.findSeat(2).bookSeat();
        CoachTravelBooking coachBookingWMTom = new CoachTravelBooking(coachTravelToMalaga, 2,
                false, 1, 3);
        myTrip.getParticipant(tom).addTransportBooking(coachBookingWMTom);

//        * coach travel back to Warsaw booking
        Transport coachTravelToWarsaw = malagaEs.findTransport(City.MALAGA, City.WARSAW, TransportType.BUS);

        coachTravelToWarsaw.findSeat(1).bookSeat();
        CoachTravelBooking coachBookingMWKate = new CoachTravelBooking(coachTravelToWarsaw, 1,
                false, 3, 2);
        myTrip.getParticipant(kate).addTransportBooking(coachBookingMWKate);

        coachTravelToWarsaw.findSeat(2).bookSeat();
        CoachTravelBooking coachBookingMWTom = new CoachTravelBooking(coachTravelToWarsaw, 2,
                false, 1, 3);
        myTrip.getParticipant(tom).addTransportBooking(coachBookingMWTom);

//        add activities for participants
        myTrip.getParticipant(john).addActivity(malagaEs.findActivity("Football Match"));
        myTrip.getParticipant(sue).addActivity(malagaEs.findActivity("Banana Boat Ride"));
        myTrip.getParticipant(sue).addActivity(malagaEs.findActivity("Caminito Del Rey Tour"));
        myTrip.getParticipant(kate).addActivity(malagaEs.findActivity("Beach Volleyball"));
        myTrip.getParticipant(tom).addActivity(malagaEs.findActivity("Football Match"));

        myTrip.printSummary();

//        * checking equal method implementation
//        AtHotelActivity a1 = new AtHotelActivity();
//        AtHotelActivity a2 = (AtHotelActivity) malagaEs.findActivity("Football Match");
//        AtHotelActivity a3 = (AtHotelActivity) malagaEs.findActivity("Football Match");
//        AtHotelActivity a4 = (AtHotelActivity) malagaEs.findActivity("Beach Volleyball");
//        AtHotelActivity a5 = null;
//        AtHotelActivity a6 = null;
//        AtHotelActivity a7 = new AtHotelActivity();
//
//        System.out.println("Checking equal function for Activity objects");
//        System.out.println("1: " + a1.equals(a7));  // two empty objects -> true
//        System.out.println("2: " + a1.equals(a6));  // empty obj & null -> false
//        System.out.println("3: " + a2.equals(a2));  // the same obj -> true
//        System.out.println("4: " + a2.equals(a3));  // two different but equal obj -> true
//        System.out.println("5: " + a3.equals(a2));  // two different but equal obj ->true
//        System.out.println("6: " + a2.equals(a4));  // two different & unequal obj -> false
    }
}
