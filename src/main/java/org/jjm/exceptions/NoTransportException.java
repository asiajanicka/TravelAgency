package org.jjm.exceptions;

public class NoTransportException extends Exception {
    public NoTransportException() {
    }

    public NoTransportException(String message) {
        super(message);
    }

    public NoTransportException(String message, Throwable cause) {
        super(message, cause);
    }

}
