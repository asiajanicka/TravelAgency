package org.jjm.exceptions;

public class NoActivityException extends Exception {
    public NoActivityException() {
    }

    public NoActivityException(String message) {
        super(message);
    }

    public NoActivityException(String message, Throwable cause) {
        super(message, cause);
    }
}
