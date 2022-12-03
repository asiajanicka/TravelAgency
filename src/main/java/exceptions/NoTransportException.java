package exceptions;

public class NoTransportException extends Exception {
    public NoTransportException() {
    }

    public NoTransportException(String message) {
        super(message);
    }
}
