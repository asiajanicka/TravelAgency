package org.jjm.hotel.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class InvalidTimePeriodException extends Exception {
    private static final Logger logger = LogManager.getLogger(InvalidTimePeriodException.class);

    public InvalidTimePeriodException() {
    }

    public InvalidTimePeriodException(String message) {
        super(message);
        logger.error(message);
    }

    public InvalidTimePeriodException(LocalDate startDate, LocalDate endDate, InvalidTimePeriodType type) {
        super(String.format(type.getDescription(), endDate, startDate));
        logger.error(String.format(type.getDescription(), endDate, startDate), this.getStackTrace());
    }


    public InvalidTimePeriodException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message, cause);
    }

}
