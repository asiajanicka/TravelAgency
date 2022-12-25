package org.jjm.utils;

import org.apache.commons.io.FileUtils;
import org.jjm.bookings.HotelBooking;
import org.jjm.bookings.TransportBooking;
import org.jjm.destination.Destination;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.transport.Seat;
import org.jjm.trip.CustomizedTrip;
import org.jjm.trip.Person;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static <T> Seat<T> findSeatByType(List<Seat<T>> seats, T seatType) throws NoPlacementAvailableException {
        List<Seat<T>> seatsOfGivenType = seats.stream()
                .filter(p -> !p.isBooked() && p.getSeatType().equals(seatType))
                .collect(Collectors.toList());
        if (seatsOfGivenType.size() == 0) {
            throw new NoPlacementAvailableException(
                    String.format("There is no free seat of type %s. All seats are booked.", seatType));
        } else
            return seatsOfGivenType.get(0);
    }

    public static List<String> readDataFromFile(String fileName) throws IOException {
            return FileUtils.readLines(new File(fileName), "UTF-8");
    }

    public static void writeSessionStatisticsToFile(String fileName) throws IOException {
        String text = String.format("Statistics about number of created objects:\n" +
                "Person: %d\nAvailable destinations: %d\nCustomized trips: %d\n" +
                "Transport bookings: %d\nHotel bookings: %d",
                Person.getCounter(), Destination.getCounter(), CustomizedTrip.getCounter(),
                TransportBooking.getCounter(), HotelBooking.getCounter());
        FileUtils.write(new File(fileName), text, "UTF-8", false);
    }
}
