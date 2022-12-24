package org.jjm.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.enums.PlacementType;

public class NoPlacementException extends Exception {
    private static final Logger logger = LogManager.getLogger(NoPlacementException.class);

    public NoPlacementException() {
    }

    public NoPlacementException(PlacementType type, int number) {
        super(String.format("%s with number %d doesn't exist.", StringUtils.capitalize(type.name().toLowerCase()),
                number));
        logger.error(String.format("Search %s by number failed. No booking could be done", type.name().toLowerCase()),
                this);
    }

    public NoPlacementException(String message) {
        super(message);
        logger.error(message);
    }

    public NoPlacementException(String message, Throwable cause) {
        super(message, cause);
    }
}
