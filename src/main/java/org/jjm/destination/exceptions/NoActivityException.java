package org.jjm.destination.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.destination.Destination;

public class NoActivityException extends Exception {
    private static final Logger logger = LogManager.getLogger(NoActivityException.class);

    public NoActivityException() {
        super("There is no such activity available at hotel/city");
        logger.error("There is no such activity available at hotel/city. Activity can't be added to the list of " +
                "participant", this);
    }

    public NoActivityException(String name) {
        super(String.format("There is no activity '%s' available at hotel/city", name));
        logger.error(String.format("There is no activity '%s' available at hotel/city. " +
                "Activity can't be added to the list of participant", name), this);
    }

    public NoActivityException(String name, Destination destination) {
        super(String.format("There is no activity '%s' available at hotel/city.", name));
        logger.error(String.format("Activity '%s' is not available at %s. Activity can't be added to the list of participant",
                name, destination), this);
    }

    public NoActivityException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message, cause);
    }
}
