package org.jjm.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.enums.City;
import org.jjm.enums.PlacementType;
import org.jjm.enums.TransportType;

public class PlacementAlreadyBooked extends Exception {

    private static final Logger logger = LogManager.getLogger(PlacementAlreadyBooked.class);

    public PlacementAlreadyBooked() {
    }

    public PlacementAlreadyBooked(String message) {
        super(message);
        logger.error(message);
    }

    public PlacementAlreadyBooked(PlacementType type, int number) {
        super(String.format("%s %d is already booked. Sorry.", StringUtils.capitalize(type.name().toLowerCase()), number));
        logger.error(String.format("%s %d is already booked. Sorry.", StringUtils.capitalize(type.name().toLowerCase()),
                number), this);
    }

    public PlacementAlreadyBooked(TransportType type, int number, City cityFrom, City cityTo) {
        super(String.format("Seat %d is already booked on the %s from %s to %s. Sorry.",
                number, type.name().toLowerCase(), cityFrom, cityTo));
        logger.error(String.format("Seat %d is already booked on the %s from %s to %s. Sorry.",
                number, type.name().toLowerCase(), cityFrom, cityTo), this);
    }
}
