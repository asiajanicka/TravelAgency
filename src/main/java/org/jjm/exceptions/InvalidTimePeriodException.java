package org.jjm.exceptions;

public class InvalidTimePeriodException extends Exception {
    public InvalidTimePeriodException() {
    }

    public InvalidTimePeriodException(String message) {
        super(message);
    }

    public InvalidTimePeriodException(Throwable cause) {
        super(cause);
    }

    public InvalidTimePeriodException(String message, Throwable cause) {
        super(message, cause);
    }

}
