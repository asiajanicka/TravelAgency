package exceptions;

public class NoDateException extends Exception {
    public NoDateException() {
    }

    public NoDateException(String message) {
        super(message);
    }
}
