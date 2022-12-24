package org.jjm.transport.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.enums.City;
import org.jjm.enums.TransportType;

public class NoTransportException extends Exception {
    private static final Logger logger = LogManager.getLogger(NoTransportException.class);

    public NoTransportException() {
    }

    public NoTransportException(String message) {
        super(message);
        logger.error(message, this);
    }

    public NoTransportException(City cityFrom, City cityTo, TransportType type) {
        super(String.format("There is no %s available from %s to %s", type.toString().toLowerCase(), cityFrom, cityTo));
        logger.error(String.format("Search transport by city and type failed. No %s from %s to %s is available.",
                type.toString().toLowerCase(), cityFrom, cityTo), this);
    }

    public NoTransportException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message, cause);
    }
}
