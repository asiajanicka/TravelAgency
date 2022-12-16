package org.jjm.utils;

import org.apache.commons.io.FileUtils;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.transport.Seat;

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
}
