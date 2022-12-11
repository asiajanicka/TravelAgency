package org.jjm.exceptions;

public class NoPlacementException extends Exception {
    public NoPlacementException() {
    }

    public NoPlacementException(String message) {
        super(message);
    }

    public NoPlacementException(String message, Throwable cause) {
        super(message, cause);
    }

}
