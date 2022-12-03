package exceptions;

public class NoPlacementAvailableException extends Exception {
    public NoPlacementAvailableException() {
    }

    public NoPlacementAvailableException(String message) {
        super(message);
    }
}
