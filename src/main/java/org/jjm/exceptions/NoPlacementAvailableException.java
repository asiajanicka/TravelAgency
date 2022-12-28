package org.jjm.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.destination.enums.City;
import org.jjm.enums.PlacementType;

public class NoPlacementAvailableException extends Exception {
    private static final Logger logger = LogManager.getLogger(NoPlacementAvailableException.class);

    public NoPlacementAvailableException() {
    }

    public NoPlacementAvailableException(String message) {
        super(message);
        logger.error(message);
    }

    public NoPlacementAvailableException(PlacementType type) {
        super(String.format("There is no free %s. No transport booking can be done.",
                        type.name().toLowerCase()));
        logger.error(String.format("There is no free %s. All %ss are booked.", type.name().toLowerCase(),
                type.name().toLowerCase()), this);
    }

    public NoPlacementAvailableException(City cityFrom, City cityTo) {
        super("There is no free seat. No transport booking can be done.");
        logger.error(String.format("There is no free seat. All seats are booked on way from %s to %s.", cityFrom, cityTo),
                this);
    }
}
