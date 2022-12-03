package exceptions;

public class InvalidTimePeriodException extends Exception {
    public InvalidTimePeriodException() {
    }

    public InvalidTimePeriodException(String message) {
        super(message);
    }
}
